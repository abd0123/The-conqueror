package view;

import java.awt.*;

import javax.swing.*;

public class StartWindow extends JFrame {
	private JButton start=new JButton("Start");
	private TextField PlayerNameTxt=new TextField();
	private JLabel playerNameLbl=new JLabel("Player Name");
	private JLabel PlayerCityLbl=new JLabel("Player City");
	private JComboBox cities=new JComboBox(new String[] {"Cairo","Sparta","Rome"});
//	private JLabel jp;
	public StartWindow() {
		setTitle("The Conqueror");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		playerNameLbl.setFont(new Font("Player Name",Font.PLAIN,20));
		PlayerNameTxt.setFont(new Font("",Font.PLAIN,20));
		cities.setFont(new Font("",Font.PLAIN,20));
		PlayerCityLbl.setFont(new Font("",Font.PLAIN,20));
		playerNameLbl.setBounds(670,300,150,30);
		PlayerNameTxt.setBounds(875, 300, 200,30);
		start.setBounds(875, 470, 200,40);
		PlayerCityLbl.setBounds(670,350,150,30);
		cities.setBounds(875, 350, 200,30);
		setLayout(null);
		add(playerNameLbl);
		add(start);
		add(cities);
		add(PlayerNameTxt);
		add(PlayerCityLbl);
		//A fail try to add backgroung photo
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
