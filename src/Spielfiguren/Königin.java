package Spielfiguren;


public class Königin implements Spielfigur{

	
private String wichsite;

private boolean moved;

private int[][] validmoves;
	
	public Königin(String site){
		moved = false;
		wichsite = site;
	}
	@Override
	public int[][] movement() {
		// TODO Auto-generated method stub
		return validmoves;
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
