package map;

import java.util.Arrays;
import java.util.Collections;

import object.Object;
import object.Feature;
import object.ObjectStock;

public class Tile {
	private int x;
	private int y;
	public Tile up;
	public Tile down;
	public Tile left;
	public Tile right;
	private ObjectStock<Object> content;
	private Feature feature;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		content = new ObjectStock<Object>();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setUp(Tile up) {
		this.up = up;
	}

	public void setDown(Tile down) {
		this.down = down;
	}

	public void setLeft(Tile left) {
		this.left = left;
	}

	public void setRight(Tile right) {
		this.right = right;
	}

	public Tile getUp() {
		return up;
	}

	public Tile getDown() {
		return down;
	}

	public Tile getLeft() {
		return left;
	}

	public Tile getRight() {
		return right;
	}

	public ObjectStock<Object> getObjects() {
		return content;
	}

	public void addObject(Object obj) {
		if (obj instanceof Feature)
			setFeature((Feature) obj);
		else
			obj.setPos(this);
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
		if (feature.getPos() != this)
			feature.setPos(this);
	}

	public Feature getFeature() {
		return feature;
	}

	public Tile getAdjacent(int pdir) {
		Tile pos = null;
		if (pdir == 1 && left != null) {
			pos = left;
		} else if (pdir == 2 && right != null) {
			pos = right;
		} else if (pdir == 3 && up != null) {
			pos = up;
		} else if (pdir == 4 && down != null) {
			pos = down;
		} else if (pdir == 5 && up != null) {
			if (up.left != null)
				pos = up.left;
		} else if (pdir == 6 && up != null) {
			if (up.right != null)
				pos = up.right;
		} else if (pdir == 7 && down != null) {
			if (down.left != null)
				pos = down.left;
		} else if (pdir == 8 && down != null) {
			if (down.right != null)
				pos = down.right;
		}
		return pos;
	}

	public TileStock getAdjacents() {
		TileStock result = new TileStock();
		result.addElement(this);
		for (int x = 1; x <= 4; x++) {
			if (getAdjacent(x) != null)
				result.addElement(getAdjacent(x));
		}
		return result;
	}

	public TileStock getLine(int dir, int length) {
		TileStock result = new TileStock();
		result.addElement(this);
		Tile t = this;
		for (int x = 0; x < length; x++) {
			t = t.getAdjacent(dir);
			if (t == null)
				break;
			result.addElement(t);
		}
		return result;
	}

	public Tile getDestination(int dir, int str) {
		Tile t = this;
		for (int x = 0; x < str; x++) {
			t = t.getAdjacent(dir);
			if (t == null)
				break;
		}
		return t;
	}

	// 1 to 4
	public TileStock getSpread(int pstr) {
		TileStock result = new TileStock();
		Integer[] dirs = { 1, 2, 3, 4 };
		Collections.shuffle(Arrays.asList(dirs));
		switch (pstr) {
		case 4:
			result.addElement(getAdjacent(dirs[3]));
		case 3:
			result.addElement(getAdjacent(dirs[2]));
		case 2:
			result.addElement(getAdjacent(dirs[1]));
		case 1:
			result.addElement(getAdjacent(dirs[0]));
		}
		return result;
	}

	public TileStock getRangeSpread(int pstr) { // Slow
		TileStock result = new TileStock();
		result.addElement(this);
		for (int x = 0; x < pstr; x++) {
			result.spread(4);
		}
		return result;
	}

	public TileStock getCrossSpread(int lenX, int lenY) {
		TileStock result = new TileStock();
		result.addAll(getLine(1, lenX));
		result.addAll(getLine(2, lenX));
		result.addAll(getLine(3, lenY));
		result.addAll(getLine(4, lenY));
		return result;
	}

	public TileStock getSquareSpread(int pstr) { // Fast
		TileStock result = new TileStock();
		Tile tmp = this;
		int up = 0;
		for (int x = 0; x < pstr; x++) {
			if (tmp.up != null) {
				tmp = tmp.up;
				up++;
			}
		}
		for (int y = 0; y < up + pstr + 1 && tmp.down != null; y++) {
			Tile lefty = tmp;
			for (int x = 0; x < pstr && lefty.getAdjacent(1) != null; x++) {
				lefty = lefty.getAdjacent(1);
				result.addElement(lefty);
			}
			Tile righty = tmp;
			for (int x = 0; x < pstr && righty.getAdjacent(2) != null; x++) {
				righty = righty.getAdjacent(2);
				result.addElement(righty);
			}
			result.addElement(tmp);
			tmp = tmp.down;
		}
		return result;
	}

	@Override
	public String toString() {
		String feat = "";
		String objs = "";
		if (getFeature() != null)
			feat = getFeature().getName();
		for (Object obj : getObjects()) {
			objs += obj.getName() + ",";
		}
		return "(" + getX() + "," + getY() + "," + feat + ",{" + objs + "})";
	}

	public int getTileDirection(Tile p) {
		int x = getX() - p.getX();
		int y = getY() - p.getY();
		int result = 0;
		if (Math.abs(x) >= Math.abs(y)) {
			if (x > 0) {
				result = 1;
			} else if (x < 0) {
				result = 2;
			}
		} else if (Math.abs(y) > Math.abs(x)) {
			if (y > 0) {
				result = 3;
			} else if (y < 0) {
				result = 4;
			}
		}
		return result;
	}
}
