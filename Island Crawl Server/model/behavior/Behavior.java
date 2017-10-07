package behavior;

import object.AIHost;

public class Behavior implements AI {
	protected AI next;
	public Behavior(AI p){
		setNext(p);
	}
	public void think(AIHost p){
//		System.out.println(p.getName()+" " +this.getClass());
		if (next != null)
			next.think(p);
	}
	public AI getNext(){
		return next;
	}
	public AI setNext(AI p){
		next = p;
		return this;
	}
}
