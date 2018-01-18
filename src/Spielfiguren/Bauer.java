package Spielfiguren;

import main.Gameboard;

public class Bauer implements Spielfigur{
	
	private String wichsite;
	
	public Bauer(String site){
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
		return "B";
	}


	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return wichsite;
	}

}
