package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Armyview extends JPanel {

	private JButton Units;
	private JButton setTarget;
	private JButton relocateUnit;
	private JLabel currentStatus;
	private JLabel distanceToTarget;
	private JLabel target;
	private JLabel currentLocation;
	
	public Armyview() {
		setLayout(null);
		setPreferredSize(new Dimension(550,800));
		this.setBounds(1350,100, 500,800);
		this.setBackground(Color.blue);
		this.setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public static void main(String[] args) {
		

		JFrame x =new JFrame();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Armyview a =new Armyview();
		x.setLayout(null);
		x.add(a);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

}
