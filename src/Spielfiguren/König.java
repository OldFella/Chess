package Spielfiguren;



public class König implements Spielfigur{

	private String wichsite;
	
	private int[][] validmoves;
	
	public König(String site){
		
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
		return "K";
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
