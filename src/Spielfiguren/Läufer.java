package Spielfiguren;

import main.Gameboard;

public class Läufer implements Spielfigur{

	
private String wichsite;

private int[][] validmoves;
	
	public Läufer(String site){
		
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
		return "L";
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
