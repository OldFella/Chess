package Spielfiguren;

import main.Gameboard;

public class Turm implements Spielfigur{

	
private String wichsite;
	
	public Turm(String site){
		
		wichsite = site;
	}
	
	@Override
	public int[][] movement(Gameboard gb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "T";
	}

	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return wichsite;
	}

}
