package mapgen;

import java.util.Random;

import map.Tile;
import map.TileGen;
import map.TileStock;
import object.Feature;
import object.ObjectStock;

public class MapGenGlobal extends MapGen {
	private Tile spawnPoint;

	public MapGenGlobal(int seed, ObjectStock<Feature> features) {
		super(seed);
		this.features = features;
		map = new TileStock();
	}

	public void generateDefaultMap(int mapSizeX, int mapSizeY) {
		Tile[][] mapTiles = TileGen.generate(mapSizeX, mapSizeY);
		map.addAll(mapTiles);
		fillMap(map,"Sea Water");
		Tile islandCenter = map.getPos(mapSizeX / 2, mapSizeY / 2);// map.getRandomTile();
		generateIsland(islandCenter);
//		test(islandCenter);
		setSpawnPoint();
	}

	public void test(Tile center) {
		TileStock island = generateFrame(center,14,5);
		island.fill(features.getByName("Rocky Surface").firstElement());
	}
	
	private void fillMap(TileStock map, String feat){
		map.fill(features.getByName(feat).firstElement());
	}

	private void generateIsland(Tile islandCenter) {
		TileStock island = generateFrame(islandCenter,20,8);

		TileStock layer1 = spread(island, 15, 2);
		layer1.fill(features.getByName("Rocky Surface").firstElement());
		TileStock layer2 = spread(layer1, 9, 1);
		layer2.removeAll(layer1);
		layer2.fill(features.getByName("Sand").firstElement());
		TileStock layer3 = spread(layer2, 8, 3);
		layer3.removeAll(layer1);
		layer3.removeAll(layer2);
		layer3.fill(features.getByName("Water").firstElement());
		
		TileStock total = new TileStock();
		total.addAll(layer1);
		total.addAll(layer2);
//		total.addAll(layer3);
		dissolve(total);
		layer3 = spread(layer3, 8, 3);
		layer3.removeAll(layer1);
		layer3.removeAll(layer2);
		layer3.fill(features.getByName("Water").firstElement());
	}

	protected TileStock generateFrame(Tile p, int pstr, int times) {
		TileStock result = new TileStock();
		int dir = rand.nextInt(8) + 1;
		Tile t = p;
		int str = rand.nextInt(pstr*2); //0 to twice
		for (int x1 = 0; x1 < times; x1++) {
			result.addAll(t.getLine(dir, str));
			t = t.getDestination(dir, str);
			dir = rand.nextInt(4) + 1;
		}
		return result;
	}

	protected TileStock spread(TileStock tiles, int str, int rate) {
		TileStock result = tiles.spread(rate);
		for (int x1 = 0; x1 < str - 1; x1++) {
			result.addAll(result.spread(rate));
		}
		return result;
	}

	protected void dissolve(TileStock p) {
		Random rand = new Random();
		for (Tile tile : p) {
			int r = rand.nextInt(8) + 1;
			Tile adj = tile.getAdjacent(r);
			if (adj != null && adj.getFeature() != null) {
				Feature result = adj.getFeature();
				tile.setFeature((Feature) result.getNewInstance());
			}
		}
	}

	private void setSpawnPoint() {
		spawnPoint = map.getRandomTile();
	}

	public Tile getSpawnPoint() {
		return spawnPoint;
	}
}
