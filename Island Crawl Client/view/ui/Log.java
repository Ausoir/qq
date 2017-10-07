package ui;

import java.util.LinkedList;
import java.util.Vector;

public class Log {
	private static LinkedList<String> messages = new LinkedList<String>();

	public static void message(String p, int type) {
		if (messages.size() > 5){
			messages.pollLast();
		}
		messages.addFirst(type+p);
	}

	public static Vector<String> getMessages() {
		return new Vector<String>(messages);
	}
}
