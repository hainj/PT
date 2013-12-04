package pack;

import java.util.Comparator;

public class Komparator implements Comparator <Vzdalenost>{
	@Override
	public int compare(Vzdalenost arg0, Vzdalenost arg1) {
		// TODO Auto-generated method stub
		if (arg0.vzdalenost < arg1.vzdalenost){ return -1;
				
		}
		else if (arg0.vzdalenost > arg1.vzdalenost){ return 1;
		
		}
		return 0;
	}


}
