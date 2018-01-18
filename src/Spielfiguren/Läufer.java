package Spielfiguren;

import main.Gameboard;

public class Läufer implements Spielfigur{

	
private String wichsite;
	
	public Läufer(String site){
		
		wichsite = site;
	}
	
	@Override
	public void movement(Gameboard gb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "L";
	}

	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return wichsite;
	}

}
