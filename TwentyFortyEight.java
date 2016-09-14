/** A system trying to replicate the game 2048 in order to test tactics
	Author: B.Madley
	Date: 20/03/14
**/

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

	public static void main(String[] args){
		
	}
}
