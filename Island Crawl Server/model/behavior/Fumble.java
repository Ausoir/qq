package behavior;

import object.AIHost;

public class Fumble extends ArbitraryBehavior {
	public Fumble(int pcent, Behavior p) {
		super(pcent, p);
	}

	public void think(AIHost p) {
		if (getPercent())
			p.clearThought();
		super.think(p);
	}
}