package connection;

public class Listener implements Runnable {
	private Host receiver;
	private Importer listener;
	private Translater tl;

	public Listener(int port) {
		receiver = new Receiver(new UDPClient(port));
		tl = new Translater();
	}

	public void setListener(Importer p) {
		listener = p;
	}

	public Importer getListener() {
		return listener;
	}

	public void run() {
		while (true) {
			String cmd = receiver.receive();
			if (cmd != null) {
				tl.translate(cmd);
				tl.setIP(receiver.getIp());
				listener.order(tl);
			}
		}
	}
}