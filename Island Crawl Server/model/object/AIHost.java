package object;

import map.TileStock;
import behavior.AI;

public class AIHost extends MaterialObject implements AI {
	// Unit is the first AI
	protected AI next;
	protected TileStock perception;
	protected int sight;

	public AIHost(int id, String pname) {
		super(id, pname);
	}
	
	public AIHost(String pname, String faction, String loot, int weight, int health) {
		super(-1, pname);
		setFaction(faction);
		setLoot(loot);
		setWeight(weight);
		setHealth(health);
		sight = 3;
	}
	
	@Override
	public AIHost getInstance(int id) {
		AIHost result = new AIHost(getName(), getFaction(), getLoot(), getWeight(), getHealth());
		result.setId(id);
		result.setImage(getImage());
		result.setNext(next);
		result.setController(ic);
		return result;
	}

	public void think(AIHost p) {
				//System.out.println(this.getName()+" "+this.getController().getCommands().get("Move").getCost());
		perception = getPos().getSquareSpread(sight);
		if (next != null)
			next.think(p);
	}

	@Override
	public void update() {
		super.update();
		think(this);
	}

	public AI getNext() {
		return next;
	}

	public AI setNext(AI p) {
		next = p;
		return this;
	}
	
	public void setSight(int p){
		sight = p;
	}
	
	public TileStock getPerception(){
		return perception;
	}

	public int getSight() {
		return sight;
	}
}
