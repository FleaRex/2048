/* The gameboard for a game of 2048
	i00 i10 i20 i30
	i01 i11 i21 i31
	i02 i12 i22 i32
	i03 i13 i23 i33
*/

public class Gameboard{
	private int[][] board;
	
	public Gameboard(){
		board = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				board[i][j] = 0;
			}
		}
	}
	
	public Gameboard(Gameboard A){
		board = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				board[i][j] = A.getIJ(i,j);
			}
		}
	}
	
	public void addRandom(){
		int pos = 0;
		double x = Math.floor(this.empties()*Math.random());
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(board[i][j]==0){
					if(x == pos){
						double p = Math.random();
						if(p < 0.9){
							board[i][j] = 2;
						}
						else{
							board[i][j] = 4;
						}
					}
					pos++;
				}
			}
		}
	}
	
	public int getIJ(int i, int j){
		return this.board[i][j];
	}
	
	public void setIJ(int i, int j, int val){
		this.board[i][j] = val;
	}
	
	public String toString(){
		MaInput Ma = new MaInput();
		
		String matrix = "";
		for (int j = 0; j < 4; j++){
			String row = "";
			for (int i = 0 ; i < 4; i++){
				int aij = this.getIJ(i,j);
				String x = MaF.iF(aij,5);
				row += x;
			}
			matrix+=row;
			matrix+="\n";
		}
		return matrix;
	}
	
	public void down(){
		if(this.playableDown()){
			this.downMove();
			for(int j = 2;j >= 0; j-=1){
				for(int i = 0; i < 4; i++){
					if(board[i][j] == board[i][j+1]){
						board[i][j+1] *= 2;
						board[i][j] = 0;
					}
				}
			}
			this.downMove();
			this.addRandom();
		}
	}
	
	public void up(){
		if(this.playableUp()){
			this.upMove();
			for(int j = 1; j < 4; j++){
				for(int i = 0; i < 4; i++){
					if(board[i][j] == board[i][j-1]){
						board[i][j-1] *= 2;
						board[i][j] = 0;
					}
				}
			}
			this.upMove();
			this.addRandom();
		}
	}
	
	public void left(){
		if(this.playableLeft()){
			this.leftMove();
			for(int i = 1; i < 4; i++){
				for(int j = 0; j < 4; j++){
					if(board[i][j] == board[i-1][j]){
						board[i-1][j] *= 2;
						board[i][j] = 0;
					}
				}
			}
			this.leftMove();
			this.addRandom();
		}
	}
	
	public void right(){
		if(this.playableRight()){
			this.rightMove();
			for(int i = 2;i >= 0; i-=1){
				for(int j = 0; j < 4; j++){
					if(board[i][j] == board[i+1][j]){
						board[i+1][j] *= 2;
						board[i][j] = 0;
					}
				}
			}
			this.rightMove();
			this.addRandom();
		}
	}
	
	public void downMove(){
		boolean doneMove=false;
		for(int j = 2;j >= 0; j-=1){
			for(int i = 0; i < 4; i++){
				if (board[i][j] != 0){
					if (board[i][j+1] == 0){
						board[i][j+1] = board[i][j];
						board[i][j] = 0;
						doneMove=true;
					}
				}
			}
		}
		if(doneMove==true){
			this.downMove();
		}
	}
	
	public void upMove(){
		boolean doneMove=false;
		for(int j = 1; j < 4; j++){
			for(int i = 0; i < 4; i++){
				if (board[i][j] != 0){
					if (board[i][j-1] == 0){
						board[i][j-1] = board[i][j];
						board[i][j] = 0;
						doneMove=true;
					}
				}
			}
		}
		if(doneMove==true){
			this.upMove();
		}
	}
	
	public void leftMove(){
		boolean doneMove=false;
		for(int i = 1; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if (board[i][j] != 0){
					if (board[i-1][j] == 0){
						board[i-1][j] = board[i][j];
						board[i][j] = 0;
						doneMove=true;
					}
				}
			}
		}
		if(doneMove==true){
			this.leftMove();
		}
	}
	
	public void rightMove(){
		boolean doneMove=false;
		for(int i = 2; i >= 0; i-=1){
			for(int j = 0; j < 4; j++){
				if (board[i][j] != 0){
					if (board[i+1][j] == 0){
						board[i+1][j] = board[i][j];
						board[i][j] = 0;
						doneMove=true;
					}
				}
			}
		}
		if(doneMove==true){
			this.rightMove();
		}
	}
	
	public int empties(){
		int empty = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
				if(board[i][j] == 0){
					empty++;
				}
			}
		}
		return empty;
	}
	
	public boolean equals(Gameboard A){
		boolean truth = true;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
				if(this.board[i][j] != A.board[i][j]){
					truth = false;
				}
			}
		}
		return truth;
	}
	
	public int max(){
		int maximum = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
				if(board[i][j] > maximum){
					maximum = board[i][j];
				}
			}
		}
		return maximum;
	}
	
	public void downOnly(){
		this.downMove();
		for(int j = 2;j >= 0; j-=1){
			for(int i = 0; i < 4; i++){
				if(board[i][j] == board[i][j+1]){
					board[i][j+1] *= 2;
					board[i][j] = 0;
				}
			}
		}
		this.downMove();
	}
	
	public void upOnly(){
		this.upMove();
		for(int j = 1; j < 4; j++){
			for(int i = 0; i < 4; i++){
				if(board[i][j] == board[i][j-1]){
					board[i][j-1] *= 2;
					board[i][j] = 0;
				}
			}
		}
		this.upMove();
	}
	
	public void leftOnly(){
		this.leftMove();
		for(int i = 1; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(board[i][j] == board[i-1][j]){
					board[i-1][j] *= 2;
					board[i][j] = 0;
				}
			}
		}
		this.leftMove();
	}
	
	public void rightOnly(){
		this.rightMove();
		for(int i = 2;i >= 0; i-=1){
			for(int j = 0; j < 4; j++){
				if(board[i][j] == board[i+1][j]){
					board[i+1][j] *= 2;
					board[i][j] = 0;
				}
			}
		}
		this.rightMove();
	}
	
	public boolean playableLeft(){
		boolean truth = true;
		Gameboard copy1 = this.copy();
		copy1.leftOnly();
		
		if(copy1.equals(this)){
			truth = false;
		}
		return truth;
	}
	
	public boolean playableRight(){
		boolean truth = true;
		Gameboard copy1 = this.copy();
		copy1.rightOnly();
		
		if(copy1.equals(this)){
			truth = false;
		}
		return truth;
	}
	
	public boolean playableUp(){
		boolean truth = true;
		Gameboard copy1 = this.copy();
		copy1.upOnly();
		
		if(copy1.equals(this)){
			truth = false;
		}
		return truth;
	}
	
	public boolean playableDown(){
		boolean truth = true;
		Gameboard copy1 = this.copy();
		copy1.downOnly();
		
		if(copy1.equals(this)){
			truth = false;
		}
		return truth;
	}
	
	public void print(){
		System.out.println(this.toString());
	}
	
	public Gameboard copy(){
		Gameboard X = new Gameboard(this);
		return X;
	}
	
	public static void main(String[] args){
		Gameboard tester = new Gameboard();
		System.out.println("Normal");
		tester.print();
		System.out.println("Add random");
		tester.addRandom();
		tester.print();
		System.out.println("Add random");
		tester.addRandom();
		tester.print();
		System.out.println("Add random");
		tester.addRandom();
		tester.print();
		System.out.println("Add random");
		tester.addRandom();
		tester.print();
		System.out.println("Left");
		tester.left();
		tester.print();
		System.out.println("Right");
		tester.right();
		tester.print();
		System.out.println("Up");
		tester.up();
		tester.print();
		System.out.println("Down");
		tester.down();
		tester.print();
	}
	
	
	
	
	
}