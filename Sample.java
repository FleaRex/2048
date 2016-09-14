import java.util.Random;

public class Sample implements Comparable<Sample>{
  public int score;
  public int[] instructions;

  public Sample(int instructionLength){
    Random rand = new Random();
    this.score = 0;
    this.instructions = new int[instructionLength];
    for(int i = 0; i < this.instructions.length; i++){
      this.instructions[i] = rand.nextInt(4);
    }
  }

  public Sample(Sample parent){
    Random rand = new Random();
    this.score = 0;
    this.instructions = new int[parent.instructions.length];
    for(int i = 0; i < this.instructions.length; i++){
      if(rand.nextFloat() > 0.99){
        this.instructions[i] = rand.nextInt(4);
      }
      else{
        this.instructions[i] = parent.instructions[i];
      }
    }
  }

  public int compareTo(Sample other){
    return other.score - this.score;
  }
}
