package pack;

public class Jidlo {
	private double jidlo;
	private int dodano;
	
	
	/**
	 * @param jidlo
	 * @param dodano
	 */
	public Jidlo(double jidlo, int dodano) {
		
		this.jidlo = jidlo;
		this.dodano = dodano;
	}


	/**
	 * @return the jidlo
	 */
	public double getJidlo() {
		return jidlo;
	}


	/**
	 * @param jidlo the jidlo to set
	 */
	public void setJidlo(double jidlo) {
		this.jidlo = jidlo;
	}


	/**
	 * @return the dodano
	 */
	public int getDodano() {
		return dodano;
	}


	/**
	 * @param dodano the dodano to set
	 */
	public void setDodano(int dodano) {
		this.dodano = dodano;
	}
	
	
}
