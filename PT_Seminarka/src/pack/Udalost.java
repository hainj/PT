package pack;

import java.util.ArrayList;

public class Udalost {
	private Mesto mesto;
	private ArrayList<Auto> auta = new ArrayList<Auto>();
	private ArrayList<Vrtulnik> vrtulniky = new ArrayList<Vrtulnik>();
	private String status;
	private boolean vrtulník;
	private Mesto odkudVrtulnik;
	private boolean dokonceno;
	
	
	/**
	 * @param mesto
	 * @param auta
	 */
	public Udalost(Mesto mesto, ArrayList<Auto> auta, boolean vrtulnik, boolean dokonceno) {
		this.mesto = mesto;
		this.vrtulník = vrtulnik;
		this.auta = auta;
		this.dokonceno = dokonceno;
	
	}

	public Udalost(Mesto mesto,Mesto odkudVrtulnik, ArrayList<Auto> auta, ArrayList<Vrtulnik> vrtulniky, boolean vrtulnik, boolean dokonceno) {
		this.mesto = mesto;
		this.vrtulník = vrtulnik;
		this.odkudVrtulnik = odkudVrtulnik;
		this.auta = auta;
		this.vrtulniky = vrtulniky;
		this.dokonceno = dokonceno;
		
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
		return mesto;
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
		return auta;
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
		return vrtulniky;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the vrtulník
	 */
	public boolean isVrtulnik() {
		return vrtulník;
	}

	/**
	 * @return the odkudVrtulnik
	 */
	public Mesto getOdkudVrtulnik() {
		return odkudVrtulnik;
	}

	/**
	 * @return the dokonceno
	 */
	public boolean isDokonceno() {
		return dokonceno;
	}

	/**
	 * @param dokonceno the dokonceno to set
	 */
	public void setDokonceno(boolean dokonceno) {
		this.dokonceno = dokonceno;
	}
	
	

}
