package controller;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.*;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;
import view.*;

public class Controller implements ActionListener {
	private StartWindow start;
	private GameView view;
	private Game g;
	private CityFrame cityFrame;
	private ArmyFrame armyFrame;
	private Map map;
	private City selectedCity;
	private JComboBox Buildings;
	private JComboBox buildcost;
	private JComboBox units;
	private JComboBox openUnits;
	private JComboBox targets;
	private Army selectedArmy;
	public Controller() {
		start=new StartWindow();
		start.getStart().addActionListener(this);
		start.getStart().setPreferredSize(new Dimension(start.getP().getWidth(),50));
		map=new Map();
		map.getCairo().addActionListener(this);
		map.getSparta().addActionListener(this);
		map.getRome().addActionListener(this);
		map.getAr().addActionListener(this);
	}
	
	
	public void drawPlayerBar() {
		this.view.getContentPane().removeAll();
		JPanel playerBar=new JPanel();
		JLabel playerName=new JLabel("Player's Name");
		JLabel gold=new JLabel("Gold");
		JLabel food=new JLabel("Food");
		JLabel turnsLeft=new JLabel("Turns count");
		int turncount=g.getCurrentTurnCount();
		playerBar.setLayout(new FlowLayout());
		
		playerName.setText(playerName.getText()+":  "+g.getPlayer().getName()+"            ");
		playerName.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		
		gold.setText(gold.getText()+":  "+g.getPlayer().getTreasury()+"$            ");
		gold.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		
		turnsLeft.setText(turnsLeft.getText()+":  "+turncount+"            ");
		turnsLeft.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		
		food.setText(food.getText()+":  "+g.getPlayer().getFood()+"            ");
		food.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		
		playerBar.add(playerName);
		playerBar.add(gold);
		playerBar.add(food);
		playerBar.add(turnsLeft);
		
		view.add(playerBar,BorderLayout.NORTH);
	}
	
	public void drawMap() {
		drawPlayerBar();
		JButton endTurn=new JButton("End Turn");
		endTurn.setPreferredSize(new Dimension(view.getWidth(),70));
		endTurn.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		view.add(endTurn,BorderLayout.SOUTH);
		view.add(map,BorderLayout.CENTER);
		endTurn.addActionListener(this);
		view.repaint();
		view.revalidate();
		
	}
	
