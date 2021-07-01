package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import units.Army;

public class Map extends JPanel {
	private JButton cairo=new JButton("Cairo");
	private JButton sparta=new JButton("Sparta");
	private JButton rome=new JButton("Rome");
	private JPanel p=new JPanel();
	private String[][]grid;
	private JButton ShowArmy=new JButton("Show Army");
	private JComboBox armies;
	private ArrayList<Army> ca=new ArrayList<Army>();
	
	public JButton getCairo() {
		return cairo;
	}
	public void setCairo(JButton cairo) {
		this.cairo = cairo;
	}
	public JButton getSparta() {
		return sparta;
	}
	public void setSparta(JButton sparta) {
		this.sparta = sparta;
	}
	public JButton getRome() {
		return rome;
	}
	public void setRome(JButton rome) {
		this.rome = rome;
	}
	public JPanel getP() {
		return p;
	}
	public void setP(JPanel p) {
		this.p = p;
	}
	public Map() {
//		setLayout(null);
//		setBounds(25,100, 1300,800);
//		cairo.setBounds(600,50,100,100);
//		sparta.setBounds(300,650,100,100);
//		rome.setBounds(900,650,100,100);
		String[]cbox=new String[ca.size()];
		for (int i = 0; i <ca.size(); i++) {
			cbox[i]="Army "+(i+1);
		}
		armies=new JComboBox(cbox);
		
		setLayout(new BorderLayout());
		grid=new String[][] {{"","","","","","Cairo","","","","Armies",""},
						 	{"","","","","","","","","","",""},
							 {"","","","","","","","","","",""}	,
							 {"","","","","","","","","","",""},
							 {"","Spart","","","","","","","","Rome",""}};			
		p.setLayout(new GridLayout(0,11));	
		ShowArmy.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		armies.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		cairo.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		sparta.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		rome.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < 11; j++) {
				if(grid[i][j].equals("Cairo"))p.add(cairo);
				else if(grid[i][j].equals("Spart"))p.add(sparta);
				else if(grid[i][j].equals("Rome"))p.add(rome);
				else if(grid[i][j].equals("Armies")) {
					JPanel x=new JPanel();
					x.setLayout(new GridLayout(0,1));
					x.add(armies);
					x.add(new JPanel());
					x.add(ShowArmy);
					p.add(x);
				}
				else {
					JPanel x=new JPanel();
//					x.setBorder(BorderFactory.createLineBorder(Color.black));
					p.add(x);
				}
			}
		}
		JLabel title=new JLabel("Map");
		title.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 22));
		title.setHorizontalAlignment(SwingConstants. CENTER);
		add(title,BorderLayout.NORTH);
		add(p,BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public String[][] getGrid() {
		return grid;
	}
	public void setGrid(String[][] grid) {
		this.grid = grid;
	}
//	public static void main(String[] args) {
//		JFrame x=new JFrame();
//		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Map m=new Map();
//		x.setLayout(null);
//		m.setBounds(50,100, 1300,800);
//		m.setBackground(Color.gray);
//		x.add( m);
//		JButton b=new JButton("hh");
//		b.setBounds(1350,100,550,800);
//		x.add(b,BorderLayout.EAST);
//		x.setVisible(true);
//		x.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		x.revalidate();
//		x.repaint();
//		
//	}
	public JButton getShowArmy() {
		return ShowArmy;
	}
	public void setShowArmy(JButton showArmy) {
		ShowArmy = showArmy;
	}
	public JComboBox getArmies() {
		return armies;
	}
	public void setArmies(JComboBox armies) {
		this.armies = armies;
	}
	public ArrayList<Army> getCa() {
		return ca;
	}
	public void setCa(ArrayList<Army> ca) {
		this.ca = ca;
	}
}
