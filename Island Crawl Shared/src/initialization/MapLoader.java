package initialization;

import java.util.Vector;

import map.Tile;
import map.TileGen;
import map.TileStock;
import object.Feature;
import object.Object;
import object.ObjectFactory;
import object.InteractiveObject;

public class MapLoader extends Loader {
	protected Vector<Vector<String[]>> content; // Header, Player, Feature,
												// Object
	protected Tile[][] tiles;

	private Tile spawnPoint;

	public int seed;
	private int playerId;
	private InteractiveObject player;

	private ObjectFactory factory;

	public MapLoader(ObjectFactory factory, String dir) {
		this.factory = factory;
		if (dir == null)
			dir = "default";
		content = loadBySection(dir);
	}

	public void setPlayerId(int id) {
		playerId = id;
	}

	public InteractiveObject getPlayer() {
		if (player == null) {
			new Exception().printStackTrace();
		}
		return player;
	}

	public TileStock getMap() {
		TileStock result = new TileStock();
		result.addAll(tiles);
		return result;
	}

	public void load() {
		loadHeader();
		loadFeature();
		loadObject();
		loadUnit();
	}

	protected void loadHeader() {
		// Load Seed
		String[] mapHeader1 = content.firstElement().firstElement();
		seed = Integer.parseInt(mapHeader1[0]);
		// Load Tile
		String[] mapHeader2 = content.firstElement().elementAt(1);
		int mapX = Integer.parseInt(mapHeader2[0]);
		int mapY = Integer.parseInt(mapHeader2[1]);
		tiles = TileGen.generate(mapX, mapY);
		// Load Spawn Point
		String[] mapHeader3 = content.firstElement().elementAt(2);
		int spawnX = Integer.parseInt(mapHeader3[0]);
		int spawnY = Integer.parseInt(mapHeader3[1]);
		spawnPoint = tiles[spawnX][spawnY];
	}

	protected void loadFeature() {
		for (String[] cells : content.elementAt(1)) {
			addFeature(cells);
		}
	}

	protected void loadObject() {
		for (String[] cells : content.elementAt(2)) {
			addObject(cells);
		}
	}

	protected void loadUnit() {
		for (String[] cells : content.elementAt(3)) {
			addUnit(cells);
		}
	}

	public Feature addFeature(String[] cells) {
		int id = Integer.parseInt(cells[1]);
		int x = Integer.parseInt(cells[2]);
		int y = Integer.parseInt(cells[3]);
		Feature result = factory.createFeature(cells[0], id);
		result.setPos(tiles[x][y]);
		return result;
	}

	public Object addObject(String[] cells) {
		int id = Integer.parseInt(cells[1]);
		int x = Integer.parseInt(cells[2]);
		int y = Integer.parseInt(cells[3]);
		Object result = factory.createObject(cells[0], id);
		result.setPos(tiles[x][y]);
		return result;
	}

	public InteractiveObject addUnit(String[] cells) {
		int id = Integer.parseInt(cells[1]);
		int x = Integer.parseInt(cells[2]);
		int y = Integer.parseInt(cells[3]);
		InteractiveObject result = factory.createUnit(cells[0], id);
		result.setPos(tiles[x][y]);
		if (id == playerId)
			player = result;
		return result;
	}

	public Tile getSpawnPoint() {
		return spawnPoint;
	}

	public int getSizeX() {
		return tiles.length;
	}

	public int getSizeY() {
		return tiles[0].length;
	}
}
