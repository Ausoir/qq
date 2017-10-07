package mapgen;

import java.util.Random;

import map.TileStock;
import object.Feature;
import object.ObjectStock;

public abstract class MapGen {
	protected Random rand;
	protected TileStock map;
	protected ObjectStock<Feature> features;

	public MapGen(int seed) {
		rand = new Random(seed);
	}
	
	public TileStock getMap(){
		return map;
	}
}
