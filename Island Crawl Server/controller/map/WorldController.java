package map;

import java.util.Vector;

import file.FileManager;
import map.Tile;
import map.TileStock;

//Adding/removing objects in world
public class WorldController {
	private static TileStock map;
	private static int mapSizeX;
	private static int mapSizeY;
	private static Tile spawnPoint;
	private static int seed;

	public static void setMap(TileStock pmap, int pseed, int x, int y) {
		map = pmap;
		seed = pseed;
		mapSizeX = x;
		mapSizeY = y;
	}

	public static Tile getSpawnPoint() {
		return spawnPoint;
	}

	public static void setSpawnPoint(Tile spawnPoint) {
		WorldController.spawnPoint = spawnPoint;
	}

	public static int getMapSizeX() {
		return mapSizeX;
	}

	public static int getMapSizeY() {
		return mapSizeY;
	}

	public static TileStock getMap() {
		return map;
	}
	
	public static void save(String dir){
		FileManager fm = new FileManager(dir);
		fm.write(toStringVector());
	}
	
	public static Vector<String> toStringVector(){
//		System.out.println(map.getObjects());
		Vector<String> result = new Vector<String>();
		result.addAll(toHeaderStringVector());
		result.addAll(map.toStringVector());
		return result;
	}
	
	public static Vector<String> toHeaderStringVector(){
		Vector<String> result = new Vector<String>();
		result.addElement("#Header (MapSizeX,MapSizeY)");
		result.addElement("#	   (SpawnX,SpawnY)");
		result.addElement(""+seed); //Spawn Point
		result.addElement(mapSizeX+","+mapSizeY); //MapSize
		result.addElement(spawnPoint.getX()+","+spawnPoint.getY()); //Spawn Point
		return result;
	}
}
