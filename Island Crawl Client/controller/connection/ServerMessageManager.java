package connection;

import object.PlayerId;
import controller.ObjectController;
import controller.ObjectUpdater;

public class ServerMessageManager implements Importer, Ready {
	private boolean downloadComplete = false;
	private boolean commandReceiveReady = false;
	private ObjectUpdater updater;

	public void setUpdater(ObjectUpdater updater){
		this.updater = updater;
	}
	
	@Override
	public void order(Translater tl) {
//		System.out.println("SMM)"+tl.getAll());
		if (tl.getType() == ConnectionType.Connect)
			respondConnection(tl);
		else if (tl.getType() == ConnectionType.Command && commandReceiveReady)
			respondCommand(tl);
	}

	private void respondConnection(Translater tl) {
		System.out.println("Receiving map from server");
//		FileManager fm = new FileManager(Setting.mapDirectory + "default"
//				+ Setting.mapExtension);
//		fm.write(tl.getData());
		PlayerId.id = tl.getObjectId();
		TCPClient.download();
		// Get player id

		downloadComplete = true;
	}

	private void respondCommand(Translater tl) {
		Integer id = tl.getObjectId();
		if (id == null){
			new Exception().printStackTrace();
		}
		ObjectController.command(id, tl.getMessage());
		updater.update();
	}

	public boolean isReady() {
		return downloadComplete;
	}
	
	public void startCommandReceive(){
		commandReceiveReady = true;
	}
}
