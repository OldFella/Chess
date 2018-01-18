package main;


import Spielfiguren.*;

public class Gameboard {

	private static final int GBWIDTH = 8;
	private static final int GBHEIGHT = 8;

	private static Spielfigur[][] board;

	private static boolean Player1 = true;

	private static boolean validTurn = false;

	public Gameboard(){
		initGameboard();

	}

	public void initGameboard(){
		board = new Spielfigur[GBWIDTH][GBHEIGHT];
		Player1 = true;
		for (int i = 0; i < GBWIDTH; i++) {
			board[1][i] = new Bauer("b");  //inits Black Bauern
		}
		for(int i = 0; i < 2; i++){
			int tmp = 0;
			String stmp = "";
			if(i == 0){
				stmp = "b";
			}
			else{
				tmp = 7;
				stmp = "w";
			}
			board[tmp][0] = new Turm(stmp);
			board[tmp][1] = new Springer(stmp);
			board[tmp][2] = new Läufer(stmp);
			board[tmp][4] = new König(stmp);
			board[tmp][3] = new Königin(stmp);
			board[tmp][5] = new Läufer(stmp);
			board[tmp][6] = new Springer(stmp);
			board[tmp][7] = new Turm(stmp);
		}
		for (int i = 0; i < GBWIDTH; i++) {
			board[6][i] = new Bauer("w"); //inits White Bauern
		}

	}

	public static void printgameboard(){
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null){
					s += "   ";
				}
				else{
					s += board[i][j].getSite() + board[i][j].getName()+ " ";
				}
			}
			s += "\n";

		}
		System.out.println(s);
	}

	public void resetBoard(){
		initGameboard();
		Player1 = true;
	}
	// takes a Spielfigur and their x-coordinate(j), y -coordinate(i) and the x(l)- and y(k)-coordinate from its destination point
	private static boolean validmove(Spielfigur sf, int i, int j, int k, int l){
		if((Player1 && board[i][j].getSite() == "w")||(!Player1 && board[i][j].getSite() == "b"))
			return false;
		if(k > 7 || k < 0 || l > 7 || l< 0 || (i == k && j == l))
			return false;
		switch(sf.getName()){
		case "B":
			if(sf.getSite()== "w"){
				if(j == l){
					if(board[k][l] == null && (k == (i-1)||(k == (i-2) && i == 6&& board[i-1][l] == null)))
						return true;
				}
				if(board[k][l] != null && ((k == (i-1) && l == (j+1))||(k == (i-1) && l == (j-1))))
					return true;
			}
			else if(sf.getSite()== "b"){
				if(j == l){
					if(board[k][l] == null && (k == (i+1)||(k == (i+2) && i == 1 && board[i+1][l] == null)))
						return true;

				}
				if(board[k][l] != null && ((k == (i+1) && l == (j+1))||(k == (i+1) && l == (j-1))))
					return true;
			} 
			return false;
		case "L":
			return validL(sf,i,j,k,l);
		case "K":
			if(board[k][l] == null ||sf.getSite() != board[k][l].getSite()){
				if(Math.abs(k-i) == 1 || Math.abs(l-j) == 1)
					return true;
				else return false;
			}
			else
				return false;
		case "D":
			return (validT(sf,i,j,k,l) ? true : validL(sf,i,j,k,l));
		case "S":
			if(board[k][l] == null ||sf.getSite() != board[k][l].getSite()){
				if((Math.abs(k-i) == 1 && Math.abs(l-j) == 2)||Math.abs(k-i) == 2 && Math.abs(l-j) == 1)
					return true;
				else
					return false;
			}
			else return false;
		case "T":
			return validT(sf, i, j, k , l);
		}

		return false;
	}
	//checks if the Läufer did a valid move
	private static boolean validL(Spielfigur sf, int i, int j, int k, int l){
		if(board[k][l] == null ||sf.getSite() != board[k][l].getSite()){
			if((Math.abs(i-k) == Math.abs(j-l) && checkBetween(i,j,k,l))){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	//check of the Turm did a valid move
	private static boolean validT(Spielfigur sf, int i, int j, int k, int l){
		if(board[k][l] == null ||sf.getSite() != board[k][l].getSite()){
			if(((Math.abs(i-k) == 0 || Math.abs(j-l) == 0) && checkBetween(i,j,k,l))){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	// checks if between i/j and k/l are just free spaces
	private static boolean checkBetween(int i, int j, int k, int l){
		int z = i-k;
		int y = j-l;
		int x = 0;
		int w = 0;
		if(z == 0){
			while((y > 0 ? x > y : x < y)){
				if (board[i][j+x] != null)
					return false;
				if(y < 0)
					x--;
				else 
					x++;
			}
			return true;
		}
		x = 0;
		if(y == 0){
			while((z > 0 ? x > z : x < z)){
				if (board[i+x][j] != null)
					return false;
				if(z < 0)
					x--;
				else 
					x++;
			}
			return true;
		}

		while((y > 0 ? x > y : x < y)){
			while((z > 0 ? w > z : w < z)){
				if (board[i+x][j+w] != null)
					return false;
				if(y < 0)
					x--;
				else 
					x++;
				if(z < 0)
					w--;
				else
					w++;
			}
		}
		return true;
	}

	public static boolean getPlayer(){
		return Player1;
	}

	public Spielfigur[][] getBoard(){
		return board;
	}
	
	public boolean getValidTurn(){
		return validTurn;
	}

	// calcs the next step, inverts the current player
	public static void nextStep(int i, int j, int k, int l){
		if(board[i][j] == null)
			return;
		validTurn = validmove(board[i][j],i,j, k,l);

		if(validTurn){
			board[k][l] = board[i][j];
			board[i][j] = null;
			Player1 = !Player1;
		}
		else return;


	}
}
