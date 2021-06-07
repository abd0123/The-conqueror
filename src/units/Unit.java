package units;

import exceptions.FriendlyFireException;

abstract public class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	private Army parentArmy;

	public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep,double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount=maxSoldierCount;
		parentArmy = new Army("city");
	}
	public void attack(Unit target) throws FriendlyFireException{
		if(this.parentArmy.getUnits().contains(target))
			throw new FriendlyFireException();
//		else {
//			this.parentArmy.handleAttackedUnit(target);
//		}
	}

	public Army getParentArmy() {
		return parentArmy;
	}

	public void setParentArmy(Army parentArmy) {
		this.parentArmy = parentArmy;
	}

	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}
	
	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}
	
	public double getIdleUpkeep() {
		return idleUpkeep;
	}
	
	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}
	
	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}
//	public boolean equals(Object arg0) {
//		Unit c=(Unit)arg0;
//		return level==c.level&&maxSoldierCount==c.maxSoldierCount&&idleUpkeep==c.idleUpkeep
//				&&marchingUpkeep==c.marchingUpkeep&&siegeUpkeep==c.siegeUpkeep;
//	}
	
}
