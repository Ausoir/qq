package command;

import java.util.HashMap;
import java.util.Map;

import object.InteractiveObject;

public class InteractiveController {
	private HashMap<String, InteractiveCommand> commandList;
	private InteractiveObject host;

	public InteractiveController(InteractiveObject p) {
		host = p;
		commandList = new HashMap<String, InteractiveCommand>();
	}
	
	public void setHost(InteractiveObject p){
		host = p;
		for (Map.Entry<String, InteractiveCommand> command : commandList.entrySet()){
			command.getValue().setHost(p);
		}
	}
	
	public InteractiveObject getHost(){
		return host;
	}
	
	public void addBasicCommands(){
		Move move = new Move(host);
		Face face = new Face(host);
		commandList.put("Move", move);
		commandList.put("Face", face);
		commandList.put("FaceMove", new FaceMove(move, face));
	}
	
	public HashMap<String, InteractiveCommand> getCommands(){
		return commandList;
	}

	public int translateCommand(String p) {
		int cost = 0;
		String cmdBase = p.substring(0, 1);
		String cmdOp = "";
		if (p.length() > 1)
			cmdOp = p.substring(1, 2);
		for (InteractiveCommand cmd : commandList.values()) {
			if (cmdBase.equals(cmd.getCode())) {
				cost = cmd.getCost();
				cmd.execute(cmdOp);
				break;
			}
		}

		return cost;
	}
}
