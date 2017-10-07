/**
 * @author Ga Lim
 * CSC 111 - 60107
 * Stores books for use by the public like the library class except using linked list technique
 */
package map;

import java.util.Random;
import java.util.Vector;

import object.Feature;
import object.Object;
import object.ObjectStock;
import object.InteractiveObject;
import map.Tile;

public class TileStock extends Vector<Tile> {
	private static final long serialVersionUID = 1L;
	private Random rand = new Random();

	public void addAll(Tile[][] tiles) {
		for (Tile[] tileRow : tiles) {
			for (Tile tile : tileRow) {
				addElement(tile);
			}
		}
	}
	
	public void addAll(Vector<Tile> tiles){
		for (Tile tile : tiles){
			if (!contains(tile)){
				addElement(tile);
			}
		}
	}
	
	@Override
	public void addElement(Tile tile){
		if (tile != null) super.addElement(tile);
	}

	public TileStock getByFeature(Feature feat) {
		TileStock result = new TileStock();
		for (Tile tile : this) {
			if (tile.getFeature().equals(feat))
				result.addElement(tile);
		}
		return result;
	}

	public ObjectStock<Object> getObjects() {
		ObjectStock<Object> result = new ObjectStock<Object>();
		for (int x = 0; x < size(); x++) {
			result.addAll(elementAt(x).getObjects());
		}
		return result;
	}

	public ObjectStock<Feature> getFeatures() {
		ObjectStock<Feature> result = new ObjectStock<Feature>();
		for (int x = 0; x < size(); x++) {
			if (elementAt(x).getFeature() != null)
				result.addElement(elementAt(x).getFeature());
		}
		return result;
	}

	public boolean checkIfTileExist(Tile ptile) {
		for (int x = 0; x < size(); x++) {
			if (elementAt(x) == ptile)
				return true;
		}
		return false;
	}

	public Tile getPos(int x, int y) {
		for (Tile tile : this) {
			if (tile.getX() == x && tile.getY() == y)
				return tile;
		}
		return null;
	}

	public Tile getRandomTile() {
		if (size() == 0)
			return null;
		int r = rand.nextInt(size());
		return elementAt(r);
	}

	public void fill(Object obj) {
		fillChance(obj, 100);
	}

	public void fillChance(Object obj, int percent) {
		for (Tile tile : this) {
			if (rand.nextInt(100) < percent){
				tile.addObject(obj.getNewInstance());
			}
		}
	}
	
	public TileStock spread(int str){
		TileStock result = new TileStock();
		for (Tile tile : this){
			result.addAll(tile.getSpread(str));
		}
		return result;
	}
	
	public Vector<String> toStringVector(){
		Vector<String> result = new Vector<String>();
		Vector<String> features = new Vector<String>();
		Vector<String> objects = new Vector<String>();
		Vector<String> units = new Vector<String>();
		for (Object obj : getFeatures()){
			features.addElement(obj.getName()+","+obj.getId()+","+obj.getPos().getX()+","+obj.getPos().getY());
		}
		for (Object obj : getObjects()){
			if (obj instanceof InteractiveObject)
				units.addElement(obj.getName()+","+obj.getId()+","+obj.getPos().getX()+","+obj.getPos().getY());
			else
				objects.addElement(obj.getName()+","+obj.getId()+","+obj.getPos().getX()+","+obj.getPos().getY());
		}
		result.addElement("-Feature (Name,ID,X,Y)");
		result.addAll(features);
		result.addElement("-Object (Name,ID,X,Y)");
		result.addAll(objects);
		result.addElement("-Unit (Name,ID,X,Y)");
		result.addAll(units);
		return result;
	}
}
