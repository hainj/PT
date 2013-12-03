



package pack;

import java.util.ArrayList;

public class Letiste {

	private int x = 0;
	private int y = 0;
	private ArrayList<Jidlo> jidlo = new ArrayList<Jidlo>();
	ArrayList<Mesto> sousedi = new ArrayList<Mesto>();

	public Letiste(int x, int y) {

		this.x = x;
		this.y = y;
		jidlo.add(new Jidlo(500000.0, 0));

	}
	
	
	
	public Letiste(int x, int y, ArrayList<Mesto> mest) {
		jidlo.add(new Jidlo(500000.0, 0));
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

	
	public ArrayList<Jidlo> getJidlo() {
		return jidlo;
	}
	
	
	public void addJidlo(double jidlo, int dodano){
		this.jidlo.add(new Jidlo(jidlo, dodano));
	}
	
	
	public void setJidlo(ArrayList<Jidlo> jidlo) {
		this.jidlo = jidlo;
	}
	
	
	public ArrayList<Mesto> getSousedi() {
		return sousedi;
	}
	
	
	public void setSousedi(ArrayList<Mesto> pomoc) {
		this.sousedi = pomoc;
	}





}
