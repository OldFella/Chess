package Spielfiguren;

import main.Gameboard;

public class Bauer implements Spielfigur{
	
	private String wichsite;
	
	private int[][] validmoves;
	
	public Bauer(String site){
		wichsite = site;
	}

	@Override
	public int[][] movement(Gameboard gb) {
		// TODO Auto-generated method stub
		return validmoves;
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
	
	public void setmoves(int[][] vm){
		validmoves = vm;
	}

}
