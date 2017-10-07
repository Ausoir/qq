package connection;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import file.FileManager;
import gameplay.Setting;

public class Messager implements Listenable {
	private Vector<Client> clients;
	private UDPClient udp;

	public Messager(int port) {
		udp = new UDPClient(port);
		clients = new Vector<Client>();
	}
	
	public void sendUsingIP(InetAddress ip, String msg){
		new Sender(udp, ip, Setting.portClientIn).order(msg);
	}
	
	public void addClient(Listenable client){
		clients.addElement(new Client(client, null, -1));
	}

	//true if new client
	public boolean addClient(InetAddress clientIP, int id) {
		Client client = getClientByIP(clientIP);
		if (client == null) {
			client = new Client(new Sender(udp, clientIP, Setting.portClientIn), clientIP, id);
			clients.addElement(client);
			return true;
		}
		return false;
	}

	public void removeClient(InetAddress clientIP) {
		clients.remove(getClientByIP(clientIP));
	}

	public Client getClientByIP(InetAddress ip) {
		for (Client sender : clients) {
			if (sender.getIp().equals(ip))
				return sender;
		}
		return null;
	}

	public void order(String p) {
		for (Client client : clients) {
			client.getSender().order(p); // Translater.translateExport(p)
		}
	}
	
	public Vector<String> toStringVector(){
		Vector<String> history = new Vector<String>();
		for (Client client : clients){
			history.addElement(client.getIp().getHostAddress()+","+client.getId());
		}
		return history;
	}
	
	public void save(){
		FileManager fm = new FileManager(Setting.mapDirectory+Setting.mapName+".dat");
		fm.write(toStringVector());
	}

	public void load() {
		FileManager fm = new FileManager(Setting.mapDirectory+Setting.mapName+".dat");
		Vector<String> content = fm.read();
		for (String line : content){
			String[] cells = line.split(",");
			try {
				InetAddress ip = InetAddress.getByName(cells[0]);
				int id = Integer.parseInt(cells[1]);
				clients.addElement(new Client(new Sender(udp, ip, Setting.portClientIn),ip,id));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
}