package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Currency;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.City;
import engine.Player;

public class GameView extends JFrame {
	private Map map;
	private ArrayList<JPanel> panels;
	private ArrayList<Object> o;
	private Player player;
	private JPanel playerBar=new JPanel();
	private JButton endTurn=new JButton("End Turn");
	private JLabel playerName=new JLabel("Player's Name");
	private JLabel gold=new JLabel("Gold");
	private JLabel food=new JLabel("Food");
	private JLabel turnsLeft=new JLabel("Turns Left");
	private int turncount;
	private JPanel currPanel=new JPanel();
	public JPanel getCurrPanel() {
		return currPanel;
	}
	public void setCurrPanel(JPanel currPanel) {
		this.currPanel = currPanel;
	}
	public GameView(){
		setVisible(true);
		setTitle("The Conqueror");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
//		Player player,int turncount
//		this.setTurncount(turncount);
//		this.setPlayer(player);
//		playerBar.setLayout(new FlowLayout());
//		
//		playerName.setText(playerName.getText()+":  "+player.getName()+"            ");
//		playerName.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
//		
//		gold.setText(gold.getText()+":  "+player.getTreasury()+"$            ");
//		gold.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
//		
//		turnsLeft.setText(turnsLeft.getText()+":  "+turncount+"            ");
//		turnsLeft.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
//		
//		food.setText(food.getText()+":  "+this.player.getFood()+"            ");
//		food.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
//		
//		playerBar.add(playerName);
//		playerBar.add(gold);
//		playerBar.add(food);
//		playerBar.add(turnsLeft);
//		
//		o=new ArrayList<Object>();
//		panels=new ArrayList<JPanel>();
//		setTitle("The Conqueror");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setLayout(new BorderLayout());
//		map=new Map();
//		endTurn.setPreferredSize(new Dimension(this.getWidth(),100));
//		endTurn.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
//		add(map,BorderLayout.CENTER);
//		
//		add(endTurn,BorderLayout.SOUTH);
//		add(playerBar,BorderLayout.NORTH);
//		draw();
//		setVisible(true);
//		revalidate();
//		repaint();
	}
//	public static void main(String[] args) {
//		GameView g = new GameView(new Player("AFASF"), 50);
//		g.panels.add(new CityView(new City("cairo")));
//		g.draw();
//		JPanel jP = g.currPanel;
//		g.remove(jP);
//		g.panels.add(new Armyview());
//		g.draw();
//		g.revalidate();
//		g.repaint();
//	}
//	public void addListener(ActionListener f) {
//		endTurn.addActionListener(f);
//		map.getCairo().addActionListener(f);
//		map.getSparta().addActionListener(f);
//		map.getRome().addActionListener(f);
//		if(panels.size()!=0) {
//			for(JPanel p:panels) {
//				if(p instanceof CityView)((CityView)p).addListener(f);
//				if(p instanceof Armyview)((Armyview)p).addListener(f);
//			}
//		}
//			
//	}
	
//	public void draw() {
//		if(panels.size()!=0) {
//			currPanel=panels.get(panels.size()-1);
//			add(currPanel,BorderLayout.EAST);
//		}
//	}
	
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public ArrayList<JPanel> getPanels() {
		return panels;
	}
	public void setPanels(ArrayList<JPanel> panels) {
		this.panels = panels;
	}
	public ArrayList<Object> getO() {
		return o;
	}
	public void setO(ArrayList<Object> o) {
		this.o = o;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public JLabel getGold() {
		return gold;
	}
	public void setGold(JLabel gold) {
		this.gold = gold;
	}
	public JLabel getTurnsLeft() {
		return turnsLeft;
	}
	public void setTurnsLeft(JLabel turnsLeft) {
		this.turnsLeft = turnsLeft;
	}
	public int getTurncount() {
		return turncount;
	}
	public void setTurncount(int turncount) {
		this.turncount = turncount;
	}
}
