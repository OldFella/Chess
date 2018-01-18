package Spielfiguren;

import main.Gameboard;

public class Springer implements Spielfigur{

	
private String wichsite;
	
	public Springer(String site){
		
		wichsite = site;
	}
	
	@Override
	public void movement(Gameboard gb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "S";
	}

	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return wichsite;
	}

}
