                                                                     
                                                                     
                                                                     
                                             
package pack;

import java.util.Comparator;

public class KomparatorMest implements Comparator<Mesto> {
	@Override
	public int compare(Mesto o1, Mesto o2) {
		// TODO Auto-generated method stub
		if (o1.getObyvatel() < o2.getObyvatel()) return -1;
		else if (o1.getObyvatel() > o2.getObyvatel()) return 1;
		return 0;
	}

}
