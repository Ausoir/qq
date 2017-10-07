package initialization;

import java.awt.image.BufferedImage;
import java.util.Vector;

import object.AIHost;
import object.InteractiveObject;
import object.ObjectStock;
import object.PictureStock;

public class ServerObjectLoader extends Loader {
	private static BufferedImage getImage(PictureStock sprite, String p){
		BufferedImage image;
		if (sprite != null)
			image = sprite.getByName(p).getImage();
		else
			image = null;
		return image;
	}
	
	public static ObjectStock<InteractiveObject> loadUnit(String dir,
			PictureStock sprite) {
		Vector<String[]> content = loadDefault(dir);
		ObjectStock<InteractiveObject> result = new ObjectStock<InteractiveObject>();
		for (String[] cells : content) {
			BufferedImage image;

			String name = cells[0];
			image = getImage(sprite, cells[1]);
			String loot = cells[2];
			int weight = Integer.parseInt(cells[3]);
			int health = Integer.parseInt(cells[4]);
			String faction = cells[5];
			String behavior = cells[6];
			int sight = Integer.parseInt(cells[7]);
			int moveSpeed = Integer.parseInt(cells[8]);
			
			AIHost obj = new AIHost(-1, name);
			obj.setFaction(faction);
			obj.setLoot(loot);
			obj.setWeight(weight);
			obj.setHealth(health);
			obj.setImage(image);
			obj.setNext(BehaviorLoader.getBehavior(behavior));
			obj.setSight(sight);
			obj.setController(UnitStatLoader.getStat(moveSpeed));
			result.addElement(obj);
		}
		return result;
	}
}
