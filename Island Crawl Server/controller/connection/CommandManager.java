package connection;

import connection.Messager;
import connection.Translater;
import controller.ObjectController;

public class CommandManager {
	private static Messager msger;

	public static void setMsger(Messager msger) {
		CommandManager.msger = msger;
	}

	public static void respondCommand(Translater tl) {
		int id = msger.getClientByIP(tl.getIP()).getId();
//		String[] multiCommand = tl.getMessage().split(",");
//		for (String cmd : multiCommand) {
			command(tl.getMessage(), id);
//		}
	}

	private static void command(String msg, int id) {
		ObjectController.command(id, msg);
	}

	public static void sendCommand(String p) {
//		System.out.println("CommandManager)"+ConnectionType.Command.getCode() + p);
		msger.order(ConnectionType.Command.getCode() + p);
	}
}
