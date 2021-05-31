package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?2000:0);
		this.setRecruitmentCost(this.getLevel()==2?650:700);
	}
}
