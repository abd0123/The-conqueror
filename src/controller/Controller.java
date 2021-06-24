package controller;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
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
//	private Map map;
	private City selectedCity;
	private JComboBox Buildings;
	private JComboBox buildcost;
	private JComboBox units;
	private JComboBox openUnits;
	private JComboBox targets;
	private Army selectedArmy;
	private Army attackedArmy;
	private ArrayList<JButton> AvailableUnits;
	private ArrayList<JButton> targetUnits;
	private JButton selectedButton1;
	private JButton selectedButton2;
	private JTextArea textArea;
	private String event;
	private JLabel attackerLeveL ;
	private JLabel attackedLeveL;
	private JLabel attackersoldier;
	private JLabel attackedsoldier;
	private JLabel image1;
	private JLabel image2;
	private JPanel images;
	private JPanel imagesinArmy;
	private Building selectedBuilding;
	private JTextArea autoLOG;
	private Clip clip;
	private AudioInputStream audioInputStream;
	private JComboBox armies;
	private JLabel testimage;
	private ArrayList<Army>sameplaceArmeis;
	private JComboBox sorroundingArmy;
	private JComboBox sorroundingUnits=new JComboBox();
	private Army toBeOpen;
	private boolean doneselect;
	private JButton relocateUnit;
	
	public Controller() {
		start=new StartWindow();
		start.getStart().addActionListener(this);
		start.getStart().setPreferredSize(new Dimension(start.getP().getWidth(),50));
//		map=new Map();
//		map.getCairo().addActionListener(this);
//		map.getSparta().addActionListener(this);
//		map.getRome().addActionListener(this);
		attackedLeveL = new JLabel();
		attackerLeveL = new JLabel();
		attackedsoldier = new JLabel();
		attackersoldier = new JLabel();
		AvailableUnits = new ArrayList<>();
		targetUnits = new ArrayList<>();
		autoLOG = new JTextArea();
		event = "";
		image1 = new JLabel();
		image2 = new JLabel();
		images = new JPanel();
		imagesinArmy = new JPanel();
		testimage = new JLabel();
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
		JButton cairo=new JButton("Cairo");
		JButton sparta=new JButton("Sparta");
		JButton rome=new JButton("Rome");
		JPanel p=new JPanel();
		String[][]grid;
		JButton ShowArmy=new JButton("Show Army");
		JButton endTurn=new JButton("End Turn");
		
		JPanel map=new JPanel();
		
		String[]cbox=new String[g.getPlayer().getControlledArmies().size()];
		for (int i = 0; i <g.getPlayer().getControlledArmies().size(); i++) {
			cbox[i]="Army "+(i+1);
		}
		armies=new JComboBox(cbox);
		
		map.setLayout(new BorderLayout());
		grid=new String[][] {{"","","","","","Cairo","","","","Armies",""},
						 	{"","","","","","","","","","",""},
							 {"","","","","","","","","","",""}	,
							 {"","","","","","","","","","",""},
							 {"","Spart","","","","","","","","Rome",""}};			
		p.setLayout(new GridLayout(0,11));	
		ShowArmy.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		armies.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		cairo.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		sparta.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		rome.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < 11; j++) {
				if(grid[i][j].equals("Cairo"))p.add(cairo);
				else if(grid[i][j].equals("Spart"))p.add(sparta);
				else if(grid[i][j].equals("Rome"))p.add(rome);
				else if(grid[i][j].equals("Armies")) {
					JPanel x=new JPanel();
					x.setLayout(new GridLayout(0,1));
					JLabel l=new JLabel("Controlled Armies");
					l.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 18));
					x.add(l);
					x.add(armies);
					
					x.add(ShowArmy);
					p.add(x);
				}
				else {
					JPanel x=new JPanel();
//					x.setBorder(BorderFactory.createLineBorder(Color.black));
					p.add(x);
				}
			}
		}
		JLabel title=new JLabel("Map");
		title.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		title.setHorizontalAlignment(SwingConstants. CENTER);
		map.add(title,BorderLayout.NORTH);
		map.add(p,BorderLayout.CENTER);
		map.setBorder(BorderFactory.createLineBorder(Color.black));
		endTurn.setPreferredSize(new Dimension(view.getWidth(),70));
		endTurn.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		map.revalidate();
		map.repaint();
		view.add(endTurn,BorderLayout.SOUTH);
		view.add(map,BorderLayout.CENTER);
		rome.addActionListener(this);
		cairo.addActionListener(this);
		sparta.addActionListener(this);
		ShowArmy.addActionListener(this);
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
		images.setVisible(true);
		images.setLayout(new GridLayout(2,4));
		images.setPreferredSize(new Dimension(1000,800));
		view.add(images);
		
		JPanel city=new JPanel();
		city.setVisible(true);
		city.setLayout(null);
		city.setPreferredSize(new Dimension(550,800));
		//buildings button -----------
		lbuildings =new JLabel("   Buildings");
		lbuildings.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
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
		Buildings.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		openBuilding=new JButton("open Building");
		openBuilding.setBounds(380,525,170,50);
		openBuilding.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20));
		
		String[]ccbox= {"Market 1500 $","Farm 1000 $","Archery Range 1500 $","Barracks 2000 $","Stable 2500 $"};
		buildcost = new JComboBox(ccbox);
		buildcost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		buildcost.setBounds(10,625,360,50);
		build = new JButton("Build");
		build.setBounds(380,625,170,50);
		build.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		//---------------
		name = new JLabel("   city name :  "+c.getName());
		name.setBounds(0,40,400,100);
		name.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		status = new JLabel("   Status : "+(c.isUnderSiege()?"Besieged":"Not Besieged"));
		status.setBounds(0,140,400,100);
		status.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		turnsUnderSeige=new JLabel("   Turns Under Seige :  "+c.getTurnsUnderSiege());
		turnsUnderSeige.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		turnsUnderSeige.setBounds(0,240,400,100);
		//--------------
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		initiateArmy.setBounds(380,725,170,50);
		defending = new JLabel("   Units ");
		defending.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
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
		units.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		units.setBounds(190,725,180,50);
		
		
		//------------------
		back = new JButton("");
		back.setText("Return to map");
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
//		back.setIcon(new ImageIcon("images/back.png"));
		back.setBounds(10,10,200,50);
		//------------------
		DefendingArmy =new JButton("Defending Army");
		DefendingArmy.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
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
		JButton autoReslove= new JButton("Auto Resolve");
		JButton manualAttack = new JButton("Manual Attack");
		JButton openUnit = new JButton("Open Unit");
		JButton setTarget = new JButton("Set target");
		relocateUnit = new JButton("Relocate unit");
		JButton selectArmy =new JButton("Select Army");
		JButton LaySiege=new JButton("Lay Siege");
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
		autoReslove.addActionListener(this);
		manualAttack.addActionListener(this);
		LaySiege.addActionListener(this);
		selectArmy.addActionListener(this);
		
		
		String cl=selectedArmy.getCurrentLocation();
		if(!cl.equals("onRoad")) {
			City c=null;
			for(City x:g.getAvailableCities())if(x.getName().equals(cl))c=x;
			int m=0;
			if(!selectedArmy.equals(c.getDefendingArmy()))m++;
			for(Army f:g.getPlayer().getControlledArmies())if(f.getCurrentLocation().equals(selectedArmy.getCurrentLocation())&&(!f.equals(selectedArmy)))m++;
			String[] ca=new String[m];
			m=0;
			if(!selectedArmy.equals(c.getDefendingArmy()))ca[m++]=selectedArmy.getCurrentLocation()+ " Army";
			for(Army f:g.getPlayer().getControlledArmies()) {
				if(f.getCurrentLocation().equals(selectedArmy.getCurrentLocation())&&(!f.equals(selectedArmy))) {
					ca[m++]="Army "+(g.getPlayer().getControlledArmies().indexOf(f)+1);
				}
			}
			sorroundingArmy=new JComboBox(ca);
		}
		
		
		
		
		
		
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
		army.setVisible(true);
		back = new JButton();
		back.setText("Return to map");
		back.addActionListener(this);
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		LaySiege.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		openUnit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		setTarget.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		relocateUnit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currentStatus.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currentLocation.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		target.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		distanceToTarget.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		autoReslove.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		manualAttack.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		selectArmy.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		sorroundingArmy.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		sorroundingUnits.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		

		grid = new String[][] { { "Units", "Set target", "Relocate unit" }, { "Army status", "", "" },
				{ "Distance to target", "", "", "" }, { "Army location", "", "Lay Siege" }, { "Target", "", "" },
				{ "back", "autoResolve", "manualAttack" } };
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
					if(!g.getPlayer().getControlledArmies().contains(a)) {
						panel.add(new JPanel());
					}else {
						JPanel y=new JPanel();
						y.setLayout(new FlowLayout());
						targets.setPreferredSize(new Dimension(300,50));
						y.add(targets);
						y.add(setTarget);
						panel.add(y);
					}
				}else if (grid[i][j].equals("Relocate unit")) {
					JPanel p=new JPanel();
					p.setLayout(new GridLayout(0,3));
					JLabel l=new JLabel("Sorrounding Armies");
					l.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
					p.add(l);
					p.add(sorroundingArmy);
					p.add(selectArmy);
					p.add(new JPanel());
					p.add(new JPanel());
					p.add(new JPanel());
					JLabel l2=new JLabel("units of selected army");
					l2.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20));
					p.add(l2);
					p.add(sorroundingUnits);
					p.add(relocateUnit);
					panel.add(p);
				}else if (grid[i][j].equals("Army status"))
					panel.add(currentStatus);
				else if (grid[i][j].equals("Distance to target"))
					panel.add(distanceToTarget);
				else if(grid[i][j].equals("Lay Siege"))
					if(g.getPlayer().getControlledArmies().contains(a))panel.add(LaySiege);
					else panel.add(new JPanel());
				else if (grid[i][j].equals("Army location"))
					panel.add(currentLocation);
				else if (grid[i][j].equals("back"))
					panel.add(back);
				else if (grid[i][j].equals("Target"))
					panel.add(target);
				else if(grid[i][j].equals("autoResolve")) {
					if(g.getPlayer().getControlledArmies().contains(a))panel.add(autoReslove);
					else panel.add(new JPanel());
				}else if(grid[i][j].equals("manualAttack")) {
					if(g.getPlayer().getControlledArmies().contains(a))panel.add(manualAttack);
					else panel.add(new JPanel());
				}else {
					JPanel x = new JPanel();
					panel.add(x);
				}
			}
		}
		JLabel title = new JLabel("Army ");
		title.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
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
		 JLabel name;
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
		 String s = "";
		 if (u instanceof Archer)s="Archer";
		 else if (u instanceof Cavalry)s= "Cavalry";
		 else s= "Infantry";
		name = new JLabel("   Type : " + s);
		name.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		name.setBounds(0,60,300,100);
		leveLabel = new JLabel("   Level :  "+u.getLevel());
		leveLabel.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		leveLabel.setBounds(0,160,300,100);
		Status = new JLabel("   Status :  "+u.getParentArmy().getCurrentStatus());
		Status.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Status.setBounds(0,260,300,100);
		currntsoL = new JLabel("   CurrentSoldierCount :  "+u.getCurrentSoldierCount());
		currntsoL.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currntsoL.setBounds(0,360,300,100);
		back = new JButton("Return to map");
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));	
		back.setBounds(10,10,200,50);
		back.addActionListener(this);
		Unit.add(name);
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
	
	public void drawAutoReslove() {
		drawPlayerBar();
		JButton back=new JButton("Return to map");
		back.setPreferredSize(new Dimension(view.getWidth(),70));
		back.addActionListener(this);
		g.setAutoevent(g.getAutoevent() + ("Inforamtions of the Battle"+"\n"));
		g.setAutoevent(g.getAutoevent() + "'''''\n");
		try {
			g.autoResolve(selectedArmy,attackedArmy );
		} catch (FriendlyFireException e) {
			JOptionPane.showMessageDialog(view,"Friendly Army","Alert",JOptionPane.INFORMATION_MESSAGE);
			drawMap();
		}
		autoLOG.setText(g.getAutoevent());
		autoLOG.setBackground(Color.black);
		autoLOG.setForeground(Color.white);
		autoLOG.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20)) ;
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20)) ;
		view.add(back,BorderLayout.SOUTH);
		view.add(autoLOG);
		view.revalidate();
		view.repaint();
		
	}
	
	public void drawBattleView(Army ar1,Army ar2){
	 drawPlayerBar();
	 JPanel army1Panel;
	 JPanel army2Panel;
	 JPanel logPanel;
	 JPanel infoPanel;
	 JPanel attackPanel;
	 ArrayList<Unit> units1;
	 ArrayList<Unit> units2;	
	 JLabel level;
	 JLabel soldierCount;
     JButton endTurn=new JButton("End Turn");
     JButton attack=new JButton("Attack");
     endTurn.addActionListener(this);
     attack.addActionListener(this);
     AvailableUnits.clear();
     targetUnits.clear();
	 units1 = ar1.getUnits(); units2=ar2.getUnits();
	 endTurn.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
	 attack.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		army1Panel =new JPanel();
		army1Panel.setBackground(Color.green);
		army1Panel.setPreferredSize(new Dimension(300,500));
		army1Panel.setLayout(new FlowLayout());
		army1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		view.add(BorderLayout.WEST,army1Panel);
		view.revalidate();
		view.repaint();
		army2Panel =new JPanel();
		army2Panel.setPreferredSize(new Dimension(300,200));
		army2Panel.setLayout(new FlowLayout());
		army2Panel.setBackground(Color.red);
		army2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		view.add(BorderLayout.EAST,army2Panel);
		view.revalidate();
		view.repaint();
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BorderLayout());
		midPanel.setPreferredSize(new Dimension(500,view.getHeight()));
		view .add(midPanel);
		view.revalidate();
		view.repaint();		
		logPanel =new JPanel();
		logPanel.setPreferredSize(new Dimension(midPanel.getWidth(),midPanel.getHeight()-60));
		JPanel leftlog = new JPanel();
		JPanel rightlog = new JPanel();
		leftlog.setPreferredSize(new Dimension(midPanel.getWidth(),midPanel.getHeight()-60));
		leftlog.setBounds(0,0,midPanel.getWidth()/3,midPanel.getHeight());
		leftlog.setLayout(null);
		leftlog.setBorder(BorderFactory.createLineBorder(Color.black));
		rightlog.setPreferredSize(new Dimension(midPanel.getWidth(),midPanel.getHeight()-60));
		rightlog.setBounds(midPanel.getWidth()*2/3,0,midPanel.getWidth()/3,midPanel.getHeight());
		rightlog.setLayout(null);
		rightlog.setBorder(BorderFactory.createLineBorder(Color.black));
		logPanel.add(leftlog);
		logPanel.add(rightlog);
		image1.setPreferredSize(new Dimension(500,500));
		image1.setBounds(20,300, 400, 400);
		leftlog.add(image1);
		image2.setPreferredSize(new Dimension(500,500));
		image2.setBounds(20,300, 400, 400);
		rightlog.add(image2);
		
		
		textArea=new JTextArea();
		textArea.setPreferredSize(new Dimension(midPanel.getWidth(),midPanel.getHeight()-60));
		textArea.setBackground(Color.black);
		event += "Battle Starts:\n";
		event += "'''''\n";
		textArea.setText(event);
		textArea.setForeground(Color.white);
		textArea.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15)) ;
