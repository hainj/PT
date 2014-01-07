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
	 * Pokud je udalost prave vyuzivana true jinak false
	 */
	private boolean soucasUdal = true;
	/**
	 * Zda je udalost ukoncena
	 */
	private boolean konec = false;
	
	/**
	 * Kdy udalost dokoncilo svuj ukol
	 */
	private int dokonKdy = 0;
	
	/**
	 * Zda udalost naklad
	 */
	private boolean naklada = false;
	/**
	 * Zda udalost jede
	 */
	private boolean jede = false;
	/**
	 * udalost vyklada
	 */
	private boolean vyklada = false;
	/**
	 * udalost dokoncilo cinost
	 */
	private boolean dokonceno = false;
	/**
	 * udalost ceka na zacatek sveho ukolu
	 */
	private boolean ceka = true;
	/**
	 * zda je pouzit v udalosti vrtulnik
	 */
	private final boolean vrtulnik;
	/**
	 * letiste, z ktereho se budou brat suroviny
	 */
	private Letiste odkudNak;
	/**
	 * index vrtulnik nebo udalost u udalosti, ktere na sebe navazuji
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

	/**getr letiste kde se naklada udalost
	 * @return the odkudNak
	 */
	public Letiste getOdkudNak() {
		return this.odkudNak;
	}

	/**setr letiste kde se naklada udalost
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


	/**Getr kdy byla udalost dokoncena
	 * @return dokonKdy minuta dokonceni udal
	 */
	public int getDokonKdy() {
		return dokonKdy;
	}


	/**Setr kdy byla udalost dokoncena
	 * @param dokonKdy int minuty kdy byla dokoncena
	 */
	public void setDokonKdy(int dokonKdy) {
		this.dokonKdy = dokonKdy;
	}


	/**Getr naklada
	 * @return naklada boolean zda naklada
	 */
	public boolean isNaklada() {
		return naklada;
	}


	/**Setr naklada
	 * @param naklada boolean pokud naklada
	 */
	public void setNaklada(boolean naklada) {
		this.naklada = naklada;
	}


	/**Getr zda auto jede
	 * @return jede boolean zda auto jede
	 */
	public boolean isJede() {
		return jede;
	}


	/**Setr zda auto plnici udalost prave jede
	 * @param jede boolean jestli autojede
	 */
	public void setJede(boolean jede) {
		this.jede = jede;
	}


	/**Getr vyklada
	 * @return the vyklada
	 */
	public boolean isVyklada() {
		return vyklada;
	}


	/**Setr vykladani udalosti
	 * @param vyklada boolean zda vyklada
	 */
	public void setVyklada(boolean vyklada) {
		this.vyklada = vyklada;
	}


	/**Getr zda je udalost dokoncena
	 * @return dokonceno
	 */
	public boolean isDokonceno() {
		return dokonceno;
	}


	/**Setr zda je udalost dokoncena uplne
	 * @param dokonceno boolean dokonceni udalosti
	 */
	public void setDokonceno(boolean dokonceno) {
		this.dokonceno = dokonceno;
	}


	/**Getr jestli udalost ceka na prideleni letiste
	 * @return ceka bollean cekani na prideleni let
	 */
	public boolean isCeka() {
		return ceka;
	}


	/**Setr zda udalost ceka na prideleni letiste
	 * @param ceka boolean zda ceka
	 */
	public void setCeka(boolean ceka) {
		this.ceka = ceka;
	}


	/**Konec udalosti
	 * @return konec boolean konce udalosti
	 */
	public boolean isKonec() {
		return konec;
	}


	/**Setr konce udalosti
	 * @param konec boolen zda je udalost ukoncena
	 */
	public void setKonec(boolean konec) {
		this.konec = konec;
	}


	/**Getr zda je udalost prave pouzivana
	 * @return the soucasUdal boolean soucasna udalost
	 */
	public boolean isSoucasUdal() {
		return soucasUdal;
	}


	/**Setr soucasne udalosti, tedy udalosti, ktere je prave provadena
	 * @param soucasUdal boolean zda je udalost souc pouzivana
	 */
	public void setSoucasUdal(boolean soucasUdal) {
		this.soucasUdal = soucasUdal;
	}


	


}
