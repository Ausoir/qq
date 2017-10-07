package object;

import java.awt.image.BufferedImage;

import map.Tile;

public class Feature extends Object {
	private String property;

	public Feature(int id, String name, BufferedImage image, String property) {
		super(id, name, image);
		this.property = property;
	}

	@Override
	public Feature getInstance(int id) {
		return new Feature(id, getName(), getImage(), property);
	}

	@Override
	public void setPos(Tile pos) {
		this.pos = pos;
		if (pos.getFeature() != this)
			pos.setFeature(this);
	}
	
	public static Feature getInstance(Feature obj) {
		return new Feature(obj.id, obj.getName(), obj.getImage(), obj.property);
	}

	@Override
	public Object getNewInstance() {
		return new Feature(GenerateId.generate(), getName(), getImage(), property);
	}
}
