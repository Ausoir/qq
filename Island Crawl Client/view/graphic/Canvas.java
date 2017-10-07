package graphic;

import gameplay.Setting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JFrame;

import ui.Log;

public class Canvas extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage defaultImage;
	private PerspectiveRenderer renderer;
	private float magnification = Setting.tileSize / Setting.textureSize;

	private final int logTextX;
	private final int logTextY;
	private final int textGap;

	public Canvas(PerspectiveRenderer renderer) {
		this.renderer = renderer;
		setVisible(true);
		createBufferStrategy(2);
		setSize(1024, 720);
		setVisible(true);
		setFocusable(true);

		logTextX = 50;
		logTextY = getHeight() - 20;
		textGap = 17;
	}

	public void paint(Graphics g) {
		BufferStrategy bs = getBufferStrategy(); // dblbuffer or pictures blink
		Graphics g2 = bs.getDrawGraphics();
		// Clear screen
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());

		renderer.render();
		draw(g2);
		renderer.clearQueue();

		drawLog(g2);

		bs.show();
		try {
			Thread.sleep(30);
		} catch (Exception e) {
			System.out.println("Thread Error");
			System.exit(1);
		}
		repaint();
	}

	private void draw(Graphics g2) {
		Vector<ImageObject> queue = renderer.getQueue();
		for (ImageObject pic : queue) {
			BufferedImage image = pic.image;
			if (image == null)
				image = defaultImage;
			g2.drawImage(image, pic.x, pic.y,
					(int) (image.getWidth() * magnification),
					(int) (image.getHeight() * magnification), null);

		}
	}

	private void drawLog(Graphics g2) {
		g2.fillRect(0, 600, getWidth(), getHeight());
		Vector<String> msgs = Log.getMessages();
		write(g2, msgs);
	}

	private void write(Graphics g2, Vector<String> msgs) {
		int nextY = 0;
		for (String msg : msgs) {
			int type = Integer.parseInt(msg.substring(0, 1));
			if (type == 0)
				g2.setColor(Color.white);
			if (type == 1)
				g2.setColor(Color.red);
			if (type == 1)
				g2.setColor(Color.green);
			g2.drawString(msg, logTextX, logTextY - nextY);
			nextY += textGap;
		}
	}

	public void setDefaultImage(BufferedImage image) {
		defaultImage = image;
	}
}