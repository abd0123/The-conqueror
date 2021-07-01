package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AttackingArmyListener implements ActionListener{
	Controller controller;
	public AttackingArmyListener(Controller controller) {
		this.controller=controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
	}

}
