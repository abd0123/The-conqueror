package view;

import java.awt.*;

import javax.swing.*;

public class StartWindow extends JFrame {
	JButton b=new JButton("Start");
	TextField t=new TextField();
	JLabel l=new JLabel("Player Name");
	JLabel l2=new JLabel("Player City");
	JComboBox c=new JComboBox(new String[] {"Cairo","Sparta","Rome"});
	public StartWindow() {
		setTitle("The Conqueror");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		l.setFont(new Font("Player Name",Font.PLAIN,20));
		t.setFont(new Font("",Font.PLAIN,20));
		c.setFont(new Font("",Font.PLAIN,20));
		l2.setFont(new Font("",Font.PLAIN,20));
		l.setBounds(670,300,150,30);
		t.setBounds(875, 300, 200,30);
		b.setBounds(875, 470, 200,40);
		l2.setBounds(670,350,150,30);
		c.setBounds(875, 350, 200,30);
		setLayout(null);
		add(l2);
		add(c);
		add(l);
		add(t);
		add(b);
		setVisible(true);
		revalidate();
		repaint();
	}
	public static void main(String[] args) {
		new StartWindow();
	}
}
