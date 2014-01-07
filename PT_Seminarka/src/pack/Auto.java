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
	/**
	 * Pokud auto splnilo vsechny udalosti true jinak false
	 */
	private boolean dokonceno;
	

	/**Kontruktor auta
	 * @param udalost seznam udalosti, ktere auto plni
	 */
	public Auto(List<Udalost> udalost) {
	
		this.udalost = udalost;
	}






	/**getr dokonceni cinnosti
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


	/**Getr seznamu udalosti
	 * @return udalost seznam udalosti
	 */
	public List<Udalost> getUdalost() {
		return this.udalost;
	}


	/**Setr seznamu udalosti
	 * @param udalost seznam udalosti
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
	public String vypisAuto(int i, Auto auto){
		String str = "Auto "+ i  +"\n";
		
		double  potrebaNaloz = 0;
		for(int k = 0; k < auto.getUdalost().size(); k++){
			
			potrebaNaloz += auto.getUdalost().get(k).getDobaNakl();
			
		}
		/*System.out.println(potrebaNaloz);*/
		str += "Potreba nalozit: " + (1000.0 / 30.0)*potrebaNaloz + "\n";
		double vyklad = 0;
		for(int k = 0; k < auto.getUdalost().size(); k++){
			Udalost ud = auto.getUdalost().get(k);
			str +="Udalost " + k + " \n";
			
				str +="Cilove mesto: " + Mapa.getIndexMest(auto.getUdalost().get(k).getMesto()) + "\n" ;
				str += "Vyklad: " + auto.getUdalost().get(k).getDobaNakl()*(1000.0/30.0) + "\n";
				str += "Stav udalosti: ";
				if(ud.isCeka()){
					str +="Ceka na prideleni letiste" + "\n";
				}
				if(ud.isNaklada()){
					str +="Naklada" + "\n";
					str +="Soucasne nalozeno: " + this.getDobaNaklada()*(1000.0/30.0) + "\n";
					str +="Ujeta vzdalenost: 0" + "\n";
				}
				if(ud.isJede()){
					str +="Jede" + "\n";
					str +="Soucasne nalozeno: " + this.getDobaNaklada()*(1000.0/30.0) + "\n";
					str +="Ujeta vzdalenost: " + this.getDobaJede()*(40.0/60.0) +"\n";
				}
				if(ud.isVyklada()){
					str +="Vyklada" + "\n";
					str +="Soucasne nalozeno: " + (this.getDobaNaklada()*(1000.0/30.0)-this.getDobaVyklada()*(1000.0/30.0)) + "\n";
					str += "Vylozeno: " + this.getDobaVyklada()*(1000.0/30.0) + "\n";
					str +="Ujeta vzdalenost: " + ud.getDobaJizd()*(40.0/60.0) +"\n";
				}
				if(ud.isDokonceno() || ud.isKonec()){
					str +="Dokoncena" + "\n";
					str +="Soucasne nalozeno: 0" +"\n";
					str += "Vylozeno: " + ud.getDobaNakl()*(1000.0/30.0) + "\n";
					str +="Ujeta vzdalenost: " + ud.getDobaJizd()*(40.0/60.0) +"\n";
				}
			
				
			
		}
		
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
