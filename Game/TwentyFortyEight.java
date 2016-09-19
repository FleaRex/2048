/** A system trying to replicate the game 2048 in order to test tactics
	Author: B.Madley
	Date: 20/03/14
**/
package game;
public class TwentyFortyEight{
	private Gameboard currentGame;

	public TwentyFortyEight(){
		currentGame = new Gameboard();
		currentGame.addRandom();
		currentGame.addRandom();
	}

	public boolean playable(){
		boolean truth = true;
		Gameboard copy1 = currentGame.copy();
		Gameboard copy2 = currentGame.copy();
		Gameboard copy3 = currentGame.copy();
		Gameboard copy4 = currentGame.copy();
		copy1.leftOnly();
		copy2.rightOnly();
		copy3.upOnly();
		copy4.downOnly();

		if(copy1.empties()+copy2.empties()+copy3.empties()+copy4.empties()==0){
			truth = false;
		}
		return truth;
	}

	public void pressDown(){
		if(currentGame.playableDown()){
			currentGame.down();
		}
	}

	public void pressUp(){
		if(currentGame.playableUp()){
			currentGame.up();
		}
	}

	public void pressLeft(){
		if(currentGame.playableLeft()){
			currentGame.left();
		}
	}

	public void pressRight(){
		if(currentGame.playableRight()){
			currentGame.right();
		}
	}

	public int play(){
		int i = 0;
		currentGame.up();
		currentGame.right();
		while(this.playable()){
			// currentGame.print();
			i++;
			if(currentGame.playableUp()){
				currentGame.up();
			}
			else if(currentGame.playableRight()){
				currentGame.right();
			}
			else if(currentGame.playableDown()){
				currentGame.down();
			}
			else if(currentGame.playableLeft()){
				currentGame.left();
			}
			else if(i > 10000){
				currentGame.print();
				break;
				//System.out.println("i=" +i);
			}
		}
		return currentGame.max();
		//System.out.println(i);
	}

	// DEPRECATED: Use sample.play
	public int play(int[] instructions) throws Exception{
		int i = 0;
		while(this.playable() && i < (10000/instructions.length) + 1){
			i++;
			for(int instructionCount = 0; instructionCount < instructions.length; instructionCount++){
				int instruction = instructions[instructionCount];
				switch(instruction){
					case 0:
						if(currentGame.playableUp()){
							currentGame.up();
						}
						break;
					case 1:
						if(currentGame.playableRight()){
							currentGame.right();
						}
						break;
					case 2:
						if(currentGame.playableDown()){
							currentGame.down();
						}
						break;
					case 3:
						if(currentGame.playableLeft()){
							currentGame.left();
						}
						break;
					default:
						throw new Exception("Unsuitable instruction");
				}

			}
		}
		return currentGame.max();
	}

	public int score(){
		return currentGame.max();
	}

	public static void main(String[] args){
		int[] instructionSet = {0,1,2,3};
		try{
			System.out.println((new TwentyFortyEight()).play(instructionSet));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