//		textArea.setBounds(midPanel.getWidth()/3,0,midPanel.getWidth()/3,midPanel.getHeight());
		JScrollPane pa=new JScrollPane(textArea);
		pa.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		pa.setAutoscrolls(true);
		textArea.setLineWrap(true);
	    textArea.setEditable(false);
	    textArea.setVisible(true);
//		pa.VERTICAL_SCROLLBAR_AS_NEEDED
		pa.setBounds(midPanel.getWidth()/3,0,midPanel.getWidth()/3,midPanel.getHeight());
		logPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		logPanel.setLayout(null);
		logPanel.add(pa);
		midPanel.add(logPanel);
		
		
		
		attackPanel =new JPanel();
		attackPanel.setBackground(Color.green);
		attackPanel.setPreferredSize(new Dimension(midPanel.getWidth(),60));
		attackPanel.setLayout(new GridLayout(1,2));
		midPanel.add(attackPanel,BorderLayout.SOUTH);
		attackPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
		for(int i=0;i<units1.size();i++) {
			Unit u = units1.get(i);
			JButton b;
			if(u instanceof Archer)
			    b =new JButton("Archer");
			
			else if(u instanceof Cavalry)
			    b =new JButton("Cavalry");
			else
			    b =new JButton("Infantry");
			b.setPreferredSize(new Dimension(90,50));
			b.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15));
			b.addActionListener(this);
			AvailableUnits.add(b);
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
			b.setPreferredSize(new Dimension(90,50));
			b.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15));
			targetUnits.add(b);
			b.addActionListener(this);
			army2Panel.add(b);
		}
		attackedLeveL.setBounds(20,20,200,50);
		attackedsoldier.setBounds(20,90,200,50);
		attackerLeveL.setBounds(20,20,200,50);
		attackersoldier.setBounds(20,90,200,50);
		leftlog.add(attackerLeveL);
		leftlog.add(attackersoldier);
        rightlog.add(attackedLeveL);
		rightlog.add(attackedsoldier);
		attack.setPreferredSize(new Dimension(460,50));
		endTurn.setPreferredSize(new Dimension(460,50));
		attackPanel.add(attack);
		attackPanel.add(endTurn);		
		view.revalidate();
		view.repaint();
		
		
	}

	public void drawMilitaryBuilding(MilitaryBuilding b ) {
		drawPlayerBar();
		imagesinArmy.setVisible(true);
		imagesinArmy.setLayout(null);
		imagesinArmy.setLayout(new GridLayout());
		imagesinArmy.add(testimage);
		imagesinArmy.setPreferredSize(new Dimension(800,800));
		view.add(imagesinArmy,BorderLayout.EAST);
		JButton back=new JButton("Return to map");
		JButton Upgrade = new JButton("Upgrade");
		JButton Recruit = new JButton("Recruit");
		JLabel Cost = new JLabel("Cost:  "+b.getCost());
		JLabel UpgradeCost = new JLabel("Upgrade cost:  "+b.getUpgradeCost());
		JLabel Level = new JLabel("Level:  "+b.getLevel());
		JLabel Incooldown = new JLabel("In cooldown:  "+b.isCoolDown());
		JLabel RecruitmentCost = new JLabel("Recruitment Cost:  "+b.getRecruitmentCost());
		JLabel CurrentRecruit = new JLabel("Current Recruit:  "+b.getCurrentRecruit());
		JPanel panel;
		String[][] grid;
		
		back.addActionListener(this);
		Upgrade.addActionListener(this);
		Recruit.addActionListener(this);
		
		JPanel MBuilding =new JPanel();
		
		MBuilding.setLayout(new BorderLayout());
//		MBuilding.setBackground(Color.lightGray);
		MBuilding.setMinimumSize(new Dimension(500, 500));
		MBuilding.setPreferredSize(new Dimension(550,800));
		
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Upgrade.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		UpgradeCost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Cost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Level.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Incooldown.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		RecruitmentCost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Recruit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		CurrentRecruit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		
//		Upgrade.setPreferredSize(new Dimension(100,30));
//		Recruit.setPreferredSize(new Dimension(100,30));

		
		grid=new String[][] {{"Cost","","","Upgrade"},{"UpgradeCost","","",""},{"Level","","",""},{"Incooldown","","","Recruit"},
			{"RecruitmentCost","","",""},{"CurrentRecruit","","","Return to map"}};
		
		panel=new JPanel(new GridLayout(6,4));
		panel.setPreferredSize(new Dimension(1050,700));
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<4;j++) {
				if(grid[i][j].equals("Cost"))
					panel.add(Cost);
				else if(grid[i][j].equals("Upgrade"))
					panel.add(Upgrade);
				else if(grid[i][j].equals("Recruit"))
					panel.add(Recruit);
				else if(grid[i][j].equals("UpgradeCost"))
					panel.add(UpgradeCost);
				else if(grid[i][j].equals("Level"))
					panel.add(Level);
				else if(grid[i][j].equals("Incooldown"))
					panel.add(Incooldown);
				else if(grid[i][j].equals("RecruitmentCost"))
					panel.add(RecruitmentCost);
				else if(grid[i][j].equals("CurrentRecruit"))
					panel.add(CurrentRecruit);
				else if(grid[i][j].equals("Return to map"))
					panel.add(back);
				else {
					JPanel x =new JPanel();
					panel.add(x);
				}
			}
		}
