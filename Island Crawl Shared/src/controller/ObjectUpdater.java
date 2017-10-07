package controller;

import java.util.Vector;

import object.Time;

public class ObjectUpdater {
	protected Vector<Time> observers;

	public ObjectUpdater(Vector<Time> observers) {
		this.observers = observers;
	}
	
	public ObjectUpdater() {
		// TODO Auto-generated constructor stub
		observers = new Vector<Time>();
	}

	public void addObserver(Time observer){
		observers.addElement(observer);
	}
	
	public void update(){
		for (Time ob : observers){
			ob.update();
		}
	}
}
