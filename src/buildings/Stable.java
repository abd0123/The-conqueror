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
		if(getCurrentRecruit()>=getMaxRecruit()) {
			throw new MaxRecruitedException();
		}
		setCurrentRecruit(getCurrentRecruit()+1);
		if(getLevel()==1) {
			return new Cavalry(1, 40, 0.6, 0.7, 0.75);
		}else if(getLevel()==2) {
			return  new Cavalry(2, 40, 0.6, 0.7, 0.75);
		}else {
			return  new Cavalry(3, 60, 0.7, 0.8, 0.9);
		}
	}
}
