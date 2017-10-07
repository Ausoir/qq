package connection;


public class Printer implements Importer{

	@Override
	public void order(Translater p) {
		System.out.println(p.getAll());
	}

}
