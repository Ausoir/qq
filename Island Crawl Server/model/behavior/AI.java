package behavior;

import object.AIHost;

public interface AI {
	abstract void think(AIHost p);
	public AI getNext();
	public AI setNext(AI p);
}
