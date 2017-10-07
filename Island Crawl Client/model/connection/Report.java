package connection;

import object.PlayerId;

public class Report implements Listenable {
	private Sender server;

	public Report(Sender server) {
		this.server = server;
	}

	public void connectReport() {
		//Island Crawl Client Connection Report
		server.order(ConnectionType.Connect.getCode());
	}

	public void disconnectReport() {
		//Island Crawl Client Connection Report
		server.order(ConnectionType.Disconnect.getCode());
	}
	
	public void commandReport(String p){
		server.order(ConnectionType.Command.getCode()+p+"@"+PlayerId.id);
	}

	@Override
	public void order(String p) {
		commandReport(p);
	}
}
