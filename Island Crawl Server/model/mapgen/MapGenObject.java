package mapgen;

import map.TileStock;
import object.Feature;
import object.Object;
import object.ObjectStock;

public class MapGenObject extends MapGen {
	private ObjectStock<Object> objects;

	public MapGenObject(int seed, ObjectStock<Feature> features, ObjectStock<Object> objects) {
		super(seed);
		this.features = features;
		this.objects = objects;
	}

	public void generate(TileStock p) {
		this.map = p;
		addObjects();
	}

	private void addObjects() {
		map.getByFeature((Feature) features.getByName("Dirt").firstElement())
				.fillChance(objects.getByName("Stone").firstElement(), 5);
		map.getByFeature((Feature) features.getByName("Rocky Surface").firstElement())
				.fillChance(objects.getByName("Stone").firstElement(), 30);
	}
}
