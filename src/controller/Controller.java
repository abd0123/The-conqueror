	package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import engine.*;
import view.*;

public class Controller implements ActionListener {
	private StartWindow start;
	private GameView view;
	private Game g;
	private CityFrame cityFrame;
	private ArmyFrame armyFrame;
	
	public Controller() {
		start=new StartWindow();
		start.getStart().addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s!=null) {
			if(s.equals("Start")) {
				if(start.getPlayerNameTxt().getText().equals("")) {
					JOptionPane.showMessageDialog(start, "write Player Name","Alert",JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
						g=new Game(start.getPlayerNameTxt().getText(),start.getCities().getName());
						playSound("sounds/Avengers.wav");
					} catch (IOException e1) {
						
					}
					this.view=new GameView(g.getPlayer(),50);
					view.addListener(this);
					start.dispose();
				}
			}else if(s.equals("Cairo")) {
				City cairo =null;
				for(City c:g.getAvailableCities())if(c.getName().equals("Cairo"))cairo = c;
				cityFrame = new CityFrame(cairo);
			}else if(s.equals("Rome")){
				City Rome =null;
				for(City c:g.getAvailableCities())if(c.getName().equals("Rome"))Rome = c;
				cityFrame = new CityFrame(Rome);	
			}
			else if(s.equals("Sparta")){
				City Sparta =null;
				for(City c:g.getAvailableCities())if(c.getName().equals("Sparta"))Sparta = c;
				cityFrame = new CityFrame(Sparta);	
			}
		}
	
			view.addListener(this);
			view.revalidate();
			view.repaint();
			
		}
	
	public void playSound (String filepath)  {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
//////	private void txt_playerName (java.awt.event.KeyEvent e) {
//////		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
//////			start.dispose();
//////			this.view=new GameView();
//////			
////		}
//	}
	public static void main(String[] args) {
		new Controller();
	}
}
