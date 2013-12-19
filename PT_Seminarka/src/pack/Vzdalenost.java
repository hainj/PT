package pack;

/**
* Kontruktory vzdalenosti, pouzite pro urceni sousedu a vzdalenosti od mest
* @author Jakub Hain, David Basta
*
*/
public class Vzdalenost{
	/**
	 * index mesta
	 */
	public int index = 0;
	/**
	 * Vzdalenost od mesta nebo letiste
	 */
	public double vzdalenost = 0;
	/**
	 * Mesto
	 */
	private Mesto mesto;
	/**
	 * letiste
	 */
	private Letiste let;
	/**
	 * KOnstruk
	 * @param index index mesta
	 * @param vzd vzdalenost od mesta
	 */
	public Vzdalenost (int index, double vzd)
	{
		this.index = index;
		this.vzdalenost = vzd;
	}
	/**
	 * Konstruktor vzdalenosti od letiste
	 * @param mesto mesto predchazejici nasemu
	 * @param let letiste
	 * @param vzd vzdalenost k letisti
	 */
	public Vzdalenost (Mesto mesto, Letiste let, double vzd)
	{
		this.mesto = mesto;
		this.let = let;
		this.vzdalenost = vzd;
	}
	/**
	 * Konstruktor vzdalenosti k predchazejicimu mestu
	 * @param mesto predch mesto
	 * @param vzd vzdalenost
	 */
	public Vzdalenost(Mesto mesto, double vzd) {
		// TODO Auto-generated constructor stub
		this.mesto = mesto;
		this.vzdalenost = vzd;
	}

	/**getr mesta
	 * @return the mesto
	 */
	public Mesto getMesto() {
		return mesto;
	}
	
	/**setr predchazejiciho mesta
	 * @param mesto predch mesto
	 */
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	/**getr letiste
	 * @return the let
	 */
	public Letiste getLet() {
		return let;
	}
	
	/**setr letiste
	 * @param let letiste
	 */
	public void setLet(Letiste let) {
		this.let = let;
	}
}
