



package pack;

import java.util.ArrayList;
import java.util.List;

public class Letiste {
	private int maxZasoben=0;
	private int x = 0;
	private int y = 0;
	List<Jidlo> jidlo = new ArrayList<>();
	List<Mesto> sousedi = new ArrayList<>();

	public Letiste(int x, int y) {

		this.x = x;
		this.y = y;
		this.jidlo.add(new Jidlo((15000.0), 0));

	}
	
	
	
	public Letiste(int x, int y, List<Mesto> mest) {
		this.jidlo.add(new Jidlo((15000.0), 0));
		this.x = x;
		this.y = y;
		this.sousedi = mest;
	}


	public int getX() {
		return this.x;
	}

	
	public void setX(int x) {
		this.x = x;
	}

	
	public int getY() {
		return this.y;
	}

	
	public void setY(int y) {
		this.y = y;
	}

	
	public List<Jidlo> getJidlo() {
		return this.jidlo;
	}
	
	
	public void addJidlo(double jidlo, int dodano){
		this.jidlo.add(new Jidlo(jidlo, dodano));
	}
	
	
	public void setJidlo(ArrayList<Jidlo> jidlo) {
		this.jidlo = jidlo;
	}
	
	
	public List<Mesto> getSousedi() {
		return this.sousedi;
	}
	
	
	public void setSousedi(List<Mesto> pomoc) {
		this.sousedi = pomoc;
	}



	/**
	 * @return the maxZasoben
	 */
	public int getMaxZasoben() {
		return maxZasoben;
	}



	/**
	 * @param maxZasoben the maxZasoben to set
	 */
	public void setMaxZasoben(int maxZasoben) {
		this.maxZasoben = maxZasoben;
	}





}
