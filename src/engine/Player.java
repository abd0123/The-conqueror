package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.MilitaryBuilding;
import exceptions.*;
import units.*;

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
	
	public void recruitUnit(String type,String cityName) throws
	BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException{
		City c=new City("Blabizo");
		for (int i = 0; i < controlledCities.size(); i++) {
			if(controlledCities.get(i).getName().equals(cityName)) {
				c=controlledCities.get(i);
				break;
			}
		}
		boolean fCoolDown=false;
		boolean fMaxR=false;
		for (int i = 0; i < c.getMilitaryBuildings().size(); i++) {
			MilitaryBuilding b=c.getMilitaryBuildings().get(i);
			String btype="";
			if(b instanceof ArcheryRange) {
				btype="archer";
			}else if(b instanceof Barracks){
				btype="infantry";
			}else {
				btype="cavalry";
			}
			if(type.toLowerCase().equals(btype)) {
				if(b.getCurrentRecruit()<b.getMaxRecruit()) {
					fMaxR=true;
				}
				if(!b.isCoolDown()) {
					fCoolDown=true;
				}
				if(b.getCurrentRecruit()<b.getMaxRecruit()&&!b.isCoolDown()) {
					if(treasury-b.getRecruitmentCost()<0) {
						throw new NotEnoughGoldException();
					}else {
						treasury-=b.getRecruitmentCost();
						if(type.toLowerCase().equals("archer")) {
							Archer a = new Archer(1, 60, 0.4, 0.5, 0.6);
							a.setParentArmy(c.getDefendingArmy());
							c.getDefendingArmy().getUnits().add(a);
						}else if(type.toLowerCase().equals("cavalry")) {
							Cavalry a=new Cavalry(1, 40, 0.6, 0.7, 0.75);
							a.setParentArmy(c.getDefendingArmy());
							c.getDefendingArmy().getUnits().add(a);
						}else {
							Infantry a=new Infantry(1, 50, 0.5, 0.6, 0.7);
							a.setParentArmy(c.getDefendingArmy());
							c.getDefendingArmy().getUnits().add(a);
						}
					}
					break;
				}
			}	
		}
		if(!fCoolDown) {
			throw new BuildingInCoolDownException();
		}
		if(!fMaxR) {
			throw new MaxRecruitedException();
		}
		
	}
	 

}
