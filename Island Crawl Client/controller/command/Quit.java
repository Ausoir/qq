package command;

import connection.Listenable;

public class Quit implements InputCommand
{
	Listenable sender;
	
	public Quit(Listenable p_sender)
	{
		sender = p_sender;
	}
	
	public void execute(boolean p)
	{
		sender.order("ISCDR");
		System.exit(0);
	}

}
