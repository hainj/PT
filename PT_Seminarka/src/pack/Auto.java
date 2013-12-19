package pack;

public class Auto {



	private static int pocet = 0;
	//private double ujetaVzdalenost = 0.0;
	//private String status;
	//private final double POTREBANALOZIT;
	private boolean naklada = false;
	private boolean jede = false;
	private boolean preklada = false;
	private boolean vyklada = false;
	private boolean dokonceno = false;
	private boolean ceka = true;
	private Udalost udalost;
	private boolean heli = false;
	private Vrtulnik vrtulnik;
	private boolean konec = false;
	private double dobaNaklada = 0;
	private double dobaJede = 0;
	private double dobaPreklada = 0;
	private double dobaVyklada = 0;
	

	/**
	 * @param udalost
	 */
	public Auto(Udalost udalost) {
		pocet++;
		this.udalost = udalost;
	}




	/**
	 * @return the naklada
	 */
	public boolean isNaklada() {
		return this.naklada;
	}


	/**
	 * @param naklada the naklada to set
	 */
	public void setNaklada(boolean naklada) {
		this.naklada = naklada;
	}


	/**
	 * @return the jede
	 */
	public boolean isJede() {
		return this.jede;
	}


	/**
	 * @param jede the jede to set
	 */
	public void setJede(boolean jede) {
		this.jede = jede;
	}


	/**
	 * @return the preklada
	 */
	public boolean isPreklada() {
		return this.preklada;
	}


	/**
	 * @param preklada the preklada to set
	 */
	public void setPreklada(boolean preklada) {
		this.preklada = preklada;
	}


	/**
	 * @return the vyklada
	 */
	public boolean isVyklada() {
		return this.vyklada;
	}


	/**
	 * @param vyklada the vyklada to set
	 */
	public void setVyklada(boolean vyklada) {
		this.vyklada = vyklada;
	}


	/**
	 * @return the dokonceno
	 */
	public boolean isDokonceno() {
		return this.dokonceno;
	}


	/**
	 * @param dokonceno the dokonceno to set
	 */
	public void setDokonceno(boolean dokonceno) {
		this.dokonceno = dokonceno;
	}


	/**
	 * @return the udalost
	 */
	public Udalost getUdalost() {
		return this.udalost;
	}


	/**
	 * @param udalost the udalost to set
	 */
	public void setUdalost(Udalost udalost) {
		this.udalost = udalost;
	}


	public boolean getnaklada() {
		return this.naklada;
	}



	public int getPocet() {
		return Auto.pocet;
	}

	public static void setPocet(int pocet) {
		Auto.pocet = pocet;
	}

	/**
	 * @return the dobaNaklada
	 */
	public double getDobaNaklada() {
		return this.dobaNaklada;
	}


	/**
	 * @param dobaNaklada the dobaNaklada to set
	 */
	public void setDobaNaklada(double dobaNaklada) {
		this.dobaNaklada = dobaNaklada;
	}


	/**
	 * @return the dobaJede
	 */
	public double getDobaJede() {
		return this.dobaJede;
	}


	/**
	 * @param dobaJede the dobaJede to set
	 */
	public void setDobaJede(double dobaJede) {
		this.dobaJede = dobaJede;
	}


	/**
	 * @return the dobaPreklada
	 */
	public double getDobaPreklada() {
		return this.dobaPreklada;
	}


	/**
	 * @param dobaPreklada the dobaPreklada to set
	 */
	public void setDobaPreklada(double dobaPreklada) {
		this.dobaPreklada = dobaPreklada;
	}


	/**
	 * @return the dobaVyklada
	 */
	public double getDobaVyklada() {
		return this.dobaVyklada;
	}


	/**
	 * @param dobaVyklada the dobaVyklada to set
	 */
	public void setDobaVyklada(double dobaVyklada) {
		this.dobaVyklada = dobaVyklada;
	}



	@Override
	public String toString() {
		String str = "  naklada: " + pocet + "\n" ;

		return str;
	}


	/**
	 * @return the ceka
	 */
	public boolean isCeka() {
		return this.ceka;
	}


	/**
	 * @param ceka the ceka to set
	 */
	public void setCeka(boolean ceka) {
		this.ceka = ceka;
	}




	/**
	 * @return the heli
	 */
	public boolean isHeli() {
		return heli;
	}




	/**
	 * @param heli the heli to set
	 */
	public void setHeli(boolean heli) {
		this.heli = heli;
	}




	/**
	 * @return the vrtulnik
	 */
	public Vrtulnik getVrtulnik() {
		return vrtulnik;
	}




	/**
	 * @param vrtulnik the vrtulnik to set
	 */
	public void setVrtulnik(Vrtulnik vrtulnik) {
		this.vrtulnik = vrtulnik;
	}




	/**
	 * @return the konec
	 */
	public boolean isKonec() {
		return konec;
	}




	/**
	 * @param konec the konec to set
	 */
	public void setKonec(boolean konec) {
		this.konec = konec;
	}





}
