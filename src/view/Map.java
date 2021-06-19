package view;

import java.awt.*;

import javax.swing.*;

public class Map extends JPanel {
	private JButton cairo=new JButton("Cairo");
	private JButton sparta=new JButton("Sparta");
	private JButton rome=new JButton("Rome");
	
	public Map() {
		setLayout(null);
		setBounds(25,100, 1300,800);
		cairo.setBounds(600,50,100,100);
		sparta.setBounds(300,650,100,100);
		rome.setBounds(900,650,100,100);
		add(rome);
		add(sparta);
		add(cairo);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public static void main(String[] args) {
		JFrame x=new JFrame();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Map m=new Map();
		x.setLayout(null);
		m.setBounds(50,100, 1300,800);
		m.setBackground(Color.gray);
		x.add( m);
		JButton b=new JButton("hh");
		b.setBounds(1350,100,550,800);
		x.add(b,BorderLayout.EAST);
		x.setVisible(true);
		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
		x.revalidate();
		x.repaint();
		
	}
}
