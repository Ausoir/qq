package initialization;

import java.util.HashMap;

import behavior.AI;
import behavior.Attack;
import behavior.Chase;
import behavior.DodgeObstacle;
import behavior.Fumble;
import behavior.RandomMove;
import behavior.RandomTurn;
import behavior.Reverse;

class BehaviorLoader {
	private static HashMap<String, AI> hm = new HashMap<String, AI>();

	protected static void load() {
		hm.put("Animal", null);
		hm.put("Prey", new RandomTurn(20, new RandomMove(33, new Fumble(20,
				new Chase(new Reverse(new Attack(new Fumble(20, null))))))));
		hm.put("MindlessHostile", new RandomTurn(20, new RandomMove(100,
				new Chase(new Attack(null)))));
		hm.put("Hostile", new RandomTurn(20, new RandomMove(15, new Chase(
				new Attack(new DodgeObstacle(new Fumble(20, null)))))));
		hm.put("DefenselessPrey", new RandomTurn(20,
				new RandomMove(50, new Fumble(10, new Chase(new Reverse(
						new RandomTurn(20, null)))))));
	}
	
	public static AI getBehavior(String p){
		return hm.get(p);
	}
}
