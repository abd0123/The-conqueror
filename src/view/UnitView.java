package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import units.Status;

public class UnitView extends JPanel{
	private JLabel leveLabel;
	private JLabel Status;
	private JLabel currntsoL;
	private JButton autoresolve;
	private JButton manualAttack;
	public UnitView() {
		this.setBounds(1350,100,550,800);
		this.setVisible(true);
		this.setLayout(new GridLayout(0,1,4,10));
		this.setPreferredSize(new Dimension(550,800));
		setBorder(BorderFactory.createLineBorder(Color.black));
		leveLabel = new JLabel("   Level : ");
		leveLabel.setFont(new Font("Arial", Font.BOLD, 19));
		Status = new JLabel("   Status : ");
		Status.setFont(new Font("Arial", Font.BOLD, 19));
		currntsoL = new JLabel("   CurrentSoldierCount : ");
		currntsoL.setFont(new Font("Arial", Font.BOLD, 19));
		autoresolve = new JButton("Auto Resolve");
		autoresolve.setFont(new Font("Arial", Font.BOLD, 19));
		manualAttack = new JButton("Manual Attack");
		manualAttack.setFont(new Font("Arial", Font.BOLD, 19));
		this.add(leveLabel);
		this.add(Status);
		this.add(currntsoL);
		this.add(autoresolve);
		this.add(manualAttack);
	}
	public static void main(String[] args) {
		JFrame x =new JFrame();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UnitView a =new 
				UnitView();
		x.setLayout(null);
		x.add(a);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	

}
