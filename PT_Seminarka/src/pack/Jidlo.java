package pack;
/**
 * Kontruktor jidla
 * @author Jakub Hain, David Basta
 *
 */
public class Jidlo {
	/**
	 * Jidlo
	 */
	private double jidlo;
	/**
	 * cas dodani
	 */
	private int dodano;
	
	
	/**konstruktor jidla
	 * @param jidlo jidlo
	 * @param dodano cas dodani
	 */
	public Jidlo(double jidlo, int dodano) {
		
		this.jidlo = jidlo;
		this.dodano = dodano;
	}


	/**getr jidla
	 * @return the jidlo
	 */
	public double getJidlo() {
		return this.jidlo;
	}


	/**setr jidla
	 * @param jidlo jidlo
	 */
	public void setJidlo(double jidlo) {
		this.jidlo += jidlo;
	}


	/**getr dodani
	 * @return the dodano
	 */
	public int getDodano() {
		return this.dodano;
	}


	/**setr dodani
	 * @param dodano cas dodani
	 */
	public void setDodano(int dodano) {
		this.dodano = dodano;
	}
	
	
}
