package engine;

import java.util.*;
import buildings.*;
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
	
	public void recruitUnit(String type,String cityName) throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException{
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
	
	public void build(String type,String cityName) throws NotEnoughGoldException{
		City c=new City("Blabizo");
		for (int i = 0; i < controlledCities.size(); i++) {
			if(controlledCities.get(i).getName().equals(cityName)) {
				c=controlledCities.get(i);
				break;
			}
		}
		if (type.toLowerCase().equals("archeryrange")) {
			ArcheryRange a = new ArcheryRange();
			if(treasury-a.getCost()<0) {
				throw new NotEnoughGoldException();
			}
			else {
				c.getMilitaryBuildings().add(a);
				treasury-=a.getCost();
				a.setCoolDown(true);
		    }
		
		}
		else if (type.toLowerCase().equals("barracks")) {
			Barracks a = new Barracks();
			if(treasury-a.getCost()<0) {
				throw new NotEnoughGoldException();
			}
			else {
				c.getMilitaryBuildings().add(a);
				treasury-=a.getCost();
				a.setCoolDown(true);
		    }
		
		}
		else if (type.toLowerCase().equals("barracks")) {
			Stable a = new Stable ();
			if(treasury-a.getCost()<0) {
				throw new NotEnoughGoldException();
			}
			else {
				c.getMilitaryBuildings().add(a);
				treasury-=a.getCost();
				a.setCoolDown(true);
		    }
		
		}
		else if (type.toLowerCase().equals("farm")) {
			Farm a = new Farm();
			if(treasury-a.getCost()<0) {
				throw new NotEnoughGoldException();
			}
			else {
				c.getEconomicalBuildings().add(a);
				treasury-=a.getCost();
				a.setCoolDown(true);
		    }
		
		}
		else {
			Market a = new Market();
			if(treasury-a.getCost()<0) {
				throw new NotEnoughGoldException();
			}
			else {
				c.getEconomicalBuildings().add(a);
				treasury-=a.getCost();
				a.setCoolDown(true);
		    }
		
		}
	}
	
	public void upgradeBuilding(Building b) throws NotEnoughGoldException,BuildingInCoolDownException, MaxLevelException{
		if(treasury-b.getUpgradeCost()<0) {
			throw new NotEnoughGoldException();
		}
		treasury-=b.getUpgradeCost();
		b.upgrade();
	}
	
	public void initiateArmy(City city,Unit unit) {
		Army n=new Army(city.getName());
		unit.setParentArmy(n);
		n.getUnits().add(unit);
		for (int i = 0; i < city.getDefendingArmy().getUnits().size(); i++) {
			if(city.getDefendingArmy().getUnits().get(i).equals(unit)) {
				city.getDefendingArmy().getUnits().remove(i);
				break;
			}
		}
		controlledArmies.add(n);
	}
	
	public void laySiege(Army army,City city) throws TargetNotReachedException,FriendlyCityException{
		if(!army.getCurrentLocation().equals(city.getName())) {
			throw new TargetNotReachedException();
		}
		if(controlledCities.contains(city)) {
			throw new FriendlyCityException();
		}
		for (int i = 0; i <controlledArmies.size(); i++) {
			if(army.equals(controlledArmies.get(i))) {
				controlledArmies.get(i).setCurrentStatus(Status.BESIEGING);
				army.setCurrentStatus(Status.BESIEGING);
				for (int j = 0; j < controlledCities.size(); j++) {
					if(city.equals(controlledCities.get(j))) {
						controlledCities.get(j).setUnderSiege(true);
						controlledCities.get(j).setTurnsUnderSiege(0);
						city.setUnderSiege(true);
						city.setTurnsUnderSiege(0);
						break;
					}
					
				}
				break;
			}
		}
	}
	
}
