import java.util.Arrays;
import game.TwentyFortyEight;

class Evolution{

  private int instructionLength;
  private Sample[] samples;
  private int generation;

  public Evolution(int instructionLength, int sampleSize){
    this.generation = 0;
    if(sampleSize % 2 == 1){
      sampleSize++;
    }
    this.instructionLength = instructionLength;
    this.samples = new Sample[sampleSize];
    for(int i = 0; i < this.samples.length; i++){
      this.samples[i] = new Sample(instructionLength);
    }
  }

  public int playGeneration(){
    this.generation++;
    // Maybe replace with some averaging.
    for(Sample sample : samples){
      try{
        sample.play(new TwentyFortyEight());
      }
      catch (Exception e){
        System.err.println(e.getMessage());
        System.exit(1);
      }
    }
    int successes = this.report();
    this.findNewSamples();
    return successes;
  }

  public void findNewSamples(){
    Arrays.sort(samples);
    for(int i = 0; i < samples.length/2; i++){
      samples[i + samples.length/2] = new Sample(samples[i]);
    }
  }

  public int report(){
    int successes = 0;
    int highScore = 0;
    for(Sample sample : samples){
      if(sample.fitness() > highScore){
        highScore = sample.fitness();
      }
      if(sample.fitness() >= 2048){
        successes++;
        System.out.println(sample.description());
      }
    }

    System.out.println("Generation:" + this.generation);
    System.out.println("-------------------------");
    System.out.println("Successes:" + successes);
    System.out.println("High Score:" + highScore);
    System.out.println("\n");
    return successes;
  }

  public static void main(String[] args){
    int totalGenerations = 0;
    int instructionLength = 10;
    int sampleSize = 10;
    int successes = 0;
    if (args.length > 0) {
        try {
            totalGenerations = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[0] + " must be an integer.");
            System.exit(1);
        }
        try {
            instructionLength = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[1] + " must be an integer.");
            System.exit(1);
        }
        try {
            sampleSize = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[2] + " must be an integer.");
            System.exit(1);
        }
    }
    Evolution ev = new Evolution(instructionLength, sampleSize);
    if(totalGenerations <= 0){
        while(successes == 0){
          successes = ev.playGeneration();
        }
    }
    else{
      for(int i = 0; i < totalGenerations; i++){
        successes = ev.playGeneration();
      }
    }

  }
}
