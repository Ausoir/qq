package object;

import command.InteractiveController;

public class InteractiveObject extends Object implements Time { // Make abstract
	protected InteractiveController ic;
	protected String command; // used to be Queue

	public InteractiveObject(int id, String name) {
		super(id, name, null);
		ic = new InteractiveController(this);
		clearThought();
	}
	
	public InteractiveController getController(){
		return ic;
	}
	
	public void setController(InteractiveController ic){
		this.ic = ic;
		ic.setHost(this);
	}

	public void clearThought() {
		command = "";
	}

	public void order(String p) {
		command = p;
	}

	@Override
	public InteractiveObject getInstance(int id) {
		InteractiveObject result = new InteractiveObject(id, getName());
		result.setImage(getImage());
		result.setController(ic);
		return result;
	}

	public boolean move() {
		int dir = getDir();
		if (dir == 1) {
			if (pos.left == null)
				return false;
			setPos(pos.left);
		} else if (dir == 2) {
			if (pos.right == null)
				return false;
			setPos(pos.right);
		} else if (dir == 3) {
			if (pos.up == null)
				return false;
			setPos(pos.up);
		} else if (dir == 4) {
			if (pos.down == null)
				return false;
			setPos(pos.down);
		}
//		 System.out.println("Unit)"+pos.toString());

		return true;
	}

	@Override
	public void update() {
		if (!command.isEmpty()) {
			act();
		}
	}

	//action taken every turn
	protected void act() {
		// System.out.println("Unit)"+getName()+" issued "+command);
		translateCommand(command);
		clearThought();
	}
	
	protected int translateCommand(String p){
		return ic.translateCommand(p);
	}

}