	public void drawCity(City c){
		drawPlayerBar();
		JLabel name;
		JButton openBuilding;
		JLabel lbuildings;
		
		
		JButton build;
		JLabel status;
		JLabel turnsUnderSeige;
		JButton initiateArmy;
		JButton back;
		JLabel defending;
		
		JButton DefendingArmy;
		
		JPanel city=new JPanel();
		city.setVisible(true);
		city.setLayout(null);
		city.setPreferredSize(new Dimension(550,800));
		//buildings button -----------
		lbuildings =new JLabel("   Buildings");
		lbuildings.setFont(new Font("Forte", Font.BOLD, 22));
		lbuildings.setBounds(0,500,180,100);
		String[] cbox=new String[c.getEconomicalBuildings().size()+c.getMilitaryBuildings().size()];
		int i=0;
		for (Building b:c.getMilitaryBuildings()) {
			if(b instanceof ArcheryRange)cbox[i]="Archery Range";
			if(b instanceof Barracks)cbox[i]="Barracks";
			if(b instanceof Stable)cbox[i]="stable";
			i++;
		}
		for(Building b:c.getEconomicalBuildings()) {
			if(b instanceof Farm)cbox[i]="Farm";
			if(b instanceof Market)cbox[i]="Market";
			i++;
		}
		Buildings=new JComboBox(cbox);
		Buildings.setBounds(190,525,180,50);
		Buildings.setFont(new Font("Forte", Font.BOLD, 22));
		openBuilding=new JButton("open Building");
		openBuilding.setBounds(380,525,170,50);
		openBuilding.setFont(new Font("Forte", Font.BOLD, 19));
		
		String[]ccbox= {"Market 1500 $","Farm 1000 $","Archery Range 1500 $","Barracks 2000 $","Stable 2500 $"};
		buildcost = new JComboBox(ccbox);
		buildcost.setFont(new Font("Forte", Font.BOLD, 22));
		buildcost.setBounds(10,625,360,50);
		build = new JButton("Build");
		build.setBounds(380,625,170,50);
		build.setFont(new Font("Forte", Font.BOLD, 22));
		//---------------
		name = new JLabel("   city name :  "+c.getName());
		name.setBounds(0,40,400,100);
		name.setFont(new Font("Forte", Font.BOLD, 22));
		status = new JLabel("   Status : "+(c.isUnderSiege()?"Besieged":"Not Besieged"));
		status.setBounds(0,140,400,100);
		status.setFont(new Font("Forte", Font.BOLD, 22));
		turnsUnderSeige=new JLabel("   Turns Under Seige :  "+c.getTurnsUnderSiege());
		turnsUnderSeige.setFont(new Font("Forte", Font.BOLD, 22));
		turnsUnderSeige.setBounds(0,240,400,100);
		//--------------
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setFont(new Font("Forte", Font.BOLD, 19));
		initiateArmy.setBounds(380,725,170,50);
		defending = new JLabel("   Units ");
		defending.setFont(new Font("Forte", Font.BOLD, 22));
		defending.setBounds(0,700,180,100);
		String [] c3box= new String[c.getDefendingArmy().getUnits().size()];
		int x = 0 ;
		for (Unit u : c.getDefendingArmy().getUnits()) {
			if(u instanceof Archer )c3box[x]= "Archer "+u.getLevel();
			if (u instanceof Cavalry)c3box[x]="Cavalry "+u.getLevel();
			if (u instanceof Infantry) c3box[x]= "Infantry "+u.getLevel();
			x++;
		}
		units = new JComboBox(c3box);
		units.setFont(new Font("Forte", Font.BOLD, 22));
		units.setBounds(190,725,180,50);
		
		
		//------------------
		back = new JButton("");
		back.setText("Return to map");
		back.setFont(new Font("Forte", Font.BOLD, 14));
//		back.setIcon(new ImageIcon("images/back.png"));
		back.setBounds(10,10,200,50);
		//------------------
		DefendingArmy =new JButton("Defending Army");
		DefendingArmy.setFont(new Font("Forte", Font.BOLD, 22));
		DefendingArmy.setBounds(50,350,450,100);
		//------------- add
		city.add(DefendingArmy);
		city.add(units);
		city.add(defending);
		city.add(buildcost);
		city.add(Buildings);
		city.add(lbuildings);
		city.add(openBuilding);
		city.add(name);
		city.add(status);
		city.add(turnsUnderSeige);
		city.add(build);
		city.add(initiateArmy);
		city.add(back);
		city.setBorder(BorderFactory.createLineBorder(Color.black));
		city.revalidate();
		city.repaint();
		back.addActionListener(this);
		DefendingArmy.addActionListener(this);
		openBuilding.addActionListener(this);
		build.addActionListener(this);
		initiateArmy.addActionListener(this);
		view.add(city,BorderLayout.WEST);
		view.repaint();
		view.revalidate();
		
	}
	
