package graphic;

import java.awt.image.BufferedImage;

public class Picture {
	private String name;
	private BufferedImage image;

	public Picture(String name, BufferedImage image) {
		setName(name);
		setImage(image);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}