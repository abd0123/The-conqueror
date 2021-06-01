package units;

import exceptions.FriendlyFireException;

public class Archer extends Unit {

	public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public void attack(Unit target) throws FriendlyFireException {
		super.attack(target);
		double c = target.getCurrentSoldierCount();
		if (target instanceof Archer) {
			if (this.getLevel() == 1) {
				c -= (0.3 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 2) {
				c -= (0.4 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 3) {
				c -= (0.5 * this.getCurrentSoldierCount());
			}
		}
		if (target instanceof Infantry) {
			if (this.getLevel() == 1) {
				c -= (0.2 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 2) {
				c -= (0.3 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 3) {
				c -= (0.4 * this.getCurrentSoldierCount());
			}
		}
		if(target instanceof Cavalry) {
			if (this.getLevel() == 1) {
				c -= (0.1 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 1) {
				c -= (0.1 * this.getCurrentSoldierCount());
			}
			if (this.getLevel() == 1) {
				c -= (0.2 * this.getCurrentSoldierCount());
			}
		}
		target.setCurrentSoldierCount(c);
	}

}
