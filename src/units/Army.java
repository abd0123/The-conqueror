package units;
import java.util.*;

public class Army {
	private Status currentStatus;
	private ArrayList<Unit> units;
	private	int distancetoTarget;
	private	String target;
	private	String currentLocation;
	private final int maxToHold=10;
	
	public Army(String currentLocation) {
		currentStatus=Status.IDLE;
		units=new ArrayList<Unit>();
		distancetoTarget=-1;
		target="";
		this.currentLocation=currentLocation;
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
