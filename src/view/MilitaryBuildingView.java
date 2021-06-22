package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	private JPanel panel;
	private String[][] grid;
	
	public MilitaryBuildingView() {
		
		setLayout(new BorderLayout());
		this.setBackground(Color.lightGray);
		setMinimumSize(new Dimension(500, 500));
		setPreferredSize(new Dimension(550,800));
		
		Upgrade.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		UpgradeCost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Cost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Level.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Incooldown.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		RecruitmentCost.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		Recruit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		CurrentRecruit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		
		Upgrade.setPreferredSize(new Dimension(100,30));
		Recruit.setPreferredSize(new Dimension(100,30));

		
		grid=new String[][] {{"Cost","","",""},{"UpgradeCost","","","Upgrade"},{"Level","","",""},{"Incooldown","","",""},
			{"RecruitmentCost","","","Recruit"},{"CurrentRecruit","","",""}};
		
		panel=new JPanel(new GridLayout(6,4));
		panel.setPreferredSize(new Dimension(650,700));
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
		
		add(panel, BorderLayout.EAST);
		revalidate();
		repaint();
		
	}

	public static void main(String[] args) {

		JFrame x =new JFrame();
		x.setDefaultCloseOperation(x.EXIT_ON_CLOSE);
		x.setMinimumSize(new Dimension(500,500));
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
		MilitaryBuildingView m =new MilitaryBuildingView();
		x.add(m);
		x.setVisible(true);
		x.revalidate();
		x.repaint();
	}

}
