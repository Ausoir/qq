package graphic;

import gameplay.Setting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureManager {
	private static BufferedImage image;

	public static void load(String dir) {
		setImage(dir);
	}
	
	public static void setImage(String dir){
		try {
			image = ImageIO.read(new File(dir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getTexture(int px, int py) {
		BufferedImage result = image
				.getSubimage(px * Setting.textureSize,
						py * Setting.textureSize, Setting.textureSize,
						Setting.textureSize);
		return Transparency.makeColorTransparent(result, new Color(255, 174,
				201));
	}
}