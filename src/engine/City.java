package engine;

import java.util.ArrayList;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

public class City {
	private String name;
	private ArrayList<EconomicBuilding> economicalBuildings=new ArrayList<EconomicBuilding>();
	private ArrayList<MilitaryBuilding> militaryBuildings=new ArrayList<MilitaryBuilding>();
	private Army defendingArmy;
	private int turnsUnderSiege;
	private boolean underSiege;
	public City(String name) {
		this.name=name;
		this.underSiege = false;  
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}
	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}
	public Army getDefendingArmy() {
		return defendingArmy;
	}
	public void setDefendingArmy(Army defendingArmy) {
		this.defendingArmy = defendingArmy;
	}
	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}
	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}
	public boolean isUnderSiege() {
		return underSiege;
	}
	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}
	  
	  
	  

}
