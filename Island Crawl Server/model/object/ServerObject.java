package object;

import connection.CommandManager;

public class ServerObject extends InteractiveObject implements Time {

	protected int actionPoint;

	public ServerObject(int id, String name) {
		super(id, name);
	}

	public boolean actable() {
		if (actionPoint > 0 && !command.isEmpty())
			return true;
		return false;
	}

	@Override
	public ServerObject getInstance(int id) {
		ServerObject result = new ServerObject(id, getName());
		result.setImage(getImage());
		return result;
	}

	@Override
	protected int translateCommand(String p) {
		//System.out.println("ServerObject)" + getName() + " issued " + p +" and has "+actionPoint);
		CommandManager.sendCommand(p + "@" + id);
		return super.translateCommand(p);
	}

	@Override
	protected void act() {
		if (actionPoint >= 0 && !command.isEmpty()) {
			String cmd = command;
			actionPoint -= translateCommand(cmd);
		} else if (actionPoint < 0)
			actionPoint++;
	}
}
