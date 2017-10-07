package gameplay;

public final class Setting {
	public static final int playerNum = 4;

	public static final int centerX = 400;
	public static final int centerY = 300;
	public static final int screenX = 800;
	public static final int screenY = 700;
	public static final int gameSpeed = 120;

	public static final int portHostIn = 3456;
	public static final int portHostOut = 3457;
	//Should clients choose their own port?
	public static final int portClientIn = 3460;
	
	public static final int portTCP = 3455;

	public static final int tileCapacity = 20;

	//Flat file instead of mysql
	public static final String mapDirectory = "Data/Map/";
	public static final String mapExtension = ".map";

	public static final String unitDirectory = "Data/Object/Unit.obj";
	public static final String objectDirectory = "Data/Object/Object.obj";
	public static final String featureDirectory = "Data/Object/Feature.obj";
	
	//TODO: Editable
	public static final String mapName = "default";
}