package pack;
/**
 * Kontruktor vrtulniku
 * @author Jakub Hain, David Basta
 *
 */
public class Vrtulnik {
	/**
	 * Kdy byla cinnost dokoncena
	 */
	private int dokonKdy = 0;
	/**
	 * Naklada-li vrtulnik
	 */
	private boolean naklada = false
			/**
			 * jede-li vrtulnik
			 */;
	private boolean jede = false;
	/**
	 * vyklada-li vrtulnik
	 */
	private boolean vyklada = false;
	/**
	 * dokoncil cinnost vrtulnik
	 */
	private boolean dokonceno = false;
	/**
	 * udalost kterou plni
	 */
	private Udalost udalost;
	/**
	 * doba nakladani
	 */
	private double dobaNaklada = 0;
	/**
	 * doba jizdy
	 */
	private double dobaJede = 0;
	/**
	 * doba vykladani
	 */
	private double dobaVyklada = 0;
	/**
	 * konec cinnosti
	 */
	private boolean konec = false;


	/**Konstrukto vrtulniku
	 * @param udalost udalost, kterou vrt plni
	 */
	public Vrtulnik(Udalost udalost) {
		super();
		this.udalost = udalost;

	}






	/**getr zda vrtulnik naklada
	 * @return the naklada
	 */
	public boolean isNaklada() {
		return this.naklada;
	}

	/**Setr nakladni
	 *@param naklada naklada-li
	 */
	public void setNaklada(boolean naklada) {
		this.naklada = naklada;
	}

	/**getr jede
	 * @return the jede
	 */
	public boolean isJede() {
		return this.jede;
	}

	/**setr jede
	 * @param jede jede
	 */
	public void setJede(boolean jede) {
		this.jede = jede;
	}

	/**getr vykladky
	 * @return the vyklada
	 */
	public boolean isVyklada() {
		return this.vyklada;
	}

	/**setr vykladky
	 * @param vyklada vyklada
	 */
	public void setVyklada(boolean vyklada) {
		this.vyklada = vyklada;
	}

	/**getr dokonceno
	 * @return the dokonceno
	 */
	public boolean isDokonceno() {
		return this.dokonceno;
	}

	/**setr dokonceno
	 * @param dokonceno dokonceno
	 */
	public void setDokonceno(boolean dokonceno) {
		this.dokonceno = dokonceno;
	}

	/**getr udalosti, kterou vrtulnik plani
	 * @return the udalost
	 */
	public Udalost getUdalost() {
		return this.udalost;
	}

	/**setr udalosti
	 * @param udalost udalost
	 */
	public void setUdalost(Udalost udalost) {
		this.udalost = udalost;
	}







	/**
	 * Vypis info o vrtulniku
	 * @param i index vrtulniku
	 * @param vrt vrtulnik ke ktremu se to vztahuje
	 * @return string informaci
	 */
	public String vypisVrt(int i, Vrtulnik vrt){
		String str = "Vrtulnik " + i + "\n";
		str += "Potreba nalozit: " + ((1000.0 / 30.0)*vrt.getUdalost().getDobaNakl()) + "\n";
		

		Udalost ud = vrt.getUdalost();
		str +="Udalost 0"+" \n";

		str +="Cilove mesto: " + Mapa.getIndexMest(vrt.getUdalost().getMesto()) + "\n" ;
		str += "Vyklad: " + vrt.getUdalost().getDobaNakl()*(1000.0/30.0) + "\n";
		str += "Stav udalosti: ";

		if(vrt.isNaklada()){
			str +="Naklada" + "\n";
			str +="Soucasne nalozeno: " + this.getDobaNaklada()*(1000.0/30.0) + "\n";
			str +="Uletena vzdalenost: 0" + "\n";
		}
		if(vrt.isJede()){
			str +="Jede" + "\n";
			str +="Soucasne nalozeno: " + this.getDobaNaklada()*(1000.0/30.0) + "\n";
			str +="Uletena vzdalenost: " + this.getDobaJede()*(40.0/60.0) +"\n";
		}
		if(vrt.isVyklada()){
			str +="Vyklada" + "\n";
			str +="Soucasne nalozeno: " + (this.getDobaNaklada()*(1000.0/30.0)-this.getDobaVyklada()*(1000.0/30.0)) + "\n";
			str += "Vylozeno: " + this.getDobaVyklada()*(1000.0/30.0) + "\n";
			str +="Uletena vzdalenost: " + ud.getDobaLetu()*(40.0/60.0) +"\n";
		}
		if(vrt.isDokonceno() || vrt.isKonec()){
			str +="Dokoncena" + "\n";
			str +="Soucasne nalozeno: 0" +"\n";
			str += "Vylozeno: " + this.getDobaVyklada()*(1000.0/30.0) + "\n";
			str +="Uletena vzdalenost: " + ud.getDobaJizd()*(40.0/60.0) +"\n";
		}





		return str;
	}





	/**getr doba jizdy
	 * @return the dobaJede
	 */
	public double getDobaJede() {
		return this.dobaJede;
	}



	/**setr doby jizdy
	 * @param dobaJede doba jizdy
	 */
	public void setDobaJede(double dobaJede) {
		this.dobaJede = dobaJede;
	}



	/**getr doby vykladky
	 * @return the dobaVyklada
	 */
	public double getDobaVyklada() {
		return this.dobaVyklada;
	}



	/**setr vykladky
	 * @param dobaVyklada
	 */
	public void setDobaVyklada(double dobaVyklada) {
		this.dobaVyklada = dobaVyklada;
	}



	/**getr doby nakladani
	 * @return dobaNaklada
	 */
	public double getDobaNaklada() {
		return this.dobaNaklada;
	}



	/**setr doby nakladani
	 * @param dobaNaklada
	 */
	public void setDobaNaklada(double dobaNaklada) {
		this.dobaNaklada = dobaNaklada;
	}



	/**getr konce vrtulniku
	 * @return konec
	 */
	public boolean isKonec() {
		return this.konec;
	}



	/**setr konce vrtulniky
	 * @param konec
	 */
	public void setKonec(boolean konec) {
		this.konec = konec;
	}



	/**getr casu dokonceni cinnosti
	 * @return dokonKdy
	 */
	public int getDokonKdy() {
		return dokonKdy;
	}



	/**setr casu dokonceni
	 * @param dokonKdy dokonceni cinnosti
	 */
	public void setDokonKdy(int dokonKdy) {
		this.dokonKdy = dokonKdy;
	}

}
