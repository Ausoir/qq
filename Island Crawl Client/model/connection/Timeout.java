package connection;

public class Timeout {
	public static void wait(Ready condition, int timeout){
		int elapsed = 0;
		while (!condition.isReady() && elapsed < timeout){
			try {
				elapsed++;
				Thread.sleep(10);
			} catch (Exception e) {
				System.out.println("Thread Error");
				System.exit(1);
			}
		}
		if (elapsed >= timeout){
			System.out.println("Timeout to server");
			new Exception();
			System.exit(-1);
		}
	}
}
