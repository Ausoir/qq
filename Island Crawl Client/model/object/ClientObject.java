package object;

import gameplay.Setting;
import ui.Log;

public class ClientObject extends InteractiveObject {
	private int smoothMoveX;
	private int smoothMoveY;
	
	public ClientObject(InteractiveObject p){
		this(p.getId(), p.getName());
		setImage(p.getImage());
		if (p.getPos() != null)
			setPos(p.getPos());
		setController(p.getController());
	}

	public ClientObject(int id, String name) {
		super(id, name);
	}

	@Override
	public boolean move(){
		boolean result = super.move();
		if (result){
			setSmoothMove();
			Log.message(getPos().toString(), 0);
		}
		return result;
	}
	
	public int getSmoothMoveX() {
		return smoothMoveX;
	}

	public int getSmoothMoveY() {
		return smoothMoveY;
	}

	public void updateSmoothMove() {
		int speed = 4;
		
		if (smoothMoveX > 0)
			smoothMoveX-=speed;
		else if (smoothMoveX < 0)
			smoothMoveX+=speed;

		if (smoothMoveY > 0)
			smoothMoveY-=speed;
		else if (smoothMoveY < 0)
			smoothMoveY+=speed;
	}

	public void setSmoothMove() {
		int dir = getDir();
		if (dir == 1) {
			smoothMoveX = -Setting.tileSize;
		} else if (dir == 2) {
			smoothMoveX = Setting.tileSize;
		} else if (dir == 3) {
			smoothMoveY = -Setting.tileSize;
		} else if (dir == 4) {
			smoothMoveY = Setting.tileSize;
		}
	}
}
