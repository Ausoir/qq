package connection;

import java.net.InetAddress;

public class Sender implements Listenable {
	private String lastorder = "";
	private UDPClient udp;
	private int client;
	private InetAddress ip;

	public Sender(UDPClient pudp, InetAddress pip, int pclient) {
		udp = pudp;
		ip = pip;
		client = pclient;
	}

	public InetAddress getIP() {
		return ip;
	}

	public void order(String p) {
		p = p+";";
		if (!lastorder.equals(p)) {
//			lastorder = p;
			try {
				// System.out.println("Sender.order(): The ip:"+ip[0]+":"+client+" , The msg:"+p);
				if (p.length() > udp.size()) {
					String[] tmp = new String[p.length() / udp.size()];
					for (int x = 0; x <= tmp.length; x++) {
						if (x == tmp.length)
							udp.send(p.substring(x * udp.size()), ip, client); // leftovers
						else
							udp.send(
									p.substring(x * udp.size(),
											(x + 1) * udp.size()), ip, client);
					}
				} else
					udp.send(p, ip, client);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}