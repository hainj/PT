package pack;

import java.util.ArrayList;
import java.util.List;
/**
 * Udalosti plnene jednotlivimy vozidly
 * @author Cybra
 *
 */
public class Udalost {

	private final boolean vrtulnik;
	private Letiste odkudNak;
	private List<Integer> index = new ArrayList<>();
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


	/**
	 * @return the vrtulnik
	 */
	public final boolean isVrtulnik() {
		return this.vrtulnik;
	}

	/**
	 * @return the casDokon
	 */
	public double getCasDokon() {
		return this.casDokon;
	}

	/**
	 * @param casDokon the casDokon to set
	 */
	public void setCasDokon(double casDokon) {
		this.casDokon = casDokon;
	}

	public double getDobaNakl() {
		return this.dobaNakl;
	}

	/**
	 * @param dobaNakl the dobaNakl to set
	 */
	public void setDobaNakl(double dobaNakl) {
		this.dobaNakl = dobaNakl;
	}

	/**
	 * @return the dobaVykl
	 */
	public double getDobaVykl() {
		return this.dobaVykl;
	}

	/**
	 * @param dobaVykl the dobaVykl to set
	 */
	public void setDobaVykl(double dobaVykl) {
		this.dobaVykl = dobaVykl;
	}

	/**
	 * @return the dobaPrekl
	 */
	public double getDobaPrekl() {
		return this.dobaPrekl;
	}

	/**
	 * @param dobaPrekl the dobaPrekl to set
	 */
	public void setDobaPrekl(double dobaPrekl) {
		this.dobaPrekl = dobaPrekl;
	}

	/**
	 * @return the dobaJizd
	 */
	public double getDobaJizd() {
		return this.dobaJizd;
	}

	/**
	 * @param dobaJizd the dobaJizd to set
	 */
	public void setDobaJizd(double dobaJizd) {
		this.dobaJizd = dobaJizd;
	}

	/**
	 * @return the dobaLetu
	 */
	public double getDobaLetu() {
		return this.dobaLetu;
	}

	/**
	 * @param dobaLetu the dobaLetu to set
	 */
	public void setDobaLetu(double dobaLetu) {
		this.dobaLetu = dobaLetu;
	}

	/**
	 * @return the mesto
	 */
	public Mesto getMesto() {
		return mesto;
	}

	/**
	 * @param mesto the mesto to set
	 */
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	/**
	 * @return the odkudNak
	 */
	public Letiste getOdkudNak() {
		return odkudNak;
	}

	/**
	 * @param odkudNak the odkudNak to set
	 */
	public void setOdkudNak(Letiste odkudNak) {
		this.odkudNak = odkudNak;
	}

	/**
	 * @return the index
	 */
	public List<Integer> getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(List<Integer> index) {
		this.index = index;
	}


}
