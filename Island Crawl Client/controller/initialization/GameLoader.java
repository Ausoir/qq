package initialization;

import java.awt.event.WindowEvent;
import java.net.InetAddress;

import command.InputConstructor;
import connection.Report;
import connection.Listener;
import connection.Sender;
import connection.ServerMessageManager;
import connection.Timeout;
import connection.UDPClient;
import controller.ObjectController;
import controller.ObjectUpdater;
import object.ClientObject;
import object.ClientObjectFactory;
import object.Feature;
import object.InteractiveObject;
import object.Object;
import object.ObjectStock;
import object.PictureStock;
import object.PlayerId;
import gameplay.Setting;
import graphic.Canvas;
import graphic.PerspectiveRenderer;
import graphic.TextureManager;

public class GameLoader {
	public static boolean readyFlag = false;
	private static Report report;
	private static ServerMessageManager smm;

	public static void load(InetAddress ip) {
		System.out.println("Client Started");
		connect(ip);
		loadGame();
	}

	public static void loadGame() {
		System.out.println("Game started");
		
		ObjectStock<Object> objects = new ObjectStock<Object>();
		ObjectUpdater objectUpdater = new ObjectUpdater();
		smm.setUpdater(objectUpdater);
		
		TextureManager.load(Setting.imageDirectory+"tile"+Setting.imageExtension);
		PictureStock sprites = SpriteLoader.load();
		ObjectStock<Feature> featureBatch = ObjectLoader.loadFeature(Setting.featureDirectory,sprites);
		ObjectStock<Object> objectBatch = ObjectLoader.loadObject(Setting.objectDirectory,sprites);
		ObjectStock<InteractiveObject> unitBatch = ObjectLoader.loadUnit(Setting.unitDirectory,sprites);
		ClientObjectFactory factory = new ClientObjectFactory(featureBatch, objectBatch, unitBatch);
		factory.setObjects(objects);
		factory.setUpdater(objectUpdater);

		ObjectController.setFactory(factory);
		
		MapLoader world;
		world = new MapLoader(factory,
				Setting.mapDirectory+"default"+Setting.mapExtension);
		world.setPlayerId(PlayerId.id);
		world.load();
		ClientObject player = (ClientObject) world.getPlayer();
		
		System.out.println("Client is ready");
		
		PerspectiveRenderer renderer = new PerspectiveRenderer(player);
		Canvas canvas = new Canvas(renderer); // 1st Thread
		canvas.setDefaultImage(sprites.getByName("Missing").getImage());

		InputConstructor ic = new InputConstructor(report);
		canvas.addKeyListener(ic);

		smm.startCommandReceive();
		
		canvas.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				report.disconnectReport();
				System.exit(0);
			}
		});
	}

	public static void connect(InetAddress ip) {
		Listener listener = new Listener(Setting.portClientIn);
		UDPClient udp = new UDPClient(Setting.portClientOut);
		Sender server = new Sender(udp, ip, Setting.portHostIn);
		report = new Report(server);
		smm = new ServerMessageManager();

		listener.setListener(smm);
		Thread thread1 = new Thread(listener, "Client Listener Thread");
		thread1.start(); // 2nd Thread
		report.connectReport();

		System.out.println("Waiting for a response from server...");
		Timeout.wait(smm, 1000);
	}
}
