package main;


import java.util.Arrays;

import Spielfiguren.*;

public class Gameboard {

	private static final int GBWIDTH = 8;
	private static final int GBHEIGHT = 8;

	private static Spielfigur[][] board;
	
	public static Spielfigur[][] copy;

	public static boolean isPlayer1() {
		return Player1;
	}

	public static void setPlayer1(boolean player1) {
		Player1 = player1;
	}

	public void setBoard(Spielfigur[][] board) {
		Gameboard.board = board;
	}

	public static void setValidTurn(boolean validTurn) {
		Gameboard.validTurn = validTurn;
	}


	private static boolean Player1 = true;

	private static boolean validTurn = false;

	public Gameboard(){
		initGameboard();
		//copy = copyarray(board);

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
		evaluateall(board);

	}

	public static int[][] getvalidmoves(Spielfigur sf){
		if(sf == null)
			return null;
		int[][] tmp = sf.movement();
		if(tmp[0] == null)
			System.out.println("no valid moves available");
		else{
			for (int i = 0; i < tmp.length; i++) {
				if(tmp[i] == null)
					return null;
				//System.out.println("(" + tmp[i][0] + "," + tmp[i][1] + ")");
			}
		}
		return tmp;
	}

	public static void evaluateall(Spielfigur[][] sfar){
	
		for (int i = 0; i < sfar.length; i++) {
			for (int j = 0; j < sfar.length; j++) {
				evaluatevalidmoves(sfar, i, j);
			}
		}

	}
	
	public static int[] getFigure(Spielfigur[][] sfar, String s, String t){
		int[] result;
		for (int i = 0; i < sfar.length; i++) {
			for (int j = 0; j < sfar.length; j++) {
				Spielfigur sf = sfar[i][j];
				if(sf == null);
				else if(sf.getName() == "K" && sf.getSite() == s && sf.getName() == t)
					return result = new int[]{i,j};
			}
		}
		return null;
	}

	public static int[][] getallsidemoves(Spielfigur[][] gb, String s){
		Spielfigur sf;
		int[][] result = new int[100][30];
		int counter = 0;
		for (int i = 0; i < gb.length; i++) {
			for (int j = 0; j < gb.length; j++) {
				sf = gb[i][j];
				if (sf == null);
				else if(sf.getSite() == s){
					for(int k = 0; sf.movement()[k][1] != -1;k++){
						int[] temp = new int[]{j,i,0,0};
						System.arraycopy(sf.movement()[k], 0, temp, 2, sf.movement()[k].length);
						//System.out.println(temp);
						result[counter] = temp;
						counter++;
						//System.out.println("x: "+sf.movement()[k][0] +" y: " +sf.movement()[k][1]);
					}
				}
			}

		}

		result[counter][0] = -1; 
		return result;
	}
	
	public static boolean calculatecheck(Spielfigur[][] b, int x, int y){
	//	if(b[x][y] == null || b[x][y].getName() != "K")
	//		return false;
	//	System.out.println(b[x][y].getSite()== "w" ? "b" : "w");
		int[][] allmoves = getallsidemoves(b, b[x][y].getSite() == "w" ? "b" : "w");
		for(int i = 0; allmoves[i][0] != -1; i++){
			//System.out.println("i,0: "+allmoves[i][0] + " i,1: "+allmoves[i][1]);
			if(allmoves[i][2] == y && allmoves[i][3] == x)
				return true;
		}
		
		return false;
	}

	public static boolean calculatecheckmate(Spielfigur[][] sfar, String s, int[] arr){
		int[][] allmoves1 = getallsidemoves(sfar, s);
		int[][] allmoves2 = getallsidemoves(sfar, s == "w" ? "b" : "w");
		
		Spielfigur[][] bo;
		if(allmoves1[0][0] == -1)
			return true;
		else if(!calculatecheck(sfar, getFigure(sfar, s, "K")[0], getFigure(sfar, s, "K")[1])){
			return false;
		}
		for(int i = 0; allmoves1[i][0] != -1;i++){
			System.out.println("a: "+ allmoves1[i][0] + " b: " +allmoves1[i][1] + " c: " +allmoves1[i][2] + " d: "+ allmoves1[i][3]);
			bo = copyarray(sfar);
			bo = nextStep(bo, allmoves1[i][1],allmoves1[i][0],allmoves1[i][3],allmoves1[i][2]);
			if(sfar.equals(bo))
				System.out.println("sfar equals bo");
			int[] tmp = getFigure(bo, s, "K");
			if(!calculatecheck(bo, tmp[0], tmp[1])){
				bo = null;
				return false;
			}
			bo = null;
			//bo = nextStep(bo, allmoves1[i][3],allmoves1[i][2],allmoves1[i][1],allmoves1[i][0]);
		}
		return true;
	}
	
