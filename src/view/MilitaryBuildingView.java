package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MilitaryBuildingView extends JPanel{
	
	private JButton Upgrade = new JButton("Upgrade");
	private JButton Recruit = new JButton("Recruit");
	private JLabel Cost = new JLabel("Cost");
	private JLabel UpgradeCost = new JLabel("Upgrade cost");
	private JLabel Level = new JLabel("Level");
	private JLabel Incooldown = new JLabel("In cooldown");
	private JLabel RecruitmentCost = new JLabel("Recruitment Cost");
	private JLabel CurrentRecruit = new JLabel("Current Recruit");
	
	public MilitaryBuildingView() {
		setLayout(null);
		setPreferredSize(new Dimension(550,800));
		
		this.setBounds(1350,100, 500,800);
		this.setBackground(Color.orange);
		this.setVisible(true);
		Upgrade.setBounds(350, 150, 200, 100);	
		
	}

	public static void main(String[] args) {

	}

}
