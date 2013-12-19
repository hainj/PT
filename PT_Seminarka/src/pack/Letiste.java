



package pack;

import java.util.ArrayList;
import java.util.List;

/**
* Konstruktor letiste
* @author Jakub Hain, David Basta
*
*/

public class Letiste {
	/**
	 * Maximalni pocet moznych zasobenych mest
	 */
	private int maxZasoben=0;
	/**
	 * x-ova sour
	 */
	private int x = 0;
	/**
	 * y-ova sour
	 */
	private int y = 0;
	/**
	 * Seznam jidla
	 */
	List<Jidlo> jidlo = new ArrayList<>();
	/**
	 * seznam sousedu
	 */
	List<Mesto> sousedi = new ArrayList<>();

	/**
	 * Konstruktor letiste
	 * @param x x-ova sour
	 * @param y y-ova sour
	 */
	public Letiste(int x, int y) {

		this.x = x;
		this.y = y;
		this.jidlo.add(new Jidlo((15000.0), 0));

	}
	
	
	/**
	* Konstruktor letiste
	 * @param x x-ova sour
	 * @param y y-ova sour
	 * @param mest seznam sousedu letiste
	 */
	public Letiste(int x, int y, List<Mesto> mest) {
		this.jidlo.add(new Jidlo((15000.0), 0));
		this.x = x;
		this.y = y;
		this.sousedi = mest;
	}

	/**
	 * getr x-ove sour
	 * @return x
	 */
	public int getX() {
		return this.x;
	}

		/**
		 * setr x-ove sour
		 * @param x x-ova sour
		 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * getr y-ove sour
	 * @return y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * setr y-ove sour
	 * @param y y-ova sour
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * getr seznamu jidla
	 * @return jidlo
	 */
	public List<Jidlo> getJidlo() {
		return this.jidlo;
	}
	
	/**
	 * prida 1 prvek do seznamu jidlo
	 * @param jidlo pocet jidla
	 * @param dodano kdy dodano
	 */
	public void addJidlo(double jidlo, int dodano){
		this.jidlo.add(new Jidlo(jidlo, dodano));
	}
	
	/**
	 * setr seznamu jidla
	 * @param jidlo
	 */
	public void setJidlo(ArrayList<Jidlo> jidlo) {
		this.jidlo = jidlo;
	}
	
	/**
	 * gets sousedu letiste
	 * @return sousedi
	 */
	public List<Mesto> getSousedi() {
		return this.sousedi;
	}
	
	/**
	 * setr sousedu letiste
	 * @param pomoc sousedi letiste
	 */
	public void setSousedi(List<Mesto> pomoc) {
		this.sousedi = pomoc;
	}



	/**getr maximalniho poctu pripojenych mest na letiste
	 * @return maxZasoben
	 */
	public int getMaxZasoben() {
		return this.maxZasoben;
	}



	/**setr maximalniho poctu pripojenych mest
	 * @param maxZasoben max pocet
	 */
	public void setMaxZasoben(int maxZasoben) {
		this.maxZasoben = maxZasoben;
	}





}
