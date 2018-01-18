package Spielfiguren;

import main.Gameboard;

public class Königin implements Spielfigur{

	
private String wichsite;
	
	public Königin(String site){
		
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
		return "D";
	}


	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return wichsite;
	}

}
