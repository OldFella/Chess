package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Spielfiguren.Spielfigur;


public class graphics implements ActionListener{


	private int width = 800;

	private int height = 800;

	private JFrame myframe;

	private JPanel mypanel;

	private boolean clicked;

	private Gameboard board;
	
	private int[] firstclicked;
	
	private JButton[][] bar;
	
	private JPanel Console;
	
	private JTextArea cons;
	
	private JScrollPane scrol;




	public graphics(){

		myframe = new JFrame("Chess");
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.setSize(new Dimension(width, height));
		myframe.setLocationRelativeTo(null); 
		myframe.setResizable(true); 
		myframe.setLayout(new BorderLayout());
		myframe.setMinimumSize(new Dimension(650,650));
		board = new Gameboard();
		//board.initGameboard();
		mypanel = new JPanel();
		Console = new JPanel();
		cons = new JTextArea(4,20);
		cons.setVisible(true);
		
		scrol = new JScrollPane(cons);
		scrol.setVisible(true);
		Console.add(scrol);
		myframe.add(Console);
		bar = new JButton[8][8];
		
		for (int i = 0; i < bar.length; i++) {
			for (int j = 0; j < bar.length; j++) {
				bar[i][j] = new JButton();
				bar[i][j].setFocusable(false);
				bar[i][j].setSelected(false);
				bar[i][j].addActionListener(this);
				bar[i][j].setRolloverEnabled(false);
				mypanel.add(bar[i][j]);
			}
		}
		paintGameboard();
		myframe.add(mypanel);
		
		JMenuBar jmb = new JMenuBar();
		JMenu menu = new JMenu("Help");
		JMenuItem menuItem = new JMenuItem("Controls");
		menu.setMnemonic(KeyEvent.VK_G);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(myframe, 
						"1. Press the Figure you want to move \n" +
						"2. Press the Field you want to move \n" +
						"Black begins");
				if(mypanel != null){
					paintGameboard();
					myframe.validate();
				}
			}
		});
		menu.add(menuItem);
		jmb.add(menu);
		myframe.setJMenuBar(jmb);
		myframe.setVisible(true);
	}
	
	private void initGame(){
		clicked = false;
		board.resetBoard();
		paintGameboard();
		
	}
	
	public void actionPerformed(ActionEvent e){
		int tmp = Integer.parseInt(e.getActionCommand());
		int y = tmp % 8;
		int x = tmp / 8;
		Spielfigur[][] temp = board.getBoard();
		if(clicked){
			System.out.println(""+ firstclicked[0]+ firstclicked[1]+ x+ y);
			board.nextStep(firstclicked[0], firstclicked[1], x, y);
			paintGameboard();
			Gameboard.evaluateall(board);
			String s = StartGame.checkwinner(temp);
			if(s != "nowin"){
				if(s != "unentschieden"){
					if(!Gameboard.getPlayer())
						JOptionPane.showMessageDialog(mypanel, "The Winner is Player 1");
					else
						JOptionPane.showMessageDialog(mypanel, "The Winner is Player 2");
				}
				else
					JOptionPane.showMessageDialog(mypanel, "Draw");
				reset();
				return;
				
				
			}
		}
		else{
			firstclicked = new int[]{x,y};
			
			bar[x][y].setBackground(new Color(255,69,0));
			
			int[][] valmoves = Gameboard.getvalidmoves(board.getBoard()[x][y]);
			
			for (int i = 0; valmoves == null ? false : valmoves[i][1] != -1; i++) {
				int a = valmoves[i][1];
				int b = valmoves[i][0];
				bar[a][b].setBackground(Color.blue);
			}
			
		}
		clicked = !clicked;
	}

	public void paintGameboard(){
		Spielfigur[][] gb = board.getBoard();
		int counter = 0;
		int cnt = 0;
		mypanel.setLayout(new GridLayout(8,8));
		Color b = Color.WHITE;
		for(int i = 0; i < gb.length;i++){
			cnt++;
			for (int j = 0; j < gb.length; j++) {
				
				bar[i][j].setIcon(null);
				bar[i][j].setName(""+counter);
				bar[i][j].setActionCommand(""+counter);
				bar[i][j].setVerticalAlignment(JButton.CENTER);
				bar[i][j].setHorizontalAlignment(JButton.CENTER);
				
				
				if((counter % 2 == 0 && cnt % 2 == 0)|| (counter % 2 == 1 && cnt % 2 == 1)){
					b = new Color(205,104,57);
				}
				else{
					b = new Color(255,222,173);
				}
				

				if(gb[i][j] == null);

				else{ 
					switch(gb[i][j].getName()){

					case "B":
						if(gb[i][j].getSite() == "w"){
							bar[i][j].setIcon(new ImageIcon (getImage("wB.png")));
						}
						else{
							bar[i][j].setIcon(new ImageIcon (getImage("bB.png")));
						}

						break;

					case "L":
						if(gb[i][j].getSite() == "w"){
							bar[i][j].setIcon(new ImageIcon (getImage("wL.png")));
						}
						else{
							bar[i][j].setIcon(new ImageIcon (getImage("bL.png")));
						}
						break;

					case "S":
						if(gb[i][j].getSite() == "w"){
							bar[i][j].setIcon(new ImageIcon (getImage("wS.png")));
						}
						else{
							bar[i][j].setIcon(new ImageIcon (getImage("bS.png")));
						}
						break;

					case "K":
						if(gb[i][j].getSite() == "w"){
							bar[i][j].setIcon(new ImageIcon (getImage("wK.png")));
						}
						else{
							bar[i][j].setIcon(new ImageIcon (getImage("bK.png")));
						}
						break;

					case "D":
						if(gb[i][j].getSite() == "w"){
							bar[i][j].setIcon(new ImageIcon (getImage("wD.png")));
						}
						else{
							bar[i][j].setIcon(new ImageIcon (getImage("bD.png")));
						}
						break;

					case "T":
						if(gb[i][j].getSite() == "w"){
							bar[i][j].setIcon(new ImageIcon (getImage("wT.png")));
						}
						else{
							bar[i][j].setIcon(new ImageIcon (getImage("bT.png")));
						}
						break;
					}

				}

				bar[i][j].setOpaque(true);
				bar[i][j].setBackground(b);
				bar[i][j].setActionCommand(counter + "");

				counter++;
			}

		}
		
		myframe.add(mypanel);

	}
	

	private void reset(){
		initGame();
	}
	

	private Image getImage(String s){

		File file = new File(s);
		BufferedImage i = null;
		try {
			i= ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;

	}
}