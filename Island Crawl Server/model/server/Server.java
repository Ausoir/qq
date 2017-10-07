package server;

import controller.ObjectUpdater;
import gameplay.Setting;

public class Server implements Runnable {
	private ObjectUpdater objectUpdater;

	public Server(ObjectUpdater p) {
		objectUpdater = p;
	}

	private void update() {
		objectUpdater.update();
	}

	public void run() {
		while (true) {
			update();
			try {
				Thread.sleep(Setting.gameSpeed);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
