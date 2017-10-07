package controller;

import map.Tile;
import object.Object;
import object.ObjectFactory;
import object.ObjectStock;
import object.InteractiveObject;

public class ObjectController {
	private static ObjectFactory factory;
	private static ObjectStock<Object> objects; // Should be InteractiveObject
	
	public static void setFactory(ObjectFactory pfactory) {
		factory = pfactory;
		objects = pfactory.getObjects();
	}

	public static InteractiveObject command(int id, String p) {
//		System.out.println("ObjectController)"+objects.getById(id)+","+id+","+objects.getById(id).getName());
		InteractiveObject unit = (InteractiveObject) objects.getById(id);
		unit.order(p);
		return unit;
	}
	
	public static void spawnUnit(int id, Tile pos){
		factory.createUnit("Player", id).setPos(pos);
	}
}
