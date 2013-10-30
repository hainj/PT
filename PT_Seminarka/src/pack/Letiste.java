                                                                     
                                                                     
                                                                     
                                             
package pack;

import java.util.ArrayList;

public class Letiste {

	private int x = 0;
	private int y = 0;
	private int jidlo = 0;
	ArrayList<Mesto> sousedi = new ArrayList<Mesto>();
	
	public Letiste(int x, int y) {

		this.x = x;
		this.y = y;
		this.jidlo = 500;
		
	}
	public Letiste(int x, int y, ArrayList<Mesto> mest) {
		this.jidlo = 500;
		this.x = x;
		this.y = y;
		this.sousedi = mest;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getJidlo() {
		return jidlo;
	}

	public void setJidlo(int jidlo) {
		this.jidlo = jidlo;
	}
	public ArrayList<Mesto> getSousedi() {
		return sousedi;
	}
	public void setSousedi(ArrayList<Mesto> pomoc) {
		this.sousedi = pomoc;
	}


}
