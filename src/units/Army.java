package units;

import java.util.*;

import exceptions.MaxCapacityException;

public class Army {
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
	private final int maxToHold = 10;

	public Army(String currentLocation) {
		currentStatus = Status.IDLE;
		units = new ArrayList<Unit>();
		distancetoTarget = -1;
		target = "";
		this.currentLocation = currentLocation;
	}

	public void relocateUnit(Unit unit) throws MaxCapacityException {
		if (units.size() >= maxToHold)
			throw new MaxCapacityException();
		else {
			Army a = unit.getParentArmy();
			unit.setParentArmy(this);
			units.add(unit);
			a.getUnits().remove(unit);
		}
	}

	public void handleAttackedUnit(Unit u) {
		if (u.getCurrentSoldierCount() <= 0) {
			units.remove(u);
		}
	}
	
	public double foodNeeded() {
		double sum =0;
		Status c = currentStatus;
		for (int i = 0; i < units.size(); i++) {
			Unit u=units.get(i);
			if(c==Status.IDLE) {
				sum+=u.getCurrentSoldierCount()*u.getIdleUpkeep();
			}else if(c==Status.BESIEGING) {
				sum+=u.getCurrentSoldierCount()*u.getSiegeUpkeep();
			}else {
				sum+=u.getCurrentSoldierCount()*u.getMarchingUpkeep();
			}
		}
		return sum;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getMaxToHold() {
		return maxToHold;
	}

}
