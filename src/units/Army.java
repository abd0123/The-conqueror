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
		if (units.size() >= 10)
			throw new MaxCapacityException();
		else {
			Army a = unit.getParentArmy();
			units.add(unit);
			for (int i = 0; i < a.getUnits().size(); i++) {
				if (a.getUnits().get(i).equals(unit))
					a.getUnits().remove(i);
			}
		}
	}

	public void handleAttackedUnit(Unit u) {
		if (u.getCurrentSoldierCount() <= 0) {
			Army a = u.getParentArmy();
			for (int i = 0; i < a.getUnits().size(); i++) {
				if (a.getUnits().get(i).equals(u))
					a.getUnits().remove(i);
			}
		}
	}
	
	public double foodNeeded() {
		double sum =0;
		Status c = currentStatus;
		switch(c) {
		case IDLE:
		   for(int i=0;i<units.size();i++) {
			 Unit u=units.get(i);
			 if(u instanceof Archer) {
				Archer arch =(Archer) u;
				if(arch.getLevel()==1 || arch.getLevel()==2) 
				   sum+=(arch.getCurrentSoldierCount()*0.4);
				else if(arch.getLevel()==3)
					sum+=(arch.getCurrentSoldierCount()*0.5);
			}
			 else if(u instanceof Infantry) {
				 Infantry in=(Infantry) u;
				 if(in.getLevel()==1 || in.getLevel()==2) 
					   sum+=(in.getCurrentSoldierCount()*0.5);
				 else if(in.getLevel()==3)
						sum+=(in.getCurrentSoldierCount()*0.6);
				}
			 else if(u instanceof Cavalry) {
				 Cavalry ca=(Cavalry)u;
				 if(ca.getLevel()==1 || ca.getLevel()==2) 
					   sum+=(ca.getCurrentSoldierCount()*0.6);
				 else if(ca.getLevel()==3)
						sum+=(ca.getCurrentSoldierCount()*0.7);
			 }
			 }
		   break;
		case MARCHING:
			for(int i=0;i<units.size();i++) {
				 Unit u=units.get(i);
				 if(u instanceof Archer) {
					Archer arch =(Archer) u;
					if(arch.getLevel()==1 || arch.getLevel()==2) 
					   sum+=(arch.getCurrentSoldierCount()*0.5);
					else if(arch.getLevel()==3)
						sum+=(arch.getCurrentSoldierCount()*0.6);
				}
				 else if(u instanceof Infantry) {
					 Infantry in=(Infantry) u;
					 if(in.getLevel()==1 || in.getLevel()==2) 
						   sum+=(in.getCurrentSoldierCount()*0.6);
					 else if(in.getLevel()==3)
							sum+=(in.getCurrentSoldierCount()*0.7);
					}
				 else if(u instanceof Cavalry) {
					 Cavalry ca=(Cavalry)u;
					 if(ca.getLevel()==1 || ca.getLevel()==2) 
						   sum+=(ca.getCurrentSoldierCount()*0.7);
					 else if(ca.getLevel()==3)
							sum+=(ca.getCurrentSoldierCount()*0.8);
				 }
				 }
			break;
		case BESIEGING:
			for(int i=0;i<units.size();i++) {
				 Unit u=units.get(i);
				 if(u instanceof Archer) {
					Archer arch =(Archer) u;
					if(arch.getLevel()==1 || arch.getLevel()==2) 
					   sum+=(arch.getCurrentSoldierCount()*0.6);
					else if(arch.getLevel()==3)
						sum+=(arch.getCurrentSoldierCount()*0.7);
				}
				 else if(u instanceof Infantry) {
					 Infantry in=(Infantry) u;
					 if(in.getLevel()==1 || in.getLevel()==2) 
						   sum+=(in.getCurrentSoldierCount()*0.7);
					 else if(in.getLevel()==3)
							sum+=(in.getCurrentSoldierCount()*0.8);
					}
				 else if(u instanceof Cavalry) {
					 Cavalry ca=(Cavalry)u;
					 if(ca.getLevel()==1 || ca.getLevel()==2) 
						   sum+=(ca.getCurrentSoldierCount()*0.75);
					 else if(ca.getLevel()==3)
							sum+=(ca.getCurrentSoldierCount()*0.9);
				 }
				 }
			break;
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
