package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnitFrame extends JFrame {
	JPanel jp;
	public UnitFrame() {
		setTitle("The Conqueror");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		jp = new UnitView();
		this.add(jp,BorderLayout.EAST);
		this.revalidate();
		this.repaint();
	}

}
