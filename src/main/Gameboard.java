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
			evaluatevalidmoves(1, i);
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
			evaluatevalidmoves(tmp, 0);
			evaluatevalidmoves(tmp, 1);
			evaluatevalidmoves(tmp, 2);
			evaluatevalidmoves(tmp, 3);
			evaluatevalidmoves(tmp, 4);
			evaluatevalidmoves(tmp, 5);
			evaluatevalidmoves(tmp, 6);
			evaluatevalidmoves(tmp, 7);
		}
		for (int i = 0; i < GBWIDTH; i++) {
			board[6][i] = new Bauer("w"); //inits White Bauern
			evaluatevalidmoves(6, i);
		}

	}

	public static void evaluatevalidmoves(int x, int y){
		Spielfigur sf = board[x][y];
		int[] tmp = new int[2];
		int[][] moves = new int[30][2];
		int cnt = 0;
		switch(sf.getName()){

		case "B":
			if(sf.getSite() == "w"){
				for (int i = -1; i < 2; i++) {
					tmp[0] = i+x;
					tmp[1] = y+1;
					if(tmp[0] > 7 ||tmp[0] < 0 || tmp[1] <0);
					else{
						if(validmove(sf, x,y,tmp[0],tmp[1])){
							moves[cnt] = tmp;
							sf.setmoves(moves);
							cnt++;
						}
					}
				}
			}
			else{
				for (int i = -1; i < 2; i++) {
					tmp[0] = i+1;
					tmp[1] = y-1;
					if(tmp[0] > 7 ||tmp[0] < 0 || tmp[1] > 7);
					else{
						if(validmove(sf, x,y,tmp[0],tmp[1])){
							moves[cnt] = tmp;
							sf.setmoves(moves);
							cnt++;
						}
					}
				}
			}
			break;

		case "D":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sf, x,y,i,j)){
						tmp[0] = i;
						tmp[1] = j;
						moves[cnt] = tmp;
						sf.setmoves(moves);
						cnt++;
					}
				}
			}


			break;

		case "K":
			for (int i = -1; i < 2; i++) {
				for(int j = -1; i < 2; i++){
					tmp[0] = i+x;
					tmp[1] = j+y;
					if(tmp[0] > 7 ||tmp[0] < 0 || tmp[1] > 7 || tmp[1] < 0);
					else{
						if(validmove(sf, x,y,tmp[0],tmp[1])){
							moves[cnt] = tmp;
							sf.setmoves(moves);
							cnt++;
						}
					}
				}
			}
			break;

		case "T":
			for (int i = -7; i < 8; i++) {
				tmp[0] = x+i;
				tmp[1] = y+i;
				if(tmp[0] > 7 ||tmp[0] < 0 || tmp[1] > 7 || tmp[1] < 0);
				else{
					if(validmove(sf, x,y,tmp[0],tmp[1])){
						moves[cnt] = tmp;
						sf.setmoves(moves);
						cnt++;
					}
					if(validmove(sf, x,y,tmp[1],tmp[0])){
						moves[cnt] = tmp;
						sf.setmoves(moves);
						cnt++;
					}
				}

			}
			break;

		case "L":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sf, x,y,i,j)){
						tmp[0] = i;
						tmp[1] = j;
						moves[cnt] = tmp;
						sf.setmoves(moves);
						cnt++;
					}
				}
			}

			break;

		case "S":
			int tmp1 = -1;
			int tmp2 = -2;
			for(int i = 0; i < 4;i++){

				if(validmove(sf, x,y,tmp1,tmp2)){
					tmp[0] = tmp1;
					tmp[1] = tmp2;
					moves[cnt] = tmp;
					sf.setmoves(moves);
					cnt++;
				}
				if(i % 2 == 0)
					tmp2 = tmp2*(-1);
				tmp1 = tmp1*(-1);
			}
			for(int i = 0; i < 4;i++){

				if(validmove(sf, x,y,tmp2,tmp1)){
					tmp[0] = tmp2;
					tmp[1] = tmp1;
					moves[cnt] = tmp;
					sf.setmoves(moves);
					cnt++;
				}
				if(i % 2 == 0)
					tmp2 = tmp2*(-1);
				tmp1 = tmp1*(-1);
			}
			break;
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
