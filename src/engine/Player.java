package engine;
import java.util.ArrayList;
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
		City c=new City(cityName);
		for (int i = 0; i < controlledCities.size(); i++) {
			if(controlledCities.get(i).getName().equals(cityName)) {
				c=controlledCities.get(i);
				break;
			}
		}
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
					throw new NotEnoughGoldException("Not Enough Gold");
				}else {
					Unit u=b.recruit();
					u.setParentArmy(c.getDefendingArmy());
					c.getDefendingArmy().getUnits().add(u);
					treasury-=b.getRecruitmentCost();
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
			boolean flag=false;
			for(MilitaryBuilding e: c.getMilitaryBuildings())if(e instanceof ArcheryRange)flag=true;
			if(!flag) {	
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
		}
		else if (type.toLowerCase().equals("barracks")) {
			boolean flag=false;
			for(MilitaryBuilding e: c.getMilitaryBuildings())if(e instanceof Barracks)flag=true;
			if(!flag) {	
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
		}
		else if (type.toLowerCase().equals("stable")) {
			boolean flag=false;
			for(MilitaryBuilding e: c.getMilitaryBuildings())if(e instanceof Stable)flag=true;
			if(!flag) {	
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
		}
		else if (type.toLowerCase().equals("farm")) {
			boolean f=false;
			for(EconomicBuilding e:c.getEconomicalBuildings())if(e instanceof Farm)f=true;
			if(!f) {	
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
		}
		else {
			boolean f=false;
			for(EconomicBuilding e:c.getEconomicalBuildings())if(e instanceof Market)f=true;
			if(!f) {	
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
	}


	public void upgradeBuilding(Building b) throws NotEnoughGoldException,BuildingInCoolDownException, MaxLevelException{
		if(treasury-b.getUpgradeCost()<0) {
			throw new NotEnoughGoldException("Not Enough Gold");
		}
		int x=b.getUpgradeCost();
		b.upgrade();
		treasury-=x;
		
	}
	
	public void initiateArmy(City city,Unit unit) {
		Army n=new Army(city.getName());
		unit.setParentArmy(n);
		n.getUnits().add(unit);
		city.getDefendingArmy().getUnits().remove(unit);
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
