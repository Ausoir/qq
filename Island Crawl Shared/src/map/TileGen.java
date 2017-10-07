package map;

public class TileGen {
	public static Tile[][] generate(int px, int py){
		Tile[][] tile = new Tile[px][py];
		for (int x=0;x<px;x++){
			for (int y=0;y<py;y++){
				tile[x][y] = new Tile(x,y);
			}
		}
		for (int x=0;x<px;x++){ //Must be done after every tile initialized
			for (int y=0;y<py;y++){
				if (y-1 >= 0) tile[x][y].setUp(tile[x][y-1]);
				if (y+1 < py) tile[x][y].setDown(tile[x][y+1]);
				if (x-1 >= 0) tile[x][y].setLeft(tile[x-1][y]);
				if (x+1 < px) tile[x][y].setRight(tile[x+1][y]);
			}
		}
		return tile;
	}
}
