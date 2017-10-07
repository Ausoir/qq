package command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import connection.Listenable;

/**
 * connects the commands with their index
 */
public class InputConstructor implements KeyListener
{
	private Invoker i = new Invoker(30);
	//private Thread thread;
	
	private final static int Quit = 0;
	//private final static int Pause = 7;
	private final static int Left = 1;
	private final static int Right = 2;
	private final static int Up = 3;
	private final static int Down = 4;
	private final static int SelectLeft = 5;
	private final static int SelectRight = 6;
	private final static int Drop = 7;
	private final static int Attack = 8;
	private final static int Throw = 9;
	private final static int Release = 10;
	private final static int Look = 11;
	private final static int Stat = 12;
	private final static int AtkLeft = 13;
	private final static int AtkRight = 14;
	private final static int AtkUp = 15;
	private final static int AtkDown = 16;
	private final static int Grab = 17;
	private final static int Build = 18;
	private final static int Cancel = 20;
	private final static int Num1 = 21;
	private final static int Num2 = 22;
	private final static int Num3 = 23;
	private final static int Num4 = 24;
	private final static int Num5 = 25;
	
	public InputConstructor(Listenable p_server)
    {
//		thread = p_th;
		i.setKey(new Quit(p_server), Quit);
		//i.setKey(new Pause(thread), Pause);
        i.setKey(new Order("cc", p_server), Release);
        i.setKey(new Order("M1", p_server), Left);
        i.setKey(new Order("M2", p_server), Right);
		i.setKey(new Order("M3", p_server), Up);
		i.setKey(new Order("M4", p_server), Down);
        i.setKey(new Order("se 1", p_server), SelectLeft);
        i.setKey(new Order("se 2", p_server), SelectRight);
        i.setKey(new Order("drop", p_server), Drop);
        i.setKey(new Order("grab", p_server), Grab);
        i.setKey(new Order("at", p_server), Attack);
        i.setKey(new Order("thro", p_server), Throw);
        
        i.setKey(new Order("bild", p_server), Build);
        i.setKey(new Order("stat", p_server), Stat);
        i.setKey(new Order("look", p_server), Look);
        i.setKey(new Order("canc", p_server), Cancel);

        i.setKey(new Order("f1,at", p_server), AtkLeft);
        i.setKey(new Order("f2,at", p_server), AtkRight);
        i.setKey(new Order("f3,at", p_server), AtkUp);
        i.setKey(new Order("f4,at", p_server), AtkDown);

        i.setKey(new Order("nm 1", p_server), Num1);
        i.setKey(new Order("nm 2", p_server), Num2);
        i.setKey(new Order("nm 3", p_server), Num3);
        i.setKey(new Order("nm 4", p_server), Num4);
        i.setKey(new Order("nm 5", p_server), Num5);
    }
	
	@Override
	public void keyPressed(KeyEvent k)
	{
		if (k.getKeyCode() == KeyEvent.VK_LEFT)
			i.invoke(Left, true);
		if (k.getKeyCode() == KeyEvent.VK_RIGHT)
			i.invoke(Right, true);
		if (k.getKeyCode() == KeyEvent.VK_UP)
			i.invoke(Up, true);
		if (k.getKeyCode() == KeyEvent.VK_DOWN)
			i.invoke(Down, true);
		
		if (k.getKeyCode() == KeyEvent.VK_A)
			i.invoke(AtkLeft, true);
		if (k.getKeyCode() == KeyEvent.VK_D)
			i.invoke(AtkRight, true);
		if (k.getKeyCode() == KeyEvent.VK_W)
			i.invoke(AtkUp, true);
		if (k.getKeyCode() == KeyEvent.VK_S)
			i.invoke(AtkDown, true);
		if (k.getKeyCode() == KeyEvent.VK_Q)
			i.invoke(SelectLeft, true);
		if (k.getKeyCode() == KeyEvent.VK_E)
			i.invoke(SelectRight, true);
		if (k.getKeyCode() == KeyEvent.VK_SPACE)
			i.invoke(Attack, true);
		if (k.getKeyCode() == KeyEvent.VK_C)
			i.invoke(Grab, true);
		if (k.getKeyCode() == KeyEvent.VK_X)
			i.invoke(Drop, true);
		if (k.getKeyCode() == KeyEvent.VK_Z)
			i.invoke(Throw, true);
		if (k.getKeyCode() == KeyEvent.VK_B)
			i.invoke(Build, true);

		if (k.getKeyCode() == KeyEvent.VK_1)
			i.invoke(Num1, true);
		if (k.getKeyCode() == KeyEvent.VK_2)
			i.invoke(Num2, true);
		if (k.getKeyCode() == KeyEvent.VK_3)
			i.invoke(Num3, true);
		if (k.getKeyCode() == KeyEvent.VK_4)
			i.invoke(Num4, true);
		if (k.getKeyCode() == KeyEvent.VK_5)
			i.invoke(Num5, true);

		if (k.getKeyCode() == KeyEvent.VK_ESCAPE)
			i.invoke(Quit, true);
		if (k.getKeyCode() == KeyEvent.VK_SEMICOLON)
			i.invoke(Stat, true);
		if (k.getKeyCode() == KeyEvent.VK_L)
			i.invoke(Look, true);
		if (k.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			i.invoke(Cancel, true);
	}

	@Override
	public void keyReleased(KeyEvent k)
	{
//		if (k.getKeyCode() == KeyEvent.VK_LEFT)
//			i.invoke(Left, false);
//		if (k.getKeyCode() == KeyEvent.VK_RIGHT)
//			i.invoke(Right, false);
//		if (k.getKeyCode() == KeyEvent.VK_UP)
//			i.invoke(Up, false);
//		if (k.getKeyCode() == KeyEvent.VK_DOWN)
//			i.invoke(Down, false);
		i.invoke(Release, false);
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}
}
