package command;

import object.InteractiveObject;

public class Move implements InteractiveCommand {
	private InteractiveObject host;
	private int cost = 3;

	public Move(InteractiveObject p) {
		host = p;
	}

	@Override
	public void execute(String p) {
		host.move();
	}

	@Override
	public final String getCode() {
		return "m";
	}

	@Override
	public final int getCost() {
		return cost;
	}

	@Override
	public void setCost(int p) {
		// TODO Auto-generated method stub
		cost = p;
	}

	@Override
	public void setHost(InteractiveObject p) {
		host = p;
	}

}
