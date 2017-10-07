package behavior;

import command.Face;
import command.Move;
import object.AIHost;
import object.Object;
import object.MaterialObject;
import object.ObjectStock;

public class Chase extends Behavior {
	public Chase(Behavior p) {
		super(p);
	}
	public void think(AIHost p){
		ObjectStock<Object> enemys = p.getPerception().getObjects();
		if (enemys != null) {
			for (Object enemy : enemys) {
				if (enemy instanceof MaterialObject) {
					MaterialObject tenemy = (MaterialObject) enemy;
					if (!p.getFaction().equals(tenemy.getFaction())
							&& !p.getFaction().equals("Neutral")) {
						int dir = p.getPos().getTileDirection(tenemy.getPos());
						p.order(new Face(null).getCode(dir));
						p.order(new Move(null).getCode());
						break;
					}
				}
			}
		}
		super.think(p);
	}
}