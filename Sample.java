import java.util.Random;
import game.TwentyFortyEight;

public class Sample implements Comparable<Sample>{
  private int score;
  private int[] instructions;

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
      if(rand.nextFloat() > 0.95){
        this.instructions[i] = rand.nextInt(4);
      }
      else{
        this.instructions[i] = parent.instructions[i];
      }
    }
  }

  public void play(TwentyFortyEight t48) throws Exception{
		int i = 0;
		while(t48.playable() && i < (10000/this.instructions.length) + 1){
			i++;
			for(int instructionCount = 0; instructionCount < this.instructions.length; instructionCount++){
				int instruction = this.instructions[instructionCount];
				switch(instruction){
					case 0:
						t48.pressUp();
						break;
					case 1:
						t48.pressRight();
						break;
					case 2:
						t48.pressDown();
						break;
					case 3:
						t48.pressLeft();
						break;
					default:
						throw new Exception("Unsuitable instruction");
				}
			}
		}
    this.score = t48.score();
  }

  public int fitness(){
    return this.score;
  }

  public int compareTo(Sample other){
    return other.fitness() - this.fitness();
  }

  public String description(){
    return this.instructions.toString();
  }
}
