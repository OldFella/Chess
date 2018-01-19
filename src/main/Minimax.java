package main;

public class Minimax {
	
	private Gameboard board;
	
	private int depth;
	
	private int score;
	
	public Minimax(Gameboard b) throws Exception{
		
		
		depth = 5;
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
