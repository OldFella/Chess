package main;

public class Minimax {
	
	private Gameboard board;
	
	private int depth;
	
	private int score;
	
	public Minimax(Gameboard b){
		
		board = new Gameboard();
		/*
		board.setBoard(b.getBoard().clone());
		board.nextStep(0, 1, 2, 2);
		board.setPlayer1(b.getPlayer());
		board.setValidTurn(b.getValidTurn());
		System.out.println(board.getBoard()[0][1]);
		*/
		depth = 5;
	}
	
	public Gameboard getBoard() {
		return board;
	}

	public void setBoard(Gameboard board) {
		this.board = board;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private int mini(){
		if(depth == 0)
			return score;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard().length; j++) {
				
			}
			
		}
		
		return min;
	}
	

}
