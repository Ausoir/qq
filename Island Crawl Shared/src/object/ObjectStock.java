/**
 * @author Ga Lim
 * CSC 111 - 60107
 * Stores books for use by the public like the library class except using linked list technique
 */
package object;

import java.util.Vector;

public class ObjectStock<T extends Object> extends Vector<T> {
	private static final long serialVersionUID = 1L;

	public ObjectStock<T> getByClass(Class<?> p) {
		ObjectStock<T> result = new ObjectStock<T>();
		for (T obj : this) {
			if (obj.getClass() == p)
				result.addElement(obj);
		}
		return result;
	}

	public Object getById(int id) {
		for (Object obj : this) {
			if (obj.getId() == id)
				return obj;
		}
		return null;
	}

	public ObjectStock<T> getByName(String p) {
		ObjectStock<T> result = new ObjectStock<T>();
		for (T obj : this) {
			if (obj.getName().equals(p))
				result.addElement(obj);
		}
		return result;
	}

	// public Matter getById(int p){
	// for (int x=0;x<size();x++){
	// if (elementAt(x).id == p) return elementAt(x);
	// }
	// return null;
	// }

	public Object getByInstance(Object p) {
		for (int x = 0; x < size(); x++) {
			if (elementAt(x).getClass() == p.getClass())
				return elementAt(x);
		}
		return null;
	}

	public Object[] getArray() {
		Object[] result = new Object[size()];
		for (int x = 0; x < size(); x++) {
			result[x] = elementAt(x);
		}
		return result;
	}

	public String toString() {
		String result = "";
		for (int x = 0; x < size(); x++) {
			result += elementAt(x).getName() + ", ";
		}
		return result;
	}
}
