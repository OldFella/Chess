package main;


import Spielfiguren.*;

public class StartGame {


/*
 * starts the game by creating a gameboard, than it prints the instructions
 * prints the initialized gameboard
 * takes 4 ints and calcs the next steop with them
 * if the game is finished the loop ends and so the programm
 */
	// checks if theres a winner and his name
	public static String checkwinner(Spielfigur[][] sparr){
		String winner = "";
		for (int i = 0; i < sparr.length; i++) {
			for (int j = 0; j < sparr[i].length; j++) {
				if(sparr[i][j] == null);

				else if(sparr[i][j].getName() =="K"){
					if (winner == "")
						winner = sparr[i][j].getSite();
					else if(checksurrounding(sparr, i, j))
						return "unentschieden";
					else
						return "nowin";
				}
				else;
			}

		}


		return winner;
	}
	
	//checks the 8 fields around the figure, returns a true if theres a King around
	private static boolean checksurrounding(Spielfigur[][] sparr, int x, int y){

		int ystart = 0;
		int xstart = 0;
		int yend = 0;
		int xend = 0;
		if(y == 0)
			ystart = 0;
		else
			ystart = y-1;

		if(x == 0)
			xstart = 0;
		else
			xstart = x-1;

		if(x == 7)
			xend = 7;
		else
			xend = x+1;

		if(y == 7)
			yend = 7;
		else
			yend = y+1;

		for(int i = xstart; i <= xend; i++){
			for(int j = ystart; j <= yend; j++){
				if(sparr[i][j] == null);
				else if(i == x && j == y);

				else if (sparr[i][j].getName() == "K")
					return true;

			}
		}

		return false;
	}

}
