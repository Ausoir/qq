package command;

/**
 *
 */
public class Invoker
{
	InputCommand[] commands;
	/**
	 * Set the key to whatever is pressed on the keyboard.
	 * @param numCommands
	 * 
	 */
	
	public Invoker(int numCommands)
	{
		commands = new InputCommand[numCommands];
	}
	
	/**
	 * @param c the command
	 * @param index the index we want
	 */
	public void setKey(InputCommand c, int index)
	{
		commands[index] = c;
	}
	
	/**
	 * executes the command at the index
	 * @param index the specific command we want
	 */
	public void invoke(int index, boolean p)
	{
		commands[index].execute(p);
	}
	
}
