package behavior;

import java.util.Random;

public class ArbitraryBehavior extends Behavior {
	private Random rand;
	private int percent;

	public ArbitraryBehavior(int pcent, AI p) {
		super(p);
		rand = new Random();
		percent = pcent;
	}
	
	protected boolean getPercent(){
		if (rand.nextInt(100) < percent)
			return true;
		return false;
	}
	
	protected int getRandomDir(){
		return rand.nextInt(4)+1;
	}

}
