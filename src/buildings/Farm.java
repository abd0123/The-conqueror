package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding{
	public Farm() {
		super(1000, 500);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?700:0);
	}

	public int harvest() {
		return this.getLevel()==1?500:this.getLevel()==2?700:1000;
	}
}
