package connection;


public class ClientReportManager implements Importer {
	private Messager msger;

	public ClientReportManager(Messager msger) {
		this.msger = msger;
		CommandManager.setMsger(msger);
	}

	@Override
	public void order(Translater tl) {
		if (tl.getType() == ConnectionType.Command)
			CommandManager.respondCommand(tl);
		else if (tl.getType() == ConnectionType.Connect)
			ConnectionManager.respondConnection(tl, msger);
		else if (tl.getType() == ConnectionType.Disconnect)
			ConnectionManager.respondDisconnection(tl, msger);
	}
}
