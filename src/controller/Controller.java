package controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import engine.Player;
import view.*;

public class Controller implements ActionListener {
	private StartWindow start;
	private GameView view;
	
	public Controller() {
		start=new StartWindow();
		start.getStart().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s!=null&&s.equals("Start")) {
			if(start.getPlayerNameTxt().getText().equals("")) {
				JOptionPane.showMessageDialog(start, "write Player Name","Alert",JOptionPane.INFORMATION_MESSAGE);
			}else {
				start.dispose();
				this.view=new GameView(new Player(start.getPlayerNameTxt().getText()),50);
			}
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
