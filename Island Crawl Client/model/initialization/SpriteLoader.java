package initialization;

import java.awt.image.BufferedImage;
import java.util.Vector;

import object.PictureStock;
import gameplay.Setting;
import graphic.Picture;
import graphic.TextureManager;

public class SpriteLoader extends Loader {
	private static final String dir = Setting.imageDirectory + "imageMap"
			+ Setting.defaultExtension;

	public static PictureStock load() {
		Vector<String[]> content = loadDefault(dir);
		PictureStock result = new PictureStock();
		for (String[] cells : content) {
			int iCell1 = Integer.parseInt(cells[1]);
			int iCell2 = Integer.parseInt(cells[2]);
			BufferedImage image = TextureManager.getTexture(iCell1, iCell2);
			Picture picture = new Picture(cells[0], image);
			result.addElement(picture);
		}
		return result;
	}
}
