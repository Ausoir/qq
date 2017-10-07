package initialization;

import file.FileManager;

import java.util.Vector;

public abstract class Loader {

// Cannot override static methods
//	public static String getDir() {
//		return null;
//	}
//
//	public static String getName() {
//		return null;
//	}
//
//	public static String getExt() {
//		return null;
//	}

	public static Vector<String[]> loadDefault(String dir) {
		// TODO: Read multiple files
		FileManager fm = new FileManager(dir);
		Vector<String> content = fm.read();
		Vector<String[]> result = new Vector<String[]>();
		for (String string : content) {
			if (string.toCharArray()[0] != '#') {
				String[] cell = string.split(",");
				result.addElement(cell);
			}
		}
		return result;
	}

	public static Vector<Vector<String[]>> loadBySection(String dir) {
		FileManager fm = new FileManager(dir);
		Vector<String> content = fm.read();
		Vector<Vector<String[]>> result = new Vector<Vector<String[]>>();
		Vector<String[]> section = new Vector<String[]>();
		for (String string : content) {
			char flag = string.toCharArray()[0];
			if (flag != '#') {
				if (flag != '-') {
					String[] cell = string.split(",");
					section.addElement(cell);
				} else {
					result.addElement(new Vector<String[]>(section));
					section = new Vector<String[]>();
				}
			}
		}
		result.addElement(new Vector<String[]>(section));
		return result;
	}
}
