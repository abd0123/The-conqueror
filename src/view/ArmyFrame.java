package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.City;

public class ArmyFrame extends JFrame {
	JPanel jp;
	public ArmyFrame() {
			setTitle("The Conqueror");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setMinimumSize(new Dimension(500,500));
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setLayout(new BorderLayout());
			this.setVisible(true);
			jp = new Armyview();
			this.add(jp,BorderLayout.EAST);
			this.revalidate();
			this.repaint();
	}
		
	}


