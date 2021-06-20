package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Ignore;

import buildings.*;
import engine.City;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class CityView extends JPanel {
	private JLabel name;
	private JButton openBuilding;
	private JLabel lbuildings;
	private JComboBox Buildings;
	private JComboBox buildcost;
	private JButton build;
	private JLabel status;
	private JLabel turnsUnderSeige;
	private JButton initiateArmy;
	private JButton back;
	private JLabel defending;
	private JComboBox units;
	private City c;
	public CityView(City c) {
		this.c=c;
		this.setBounds(900,100,550,800);
		this.setVisible(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(550,800));
		//buildings button -----------
		lbuildings =new JLabel("   Buildings");
		lbuildings.setFont(new Font("Forte", Font.BOLD, 19));
		lbuildings.setBounds(0,400,180,100);
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
		Buildings.setBounds(190,425,180,50);
		Buildings.setFont(new Font("Forte", Font.BOLD, 19));
		openBuilding=new JButton("open Building");
		openBuilding.setBounds(380,425,170,50);
		openBuilding.setFont(new Font("Forte", Font.BOLD, 19));
		
		//build button---------------------------
		String [] ccbox= new String[c.getEconomicalBuildings().size()+c.getMilitaryBuildings().size()];
		int j = 0 ;
		for (Building b:c.getMilitaryBuildings()) {
			if(b instanceof ArcheryRange)ccbox[j]="Archery Range :  "+ b.getCost() + "$";
			if(b instanceof Barracks)ccbox[j]="Barracks :  "+ b.getCost()+"$";
			if(b instanceof Stable)ccbox[j]="stable :  "+b.getCost()+"$";
		    j++;
		}
		for(Building b:c.getEconomicalBuildings()) {
			if(b instanceof Farm)ccbox[j]="Farm :  "+b.getCost()+"$" ;
			if(b instanceof Market)ccbox[j]="Market :  "+b.getCost()+"$";
			j++;
		}
		buildcost = new JComboBox(ccbox);
		buildcost.setFont(new Font("Forte", Font.BOLD, 19));
		buildcost.setBounds(10,525,360,50);
		build = new JButton("Build");
		build.setBounds(380,525,170,50);
		build.setFont(new Font("Forte", Font.BOLD, 19));
		//---------------
		name = new JLabel("   city name :");
		name.setBounds(0,40,200,100);
		name.setFont(new Font("Forte", Font.BOLD, 19));
		status = new JLabel("   Status : ");
		status.setBounds(0,140,100,100);
		status.setFont(new Font("Forte", Font.BOLD, 19));
		turnsUnderSeige=new JLabel("   Turns Under Seige : ");
		turnsUnderSeige.setFont(new Font("Forte", Font.BOLD, 19));
		turnsUnderSeige.setBounds(0,240,300,100);
		//--------------
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setFont(new Font("Forte", Font.BOLD, 19));
		initiateArmy.setBounds(380,625,170,50);
		defending = new JLabel("   Units ");
		defending.setFont(new Font("Forte", Font.BOLD, 19));
		defending.setBounds(0,600,180,100);
		String [] c3box= new String[c.getDefendingArmy().getUnits().size()];
		int x = 0 ;
		for (Unit u : c.getDefendingArmy().getUnits()) {
			if(u instanceof Archer )c3box[x]= "Archer";
			if (u instanceof Cavalry)c3box[x]="Cavalry";
			if (u instanceof Infantry) c3box[x]= "Infantry";
		}
		units = new JComboBox(c3box);
		units.setFont(new Font("Forte", Font.BOLD, 19));
		units.setBounds(190,625,180,50);
		
		
		//------------------
		back = new JButton();
		back.setIcon(new ImageIcon("C://back.PNG"));
		back.setBounds(10,10,50,50);
		//------------- add
		this.add(units);
		this.add(defending);
		this.add(buildcost);
		add(Buildings);
		add(lbuildings);
		add(openBuilding);
		this.add(name);
		this.add(status);
		this.add(turnsUnderSeige);
		this.add(build);
		this.add(initiateArmy);
		this.add(back);
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.revalidate();
		this.repaint();
		
	}
	
	public void setName(JLabel name) {
		this.name = name;
	}
	public JButton getBuild() {
		return build;
	}
	public void setBuild(JButton build) {
		this.build = build;
	}
	public JLabel getStatus() {
		return status;
	}
	public void setStatus(JLabel status) {
		this.status = status;
	}
	public JLabel getTurnsUnderSeige() {
		return turnsUnderSeige;
	}
	public void setTurnsUnderSeige(JLabel turnsUnderSeige) {
		this.turnsUnderSeige = turnsUnderSeige;
	}
	public JButton getInitiateArmy() {
		return initiateArmy;
	}
	public void setInitiateArmy(JButton initiateArmy) {
		this.initiateArmy = initiateArmy;
	}
	public static void main(String[] args) {
		JFrame x =new JFrame();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		City c=new City("Cairo");
		Army  a = new Army("gad");
		Archer b = new Archer(1, 30, 0.6, 0.7, 0.5);
		a.getUnits().add(b);
		c.setDefendingArmy(a);
		c.getEconomicalBuildings().add(new Market());
		CityView d =new CityView(c);
		x.setLayout(null);
		x.add(d);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
