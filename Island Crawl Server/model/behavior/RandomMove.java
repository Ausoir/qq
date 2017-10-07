package behavior;

import command.Move;
import object.AIHost;

public class RandomMove extends ArbitraryBehavior {
	public RandomMove(int pcent, Behavior p) {
		super(pcent, p);
	}
	public void think(AIHost p){
//		System.out.println("MOVE!");
		if (getPercent())
			p.order(new Move(null).getCode());
		super.think(p);
	}
}