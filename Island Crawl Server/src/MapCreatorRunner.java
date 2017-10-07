import initialization.ObjectLoader;
import object.Feature;
import object.Object;
import object.ObjectStock;
import gameplay.Setting;
import map.MapCreator;

public class MapCreatorRunner {

	public static void main(String[] args) {
		ObjectStock<Feature> features = ObjectLoader.loadFeature(
				Setting.featureDirectory, null);
		ObjectStock<Object> objects = ObjectLoader.loadObject(Setting.objectDirectory,
				null);
		MapCreator mapmaker = new MapCreator(features, objects);
		
		mapmaker.makeMap(0, 10, 10);
	}
}
