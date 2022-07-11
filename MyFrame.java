package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyFrame extends JFrame implements ActionListener {
	
	Tabella tabella=new Tabella();
	JPanel campoDiGioco;
	JLabel header;
	ImageIcon circle=new ImageIcon("circle.png");
	ImageIcon cross=new ImageIcon("cross.png");
	List<MyButton> buttonsList=new LinkedList<>();
	String[] players= {"Player 1","Player 2"};
	JMenuItem newGameItem;
	JMenuItem exitItem;
	JMenuItem setPlayer1Item;
	JMenuItem setPlayer2Item;
	
	MyFrame(){
		this.setSize(600,750);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("TicTacToe");
		this.setIconImage((new ImageIcon("cross.png").getImage()));
		
		campoDiGioco=new JPanel();
		campoDiGioco.setPreferredSize(new Dimension(600,600));
		campoDiGioco.setBackground(Color.black);
		campoDiGioco.setLayout(new GridLayout(3,3));
		
		buttonsList.add(new MyButton(0,0));
		buttonsList.add(new MyButton(0,1));
		buttonsList.add(new MyButton(0,2));
		buttonsList.add(new MyButton(1,0));
		buttonsList.add(new MyButton(1,1));
		buttonsList.add(new MyButton(1,2));
		buttonsList.add(new MyButton(2,0));
		buttonsList.add(new MyButton(2,1));
		buttonsList.add(new MyButton(2,2));
		
		
		buttonsList.stream().forEach(b->campoDiGioco.add(b));
		
				
		this.add(campoDiGioco,BorderLayout.SOUTH);
		
		
		header=new JLabel();
		header.setFont(new Font("Times New Roman", Font.PLAIN,25));
		header.setPreferredSize(new Dimension(600,100));
		header.setForeground(Color.green);
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.CENTER);
		refreshHeader();
		this.add(header,BorderLayout.NORTH);
		this.getContentPane().setBackground(Color.black);
		
		JMenuBar menuBar=new JMenuBar();
		JMenu gameMenu=new JMenu("Game");
		JMenu editMenu=new JMenu("Edit");
		menuBar.add(gameMenu);
		menuBar.add(editMenu);
		newGameItem= new JMenuItem("New Game");
		exitItem=new JMenuItem("Exit");
		setPlayer1Item=new JMenuItem("Set player 1's name");
		setPlayer2Item=new JMenuItem("Set player 2's name");
		gameMenu.add(newGameItem);
		gameMenu.add(exitItem);
		editMenu.add(setPlayer1Item);
		editMenu.add(setPlayer2Item);
		newGameItem.addActionListener(this);
		exitItem.addActionListener(this);
		setPlayer1Item.addActionListener(this);
		setPlayer2Item.addActionListener(this);
		this.setJMenuBar(menuBar);
		
		this.setVisible(true);
		
	}
	
	public void refreshHeader() {
		header.setText("Turno di "+ players[tabella.getTurn()-1]);
	}
	
	public void headerWin() {
		int vincitore;
		if(tabella.getTurn()==1) {
			vincitore=2;
		}else {
			vincitore=1;
		}
		header.setText(players[vincitore-1] +" ha vinto!");
	}
	
	public void win() {
		buttonsList.stream().forEach(b->b.winner());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(newGameItem)) {
			tabella.reset();
			refreshHeader();
			buttonsList.stream().forEach(b->{
				b.setIcon(null);
				b.setEnabled(true);
				b.setBackground(Color.white);
				});
			
		}else if(e.getSource().equals(exitItem)) {
			System.exit(0);
		}else if(e.getSource().equals(setPlayer1Item)) {
			players[0]=JOptionPane.showInputDialog("Insert player 1's name");
			refreshHeader();
		}else if(e.getSource().equals(setPlayer2Item)) {
			players[1]=JOptionPane.showInputDialog("Insert player 2's name");
			refreshHeader();
		}
		
	}
	
	 private void headerPareggio() {
		header.setText("Pareggio!");
		
	}
	
	 class MyButton extends JButton{

		int x;
		int y;
		
		 MyButton(int x,int y){
			 super();
			 this.x=x;
			 this.y=y;
			 this.setBackground(Color.white);
			 this.setFocusable(false);
			 this.addActionListener(e->{
				 if(e.getSource().equals(this)) {
					 boolean ok=tabella.draw(x, y,tabella.getTurn());
					 if(ok) {
						 	refreshHeader();
						 	if(tabella.getTurn()==1) {  //è cambiato!! ho già chiamato draw che ha permutato
						 		player2();
						 	}else {
						 		player1();
						 	}
						 	int win=tabella.checkWin();
						 	if(win!=0) {
						 		headerWin();
						 		win();
						 		buttonsList.stream().forEach(b->b.setEnabled(false));
						 	}else if(tabella.getCounter()==9) {
						 		headerPareggio();
						 		buttonsList.stream().forEach(b->b.setEnabled(false));
						 	}
					 }
					 
				 }
			 });
		 }
		 

		public void player1(){
			 this.setIcon(cross);
		 }
		 
		 public void player2() {
			 this.setIcon(circle);
		 }
		 
		 public void winner() {
			 if(tabella.getValue(x, y)==9){
				 this.setBackground(Color.orange);
			 }
		 }
		 
		 public boolean match(int x,int y) {
			 return (this.x==x && this.y==y);
		 }
		 
	}



}






