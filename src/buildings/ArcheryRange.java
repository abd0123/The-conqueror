package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?700:0);
		this.setRecruitmentCost(this.getLevel()==2?450:500);
	}
}
