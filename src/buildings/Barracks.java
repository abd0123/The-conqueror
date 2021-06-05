package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?1500:0);
		this.setRecruitmentCost(this.getLevel()==2?550:600);
	}
}
