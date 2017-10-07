package graphic;

import java.awt.image.BufferedImage;
import java.util.Vector;

import map.TileStock;
import gameplay.Setting;
import object.ClientObject;
import object.Object;
import object.ObjectStock;

public class PerspectiveRenderer {

	private Vector<ImageObject> drawQueue = new Vector<ImageObject>();
	private ClientObject viewer;

	public PerspectiveRenderer(ClientObject viewer) {
		this.viewer = viewer;
	}

	private ObjectStock<Object> getRenderObjects() {
		ObjectStock<Object> result = new ObjectStock<Object>();
		TileStock los = viewer.getPos().getSquareSpread(Setting.viewSize);
		result.addAll(los.getFeatures());
		result.addAll(los.getObjects());
		return result;
	}

	public Vector<ImageObject> getQueue() {
		return drawQueue;
	}

	private void addQueue(BufferedImage image, int x, int y) {
		drawQueue.addElement(new ImageObject(image, x, y));
	}

	public void clearQueue() {
		drawQueue = new Vector<ImageObject>();
	}

	public void render() {
		for (Object obj : getRenderObjects()) {
			if (obj != viewer){
				int absX = obj.getPos().getX();
				int absY = obj.getPos().getY();
				int x = absX - viewer.getPos().getX();
				int y = absY - viewer.getPos().getY();
				int relativeX = Setting.centerX + x * Setting.tileSize + viewer.getSmoothMoveX();
				int relativeY = Setting.centerY + y * Setting.tileSize + viewer.getSmoothMoveY();
				if (obj instanceof ClientObject){
					ClientObject unit = (ClientObject) obj;
					unit.updateSmoothMove();
					relativeX = relativeX - unit.getSmoothMoveX();
					relativeY = relativeY - unit.getSmoothMoveY();
				}
				addQueue(obj.getImage(), relativeX, relativeY);
			}
		}
		addQueue(viewer.getImage(), Setting.centerX, Setting.centerY);
		viewer.updateSmoothMove();
	}
}
