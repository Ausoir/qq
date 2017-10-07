package object;

public class MaterialObject extends ServerObject { //AKA Unit
	
	protected int health;
	protected int weight;
	protected String faction;
	protected String loot;
	
	public MaterialObject(){
		this(0,"");
	}

	public MaterialObject(int id, String name) {
		super(id, name);
	}

	public String getLoot() {
		return loot;
	}

	public void setLoot(String loot) {
		this.loot = loot;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

}
