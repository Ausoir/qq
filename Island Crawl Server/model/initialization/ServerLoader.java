package initialization;

import map.Debugger;
import map.WorldController;
import object.InteractiveObject;
import object.Object;
import object.Feature;
import object.ObjectFactory;
import object.ObjectStock;
import server.Server;
import gameplay.Setting;
import connection.ClientReportManager;
import connection.Messager;
import connection.Listener;
import controller.ObjectController;
import controller.ObjectUpdater;

public class ServerLoader {
	public static void load() {
		System.out.println("Server Started");

		Listener listener = new Listener(Setting.portHostIn); // Receive
		Messager clients = new Messager(Setting.portHostOut); // Send
		clients.load(); //Load clients' PID using IP
		ClientReportManager crm = new ClientReportManager(clients);
		listener.setListener(crm);

		BehaviorLoader.load();
		ObjectStock<Feature> featureBatch = ObjectLoader.loadFeature(Setting.featureDirectory, null);
		ObjectStock<Object> objectBatch = ObjectLoader.loadObject(Setting.objectDirectory, null);
		ObjectStock<InteractiveObject> unitBatch = ServerObjectLoader.loadUnit(Setting.unitDirectory, null); //Cannot apply behavior here		
		ObjectFactory factory = new ObjectFactory(featureBatch, objectBatch, unitBatch);
		
		ObjectUpdater objectUpdater = new ObjectUpdater();
		ObjectStock<Object> objects = new ObjectStock<Object>();
		factory.setUpdater(objectUpdater);
		factory.setObjects(objects);
		ObjectController.setFactory(factory);

		MapLoader world;
		world = new MapLoader(factory,
				Setting.mapDirectory+Setting.mapName+Setting.mapExtension);
		world.load();
		factory.updateId();
		WorldController.setMap(world.getMap(), 0, world.getSizeX(), world.getSizeY());
		WorldController.setSpawnPoint(world.getSpawnPoint());

		Thread thread1 = new Thread(listener, "Server Listener Thread");
		thread1.start();

		Server game = new Server(objectUpdater);
		Thread thread2 = new Thread(game, "Game Thread");
		thread2.start();
		
		Debugger.start(crm);
	}
}
