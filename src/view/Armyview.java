package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class Armyview extends JPanel {

	private JButton Units = new JButton("Units");
	private JButton setTarget = new JButton("Set target");
	private JButton relocateUnit = new JButton("Relocate unit");
	private JLabel currentStatus = new JLabel("Army status");
	private JLabel distanceToTarget= new JLabel("Distance to target");
	private JLabel target= new JLabel("Target");
	private JLabel currentLocation= new JLabel("Army location");
	
	public Armyview() {
		setLayout(null);
		setPreferredSize(new Dimension(550,800));
		
		this.setBounds(1350,100, 500,800);
		this.setBackground(Color.orange);
		this.setVisible(true);
		Units.setBounds(350, 150, 200, 100);
		Units.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		this.add(Units);
		setTarget.setBounds(700, 150, 200, 100);
		setTarget.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		this.add(setTarget);
		relocateUnit.setBounds(1050, 150, 200, 100);
		relocateUnit.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		this.add(relocateUnit);
		currentStatus.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		currentLocation.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		target.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		distanceToTarget.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
		currentStatus.setBounds(750, 250, 150, 50);
		currentLocation.setBounds(750, 350, 150, 50);
		target.setBounds(750, 450, 150, 50);
		distanceToTarget.setBounds(750, 550, 200, 50);
		this.add(currentStatus);
		this.add(currentLocation);
		this.add(target);
		this.add(distanceToTarget);

		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public static void main(String[] args) {
		
		JFrame x =new JFrame();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Armyview a =new Armyview();
		x.add(a);


		
	}

}
