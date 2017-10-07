package object;

public class GenerateId {
	private static int id = 0;

	public static int generate() {
		id++;
		return id;
	}

	public static void setId(int id2) {
		id = id2;
	}
}