//		public void addListener(ActionListener f) {
//			Upgrade.addActionListener(f);
//			Recruit.addActionListener(f);
//		}
		JLabel gj=new JLabel(b instanceof ArcheryRange? "Archery Range":b instanceof Stable? "Stable":"Baracks");
		gj.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		MBuilding.setBorder(BorderFactory.createLineBorder(Color.black));
		MBuilding.add(gj,BorderLayout.NORTH);
		MBuilding.add(panel, BorderLayout.WEST);
		MBuilding.revalidate();
		MBuilding.repaint();
		
		view .add(MBuilding,BorderLayout.CENTER);
		view.repaint();
		view.revalidate();
		
	}
	
	public void drawEconomicBuilding(EconomicBuilding b) {
		drawPlayerBar();
		imagesinArmy.setVisible(true);
		imagesinArmy.setLayout(null);
		imagesinArmy.add(testimage);
		imagesinArmy.setLayout(new GridLayout());
		imagesinArmy.setPreferredSize(new Dimension(800,800));
		view.add(imagesinArmy,BorderLayout.EAST);
		JButton back=new JButton("Return to map");
		JButton Upgrade = new JButton("Upgrade");
		JLabel Cost = new JLabel("Cost:  "+b.getCost());
		JLabel UpgradeCost = new JLabel("Upgrade cost:  "+b.getUpgradeCost());
		JLabel Level = new JLabel("Level:  "+b.getLevel());
		JLabel Incooldown = new JLabel("In cooldown:  "+b.isCoolDown());
		JLabel title=new JLabel();
		String[][] grid;
		
		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Upgrade.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Cost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		UpgradeCost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Level.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Incooldown.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		title.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		
		back.addActionListener(this);
		Upgrade.addActionListener(this);
		
		if(b instanceof Market)title.setText("Market");
		else title.setText("Farm");
		
		
		JPanel Building=new JPanel();
		Building.setLayout(new BorderLayout());
		
		Building.add(title,BorderLayout.NORTH);
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(6,3));
		panel.setPreferredSize(new Dimension(950,700));
		
		grid=new String[][] {{"Cost","","Upgrade"},{"UpgradeCost","",""},{"Level","",""},{"Incooldown","",""},
			{"","","Return to map"},{"","",""}};
			
			for(int i=0;i<grid.length;i++) {
				for(int j=0;j<3;j++) {
					if(grid[i][j].equals("Cost"))
						panel.add(Cost);
					else if(grid[i][j].equals("Upgrade"))
						panel.add(Upgrade);
					else if(grid[i][j].equals("UpgradeCost"))
						panel.add(UpgradeCost);
					else if(grid[i][j].equals("Level"))
						panel.add(Level);
					else if(grid[i][j].equals("Incooldown"))
						panel.add(Incooldown);
					else if(grid[i][j].equals("Return to map"))
						panel.add(back);
					else {
						JPanel x =new JPanel();
						panel.add(x);
					}
				}
			}
			Building.add(panel,BorderLayout.CENTER);
			Building.setBorder(BorderFactory.createLineBorder(Color.black));
			view.add(Building,BorderLayout.CENTER);
			
			view.revalidate();
			view.repaint();
		
		
	}

	public void drawGameOver() {
		view.getContentPane().removeAll();
		String gameOver =(g.getPlayer().getControlledCities().size()==g.getAvailableCities().size()?"You Win":"You are Defeated");
		JLabel l=new JLabel(gameOver);
		JButton playagain=new JButton("Play Again");
		playagain.setPreferredSize(new Dimension(view.getWidth(),70));
		playagain.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
		playagain.addActionListener(this);
		l.setFont(new Font("SansSerif",Font.PLAIN,70));
		if(gameOver.charAt(gameOver.length()-1)=='d') {
			l.setForeground(Color.red);
		}else {
			l.setForeground(Color.blue);
		}
		l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JPanel p=new JPanel();
		p.setLayout(new FlowLayout());
		p.add(l);
		view.add(p,BorderLayout.NORTH);
		view.add(playagain,BorderLayout.SOUTH);
		JPanel p2=new JPanel();
		p2.setBackground(Color.black);
		p.setBackground(Color.black);
		view.add(p2,BorderLayout.CENTER);
		view.revalidate();
		view.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s!=null) {
			if(s.equals("Start")) {
				playSound("sounds/Mouse.wav");
				if(start.getPlayerNameTxt().getText().equals("")) {
					playSound("sounds/Warning.wav");
					JOptionPane.showMessageDialog(start, "write Player Name","Alert",JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					try {
						g=new Game(start.getPlayerNameTxt().getText(),start.getCities().getSelectedItem().toString());
				playSound("sounds/Avengers.wav");
					} catch (IOException e1) {
					}
					this.view=new GameView();
					start.dispose();
					this.drawMap();
				}
			}else if(s.equals("Cairo")) {
				playSound("sounds/Mouse.wav");
				City cairo =null;
				boolean flag=false;
				for(City c:g.getPlayer().getControlledCities())if(c.getName().equals("Cairo")) {
					cairo = c;
					flag = true;
				}
				if (flag){
					drawCity(cairo);
					selectedCity=cairo;
					
				}
				else {
					JOptionPane.showMessageDialog(view, "Not controlled city","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
				
			
			}else if(s.equals("Rome")){
				playSound("sounds/Mouse.wav");
				City Rome =null;
				boolean flag=false;
				for(City c:g.getPlayer().getControlledCities()) {
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
			}else if(s.equals("Sparta")){
				playSound("sounds/Mouse.wav");
				City Sparta =null;
				boolean flag=false;
				for(City c:g.getPlayer().getControlledCities())if(c.getName().equals("Sparta")) {
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
				
				playSound("sounds/Mouse.wav");
				drawMap();
			}else if(s.equals("Defending Army")) {
				
				playSound("sounds/Mouse.wav");
				selectedArmy=selectedCity.getDefendingArmy();
				drawArmy(selectedCity.getDefendingArmy());
			}else if(s.equals("Army")) {
				
				playSound("sounds/Mouse.wav");
				Army a=new Army("onRoad");
				a.getUnits().add(new Archer(1,60,0.3,0.4,0.5));
				drawArmy(a);
			}else if(s.equals("Open Unit")) {
				
				playSound("sounds/Mouse.wav");
				int i=openUnits.getSelectedIndex();
				if(i>-1)
					drawUnit(selectedArmy.getUnits().get(i));
				else
					JOptionPane.showMessageDialog(view, "No units to show","Alert",JOptionPane.INFORMATION_MESSAGE);
			}else if (s.equals("Build")) {	
				int j = buildcost.getSelectedIndex();
				if (j>-1) {
					if (buildcost.getSelectedItem().toString().charAt(0)=='M') {
						try {
							boolean f=false;
							for (EconomicBuilding b:selectedCity.getEconomicalBuildings()) if(b instanceof Market)f=true;
							if(!f) {
								playSound("sounds/Coins.wav");
								JLabel test =new JLabel();
								test.setIcon(new ImageIcon("images/Market.png"));
								images.add(test);
								g.getPlayer().build("Market", selectedCity.getName());
							drawCity(selectedCity);
							}else {
								JOptionPane.showMessageDialog(view, "Already Exist","Alert",JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(view, "Not Enough Gold","Alert",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if (buildcost.getSelectedItem().toString().charAt(0)=='F') {
						try {
							boolean f=false;
							for (EconomicBuilding b:selectedCity.getEconomicalBuildings()) if(b instanceof Farm)f=true;
							if(!f) {
								playSound("sounds/Coins.wav");
								JLabel test =new JLabel();
								test.setIcon(new ImageIcon("images/Farm.png"));
								images.add(test);
								g.getPlayer().build("Farm", selectedCity.getName());
							drawCity(selectedCity);
							}else {
								JOptionPane.showMessageDialog(view, "Already Exist","Alert",JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(view, "Not Enough Gold","Alert",JOptionPane.INFORMATION_MESSAGE);
						}
					
					}else if (buildcost.getSelectedItem().toString().charAt(0)=='A') {
						try {
							boolean f=false;
							for (MilitaryBuilding b:selectedCity.getMilitaryBuildings()) if(b instanceof ArcheryRange)f=true;
							if(!f) {
								playSound("sounds/Coins.wav");
								JLabel test =new JLabel();
								test.setIcon(new ImageIcon("images/Archery2.png"));
								images.add(test);
								g.getPlayer().build("ArcheryRange", selectedCity.getName());
							drawCity(selectedCity);
							}else {
								JOptionPane.showMessageDialog(view, "Already Exist","Alert",JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(view, "Not Enough Gold","Alert",JOptionPane.INFORMATION_MESSAGE);
						}
					}else if (buildcost.getSelectedItem().toString().charAt(0)=='B') {
						try {
							boolean f=false;
							for (MilitaryBuilding b:selectedCity.getMilitaryBuildings()) if(b instanceof Barracks)f=true;
							if(!f) {
								playSound("sounds/Coins.wav");
								JLabel test =new JLabel();
								test.setIcon(new ImageIcon("images/Barracks2.jpg"));
								images.add(test);
								g.getPlayer().build("Barracks", selectedCity.getName());
							drawCity(selectedCity);
							}else {
								JOptionPane.showMessageDialog(view, "Already Exist","Alert",JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(view, "Not Enough Gold","Alert",JOptionPane.INFORMATION_MESSAGE);
						}
					}else if (buildcost.getSelectedItem().toString().charAt(0)=='S') {
						try {
							boolean f=false;
							for (MilitaryBuilding b:selectedCity.getMilitaryBuildings()) if(b instanceof Stable)f=true;
							if(!f) {
								playSound("sounds/Coins.wav");
								JLabel test =new JLabel();
								test.setIcon(new ImageIcon("images/Stable.png"));
								images.add(test);
								g.getPlayer().build("Stable", selectedCity.getName());
							drawCity(selectedCity);
							}else {
								JOptionPane.showMessageDialog(view, "Already Exist","Alert",JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(view, "Not Enough Gold","Alert",JOptionPane.INFORMATION_MESSAGE);
						}
				}
				}else {
					JOptionPane.showMessageDialog(view, "There is no Buildings","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}else if (s.equals("Manual Attack")) {
				playSound("sounds/Mouse.wav");
				City city=null;
				for (City c:g.getAvailableCities()) {
					if(selectedArmy.getCurrentLocation().equals(c.getName())&&(!g.getPlayer().getControlledCities().contains(c))) {
						city=c;
					}
				}
				if(selectedArmy.getCurrentLocation().equals("onRoad")||city==null) {
					JOptionPane.showMessageDialog(view, "Target Not reached yet","Alert",JOptionPane.INFORMATION_MESSAGE);
				}else {
					attackedArmy=city.getDefendingArmy();
					drawBattleView(selectedArmy, attackedArmy);
				}
			}else if (s.equals("open Building")){
				playSound("sounds/Mouse.wav");
				int i=Buildings.getSelectedIndex();
				if (Buildings.getSelectedIndex()>-1) {
					
					if(i<selectedCity.getMilitaryBuildings().size()) {
						 if (Buildings.getSelectedItem().toString().charAt(0)=='B') {
							 testimage.setIcon(new ImageIcon("images/Barracks2.jpg"));
							 
						 }
						 else  if (Buildings.getSelectedItem().toString().charAt(0)=='A') {
							 testimage.setIcon(new ImageIcon("images/Archery2.png"));
							 
						 }
						 else  if (Buildings.getSelectedItem().toString().charAt(0)=='s') {
							 testimage.setIcon(new ImageIcon("images/Stable.png"));
							 
						 }
						 
						drawMilitaryBuilding(selectedCity.getMilitaryBuildings().get(i));
						selectedBuilding=selectedCity.getMilitaryBuildings().get(i);
					}
					else {
						i=i-selectedCity.getMilitaryBuildings().size();
						 if (Buildings.getSelectedItem().toString().charAt(0)=='M') {
							 testimage.setIcon(new ImageIcon("images/Market.png"));
							
						 }
						 else  if (Buildings.getSelectedItem().toString().charAt(0)=='F') {
							 testimage.setIcon(new ImageIcon("images/Farm.png"));
							 
						 }
						drawEconomicBuilding(selectedCity.getEconomicalBuildings().get(i));	
						selectedBuilding=selectedCity.getEconomicalBuildings().get(i);
					}
				}else {
					JOptionPane.showMessageDialog(view, "There is no Buildings","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if (s.equals("Auto Resolve")) {
				playSound("sounds/Mouse.wav");
				City city=null;
				for (City c:g.getAvailableCities()) {
					if(selectedArmy.getCurrentLocation().equals(c.getName())&&(!g.getPlayer().getControlledCities().contains(c))) {
						city=c;
					}
				}
				if(selectedArmy.getCurrentLocation().equals("onRoad")||city==null) {
					JOptionPane.showMessageDialog(view, "Target Not reached yet","Alert",JOptionPane.INFORMATION_MESSAGE);
				}else {
					attackedArmy=city.getDefendingArmy();
					drawAutoReslove();
				}
			}else if(s.equals("Upgrade")){
				playSound("sounds/upgrade.wav");
				try {
					g.getPlayer().upgradeBuilding(selectedBuilding);
					JOptionPane.showMessageDialog(view, "Building has been succesfully upgraded","Alert",JOptionPane.INFORMATION_MESSAGE);
					if(selectedBuilding instanceof MilitaryBuilding) {
						drawMilitaryBuilding((MilitaryBuilding)selectedBuilding);
					}else {
						drawEconomicBuilding((EconomicBuilding)selectedBuilding);
					}
				} catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
					JOptionPane.showMessageDialog(view, e1.getMessage(),"Alert",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}else if(s.equals("End Turn")){
				playSound("sounds/Mouse.wav");
				boolean f=false;
				City c=null;
				for(City x:g.getAvailableCities()) {
					if(x.getTurnsUnderSiege()==3) {
						f=true;
						c=x;
						break;
					}
				}
				if(f) {
					JOptionPane.showMessageDialog(view, c.getName()+" has been sieged for 3 turns you have to Auto or Manual resolve it","Alert",JOptionPane.INFORMATION_MESSAGE);
				}else {
					g.endTurn();
					if(g.isGameOver()) {
						JOptionPane.showMessageDialog(view, "The game is Over ÑæÍ Úáì ÏÇÑßã íáÇ","Alert",JOptionPane.INFORMATION_MESSAGE);
						drawGameOver();
					}else
						drawMap();
				}
			}else if(s.equals("Recruit")) {
				playSound("sounds/Mouse.wav");
				MilitaryBuilding b=(MilitaryBuilding)selectedBuilding;
				try {
					String btype="";
					if(b instanceof ArcheryRange) {
						btype="archer";
					}else if(b instanceof Barracks) {
						btype="infantry";
					}else {
						btype="cavalry";
					}
					g.getPlayer().recruitUnit(btype, selectedCity.getName());
					JOptionPane.showMessageDialog(view, btype+" has been recruited","Alert",JOptionPane.INFORMATION_MESSAGE);
					drawMilitaryBuilding(b);
				} catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(view, e1.getMessage(),"Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(s.equals("Initiate Army")){
				playSound("sounds/Mouse.wav");
				int i=units.getSelectedIndex();
				if(i>-1) {
					g.getPlayer().initiateArmy(selectedCity, selectedCity.getDefendingArmy().getUnits().get(i));
					JOptionPane.showMessageDialog(view, "New army has been succesfully initiated","Alert",JOptionPane.INFORMATION_MESSAGE);
					drawCity(selectedCity);
				}else
					JOptionPane.showMessageDialog(view, "No units to recruit","Alert",JOptionPane.INFORMATION_MESSAGE);
			}else if (s.equals("Play Again")){
				playSound("sounds/Mouse.wav");
				view.dispose();
				new Controller();
			}else if(s.equals("Show Army")){
				playSound("sounds/Mouse.wav");
				int i=armies.getSelectedIndex();
				if(i>-1) {
					selectedArmy=g.getPlayer().getControlledArmies().get(i);
					drawArmy(g.getPlayer().getControlledArmies().get(i));
					
				}else {
					JOptionPane.showMessageDialog(view, "No Controlled Armies yet","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(s.equals("Set target")){
				playSound("sounds/Mouse.wav");
				int i=targets.getSelectedIndex();
				if(i>-1) {
					g.targetCity(selectedArmy, targets.getSelectedItem().toString());
					JOptionPane.showMessageDialog(view, "target has been succesfully set","Alert",JOptionPane.INFORMATION_MESSAGE);
					drawArmy(selectedArmy);
				}else {
					JOptionPane.showMessageDialog(view, "No Targets Availabel","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(s.equals("Lay Siege")){
				playSound("sounds/Mouse.wav");
				City city=null;
				for (City c:g.getAvailableCities()) {
					if(selectedArmy.getCurrentLocation().equals(c.getName())) {
						city=c;
					}
				}
				if(selectedArmy.getCurrentLocation().equals("onRoad")||city==null) {
					JOptionPane.showMessageDialog(view, "Target Not reached yet","Alert",JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
						g.getPlayer().laySiege(selectedArmy, city);
						drawArmy(selectedArmy);
					} catch (TargetNotReachedException | FriendlyCityException e1) {
						JOptionPane.showMessageDialog(view, e1.getMessage(),"Alert",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else if(s.equals("Select Army")){
				int i=sorroundingArmy.getSelectedIndex();
				if(i>-1) {
				doneselect=true;
				relocateUnit.setEnabled(true);
				String v=sorroundingArmy.getSelectedItem().toString();
				toBeOpen=null;
				if(v.charAt(v.length()-1)=='y') {
					for(City c:g.getAvailableCities()) {
						if(v.charAt(0)==c.getName().charAt(0))toBeOpen=c.getDefendingArmy();
					}
				}else {
					toBeOpen=g.getPlayer().getControlledArmies().get(Integer.parseInt(v.charAt(v.length()-1)+"")-1);
				}
				String[]su=new String[toBeOpen.getUnits().size()];
				int m=0;
				for(Unit u:toBeOpen.getUnits()) {
					if(u instanceof Archer)su[m++]="Archer lv."+u.getLevel();
					if(u instanceof Cavalry)su[m++]="Cavalry lv."+u.getLevel();
					if(u instanceof Infantry)su[m++]="Infantry lv."+u.getLevel();
				}
				sorroundingUnits=new JComboBox(su);
				drawArmy(selectedArmy);
				}
				else {
					JOptionPane.showMessageDialog(view, "No selected Army","Alert",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(s.equals("Relocate unit")){
				if(!doneselect) {
					
				}else {
					try {
						selectedArmy.relocateUnit(toBeOpen.getUnits().get(sorroundingUnits.getSelectedIndex()));
						drawArmy(selectedArmy);
						JOptionPane.showMessageDialog(view, "Relocate done correctly","Alert",JOptionPane.INFORMATION_MESSAGE);
					} catch (MaxCapacityException e1) {
						JOptionPane.showMessageDialog(view, "MaxCapacity","Alert",JOptionPane.INFORMATION_MESSAGE);
					}
					doneselect=false;
					relocateUnit.setEnabled(false);
				}
			}else {
				JButton b = (JButton) e.getSource();
				if (b.getActionCommand().equals("Attack")) {
					playSound("sounds/Sword.wav");
					if (selectedButton1==null) {
						JOptionPane.showMessageDialog(view, "please select attacking unit","Alert",JOptionPane.INFORMATION_MESSAGE);
						
					}
					if (selectedButton2==null) {
						JOptionPane.showMessageDialog(view, "please select attacked unit","Alert",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
					
					int index1 = AvailableUnits.indexOf(selectedButton1);
					int index2 = targetUnits.indexOf(selectedButton2);  
					Unit u =selectedArmy.getUnits().get(index1);
					String w;
					 if (u instanceof Archer)w="Archer";
					 else if (u instanceof Cavalry)w= "Cavalry";
					 else w= "Infantry";
					Unit gg =attackedArmy.getUnits().get(index2);
						String t;
						 if (gg instanceof Archer)t="Archer";
						 else if (gg instanceof Cavalry)t= "Cavalry";
						 else t= "Infantry";
					try {
						u.attack(gg);
						event += g.getPlayer().getName()+"\n"+"-" + w +" "+"attacked"+"  "+ t + "\n" ;
						event += "Remaining soldiers : "+ " "+ gg.getCurrentSoldierCount()+"\n";
						textArea.setText(event);
						textArea.setForeground(Color.white);
						textArea.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15)) ;
						selectedButton1 = null;
						selectedButton2 = null;
						if(attackedArmy.getUnits().size()==0) {
							JOptionPane.showMessageDialog(view, "You Win","Alert",JOptionPane.INFORMATION_MESSAGE);
							g.occupy(selectedArmy,attackedArmy.getCurrentLocation());
							drawMap();
						}else {
							drawBattleView(selectedArmy, attackedArmy);
							int rn1= new Random().nextInt(attackedArmy.getUnits().size());
							int rn2=new Random().nextInt(selectedArmy.getUnits().size());
							u=attackedArmy.getUnits().get(rn1);
							gg=selectedArmy.getUnits().get(rn2);
							u.attack(gg);
							if (u instanceof Archer)w="Archer";
							 else if (u instanceof Cavalry)w= "Cavalry";
							 else w= "Infantry";
								 if (gg instanceof Archer)t="Archer";
								 else if (gg instanceof Cavalry)t= "Cavalry";
								 else t= "Infantry";
							event += "PC"+"\n"+"-" + w +" "+"attacked"+"  "+ t + "\n" ;
							event += "Remaining soldiers : "+ " "+ gg.getCurrentSoldierCount()+"\n";
							textArea.setText(event);
							textArea.setForeground(Color.white);
							textArea.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15)) ;
							if(selectedArmy.getUnits().size()==0) {
								JOptionPane.showMessageDialog(view, "You Lose","Alert",JOptionPane.INFORMATION_MESSAGE);
								drawMap();
							}else
								drawBattleView(selectedArmy, attackedArmy);
						}	
					} catch (FriendlyFireException e1) {
						JOptionPane.showMessageDialog(view, "Friendly Unit","Alert",JOptionPane.INFORMATION_MESSAGE);
						drawMap();
					}	 
					
					}
				}
				else {
					if (!b.getActionCommand().equals("Attack") && AvailableUnits.contains(b)) {
						
					selectedButton1=b;
					attackerLeveL.setText("Level :"+"  " +selectedArmy.getUnits().get(AvailableUnits.indexOf(b)).getLevel()+"\n");
					//-----
					if (b.getActionCommand().equals("Archer")) image1.setIcon(new ImageIcon("images/Archer.jpg"));
					else if (b.getActionCommand().equals("Cavalry")) {
						image1.setIcon(new ImageIcon("images/Cavalry.jpg"));
						
					}
					else image1.setIcon(new ImageIcon("images/Infantry.jpg"));
					//-----------
					attackersoldier.setText("Soldier count :"+"  " +selectedArmy.getUnits().get(AvailableUnits.indexOf(b)).getCurrentSoldierCount()+"\n");
					attackerLeveL.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20));
					attackersoldier.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20));
					
					
					}
					 if  (!b.getActionCommand().equals("Attack") && targetUnits.contains(b)) {
					selectedButton2 = b;
					//-----
					if (b.getActionCommand().equals("Archer")) image2.setIcon(new ImageIcon("images/Archer.jpg"));
					else if (b.getActionCommand().equals("Cavalry")) {
						image2.setIcon(new ImageIcon("images/Cavalry.jpg"));
					}
					else image2.setIcon(new ImageIcon("images/Infantry.jpg"));
					//-----------
					attackedLeveL.setText("Level :"+"  " +attackedArmy.getUnits().get(targetUnits.indexOf(b)).getLevel()+"\n");
					attackedsoldier.setText("Soldier count :"+"  " +attackedArmy.getUnits().get(targetUnits.indexOf(b)).getCurrentSoldierCount()+"\n");
					attackedLeveL.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20));
					attackedsoldier.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 20));}
				}
				
			}
		
		
		}

		}
	
	public void playSound (String filepath)  {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		new Controller();
	}
}
