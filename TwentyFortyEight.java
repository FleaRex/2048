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


	public void play(){
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
				return;
				//System.out.println("i=" +i);
			}
		}
		//System.out.println(i);
	}

	public static void main(String[] args){
		int n = 1000000;
		int maximum = 0;
		int successCounter2048=0;
		int successCounter1024=0;
		int successCounter512=0;
		int maxScore = 0;
		for(int counter = 0; counter < n; counter++){
			TwentyFortyEight tester = new TwentyFortyEight();
			tester.play();
			int max = tester.currentGame.max();
			maximum += max;
			System.out.println(max);
			if(max >= 2048){
				successCounter2048++;
				tester.currentGame.print();
			}
			if(max >= 1024){
				successCounter1024++;
			}
			if(max >= 512){
				successCounter512++;
			}
			//if(counter % 10000 == 0){
			//	System.out.println(counter);
			//}
			if(max > maxScore){
				maxScore = max;
			}
		}
		double averageMax 	= (double) maximum / n;
		double successP2048 = (double) successCounter2048 / n;
		double successP1024 = (double) successCounter1024 / n;
		double successP512 	= (double) successCounter512 / n;
		System.out.println("The average max is " + averageMax);
		System.out.println("There were " + successCounter2048 + " over 2048s");
		System.out.println("There was " + successP2048 + " chance of over 2048");
		System.out.println("There were " + successCounter1024 + " over 1024s");
		System.out.println("There was " + successP1024 + " chance of over 1024");
		System.out.println("There were " + successCounter512 + " over 512s");
		System.out.println("There was " + successP512 + " chance of over 512");
	}






}
