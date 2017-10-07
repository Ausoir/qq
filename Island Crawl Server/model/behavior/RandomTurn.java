package behavior;

import command.Face;

import object.AIHost;

public class RandomTurn extends ArbitraryBehavior {
	public RandomTurn(int pcent, Behavior p) {
		super(pcent, p);
	}
	public void think(AIHost p){
		//System.out.println("MOVE!");
		if (getPercent())
			p.order(new Face(null).getCode(getRandomDir()));
		super.think(p);
	}
}