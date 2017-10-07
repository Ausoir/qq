package command;

import object.InteractiveObject;

public class FaceMove implements InteractiveCommand {
	private Move move;
	private Face face;

	public FaceMove(Move move, Face face) {
		this.move = move;
		this.face = face;
	}

	@Override
	public void execute(String p) {
		face.execute(p);
		move.execute(p);
	}

	@Override
	public final String getCode() {
		// TODO Auto-generated method stub
		return "M";
	}

	@Override
	public final int getCost() {
		// TODO Auto-generated method stub
		return move.getCost() + face.getCost();
	}

	@Override
	public void setCost(int p) {

	}

	@Override
	public void setHost(InteractiveObject p) {
		move.setHost(p);
		face.setHost(p);
	}


}
