/**
 * @author Ga Lim
 * CSC 111 - 60107
 * Stores books for use by the public like the library class except using linked list technique
 */
package object;

import graphic.Picture;

import java.util.Vector;

public class PictureStock extends Vector<Picture>
{
	private static final long serialVersionUID = 1L;
    
	public Picture getByName(String p){
		for (int x=0;x<size();x++){
			if (elementAt(x).getName().equals(p)) return get(x);
		}
		return null;
	}
    
//	public Matter getById(int p){
//		for (int x=0;x<size();x++){
//			if (elementAt(x).id == p) return elementAt(x);
//		}
//		return null;
//	}
	
	public Picture getByInstance(Picture p){
		for (int x=0;x<size();x++){
			if (elementAt(x).getClass() == p.getClass()) return get(x);
		}
		return null;
	}
	
	public Picture[] getArray(){
		Picture[] result = new Picture[size()];
		for (int x=0;x<size();x++){
			result[x] = elementAt(x);
		}
		return result;
	}
	
	public String toString(){
		String result = "";
		for (int x=0;x<size();x++){
			result += elementAt(x)+", ";
		}
		return result;
	}
}
