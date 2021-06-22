package view;

import java.awt.*;

import javax.swing.*;

public class StartWindow extends JFrame {
	private JButton start=new JButton("Start");
	private TextField PlayerNameTxt=new TextField();
	private JLabel playerNameLbl=new JLabel("Player Name");
	private JLabel PlayerCityLbl=new JLabel("Player City");
	private JComboBox cities=new JComboBox(new String[] {"Cairo","Sparta","Rome"});
	private JPanel p=new JPanel();
	public JPanel getP() {
		return p;
	}
	public void setP(JPanel p) {
		this.p = p;
	}
	public StartWindow() {
		setTitle("The Conqueror");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new GridLayout(5,5));
		for (int i = 0; i < 25; i++) {
			if(i==12) {
				add(p);
			}else {
				JPanel x=new JPanel();
				add(x);
			}
		}
		playerNameLbl.setFont(new Font("Player Name",Font.PLAIN,20));
		PlayerNameTxt.setFont(new Font("",Font.PLAIN,20));
		cities.setFont(new Font("",Font.PLAIN,20));
		PlayerCityLbl.setFont(new Font("",Font.PLAIN,20));
//		playerNameLbl.setBounds(670,300,150,30);
//		PlayerNameTxt.setBounds(875, 300, 200,30);
//		start.setBounds(875, 470, 200,40);
//		PlayerCityLbl.setBounds(670,350,150,30);
//		cities.setBounds(875, 350, 200,30);
		start.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
//		setLayout(null);
		JPanel p1=new JPanel();
		p.setLayout(new BorderLayout());
		p.add(p1,BorderLayout.NORTH);
		p1.setLayout(new GridLayout(0,2));
		p1.add(playerNameLbl);
		p1.add(PlayerNameTxt);
		p1.add(PlayerCityLbl);
		p1.add(cities);
		JPanel p2 =new JPanel();
		p2.setLayout(new GridLayout(0,1));
		p2.add(new JPanel());
		p2.add(start);
		p.add(p2,BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(start);
//		
//		
//		
		//A fail try to add background photo
//		jp = new JLabel();
//		jp.setIcon(new ImageIcon("C://StartWindow.JPG"));
//		jp.setBounds(200,100,1000,1000);;
//		this.add(jp);
		setVisible(true);
		revalidate();
		repaint();
	}
	public JButton getStart() {
		return start;
	}
	public void setStart(JButton start) {
		this.start = start;
	}
	public TextField getPlayerNameTxt() {
		return PlayerNameTxt;
	}
	public void setPlayerNameTxt(TextField playerNameTxt) {
		PlayerNameTxt = playerNameTxt;
	}
	public JLabel getPlayerNameLbl() {
		return playerNameLbl;
	}
	public void setPlayerNameLbl(JLabel playerNameLbl) {
		this.playerNameLbl = playerNameLbl;
	}
	public JLabel getPlayerCityLbl() {
		return PlayerCityLbl;
	}
	public void setPlayerCityLbl(JLabel playerCityLbl) {
		PlayerCityLbl = playerCityLbl;
	}
	public JComboBox getCities() {
		return cities;
	}
	public void setCities(JComboBox cities) {
		this.cities = cities;
	}
	public static void main(String[] args) {
		new StartWindow();
	}
}