	public void drawArmy(Army a) {
		drawPlayerBar();
		 JButton openUnit = new JButton("Open Unit");
		 JButton setTarget = new JButton("Set target");
		 JButton relocateUnit = new JButton("Relocate unit");
		 JLabel currentStatus = new JLabel("Current status:  "+a.getCurrentStatus());
		 JLabel distanceToTarget = new JLabel("Distance to target:  "+a.getDistancetoTarget());
		 JLabel target = new JLabel("Target:  " +(a.getTarget().equals("")?"No Target Assigned":a.getTarget()));
		 JLabel currentLocation = new JLabel("Current location:  "+a.getCurrentLocation());
		 String[][] grid;
		 JPanel panel = new JPanel();
		 JButton back;
		 openUnit.addActionListener(this);
		 setTarget.addActionListener(this);
		 relocateUnit.addActionListener(this);
		 
		 
		 String[]openun=new String[a.getUnits().size()];
		 for (int i = 0; i < openun.length; i++) {
			Unit u=a.getUnits().get(i);
			if(u instanceof Archer)openun[i]="Archer "+u.getLevel();
			else if(u instanceof Cavalry)openun[i]="Cavalry "+u.getLevel();
			else openun[i]="Infantry "+u.getLevel();
		} 
		 openUnits=new JComboBox(openun);
		 openUnits.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22) );
		 
		 String[]targetss=new String[g.getAvailableCities().size()-g.getPlayer().getControlledCities().size()];
		 int s=0;
		 for (int i = 0; i < g.getAvailableCities().size();i++) {
			City c= g.getAvailableCities().get(i);
			if(!g.getPlayer().getControlledCities().contains(c)) {
				targetss[s++]=c.getName();
			}
		}
		 targets=new JComboBox(targetss);
		 targets.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22) );
		 
		 JPanel army =new JPanel();
		army.setLayout(new BorderLayout());
		army.setBackground(Color.lightGray);
		army.setMinimumSize(new Dimension(500, 500));
		army.setPreferredSize(new Dimension(550,800));
		back = new JButton();
		back.setText("Return to map");
		back.addActionListener(this);
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		army.setVisible(true);
		openUnit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		setTarget.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		relocateUnit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currentStatus.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currentLocation.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		target.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		distanceToTarget.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));

		grid = new String[][] { { "Units", "Set target", "Relocate unit" }, { "Army status", "", "" },
				{ "Distance to target", "", "", "" }, { "Army location", "", "" }, { "Target", "", "" },
				{ "back", "", "" } };
		panel.setLayout(new GridLayout(0, 3));
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j].equals("Units")) {
					JPanel y=new JPanel();
					y.setLayout(new FlowLayout());
					openUnits.setPreferredSize(new Dimension(300,50));
					y.add(openUnits);
					y.add(openUnit);
					panel.add(y);
				}else if (grid[i][j].equals("Set target")) {
					if(a.equals(selectedCity.getDefendingArmy())) {
						panel.add(new JPanel());
					}else {
						JPanel y=new JPanel();
						y.setLayout(new FlowLayout());
						targets.setPreferredSize(new Dimension(300,50));
						y.add(targets);
						y.add(setTarget);
						panel.add(y);
					}
				}else if (grid[i][j].equals("Relocate unit"))
					panel.add(relocateUnit);
				else if (grid[i][j].equals("Army status"))
					panel.add(currentStatus);
				else if (grid[i][j].equals("Distance to target"))
					panel.add(distanceToTarget);
				else if (grid[i][j].equals("Army location"))
					panel.add(currentLocation);
				else if (grid[i][j].equals("back"))
					panel.add(back);
				else if (grid[i][j].equals("Target"))
					panel.add(target);

				else {
					JPanel x = new JPanel();
					panel.add(x);
				}
			}
		}
		JLabel title = new JLabel("Army ");
		title.setFont(new Font("Forte", Font.ROMAN_BASELINE, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		army.add(title, BorderLayout.NORTH);
		army.add(panel, BorderLayout.CENTER);
		army.setBorder(BorderFactory.createLineBorder(Color.black));
		army.revalidate();
		army.repaint();
		view.add(army);
		view.revalidate();
		view.repaint();
	}
	
	public void drawUnit(Unit u) {
		drawPlayerBar();
		 
		 JLabel leveLabel;
		 JLabel Status;
		 JLabel currntsoL;
		 JButton back;
		 JPanel Unit=new JPanel();
		 Unit.setBounds(1350,100,550,800);
		 Unit.setVisible(true);
		 Unit.setLayout(null);
		 Unit.setPreferredSize(new Dimension(550,800));
		 Unit.setBorder(BorderFactory.createLineBorder(Color.black));
		leveLabel = new JLabel("   Level :  "+u.getLevel());
		leveLabel.setFont(new Font("Forte", Font.BOLD, 19));
		leveLabel.setBounds(0,60,200,100);
		Status = new JLabel("   Status :  "+u.getParentArmy().getCurrentStatus());
		Status.setFont(new Font("Forte", Font.BOLD, 19));
		Status.setBounds(0,160,100,100);
		currntsoL = new JLabel("   CurrentSoldierCount :  "+u.getCurrentSoldierCount());
		currntsoL.setFont(new Font("Forte", Font.BOLD, 19));
		currntsoL.setBounds(0,260,300,100);
		back = new JButton("Return to map");
		back.setIcon(new ImageIcon("C://back.PNG"));
		back.setBounds(10,10,50,50);
		back.addActionListener(this);
		Unit.add(leveLabel);
		Unit.add(Status);
		Unit.add(currntsoL);
		Unit.add(back);
		
		Unit.revalidate();
		Unit.repaint();
		
		view.add(Unit);
		view.revalidate();
		view.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s!=null) {
			if(s.equals("Start")) {
				if(start.getPlayerNameTxt().getText().equals("")) {
					playSound("sounds/Warning.wav");
					JOptionPane.showMessageDialog(start, "write Player Name","Alert",JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					try {
						g=new Game(start.getPlayerNameTxt().getText(),start.getCities().getSelectedItem().toString());
//						playSound("sounds/Avengers.wav");
					} catch (IOException e1) {
					}
					System.out.println("asa");
					this.view=new GameView();
//					view.addListener(this);
					start.dispose();
					this.drawMap();
				}
			}else if(s.equals("Cairo")) {
				City cairo =null;
				boolean flag=false;
				for(City c:g.getAvailableCities())if(c.getName().equals("Cairo")) {
					cairo = c;
					flag = true;
				}
				System.out.println(flag);
				if (flag){
					drawCity(cairo);
					selectedCity=cairo;
					
				}
				else {
					JOptionPane.showMessageDialog(view, "Not controlled city","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
				
			
			}else if(s.equals("Rome")){
				City Rome =null;
				boolean flag=false;
				for(City c:g.getAvailableCities()) {
					if(c.getName().equals("Rome")) {
						Rome = c;
						flag=true;
					}
				}
				if(flag) {
					drawCity(Rome);
					selectedCity=Rome;
				}else {
					JOptionPane.showMessageDialog(view, "Not controlled city","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if(s.equals("Sparta")){
				City Sparta =null;
				boolean flag=false;
				for(City c:g.getAvailableCities())if(c.getName().equals("Sparta")) {
					Sparta = c;
					flag = true;
				}
				if (flag){
					drawCity(Sparta);
					selectedCity=Sparta;
					
				}
				else {
					JOptionPane.showMessageDialog(view, "Not controlled city","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(s.equals("Return to map")) {
				drawMap();
			}else if(s.equals("Defending Army")) {
				selectedArmy=selectedCity.getDefendingArmy();
				drawArmy(selectedCity.getDefendingArmy());
			}else if(s.equals("Army")) {
				Army a=new Army("onRoad");
				a.getUnits().add(new Archer(1,60,0.3,0.4,0.5));
				drawArmy(a);
			}else if(s.equals("Open Unit")) {
				drawUnit(selectedArmy.getUnits().get(openUnits.getSelectedIndex()));
			}
		}
	
//			view.addListener(this);
//			view.revalidate();
//			view.repaint();
			
		}
	
	public void playSound (String filepath)  {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
//////	private void txt_playerName (java.awt.event.KeyEvent e) {
//////		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
//////			start.dispose();
//////			this.view=new GameView();
//////			
////		}
//	}
	public static void main(String[] args) {
		new Controller();
	}
}
