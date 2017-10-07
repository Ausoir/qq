package connection;

import java.net.InetAddress;
import java.util.Vector;

public class Translater {

	private InetAddress ip;
	private ConnectionType type;
	private String message;
	private String all;
	private int objectId;

	private Vector<String> data;
	private boolean dataReady = false;

	public void translate(String msg) {
		String content = msg.split(";")[0];
		all = content;
		type = ConnectionType.getEnum(content.substring(0, 2));
		translateOther(content);
	}

	public void translateOther(String p) {
		String info = null;
		if (p.length() > 2)
			info = p.substring(2);
		if (type == ConnectionType.Data) {
//			System.out.println("Translater)"+info);
			data.addElement(info);
		} else if (type == ConnectionType.Start) {
			data = new Vector<String>();
			dataReady = false;
		} else if (type == ConnectionType.End) {
			dataReady = true;
		} else if (type == ConnectionType.Command) {
			String data[] = info.split("@");
			message = data[0];
			objectId = Integer.parseInt(data[1]);
		} else if (type == ConnectionType.Connect) {
			if (info != null)
				objectId = Integer.parseInt(info);
		}
	}

	public boolean isDataReady() {
		return dataReady;
	}

	public int getObjectId() {
		return objectId;
	}

	public Vector<String> getData() {
		return data;
	}
	
	public void displayToConsole(){
		System.out.println(all);
	}

	public String getAll() {
		return all;
	}

	public String getMessage() {
		return message;
	}

	public void setIP(InetAddress ip) {
		this.ip = ip;
	}

	public InetAddress getIP() {
		return ip;
	}

	public ConnectionType getType() {
		return type;
	}

	public static String toString(String msg, InetAddress ip) {
		String result = "IP:" + ip.getHostAddress() + "," + msg;
		return result;
	}
}
