package initialization;

import java.awt.image.BufferedImage;
import java.util.Vector;

import object.Feature;
import object.Object;
import object.ObjectStock;
import object.PictureStock;
import object.InteractiveObject;

public class ObjectLoader extends Loader {
	
	//Could combine with loadObject using Class<T> function but cannot access MaterialObject
	public static ObjectStock<InteractiveObject> loadUnit(String dir, PictureStock sprite) {
		Vector<String[]> content = loadDefault(dir);
		ObjectStock<InteractiveObject> result = new ObjectStock<InteractiveObject>();
		for (String[] cells : content) {
			BufferedImage image;
			if (sprite != null)
				image = sprite.getByName(cells[1]).getImage();
			else
				image = null;

			InteractiveObject obj = new InteractiveObject(-1, cells[0]);
			obj.setImage(image);
			result.addElement(obj);
		}
		return result;
	}

	public static ObjectStock<Object> loadObject(String dir, PictureStock sprite) {
		Vector<String[]> content = loadDefault(dir);
		ObjectStock<Object> result = new ObjectStock<Object>();
		for (String[] cells : content) {
			BufferedImage image;
			if (sprite != null)
				image = sprite.getByName(cells[1]).getImage();
			else
				image = null;

			Object obj = new Object(-1, cells[0], image);
			result.addElement(obj);
		}
		return result;
	}

	public static ObjectStock<Feature> loadFeature(String dir, PictureStock sprite) {
		Vector<String[]> content = loadDefault(dir);
		ObjectStock<Feature> result = new ObjectStock<Feature>();
		for (String[] cells : content) {

			BufferedImage image;
			if (sprite != null)
				image = sprite.getByName(cells[1]).getImage();
			else
				image = null;
			String property = cells[2];
			Feature obj = null;
			if (property.equals("ground")){
				obj = new Feature(-1, cells[0], image, cells[2]);
			}else if (property.equals("water")){
				obj = new Feature(-1, cells[0], image, cells[2]);
			}else if (property.equals("tide")){
				obj = new Feature(-1, cells[0], image, cells[2]);
			}
			result.addElement(obj);
		}
		return result;
	}
}
