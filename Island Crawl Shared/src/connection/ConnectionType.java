package connection;

public enum ConnectionType {
	Connect("CN"), Disconnect("DC"), Command("CM"), Data("DT"), Start("BG"), End("ND");
	private String code;
	private ConnectionType(String p){
		code = p;
	}
	public String getCode(){
		return code;
	}
	public static ConnectionType getEnum(String p){
		for (ConnectionType type : ConnectionType.values()){
			if (type.getCode().equals(p)) return type;
		}
		new Exception().printStackTrace();
		return null;
	}
}
