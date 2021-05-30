package buildings;

import exceptions.*;

public class Market extends EconomicBuilding {
	public Market() {
		super(1500, 700);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		this.setUpgradeCost(this.getLevel()==2?1000:0);
	}
}
