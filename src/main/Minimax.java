package main;

import Spielfiguren.Spielfigur;

public class Minimax {

	private Spielfigur[][] board;

	private int depth;

	private int score;

	private int[] move;
	
	private int counter;

	public Minimax(Gameboard b){

		board = graphics.deepCopy(b.getBoard());
		/*
		board.setBoard(b.getBoard().clone());
		board.nextStep(0, 1, 2, 2);
		board.setPlayer1(b.getPlayer());
		board.setValidTurn(b.getValidTurn());
		System.out.println(board.getBoard()[0][1]);
		 */
		minimax(2, board);
		System.out.println("score: " + score);
		System.out.println("x1: "+ move[0]+ " y1: " + move[1] + " x2: " + move[2] + " y2: " + move[3]);
	}

	public Spielfigur[][] getBoard() {
		return board;
	}

	public void setBoard(Spielfigur[][] board) {
		this.board = board;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getScore() {
		return score;
	}

	public int[] getmove(){
		return move;
	}
	
private void minimax(int depth, Spielfigur[][] sfar){
	int[][] moves = Gameboard.getallsidemoves(board, "w");
	maxi(depth, sfar);
	System.out.println(counter);
	move = moves[counter];
	
	}


	private int mini(int depth, Spielfigur[][] sfar){
		//System.out.println("_____________");
		if(depth == 0){
			score = -evaluate(sfar);
			//System.out.println(score);
			return -evaluate(sfar);
		}

		int min = Integer.MAX_VALUE;
		Spielfigur[][] copy = graphics.deepCopy(sfar);
		int[][] moves = Gameboard.getallsidemoves(board, "b");
		for (int i = 0; moves[i][0] != -1; i++) {
			//System.out.println("x: "+ moves[i][1] + " y: " + moves[i][0]);
			int a = moves[i][0];
			int b = moves[i][1];
			int c = moves[i][2];
			int d = moves[i][3];
			//System.out.println("" + a + " "+ b+ " " + c+ " " +d);
			copy = Gameboard.nextStep(copy, moves[i][1], moves[i][0], moves[i][3], moves[i][2]);
			Gameboard.evaluateall(copy);
			score = maxi(depth-1,copy);
			copy = graphics.deepCopy(sfar);
			if(score < min){
				min = score;
				//move = move;
			}

		}
		
		return min;
	}
	
	private int maxi(int depth, Spielfigur[][] sfar){
		//System.out.println("_____________");
		if(depth == 0){
			score = evaluate(sfar);

			return evaluate(sfar);
		}
		int max = Integer.MIN_VALUE;
		Spielfigur[][] copy = graphics.deepCopy(sfar);
		int[][] moves = Gameboard.getallsidemoves(sfar, "w");
		for (int i = 0; moves[i][0] != -1; i++) {
			int a = moves[i][0];
			int b = moves[i][1];
			int c = moves[i][2];
			int d = moves[i][3];
			//System.out.println("" + a + " "+ b+ " " + c+ " " +d);
			copy = Gameboard.nextStep(copy, moves[i][1], moves[i][0], moves[i][3], moves[i][2]);
			Gameboard.evaluateall(copy);
			score = mini(depth-1, copy);
			copy = graphics.deepCopy(sfar);
			//System.out.println("sc: "+score + " max: "+ max);
			if(score > max){
				max = score;
				counter = i;
				System.out.println("cnt: " + counter);
				//move = new int[]{a, b, c, d};
			}
			

		}
		//score = max;
		return max;
	}

	public static int evaluate(Spielfigur[][] sfar){
		int result = 0;
		for (int i = 0; i < sfar.length; i++) {
			for (int j = 0; j < sfar.length; j++) {
				Spielfigur sf = sfar[i][j];
				if(sf == null);
				else{
					switch(sf.getName()){

					case "B":
						if(sf.getSite() == "w")
							result += 10;
						else
							result -= 10;
						break;
					case "T":
						if(sf.getSite() == "w")
							result += 50;
						else
							result -= 50;
						break;
					case "K":
						if(sf.getSite() == "w")
							result += 1000;
						else
							result -= 1000;
						break;
					case "D":
						if(sf.getSite() == "w")
							result += 90;
						else
							result -= 90;
						break;
					case "L":
						if(sf.getSite() == "w")
							result += 30;
						else
							result -= 30;
						break;
					case "S":
						if(sf.getSite() == "w")
							result += 30;
						else
							result -= 30;
						break;
					}
				}
			}
		}
		return result;
	}


}
