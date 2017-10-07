package file;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

public class FileManager {
	private String fileName;

	public FileManager(String fileName) {
		this.fileName = fileName;
	}

	public Vector<String> read() {
		Vector<String> data_line = new Vector<String>();
		try {
			FileReader reader = new FileReader(fileName);
			Scanner scanner = new Scanner(reader);
			while (scanner.hasNextLine()) {
				data_line.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not read from file "+fileName);
//			e.printStackTrace();
		}
		return data_line;
	}

	public void write(String p_text) {
		try {
			FileWriter g_istream = new FileWriter(fileName);
			BufferedWriter write = new BufferedWriter(g_istream);
			write.write(p_text);
			write.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}

	public void write(Vector<String> p_text) {
		try {
			FileWriter g_istream = new FileWriter(fileName);
			BufferedWriter write = new BufferedWriter(g_istream);
			String data = "";
			for (int x = 0; x < p_text.size(); x++) {
				data += p_text.elementAt(x);
				data += "\n";
			}
			write.write(data);
			write.flush();
			write.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}
}