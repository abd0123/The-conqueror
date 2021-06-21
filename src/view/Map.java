package view;

import java.awt.*;

import javax.swing.*;

public class Map extends JPanel {
	private JButton cairo=new JButton("Cairo");
	private JButton sparta=new JButton("Sparta");
	private JButton rome=new JButton("Rome");
	private JPanel p=new JPanel();
	private String[][]grid;
	private JButton Ar;
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
		setLayout(new BorderLayout());
		grid=new String[][] {{"","","","","","Cairo","","","","",""},
						 	{"","","","","","","","","","",""},
							 {"","","Army","","","","","","","",""}	,
							 {"","","","","","","","","","",""},
							 {"","Spart","","","","","","","","Rome",""}};			
		p.setLayout(new GridLayout(0,11));		 	
		cairo.setFont(new Font("Forte", Font.BOLD, 19));
		sparta.setFont(new Font("Forte", Font.BOLD, 19));
		rome.setFont(new Font("Forte", Font.BOLD, 19));
		 Ar=new JButton("Army");
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < 11; j++) {
				if(grid[i][j].equals("Cairo"))p.add(cairo);
				else if(grid[i][j].equals("Spart"))p.add(sparta);
				else if(grid[i][j].equals("Rome"))p.add(rome);
				else if(grid[i][j].equals("Army"))p.add(Ar);
				else {
					JPanel x=new JPanel();
//					x.setBorder(BorderFactory.createLineBorder(Color.black));
					p.add(x);
				}
			}
		}
		JLabel title=new JLabel("Map");
		title.setFont(new Font("Forte", Font.BOLD, 19));
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
	public JButton getAr() {
		return Ar;
	}
	public void setAr(JButton ar) {
		Ar = ar;
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
