package behavior;

import object.Object;
import object.AIHost;
import object.MaterialObject;
import object.ObjectStock;

public class Attack extends Behavior {
	public Attack(Behavior p) {
		super(p);
	}

	public void think(AIHost p) {
		ObjectStock<Object> enemys = p.getPos().getAdjacents().getObjects();
		if (enemys != null) {
			for (Object enemy : enemys) {
				if (enemy instanceof MaterialObject) {
					MaterialObject tenemy = (MaterialObject) enemy;
					if (!tenemy.getFaction().equals(p.getFaction())
							&& !tenemy.getFaction().equals("Neutral")) {
						p.order("at");
						int dir = p.getPos().getTileDirection(tenemy.getPos());
						p.setDir(dir);
						break;
					}
				}
			}
		}
		super.think(p);
	}
}