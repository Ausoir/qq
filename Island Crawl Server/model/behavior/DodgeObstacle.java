package behavior;

import object.AIHost;
import object.Object;
import object.ObjectStock;

public class DodgeObstacle extends Behavior {

	public DodgeObstacle(AI p) {
		super(p);
	}

	public void think(AIHost p) {
		ObjectStock<Object> obstacles = p.getPos().getSpread(1).getObjects();
		if (obstacles != null) {
			for (Object obstacle : obstacles) {
				
			}
		}
		super.think(p);
	}
}
