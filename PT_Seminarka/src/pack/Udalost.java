package pack;

import java.util.ArrayList;
import java.util.List;
/**
 * Udalosti plnene jednotlivimy vozidly
 * @author Jakub Hain, David Basta
 *
 */
public class Udalost {
	/**
	 * zda je pouzit v udalosti vrtulnik
	 */
	private final boolean vrtulnik;
	/**
	 * letiste, z ktereho se budou brat suroviny
	 */
	private Letiste odkudNak;
	/**
	 * index vrtulnik nebo auto u udalosti, ktere na sebe navazuji
	 */
	private List<Integer> index = new ArrayList<>();
	/**
	 * cas uplneho dokonceni udalosti
	 */
	private double casDokon =Integer.MIN_VALUE;
	/**
	 * doba nakladky
	 */
	private double dobaNakl;
	/**
	 * doba vykladani
	 */
	private double dobaVykl;
	/**
	 * doba prekladu
	 */
	private double dobaPrekl;
	/**
	 * doba jizdy
	 */
	private double dobaJizd;
	/**
	 * doba letu
	 */
	private double dobaLetu;

	/**
	 * Cilove mesto
	 */
	private Mesto mesto;



	/**
	 * Kontruktor udalosti do mesta
	 * @param vrtulnik boolean zda je pouzit vrtulnik
	 * @param dobaNakl trvani nakladu
	 * @param dobaVykl trvani vykladky
	 * @param dobaPrekl trvani prekladky
	 * @param dobaJizd trvani jizdy
	 */
	/*public Udalost(boolean vrtulnik, double dobaNakl, double dobaVykl, double dobaPrekl,
			double dobaJizd) {
		
		this.vrtulnik = vrtulnik;
		this.dobaNakl = dobaNakl;
		this.dobaVykl = dobaVykl;
		this.dobaPrekl = dobaPrekl;
		this.dobaJizd = dobaJizd;
	}*/

	/**Kontruktor udalosti pro mesto s vrtulnikem
	 * @param pomMesto cilove mesto
	 * @param vrtulnik boolean zda je pouzit vrtulnik
	 * @param dobaNakl trvani nakladu
	 */
	public Udalost(Mesto pomMesto, boolean vrtulnik, double dobaNakl) {
		this.setMesto(pomMesto);
		this.vrtulnik = vrtulnik;
		this.dobaNakl = dobaNakl;
		//this.dobaJizd = dobaJizd;
	
	}


	/**getr zda je pouzit vrtulnik
	 * @return the vrtulnik
	 */
	public final boolean isVrtulnik() {
		return this.vrtulnik;
	}

	/**Getr casu dokonceni udalosti
	 * @return the casDokon
	 */
	public double getCasDokon() {
		return this.casDokon;
	}

	/**setr casu dokonceni udalosti
	 * @param casDokon cas dokonceni
	 */
	public void setCasDokon(double casDokon) {
		this.casDokon = casDokon;
	}
	/**
	 * doba nakladni
	 * @return dobaNakl
	 */
	public double getDobaNakl() {
		return this.dobaNakl;
	}

	/**setr doby nakladani
	 * @param dobaNakl trvani nakladani
	 */
	public void setDobaNakl(double dobaNakl) {
		this.dobaNakl = dobaNakl;
	}

	/**getr doby vykladani
	 * @return the dobaVykl
	 */
	public double getDobaVykl() {
		return this.dobaVykl;
	}

	/**setr doby vykladni
	 * @param dobaVykl doba vykladani
	 */
	public void setDobaVykl(double dobaVykl) {
		this.dobaVykl = dobaVykl;
	}



	/**getr trvanirvani jizdy
	 * @return the dobaJizd
	 */
	public double getDobaJizd() {
		return this.dobaJizd;
	}

	/**setr trvani jizdy
	 * @param dobaJizd doba jizdy
	 */
	public void setDobaJizd(double dobaJizd) {
		this.dobaJizd = dobaJizd;
	}

	/**getr trvani letu
	 * @return the dobaLetu trvani letu
	 */
	public double getDobaLetu() {
		return this.dobaLetu;
	}

	/**setr trvani letu
	 * @param dobaLetu trvani letu
	 */
	public void setDobaLetu(double dobaLetu) {
		this.dobaLetu = dobaLetu;
	}

	/**getr ciloveho mesta udalosti
	 * @return the mesto
	 */
	public Mesto getMesto() {
		return this.mesto;
	}

	/**setr ciloveho mesta udalosti
	 * @param mesto cilove mesto
	 */
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	/**getr letiste kde se naklada auto
	 * @return the odkudNak
	 */
	public Letiste getOdkudNak() {
		return this.odkudNak;
	}

	/**setr letiste kde se naklada auto
	 * @param odkudNak Letiste odkud se naklada
	 */
	public void setOdkudNak(Letiste odkudNak) {
		this.odkudNak = odkudNak;
	}

	/**getr indexu vrt nebo aut 
	 * @return the index
	 */
	public List<Integer> getIndex() {
		return this.index;
	}

	/**setr indexu vrtulniku a aut
	 * @param index indexy
	 */
	public void setIndex(List<Integer> index) {
		this.index = index;
	}


}
