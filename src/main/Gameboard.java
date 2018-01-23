package main;


import Spielfiguren.*;

public class Gameboard {

	private static final int GBWIDTH = 8;
	private static final int GBHEIGHT = 8;

	private static Spielfigur[][] board;

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
		evaluateall(this);

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

	public static void evaluateall(Gameboard gb){
		Spielfigur[][] sfar = gb.getBoard();
		for (int i = 0; i < sfar.length; i++) {
			for (int j = 0; j < sfar.length; j++) {
				evaluatevalidmoves(gb, i, j);
			}
		}

	}
	
	public static int[] getKing(Gameboard gb, String s){
		int[] result;
		for (int i = 0; i < gb.getBoard().length; i++) {
			for (int j = 0; j < gb.getBoard().length; j++) {
				Spielfigur sf = gb.getBoard()[i][j];
				if(sf == null);
				else if(sf.getName() == "K" && sf.getSite() == s)
					return result = new int[]{i,j};
			}
		}
		return null;
	}

	public static int[][] getallsidemoves(Gameboard gb, String s){
		Spielfigur sf;
		int[][] result = new int[100][30];
		int counter = 0;
		for (int i = 0; i < gb.getBoard().length; i++) {
			for (int j = 0; j < gb.getBoard().length; j++) {
				sf = gb.getBoard()[i][j];
				if (sf == null);
				else if(sf.getSite() == s){
					for(int k = 0; sf.movement()[k][1] != -1;k++){
						result[counter] = sf.movement()[k];
						counter++;
						//System.out.println("x: "+sf.movement()[k][0] +" y: " +sf.movement()[k][1]);
					}
				}
			}

		}

		result[counter][0] = -1; 
		return result;
	}
	
	public static boolean calculatecheck(Gameboard gb, int x, int y){
		Spielfigur[][] b = gb.getBoard();
	//	if(b[x][y] == null || b[x][y].getName() != "K")
	//		return false;
	//	System.out.println(b[x][y].getSite()== "w" ? "b" : "w");
		int[][] allmoves = getallsidemoves(gb, b[x][y].getSite() == "w" ? "b" : "w");
		for(int i = 0; allmoves[i][0] != -1; i++){
			//System.out.println("i,0: "+allmoves[i][0] + " i,1: "+allmoves[i][1]);
			if(allmoves[i][0] == y && allmoves[i][1] == x)
				return true;
		}
		
		return false;
	}

	public static boolean calculatecheckmate(Gameboard gb, String s, int[] arr){
		int x = arr[0];
		int y = arr[1];
		System.out.println("x: " + x + " y: " + y);
		Spielfigur sf = gb.getBoard()[x][y];
		if(sf.getName() != "K")
			return false;
		int[][] allmoves = getallsidemoves(gb,s);
		int counter = 0;
		for (int i = 0; allmoves[i][0] != -1; i++) {
			for (int j = 0; j < sf.movement().length; j++) {
			//	System.out.println("a: " + allmoves[i][1]+ " b: "+allmoves[i][0]);
				if(allmoves[i][1] == sf.movement()[j][0] &&allmoves[i][0] == sf.movement()[j][1])
					counter++;
			}
		}
		if(counter == sf.movement().length)
			return true;
		return false;
	}



	public static void evaluatevalidmoves(Gameboard gb, int x, int y){
		Spielfigur sf = gb.getBoard()[x][y];
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
							if(validmove(sf, x,y,tmp[1],tmp[0])){
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
							if(validmove(sf, x,y,tmp[1],tmp[0])){

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

					if(validmove(sf, x,y,j,i)){
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

					if(validmove(sf, x,y,i,j)){
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

					if(validmove(sf, x,y,i,j)){
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

					if(validmove(sf, x,y,i,j)){
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

					if(validmove(sf, x,y,i,j)){
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
	private static boolean validmove(Spielfigur sf, int i, int j, int k, int l){

		if(k > 7 || k < 0 || l > 7 || l< 0 || (i == k && j == l))
			return false;
		switch(sf.getName()){
		case "B":
			if(sf.getSite()== "w"){
				if(j == l){
					if(board[k][l] == null && (k == (i-1)||(k == (i-2) && i == 6&& board[i-1][l] == null)))
						return true;
				}
				if(board[k][l] != null && board[k][l].getSite() != board[i][j].getSite() &&((k == (i-1) && l == (j+1))||(k == (i-1) && l == (j-1))))
					return true;
			}
			else if(sf.getSite()== "b"){
				if(j == l){
					if(board[k][l] == null && (k == (i+1)||(k == (i+2) && i == 1 && board[i+1][l] == null)))
						return true;

				}
				if(board[k][l] != null &&board[k][l].getSite() != board[i][j].getSite() && ((k == (i+1) && l == (j+1))||(k == (i+1) && l == (j-1))))
					return true;
			} 
			return false;
		case "L":
			return validL(sf,i,j,k,l);
		case "K":
			if(board[k][l] == null ||sf.getSite() != board[k][l].getSite()){
				if((Math.abs(k-i) == 1 && Math.abs(l-j) == 1) || (Math.abs(l-j) == 1 && Math.abs(k-i) == 0) || (Math.abs(k-i) == 1 && Math.abs(l-j) == 0))
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
			while(Math.abs(y) > 0){ 
				if (x == 0);
				else if (board[i][j-y] != null)
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
				else if (board[i-z][j] != null)
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

				else if (board[i+w][j+x] != null) 
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


	// calcs the next step, inverts the current player
	public void nextStep(int i, int j, int k, int l){
		if(board[i][j] == null)
			return;
		if((board[i][j] == null ||Player1 && board[i][j].getSite() == "w")||(!Player1 && board[i][j].getSite() == "b"))
			return;
		validTurn = validmove(board[i][j],i,j, k,l);

		if(validTurn){
			board[k][l] = board[i][j];
			board[i][j] = null;
			Player1 = !Player1;
			evaluateall(this);
		}
		else return;


	}
}
