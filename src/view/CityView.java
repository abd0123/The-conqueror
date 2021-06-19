package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	private JButton back;
	public CityView() {
		this.setBounds(1350,100,550,800);
		this.setVisible(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(550,800));
		buildings=new JButton("Buildings");
		buildings.setFont(new Font("Forte", Font.BOLD, 19));
		buildings.setBounds(0,420,550,100);
		name = new JLabel("   city name :");
		name.setBounds(0,40,200,100);
		name.setFont(new Font("Forte", Font.BOLD, 19));
		build = new JButton("Build");
		build.setBounds(0,560,550,100);
		build.setFont(new Font("Forte", Font.BOLD, 19));
		status = new JLabel("   Status : ");
		status.setBounds(0,140,100,100);
		status.setFont(new Font("Forte", Font.BOLD, 19));
		turnsUnderSeige=new JLabel("   Turns Under Seige : ");
		turnsUnderSeige.setFont(new Font("Forte", Font.BOLD, 19));
		turnsUnderSeige.setBounds(0,240,300,100);
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setFont(new Font("Forte", Font.BOLD, 19));
		initiateArmy.setBounds(0,700,550,100);
		back = new JButton();
		back.setIcon(new ImageIcon("C://back.PNG"));
		back.setBounds(10,10,50,50);
		this.add(name);
		this.add(status);
		this.add(turnsUnderSeige);
		this.add(buildings);
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
