package view;

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

import units.Status;

public class UnitView extends JPanel{
	private JLabel leveLabel;
	private JLabel Status;
	private JLabel currntsoL;
	private JButton autoresolve;
	private JButton manualAttack;
	private JButton back;
	public UnitView() {
		this.setBounds(1350,100,550,800);
		this.setVisible(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(550,800));
		setBorder(BorderFactory.createLineBorder(Color.black));
		leveLabel = new JLabel("   Level : ");
		leveLabel.setFont(new Font("Arial", Font.BOLD, 19));
		leveLabel.setBounds(0,60,200,100);
		Status = new JLabel("   Status : ");
		Status.setFont(new Font("Arial", Font.BOLD, 19));
		Status.setBounds(0,160,100,100);
		currntsoL = new JLabel("   CurrentSoldierCount : ");
		currntsoL.setFont(new Font("Arial", Font.BOLD, 19));
		currntsoL.setBounds(0,260,300,100);
		autoresolve = new JButton("Auto Resolve");
		autoresolve.setFont(new Font("Arial", Font.BOLD, 19));
		autoresolve.setBounds(0,560,550,100);
		manualAttack = new JButton("Manual Attack");
		manualAttack.setFont(new Font("Arial", Font.BOLD, 19));
		manualAttack.setBounds(0, 700, 550, 100);
		back = new JButton();
		back.setIcon(new ImageIcon("C://back.PNG"));
		back.setBounds(10,10,50,50);
		this.add(leveLabel);
		this.add(Status);
		this.add(currntsoL);
		this.add(autoresolve);
		this.add(manualAttack);
		this.add(back);
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
