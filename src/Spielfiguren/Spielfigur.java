package Spielfiguren;

import main.Gameboard;

public interface Spielfigur {
	
	public int[][] movement();
	
	public String getName();
	
	public String getSite();
	
	public void setmoves(int[][] vm);
		
	
}
