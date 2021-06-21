package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private ImagePanel logPanel;
	private JPanel infoPanel;
	private JPanel attackPanel;
	private ArrayList<Unit> units1;
	private ArrayList<Unit> units2;
	
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
		army1Panel.setPreferredSize(new Dimension(300,200));
		army1Panel.setLayout(new FlowLayout());
		add(BorderLayout.WEST,army1Panel);
		
		army2Panel =new JPanel();
		army2Panel.setBackground(Color.yellow);
		army2Panel.setPreferredSize(new Dimension(300,200));
		army2Panel.setLayout(new FlowLayout());
		add(BorderLayout.EAST,army2Panel);
		
		logPanel =new ImagePanel("sa.jpg");
		logPanel.setBackground(Color.pink);
		logPanel.setPreferredSize(new Dimension(500,this.getHeight()-200));
		add(BorderLayout.CENTER,logPanel);
		
		attackPanel =new JPanel();
		attackPanel.setBackground(Color.green);
		attackPanel.setPreferredSize(new Dimension(this.getWidth(),60));
		attackPanel.setLayout(new FlowLayout());
		add(BorderLayout.SOUTH,attackPanel);
		
		for(int i=0;i<units1.size();i++) {
			Unit u = units1.get(i);
			JButton b;
			if(u instanceof Archer)
			    b =new JButton("Archer");
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
		attack.setPreferredSize(new Dimension(650,50));
		endTurn.setPreferredSize(new Dimension(650,50));
		attackPanel.add(attack);
		attackPanel.add(endTurn);
		
		playerName.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		gold.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		turnsLeft.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		infoPanel.add(playerName);  infoPanel.add(gold);  infoPanel.add(turnsLeft);

		
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
