package pack;

import java.util.ArrayList;

public class Udalost {
	private Mesto mesto;
	private ArrayList<Auto> auta = new ArrayList<Auto>();
	private ArrayList<Vrtulnik> vrtulniky = new ArrayList<Vrtulnik>();
	private final boolean vrtulnik;
	private Mesto odkudVrtulnik;
	private int casDokon =Integer.MIN_VALUE;
	
	
	/**
	 * @param mesto
	 * @param auta
	 */
	public Udalost(Mesto mesto, ArrayList<Auto> auta, final boolean vrtulnik) {
		this.mesto = mesto;
		this.vrtulnik = vrtulnik;
		this.auta = auta;
		
	
	}

	public Udalost(Mesto mesto,Mesto odkudVrtulnik, ArrayList<Auto> auta, ArrayList<Vrtulnik> vrtulniky, final boolean vrtulnik) {
		this.mesto = mesto;
		this.vrtulnik = vrtulnik;
		this.odkudVrtulnik = odkudVrtulnik;
		this.auta = auta;
		this.vrtulniky = vrtulniky;
		
		
	}
	/**
	 * @param mesto
	 * @param auta
	 * @param vrtulniky
	 */
	

	/**
	 * @return the mesto
	 */
	public Mesto getMesto() {
		return this.mesto;
	}


	/**
	 * @param mesto the mesto to set
	 */
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}


	/**
	 * @return the auta
	 */
	public ArrayList<Auto> getAuta() {
		return this.auta;
	}
	public void addAuto(Auto auto) {
		this.auta.add(auto);
	}

	/**
	 * @param auta the auta to set
	 */
	public void setAuta(ArrayList<Auto> auta) {
		this.auta = auta;
	}


	/**
	 * @return the vrtulniky
	 */
	public ArrayList<Vrtulnik> getVrtulniky() {
		return this.vrtulniky;
	}
	
	public void addVrtulnik(Vrtulnik vrtulnik) {
		this.vrtulniky.add(vrtulnik);
	}
	/**
	 * @param vrtulniky the vrtulniky to set
	 */
	public void setVrtulniky(ArrayList<Vrtulnik> vrtulniky) {
		this.vrtulniky = vrtulniky;
	}

	/**
	 * @return the vrtulnik
	 */
	public final boolean isVrtulnik() {
		return this.vrtulnik;
	}

	/**
	 * @return the odkudVrtulnik
	 */
	public Mesto getOdkudVrtulnik() {
		return this.odkudVrtulnik;
	}

	/**
	 * @return the casDokon
	 */
	public int getCasDokon() {
		return this.casDokon;
	}

	/**
	 * @param casDokon the casDokon to set
	 */
	public void setCasDokon(int casDokon) {
		this.casDokon = casDokon;
	}

	/**
	 * @return the dokonceno
	 */
	
	
	

}
