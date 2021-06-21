package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CityFrame;

public class CityFrameListener implements ActionListener {
	Controller controller;
	 public CityFrameListener(Controller controller) {	
		this.controller = controller;	
	}

	public void actionPerformed(ActionEvent e) {
		CityFrame a  = (CityFrame) e.getSource();
		
		
	}

}
