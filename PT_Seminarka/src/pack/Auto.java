package pack;

import java.util.List;

/**
 * Trida Auto obsahujici konstruktory aut
 * @author Jakub Hain, David Basta
 *
 */
public class Auto {

	
	/**
	 * Udalost, kterou auto plni
	 */
	private List<Udalost> udalost;
	/**
	 * Zasobuje-li mesto vrtulnikem
	 */
	private boolean heli = false;
	/**
	 * Uplny konec auta, pote se s nim pracuje jen ve statistikach
	 */
	private boolean konec = false;
	/**
	 * Jak dlouho naklada, nepocitaji se mezery
	 */
	private double dobaNaklada = 0;
	/**
	 * doba jizdy
	 */
	private double dobaJede = 0;
	/**
	 * doba prekladu na vrtulnik
	 */
	private double dobaPreklada = 0;
	/**
	 * doba vykladky do mesta
	 */
	private double dobaVyklada = 0;
	private boolean dokonceno;
	

	/**Kontruktor auta
	 * @param udalost udalost, kterou auto plni
	 */
	public Auto(List<Udalost> udalost) {
	
		this.udalost = udalost;
	}






	/**getr dokonceni cin
	 * @return dokonceno
	 */
	public boolean isDokonceno() {
		return this.dokonceno;
	}


	/**setr dokonceni cinnosti
	 * @param dokonceno dokoncena cinnost
	 */
	public void setDokonceno(boolean dokonceno) {
		this.dokonceno = dokonceno;
	}


	/**Getr udalosti
	 * @return udalost
	 */
	public List<Udalost> getUdalost() {
		return this.udalost;
	}


	/**Setr udalosti
	 * @param udalost udalost
	 */
	public void setUdalost(List<Udalost> udalost) {
		this.udalost = udalost;
	}

	


	
	/**Getr doby nakladky
	 * @return dobaNaklada
	 */
	public double getDobaNaklada() {
		return this.dobaNaklada;
	}


	/**setr doby nakladadky
	 * @param dobaNaklada doba nakladni
	 */
	public void setDobaNaklada(double dobaNaklada) {
		this.dobaNaklada = dobaNaklada;
	}


	/**getr doby jizdy
	 * @return dobaJede
	 */
	public double getDobaJede() {
		return this.dobaJede;
	}


	/**setr jak dlouho auto jede
	 * @param dobaJede doba jizdy
	 */
	public void setDobaJede(double dobaJede) {
		this.dobaJede = dobaJede;
	}


	/**getr trvani prekladky
	 * @return dobaPreklada
	 */
	public double getDobaPreklada() {
		return this.dobaPreklada;
	}


	/**setr trvani prekladky
	 * @param dobaPreklada doba prekladani
	 */
	public void setDobaPreklada(double dobaPreklada) {
		this.dobaPreklada = dobaPreklada;
	}


	/**getr vykladky
	 * @return dobaVyklada
	 */
	public double getDobaVyklada() {
		return this.dobaVyklada;
	}


	/**setr doby vykladani
	 * @param dobaVyklada doba vykladani
	 */
	public void setDobaVyklada(double dobaVyklada) {
		this.dobaVyklada = dobaVyklada;
	}


	/**
	 * Vypise info o autu
	 * @param i index auta
	 * @return string informaci
	 */
	public String vypisAuto(int i){
		String str = "Auto "+ i  +"\n" + "Nalozeno " + 
				//(this.getUdalost().getDobaNakl()*1000/30) + "\n" + "Ujeta vzdalenost " + 
				this.getDobaJede()*(40/60) + "\n"
				+ "Vylozeno " +(this.getDobaVyklada()*1000/30) + "\n" +"Cilove mesto: " + this.getUdalost().get(0).getMesto()+ "\n";
		
		return str;
	}





	/**Getr zda jsou vytvorene helikoptery do kterych budem nakladat
	 * @return heli
	 */
	public boolean isHeli() {
		return this.heli;
	}




	/**Setr zda jsou vytv helikoptery
	 * @param heli heli
	 */
	public void setHeli(boolean heli) {
		this.heli = heli;
	}



	/**Getr dokonceni auta
	 * @return konec
	 */
	public boolean isKonec() {
		return this.konec;
	}




	/**Setr dokonceni auta
	 * @param konec konec
	 */
	public void setKonec(boolean konec) {
		this.konec = konec;
	}



}
