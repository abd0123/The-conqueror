package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame {
	private Map map;
	private ArrayList<JPanel> panels;
	private ArrayList<Object> o;
	
	public GameView(){
		o=new ArrayList<Object>();
		panels=new ArrayList<JPanel>();
		setTitle("The Conqueror");
		add(new CityView());
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
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public ArrayList<JPanel> getPanels() {
		return panels;
	}
	public void setPanels(ArrayList<JPanel> panels) {
		this.panels = panels;
	}
	public ArrayList<Object> getO() {
		return o;
	}
	public void setO(ArrayList<Object> o) {
		this.o = o;
	}
}
