package connection;

import java.net.InetAddress;

public class Client {
	private Listenable sender;
	private InetAddress ip;
	private int id;

	public Client(Listenable sender, InetAddress ip, int id) {
		this.sender = sender;
		this.ip = ip;
		this.id = id;
	}

	public Listenable getSender() {
		return sender;
	}

	public InetAddress getIp() {
		return ip;
	}

	public int getId() {
		return id;
	}
}
