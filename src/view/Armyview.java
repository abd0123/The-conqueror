package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class Armyview extends JPanel {

	private JButton Units = new JButton("Units");
	private JButton setTarget = new JButton("Set target");
	private JButton relocateUnit = new JButton("Relocate unit");
	private JLabel currentStatus = new JLabel("Current status: ");
	private JLabel distanceToTarget = new JLabel("Distance to target: ");
	private JLabel target = new JLabel("Target: ");
	private JLabel currentLocation = new JLabel("Current location: ");
	private String[][] grid;
	private JPanel panel = new JPanel();
	private JButton back;

	public Armyview() {
		setLayout(new BorderLayout());
		this.setBackground(Color.lightGray);
		setMinimumSize(new Dimension(500, 500));

		back = new JButton(new ImageIcon("back.jpg"));
//		back.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		this.setVisible(true);
		Units.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		setTarget.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		relocateUnit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currentStatus.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		currentLocation.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		target.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		distanceToTarget.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));

		grid = new String[][] { { "Units", "Set target", "Relocate unit" }, { "Army status", "", "" },
				{ "Distance to target", "", "", "" }, { "Army location", "", "" }, { "Target", "", "" },
				{ "back", "", "" } };
		panel.setLayout(new GridLayout(0, 3));
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j].equals("Units"))
					panel.add(Units);
				else if (grid[i][j].equals("Set target"))
					panel.add(setTarget);
				else if (grid[i][j].equals("Relocate unit"))
					panel.add(relocateUnit);
				else if (grid[i][j].equals("Army status"))
					panel.add(currentStatus);
				else if (grid[i][j].equals("Distance to target"))
					panel.add(distanceToTarget);
				else if (grid[i][j].equals("Army location"))
					panel.add(currentLocation);
				else if (grid[i][j].equals("back"))
					panel.add(back);
				else if (grid[i][j].equals("Target"))
					panel.add(target);

				else {
					JPanel x = new JPanel();
					panel.add(x);
				}
			}
		}
		JLabel title = new JLabel("Army ");
		title.setFont(new Font("Forte", Font.ROMAN_BASELINE, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public static void main(String[] args) {

		JFrame x = new JFrame();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Armyview av = new Armyview();
		x.setLayout(null);
		av.setBounds(900, 100, 600, 700);
		av.setBackground(Color.orange);
		x.add(av);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
		x.revalidate();
		x.repaint();

	}

}
