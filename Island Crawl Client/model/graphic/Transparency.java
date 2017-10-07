package graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

/**
 * All code on this page:
 * http://marxsoftware.blogspot.com/2011/04/making-white-image-backgrounds.html
 */
public class Transparency {
	private static BufferedImage imageToBufferedImage(final Image image) {
		final BufferedImage bufferedImage = new BufferedImage(
				image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		return bufferedImage;
	}

	public static BufferedImage makeColorTransparent(final BufferedImage im,
			final Color color) {
		final ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB(); // 0xFFFFFFFF for white
													// background transparency

			public final int filterRGB(final int x, final int y, final int rgb) {
				if ((rgb) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF;
				} else {
					return rgb;
				}
			}
		};

		final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return imageToBufferedImage(Toolkit.getDefaultToolkit().createImage(ip));
	}
}