package gameplay;

public final class Setting {
	public static final int playerNum = 4;

	public static final int centerX = 400;
	public static final int centerY = 300;
	public static final int screenX = 800;
	public static final int screenY = 700;
	public static final int gameSpeed = 120;

	public static final int portClientIn = 3460;
	public static final int portClientOut = 3461;
	public static final int portHostIn = 3456;
	public static final int portTCP = 3455;
	
	public static final int tileSize = 64;
	public static final int textureSize = 32;
	
	//Flat file instead of mysql
	public static final String defaultExtension = ".txt";
	
	public static final String imageDirectory = "Data/Sprite/";
	public static final String imageExtension = ".png";

	public static final String mapDirectory = "Data/Map/";
	public static final String mapExtension = ".map";

	public static final String unitDirectory = "Data/Object/Unit.obj";
	public static final String objectDirectory = "Data/Object/Object.obj";
	public static final String featureDirectory = "Data/Object/Feature.obj";
	
	//Performance
	public static final int chunkLoadSize = 20;
	
	//Gameplay
	public static final int viewSize = 5;
	public static final int tileCapacity = 10;
	
}