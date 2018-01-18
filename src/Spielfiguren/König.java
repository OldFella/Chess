package Spielfiguren;

import main.Gameboard;

public class König implements Spielfigur{

	private String wichsite;
	
	public König(String site){
		
		wichsite = site;
	}
	@Override
	public void movement(Gameboard gb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "K";
	}


	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return wichsite;
	}

}
