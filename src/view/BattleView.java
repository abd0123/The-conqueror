package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;
import units.Army;
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
	
	private JButton attack;
	
	public BattleView(Army ar1,Army ar2,Game g) {
		playerArmy =ar1; defendingArmy =ar2; game =g;
		units1 = ar1.getUnits(); units2=ar2.getUnits();
		
		setTitle("Battle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		infoPanel =new JPanel();
		infoPanel.setBackground(Color.BLUE);
		infoPanel.setPreferredSize(new Dimension(this.getWidth(),100));
		infoPanel.setLayout(new FlowLayout());
		add(BorderLayout.NORTH,infoPanel);
		
		army1Panel =new JPanel();
		army1Panel.setBackground(Color.red);
		army1Panel.setPreferredSize(new Dimension(200,200));
		army1Panel.setLayout(new FlowLayout());
		add(BorderLayout.WEST,army1Panel);
		
		army2Panel =new JPanel();
		army2Panel.setBackground(Color.yellow);
		army2Panel.setPreferredSize(new Dimension(200,200));
		army2Panel.setLayout(new FlowLayout());
		add(BorderLayout.EAST,army2Panel);
		
		logPanel =new JPanel();
		logPanel.setBackground(Color.pink);
		logPanel.setPreferredSize(new Dimension(500,this.getHeight()-200));
		add(BorderLayout.CENTER,logPanel);
		
		attackPanel =new JPanel();
		attackPanel.setBackground(Color.green);
		attackPanel.setPreferredSize(new Dimension(this.getWidth(),100));
		attackPanel.setLayout(new FlowLayout());
		add(BorderLayout.SOUTH,attackPanel);
		
		
		
		setVisible(true);
		revalidate();
		repaint();
	}

	public static void main(String[] args) throws IOException {
		
     BattleView bv = new BattleView(new Army("rome_army.csv"),new Army("cairo_army.csv"),new Game("Far","Rome"));
	}

}
