package engine;
import java.util.ArrayList;
import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
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
		if(c.getDefendingArmy().getUnits().size()<c.getDefendingArmy().getMaxToHold()) {
			for (int i = 0; i < c.getMilitaryBuildings().size(); i++) {
				MilitaryBuilding b=c.getMilitaryBuildings().get(i);
				String btype="";
				if(b instanceof ArcheryRange) {
					btype="archer";
				}else if(b instanceof Barracks) {
					btype="infantry";
				}else {
					btype="cavalry";
				}
				if(type.toLowerCase().equals(btype)) {	
					if(treasury-b.getRecruitmentCost()<0) {
						throw new NotEnoughGoldException();
					}else {
						Unit u=b.recruit();
						u.setParentArmy(c.getDefendingArmy());
						c.getDefendingArmy().getUnits().add(u);
						treasury-=b.getRecruitmentCost();
					}
				}	
			}
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
		int x=b.getUpgradeCost();
		b.upgrade();
		treasury-=x;
		
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
	
	public void laySiege(Army army,City city) throws TargetNotReachedException,FriendlyCityException {
		if(!army.getCurrentLocation().equals(city.getName())) {
			throw new TargetNotReachedException();
		}
		if(controlledCities.contains(city)) {
			throw new FriendlyCityException();
		}
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		city.setTurnsUnderSiege(0);
	}
	
}
