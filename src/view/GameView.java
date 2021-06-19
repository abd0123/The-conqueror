package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame {
	private Map map;
	private ArrayList<JPanel> panels;
	
	public GameView(){
		panels=new ArrayList<JPanel>();
		setTitle("The Conqueror");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		map=new Map();
		add(map);
		setVisible(true);
		revalidate();
		repaint();
	}
	public static void main(String[] args) {
		new GameView();
	}
}