	public static Spielfigur[][] copyarray(Spielfigur[][] sfar){
		final Spielfigur[][] result;
		result = Arrays.copyOf(sfar, sfar.length);
		return result;
	}



	public static void evaluatevalidmoves(Spielfigur[][] sfar, int x, int y){
		Spielfigur sf = sfar[x][y];
		int[] tmp = new int[2];
		int[][] moves = new int[30][2];
		int cnt = 0;

		if(sf == null)
			return;

		switch(sf.getName()){

		case "B":
			if(sf.getSite() == "w"){

				for (int i = -1; i < 2; i++) {
					for (int j = 1; j < 3; j++) {


						tmp[0] = i+y;
						tmp[1] = x-j;
						if(tmp[0] > 7 ||tmp[0] < 0 || tmp[1] <0 || tmp [1] > 7);
						else{
							//System.out.println(sf.getSite() + x + y + tmp[1] + tmp[0]);
							//System.out.println(validmove(sf, y,x,tmp[0],tmp[1]));

							//System.out.println("" + x + y);
							if(validmove(sfar, sf, x,y,tmp[1],tmp[0])){
								//System.out.println(tmp[0]);
								moves[cnt] = new int[]{y+i,x-j};
								sf.setmoves(moves);
								cnt++;
							}
						}
					}
				}
			}
			else{
				for (int i = -1; i < 2; i++) {
					for (int j = 1; j < 3; j++) {

						tmp[0] = i+y;
						tmp[1] = x+j;
						if(tmp[0] > 7 ||tmp[0] < 0 || tmp[1] <0 || tmp [1] > 7);
						else{
							//System.out.println(sf.getSite() + x + y + tmp[1] + tmp[0]);
							//System.out.println(validmove(board[x][y],x,y, tmp[1],tmp[0]));
							if(validmove(sfar, sf, x,y,tmp[1],tmp[0])){

								moves[cnt] = new int[]{y+i,x+j};
								sf.setmoves(moves);
								cnt++;
							}
						}
					}
				}
			}
			break;

		case "D":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sfar, sf, x,y,j,i)){
						//System.out.println("+1");
						moves[cnt] = new int[]{i,j};
						sf.setmoves(moves);
						cnt++;
					}
				}
			}


			break;

		case "K":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sfar, sf, x,y,i,j)){
						tmp[0] = i;
						tmp[1] = j;
						moves[cnt] = new int[]{j,i};
						sf.setmoves(moves);
						cnt++;
					}
				}
			}
			break;

		case "T":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sfar, sf, x,y,i,j)){
						tmp[0] = i;
						tmp[1] = j;
						moves[cnt] = new int[]{j,i};
						sf.setmoves(moves);
						cnt++;
					}
				}
			}
			break;

		case "L":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sfar, sf, x,y,i,j)){
						tmp[0] = i;
						tmp[1] = j;
						moves[cnt] = new int[]{j,i};
						sf.setmoves(moves);
						cnt++;
					}
				}
			}

			break;

		case "S":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if(validmove(sfar, sf, x,y,i,j)){
						tmp[0] = i;
						tmp[1] = j;
						moves[cnt] = new int[]{j,i};
						sf.setmoves(moves);
						cnt++;
					}
				}
			}
			break;
		}

		moves[cnt] = new int[]{-1,-1};
		sf.setmoves(moves);
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
	private static boolean validmove(Spielfigur[][] sfar, Spielfigur sf, int i, int j, int k, int l){

		if(k > 7 || k < 0 || l > 7 || l< 0 || (i == k && j == l))
			return false;
		switch(sf.getName()){
		case "B":
			if(sf.getSite()== "w"){
				if(j == l){
					if(sfar[k][l] == null && (k == (i-1)||(k == (i-2) && i == 6&& sfar[i-1][l] == null)))
						return true;
				}
				if(sfar[k][l] != null && sfar[k][l].getSite() != sfar[i][j].getSite() &&((k == (i-1) && l == (j+1))||(k == (i-1) && l == (j-1))))
					return true;
			}
			else if(sf.getSite()== "b"){
				if(j == l){
					if(sfar[k][l] == null && (k == (i+1)||(k == (i+2) && i == 1 && sfar[i+1][l] == null)))
						return true;

				}
				if(sfar[k][l] != null &&sfar[k][l].getSite() != sfar[i][j].getSite() && ((k == (i+1) && l == (j+1))||(k == (i+1) && l == (j-1))))
					return true;
			} 
			return false;
		case "L":
			return validL(sfar, sf,i,j,k,l);
		case "K":
			if(sfar[k][l] == null ||sf.getSite() != sfar[k][l].getSite()){
				if((Math.abs(k-i) == 1 && Math.abs(l-j) == 1) || (Math.abs(l-j) == 1 && Math.abs(k-i) == 0) || (Math.abs(k-i) == 1 && Math.abs(l-j) == 0))
					return true;
				else return false;
			}
			else
				return false;
		case "D":
			return (validT(sfar, sf,i,j,k,l) ? true : validL(sfar, sf,i,j,k,l));
		case "S":
			if(sfar[k][l] == null ||sf.getSite() != sfar[k][l].getSite()){
				if((Math.abs(k-i) == 1 && Math.abs(l-j) == 2)||Math.abs(k-i) == 2 && Math.abs(l-j) == 1)
					return true;
				else
					return false;
			}
			else return false;
		case "T":
			return validT(sfar, sf, i, j, k , l);
		}
		

		return false;//rochade(sf,i,j,k,l);
	}
	//checks if the Läufer did a valid move
	private static boolean validL(Spielfigur[][] sfar, Spielfigur sf, int i, int j, int k, int l){
		if(sfar[k][l] == null ||sf.getSite() != sfar[k][l].getSite()){
			if((Math.abs(i-k) == Math.abs(j-l) && checkBetween(sfar, i,j,k,l))){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	//check of the Turm did a valid move
	private static boolean validT(Spielfigur[][] sfar, Spielfigur sf, int i, int j, int k, int l){
		if(sfar[k][l] == null ||sf.getSite() != sfar[k][l].getSite()){
			if(((Math.abs(i-k) == 0 || Math.abs(j-l) == 0) && checkBetween(sfar, i,j,k,l))){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
/*	
	private boolean rochade(Spielfigur sf, int i, int j, int k, int l){
		if(board[k][l] == null ||sf.getSite() != board[k][l].getSite()){
			int x = getFigure(this, "w", "T")[0];
			int y = getFigure(this, "w", "T")[1];
			if (sf.getSite() == "w" && ((board[7][0].getName() == "T" && board[7][0].getmoved()) ||((board[7][7].getName() == "T" && board[7][7].getmoved())))){
				if(board[7][4] != null && board[7][4].getmoved()){
					return true;
				}
			}
			if (sf.getSite() == "b" && ((board[0][0].getName() == "T" && board[0][7].getmoved()) ||((board[7][0].getName() == "T" && board[7][7].getmoved())))){
				if(board[0][4] != null && board[0][4].getmoved()){
					return true;
				}
			}
		}
		
		return false;
	}
	*/

	// checks if between i/j and k/l are just free spaces
	private static boolean checkBetween(Spielfigur[][] sfar, int i, int j, int k, int l){
		int z = i-k;
		int y = j-l;
		int x = 0;
		int w = 0;

		if(z == 0){
			while(Math.abs(y) > 0){ 
				if (x == 0);
				else if (sfar[i][j-y] != null)
					return false;
				if(y > 0)
					y--;
				else 
					y++;
				x++;
			}
			return true;
		}
		x = 0;
		if(y == 0){
			while(Math.abs(z) > 0){ 
				if(x == 0);
				else if (sfar[i-z][j] != null)
					return false;
				if(z > 0)
					z--;
				else 
					z++;
				x++;
			}
			return true;
		}

		x = 0 ;

		while(Math.abs(y) != Math.abs(x)){
			while(Math.abs(z) != Math.abs(w)){
				if(x == 0  && w == 0);

				else if (sfar[i+w][j+x] != null) 
					return false;

				if(y > 0)
					x--;
				else 
					x++;
				if(z > 0)
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
	
	public static Spielfigur[][] rightnextStep(Spielfigur[][] sfar,int i, int j, int k, int l){
		if(sfar[i][j] == null)
			return sfar;
		if((sfar[i][j] == null ||Player1 && sfar[i][j].getSite() == "w")||(!Player1 && sfar[i][j].getSite() == "b"))
			return sfar;
		validTurn = validmove(sfar, sfar[i][j],i,j, k,l);

		if(validTurn){
			sfar[k][l] = sfar[i][j];
			sfar[i][j] = null;
			Player1 = !Player1;
			//evaluateall(sfar);
			return sfar;
		}
		else return sfar;


	}



	// calcs the next step, inverts the current player
	public static Spielfigur[][] nextStep(Spielfigur[][] sfar,int i, int j, int k, int l){
		if(sfar[i][j] == null)
			return sfar;
		validTurn = validmove(sfar, sfar[i][j],i,j, k,l);

		if(validTurn){
			sfar[k][l] = sfar[i][j];
			sfar[i][j] = null;
			evaluateall(sfar);
			return sfar;
		}
		else return sfar;


	}
}
