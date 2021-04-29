package engine;

import java.util.ArrayList;

import units.Army;

public class Player {
	 private String name;
	 private ArrayList<City> controlledCities=new ArrayList<City>();
	 private ArrayList<Army> controlledArmies=new ArrayList<Army>();
	 private double treasury;
	 private double food;
	 public Player(String name) {
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
