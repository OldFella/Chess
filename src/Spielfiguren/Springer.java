package Spielfiguren;



public class Springer implements Spielfigur{

	
private String wichsite;

private boolean moved;

private int[][] validmoves;
	
	public Springer(String site){
		
		wichsite = site;
		moved = false;
	}
	
	@Override
	public int[][] movement() {
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
	@Override
	public void setmoved() {
		// TODO Auto-generated method stub
		moved = true;
	}
	@Override
	public boolean getmoved() {
		// TODO Auto-generated method stub
		return moved;
	}

}
