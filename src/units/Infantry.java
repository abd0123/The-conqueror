package units;

import exceptions.FriendlyFireException;

public class Infantry extends Unit {

	public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep,double siegeUpkeep) {
		super(level, maxSoldierCount,  idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public void attack(Unit target) throws FriendlyFireException {
		super.attack(target);
		int c = target.getCurrentSoldierCount();
		if (target instanceof Archer) {
			if (this.getLevel() == 1) {
				c -= (int)(0.3 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 2) {
				c -=(int) (0.4 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 3) {
				c -= (int)(0.5 * this.getCurrentSoldierCount());
			}
		}
		if (target instanceof Infantry) {
			if (this.getLevel() == 1) {
				c -= (int)(0.1 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 2) {
				c -= (int)(0.2 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 3) {
				c -= (int)(0.3 * this.getCurrentSoldierCount());
			}
		}
		if(target instanceof Cavalry) {
			if (this.getLevel() == 1) {
				c -= (int)(0.1 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 1) {
				c -= (int)(0.2 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 1) {
				c -= (int)(0.25 * this.getCurrentSoldierCount());
			}
		}
		target.setCurrentSoldierCount((int)c);
		this.getParentArmy().handleAttackedUnit(target);
	}
	
}
