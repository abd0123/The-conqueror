package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CityView extends JPanel {
	private JLabel name;
	private JButton buildings;
	private JButton build;
	private JLabel status;
	private JLabel turnsUnderSeige;
	private JButton initiateArmy;
	public CityView() {
		this.setBounds(1030,100, 500,650);
		this.setVisible(true);
		this.setLayout(new GridLayout(0,1));
		this.setPreferredSize(new Dimension(500,800));
		this.setBackground(Color.red);
		buildings=new JButton("Buildings");
		name = new JLabel("city name :");
		build = new JButton("Build");
		status = new JLabel("Status : ");
		turnsUnderSeige=new JLabel("Turns Under Seige : ");
		initiateArmy = new JButton("Initiate Army");
		this.add(name);
		this.add(status);
		this.add(turnsUnderSeige);
		this.add(buildings);
		this.add(build);
		this.add(initiateArmy);
		this.revalidate();
		this.repaint();
		
	}
	
	public void setName(JLabel name) {
		this.name = name;
	}
	public JButton getBuildings() {
		return buildings;
	}
	public void setBuildings(JButton buildings) {
		this.buildings = buildings;
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
		CityView a =new 
				  CityView();
		x.setLayout(null);
		x.add(a);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
