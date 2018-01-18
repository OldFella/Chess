package Spielfiguren;

import main.Gameboard;

public interface Spielfigur {
	
	public int[][] movement(Gameboard gb);
	
	public String getName();
	
	public String getSite();
	
	public void setmoves(int[][] vm);
		
	
}
