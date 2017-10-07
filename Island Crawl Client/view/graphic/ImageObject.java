package graphic;

import java.awt.image.BufferedImage;

public class ImageObject {
	public BufferedImage image;
	public int x;
	public int y;

	public ImageObject(BufferedImage image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
}