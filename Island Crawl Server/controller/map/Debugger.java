package map;

import java.util.Scanner;

import connection.Importer;
import connection.Translater;

public class Debugger {
	public static void start(Importer server){
		Scanner sc = new Scanner(System.in);
		String cmd = sc.nextLine();
		Translater packer = new Translater();
		packer.translate(cmd);
		server.order(packer);
		sc.close();
	}
}
