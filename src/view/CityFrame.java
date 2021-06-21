package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import engine.City;

public class CityFrame extends JFrame {
	JPanel jp;
	public CityFrame(City c) {
		setTitle("The Conqueror");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		jp = new CityView(c);
		this.add(jp,BorderLayout.EAST);
		this.revalidate();
		this.repaint();
		
	}
public static void main(String[] args) {
	
}
}
