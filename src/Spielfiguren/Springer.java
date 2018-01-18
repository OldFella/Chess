package Spielfiguren;

import main.Gameboard;

public class Springer implements Spielfigur{

	
private String wichsite;

private int[][] validmoves;
	
	public Springer(String site){
		
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
		return "S";
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
