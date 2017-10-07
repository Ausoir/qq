package command;

import object.InteractiveObject;

public class Face implements InteractiveCommand {
	private InteractiveObject host;
	
	public Face(InteractiveObject p) {
		host = p;
	}

	@Override
	public void execute(String p) {
		int direction = Integer.parseInt(p);
		host.setDir(direction);
	}

	@Override
	public final String getCode() {
		// TODO Auto-generated method stub
		return "f";
	}
	
	public final String getCode(int p) {
		// TODO Auto-generated method stub
		return "f"+p;
	}

	@Override
	public final int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCost(int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHost(InteractiveObject p) {
		host = p;
	}


}
