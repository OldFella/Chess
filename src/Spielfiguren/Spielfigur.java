package Spielfiguren;

import main.Gameboard;

public interface Spielfigur {
	
	public void movement(Gameboard gb);
	
	public String getName();
	
	public String getSite();
		
	
}
