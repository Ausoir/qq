package map;
import java.util.Vector;

import object.Object;
import object.Feature;
import object.ObjectStock;
import file.FileManager;
import gameplay.Setting;
import mapgen.MapGenGlobal;
import mapgen.MapGenObject;


public class MapCreator {
	private ObjectStock<Feature> features;
	private ObjectStock<Object> objects;
	private String mapDir = Setting.mapDirectory + Setting.mapName
			+ Setting.mapExtension;
	private String mapDataDir = Setting.mapDirectory + Setting.mapName
			+ ".dat";
	
	public MapCreator(ObjectStock<Feature> features, ObjectStock<Object> objects){
		this.features = features;
		this.objects = objects;
	}
	
	public void makeMap(int seed, int x, int y){
		MapGenGlobal mapgen = new MapGenGlobal(seed, features);
		MapGenObject objectgen = new MapGenObject(seed, features, objects);
		mapgen.generateDefaultMap(x, y);
		objectgen.generate(mapgen.getMap());
		
		FileManager fm = new FileManager(mapDir);
		Vector<String> mapData = new Vector<String>();
		mapData.addAll(makeMapHeader(seed, x, y, mapgen.getSpawnPoint().getX(), mapgen.getSpawnPoint().getY()));
		mapData.addAll(mapgen.getMap().toStringVector());
		fm.write(mapData);
		
		fm = new FileManager(mapDataDir);
		fm.write(""); //Erase map data
	}
	
	public static Vector<String> makeMapHeader(int seed, int x, int y, int sX, int sY){
		Vector<String> result = new Vector<String>();
		result.addElement("#Header (MapSizeX,MapSizeY)");
		result.addElement("#	   (SpawnX,SpawnY)");
		result.addElement(""+seed); //Seed
		result.addElement(x+","+y); //MapSize
		result.addElement(sX+","+sY); //Spawn Point
		return result;
	}
}
