package connection;

import controller.ObjectController;
import map.WorldController;
import object.GenerateId;
import gameplay.Setting;

public class ConnectionManager {
	public static void respondConnection(Translater tl, Messager msger) {
		boolean firstTime = msger.getClientByIP(tl.getIP()) == null ? true
				: false;
		int id;

		if (firstTime) {
			System.out
					.println("Client connected from " + tl.getIP().toString());
			id = GenerateId.generate();
			msger.addClient(tl.getIP(), id);
			ObjectController.spawnUnit(id, WorldController.getSpawnPoint());
			msger.save(); // Add client to history
		} else {
			System.out.println("Client reconnected from "
					+ tl.getIP().toString());
			id = msger.getClientByIP(tl.getIP()).getId();
		}
		msger.order(ConnectionType.Connect.getCode() + id);
		WorldController.save(Setting.mapDirectory + Setting.mapName
				+ Setting.mapExtension);
		TCPServer.send(); // Send map socket loop
	}

	// private static void sendMap(Messager msger) {
	// Vector<String> map = WorldController.toStringVector();
	// msger.order(ConnectionType.Start.getCode());
	// for (String content : map) {
	// msger.order(ConnectionType.Data.getCode() + content);
	// }
	// msger.order(ConnectionType.End.getCode());
	// }

	public static void respondDisconnection(Translater tl, Messager msger) {
		System.out.println("Client disconnected from " + tl.getIP().toString());
		// msger.removeClient(tl.getIP());
	}
}
