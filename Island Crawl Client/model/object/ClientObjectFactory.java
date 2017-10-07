package object;

import initialization.UnitStatLoader;

public class ClientObjectFactory extends ObjectFactory {

	public ClientObjectFactory(ObjectStock<Feature> features,
			ObjectStock<Object> objects, ObjectStock<InteractiveObject> units) {
		super(features, objects, units);
	}

	@Override
	public ClientObject createUnit(String name, int id) {
		InteractiveObject obj = unitBatch.getByName(name).firstElement().getInstance(id);
		obj.setController(UnitStatLoader.createBasicController());
		ClientObject result = new ClientObject(obj);
		objects.addElement(result);
		updater.addObserver(result);
		return result;
	}
}