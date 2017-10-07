package object;

import controller.ObjectUpdater;

public class ObjectFactory {
	protected ObjectUpdater updater;
	protected ObjectStock<Object> objects;

	private ObjectStock<Object> objectBatch;
	private ObjectStock<Feature> featureBatch;
	protected ObjectStock<InteractiveObject> unitBatch;

	public ObjectFactory(ObjectStock<Feature> features,
			ObjectStock<Object> objects, ObjectStock<InteractiveObject> units) {
		this.featureBatch = features;
		this.objectBatch = objects;
		this.unitBatch = units;
	}

	public void setObjects(ObjectStock<Object> pobjects) {
		objects = pobjects;
	}
	
	public ObjectStock<Object> getObjects(){
		return objects;
	}

	public void setUpdater(ObjectUpdater pupdater) {
		updater = pupdater;
	}

	public InteractiveObject createUnit(String name, int id) {
		InteractiveObject obj = unitBatch.getByName(name).firstElement().getInstance(id);
		objects.addElement(obj);
		updater.addObserver(obj);
		return obj;
	}
	
	public Object createObject(String name, int id) {
		Object obj = objectBatch.getByName(name).firstElement().getInstance(id);
		objects.addElement(obj);
		if (obj instanceof Time) {
			updater.addObserver((Time) obj);
		}
		return obj;
	}

	public Feature createFeature(String name, int id) {
		Feature obj = featureBatch.getByName(name).firstElement().getInstance(id);
		objects.addElement(obj);
		return obj;
	}

	public void updateId() { //DO NOT USE
		int id = 0;
		for (Object obj : objects) {
			if (id < obj.getId())
				id = obj.getId();
		}
		GenerateId.setId(id);
	}
}
