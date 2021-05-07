package engine;

import java.util.ArrayList;

import units.Army;

public class Player {
	 private String name;
	 private ArrayList<City> controlledCities;
	 private ArrayList<Army> controlledArmies;
	 private double treasury;
	 private double food;
	 public Player(String name) {
		 controlledArmies=new ArrayList<Army>();
		 controlledCities=new ArrayList<City>();
		 this.name=name;
	 }
	public String getName() {
		return name;
	}
	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}
	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
	public double getFood() {
		return food;
	}
	public void setFood(double food) {
		this.food = food;
	}

	public double getTreasury() {
		return treasury;
	}
	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}
	 
	 

}
