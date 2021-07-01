package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Unit;

public class BattleView extends JFrame {
	
	private Army playerArmy;
	private Army defendingArmy;
	private Game game;
	private JPanel army1Panel;
	private JPanel army2Panel;
	private JPanel logPanel;
	private JPanel infoPanel;
	private JPanel attackPanel;
	private ArrayList<Unit> units1;
	private ArrayList<Unit> units2;
	
	private JLabel level;
	private JLabel soldierCount;
	
	private JButton endTurn=new JButton("End Turn");
	private JLabel playerName=new JLabel("Player's Name:");
	private JLabel gold=new JLabel("Gold:");
	private JLabel turnsLeft=new JLabel("Turns Left:");
	
	private JButton attack=new JButton("Attack");
	
	public BattleView(Army ar1,Army ar2,Game g) {
		playerArmy =ar1; defendingArmy =ar2; game =g;
		units1 = ar1.getUnits(); units2=ar2.getUnits();
		
		setTitle("Battle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		infoPanel =new JPanel();
		infoPanel.setBackground(Color.BLUE);
		infoPanel.setPreferredSize(new Dimension(this.getWidth(),50));
		infoPanel.setLayout(new FlowLayout());
		add(BorderLayout.NORTH,infoPanel);
		
		army1Panel =new JPanel();
		army1Panel.setBackground(Color.red);
		army1Panel.setPreferredSize(new Dimension(300,500));
		army1Panel.setLayout(new FlowLayout());
//		JPanel upper = new JPanel();
//		upper.setBackground(Color.orange);
//		upper.setLayout(new FlowLayout());
		add(BorderLayout.WEST,army1Panel);
//		army1Panel.add(upper);
//		JPanel lower =new JPanel();
//		lower.setBackground(Color.lightGray);
//		lower.setLayout(new FlowLayout());
//		army1Panel.add(lower);
		
		army2Panel =new JPanel();
		army2Panel.setPreferredSize(new Dimension(300,200));
		army2Panel.setLayout(new FlowLayout());
//		JPanel upper2 = new JPanel();
//		upper2.setBackground(Color.orange);
//		upper2.setLayout(new FlowLayout());
		add(BorderLayout.EAST,army2Panel);
//		army2Panel.add(upper2);
//		JPanel lower2 =new JPanel();
//		lower2.setBackground(Color.lightGray);
//		lower2.setLayout(new FlowLayout());
//		army2Panel.add(lower2);
		
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BorderLayout());
		midPanel.setPreferredSize(new Dimension(500,this.getHeight()-50));
		add(midPanel);
		
		logPanel =new JPanel();
		logPanel.setBackground(Color.pink);
		logPanel.setPreferredSize(new Dimension(midPanel.getWidth(),midPanel.getHeight()-60));
		logPanel.setLayout(new GridLayout());
		midPanel.add(logPanel);
		
		attackPanel =new JPanel();
		attackPanel.setBackground(Color.green);
		attackPanel.setPreferredSize(new Dimension(midPanel.getWidth(),60));
		attackPanel.setLayout(new GridLayout(1,2));
		midPanel.add(attackPanel,BorderLayout.SOUTH);
				
		for(int i=0;i<units1.size();i++) {
			Unit u = units1.get(i);
			JButton b;
			if(u instanceof Archer)
			    b =new JButton("Archer ");
			else if(u instanceof Cavalry)
			    b =new JButton("Cavalry");
			else
			    b =new JButton("Infantry");
			b.setPreferredSize(new Dimension(95,50));
			army1Panel.add(b);
		}
		for(int i=0;i<units2.size();i++) {
			Unit u = units2.get(i);
			JButton b;
			if(u instanceof Archer)
			    b =new JButton("Archer");
			else if(u instanceof Cavalry)
			    b =new JButton("Cavalry");
			else
			    b =new JButton("Infantry");
			b.setPreferredSize(new Dimension(95,50));
			army2Panel.add(b);
		}
		attack.setPreferredSize(new Dimension(460,50));
		endTurn.setPreferredSize(new Dimension(460,50));
		attackPanel.add(attack);
		attackPanel.add(endTurn);
		
		playerName.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		gold.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		turnsLeft.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		infoPanel.add(playerName);  infoPanel.add(gold);  infoPanel.add(turnsLeft);
		
//		JButton b = new JButton("djfnd");
//		upper2.add(b);
//		JButton c = new JButton("djfnd");
//		upper2.add(c);
//		JButton d = new JButton("djfnd");
//		upper2.add(d);
//		JButton e = new JButton("djfnd");
//		upper2.add(e);
//		JButton f = new JButton("djfnd");
//		upper2.add(f);
//		JButton h = new JButton("djfnd");
//		upper2.add(h);
//		JButton j = new JButton("djfnd");
//		upper2.add(j);
//		JButton k = new JButton("djfnd");
//		upper2.add(k);
		
		setVisible(true);
		revalidate();
		repaint();
	}

	public static void main(String[] args) throws IOException {
	Game game = new Game("ahmed", "cairo");
	Army army = game.getAvailableCities().get(0).getDefendingArmy();
	Army army2 = game.getAvailableCities().get(1).getDefendingArmy();
     BattleView bv = new BattleView(army,army2,game);
	}

}
