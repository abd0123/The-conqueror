package buildings;

import exceptions.*;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?700:0);
		this.setRecruitmentCost(this.getLevel()==2?450:500);
	}
	
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if(isCoolDown()) {
			throw new BuildingInCoolDownException();
		}
		if(getCurrentRecruit()==getMaxRecruit()) {
			throw new MaxRecruitedException();
		}
		setCurrentRecruit(getCurrentRecruit()+1);
		if(this.getLevel()==1) {
			return new Archer(1, 60, 0.4, 0.5, 0.6);
		}else if (getLevel()==2) {
			return new Archer(2, 60, 0.4, 0.5, 0.6);
		}else {
			return new Archer(3, 70, 0.5, 0.6, 0.7);
		}
		
		
	}
}
