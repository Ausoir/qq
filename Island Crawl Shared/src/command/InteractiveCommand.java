package command;

import object.InteractiveObject;

public abstract interface InteractiveCommand {
	
	public void setHost(InteractiveObject p);

	public String getCode();

	public int getCost();
	
	public void setCost(int p);

	public void execute(String p);
}
