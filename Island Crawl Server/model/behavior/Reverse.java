package behavior;

import object.AIHost;

public class Reverse extends Behavior {
	public Reverse(Behavior p) {
		super(p);
	}
	public void think(AIHost p){
		int dir = p.getDir();
		p.setDir(dir==1?2:dir==2?1:dir==3?4:dir==4?3:dir);
		super.think(p);
	}
}
