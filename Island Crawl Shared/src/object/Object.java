package object;

import java.awt.image.BufferedImage;

import map.Tile;

//Not abstract, because some are neither feature or material like rocks
public class Object {
	private String name;
	protected int id;
	protected Tile pos;
	private BufferedImage image;
	private int dir = 4;

	public Object(int id, String name, BufferedImage image) {
		this.id = id;
		this.image = image;
		this.name = name;
	}

	public Object getInstance(int id) {
		return new Object(id, getName(), getImage());
	}

	public Object getNewInstance() {
		return new Object(GenerateId.generate(), getName(), getImage());
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object setImage(BufferedImage image) {
		this.image = image;
		return this;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Tile getPos() {
		return pos;
	}

	public void setPos(Tile pos) {
		if (getPos() != null){
			getPos().getObjects().remove(this);
		}
		this.pos = pos;
		pos.getObjects().addElement(this);
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public boolean equals(Object obj) {
		if (name.equals(obj.name))
			return true;
		return false;
	}
	
	public String toString(){
		return "("+super.toString()+":"+getName()+")";
	}
}
