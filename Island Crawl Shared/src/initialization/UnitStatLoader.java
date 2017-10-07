package initialization;

import command.InteractiveController;

public class UnitStatLoader {
	
	public static InteractiveController createBasicController(){ //mini factory
		InteractiveController result = new InteractiveController(null);
		result.addBasicCommands();
		return result;
	}
	
	private static InteractiveController createModifiableController(int move){ //mini factory
		InteractiveController result = createBasicController();
		result.getCommands().get("Move").setCost(move);
		return result;
	}
	
	public static InteractiveController getStat(int moveSpeed){
		InteractiveController result = createModifiableController(moveSpeed);
		return result;
	}
}
