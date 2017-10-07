package command;

import connection.Listenable;

public class Order implements InputCommand
{
	String order;
	Listenable sender;
	
	/**
	 * Constructor sets the variables.
	 * @param p the player
	 */
	public Order(String p_order, Listenable p_sender)
	{
		order = p_order;
		sender = p_sender;
	}
	
	public void execute(boolean p)
	{
		sender.order(order);
	}
}