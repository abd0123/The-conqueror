package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?2000:0);
		this.setRecruitmentCost(this.getLevel()==2?650:700);
	}
	
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if(isCoolDown()) {
			throw new BuildingInCoolDownException();
		}
		if(getCurrentRecruit()==getMaxRecruit()) {
			throw new MaxRecruitedException();
		}
		return new Cavalry(1, 40, 0.6, 0.7, 0.75);
	}
}
