package pack;

public class Vrtulnik {
	
	private boolean naklada = false;
	private boolean jede = false;

	private boolean vyklada = false;
	private boolean dokonceno = false;
	private Udalost udalost;
	private double dobaNaklada = 0;
	private double dobaJede = 0;
	private double dobaVyklada = 0;
	private boolean konec = false;

	private static int pocet = 0;
	/**
	 * @param udalost
	 */
	public Vrtulnik(Udalost udalost) {
		super();
		this.udalost = udalost;
		setPocet(getPocet() + 1);
	}



	/**
	 * @return the pocet
	 */
	public static int getPocet() {
		return pocet;
	}



	/**
	 * @param pocet the pocet to set
	 */
	public static void setPocet(int pocet) {
		Vrtulnik.pocet = pocet;
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



	boolean nalozeno = false;
	
	
	/**
	 * @return the nalozeno
	 */
	public boolean isNalozeno() {
		return this.nalozeno;
	}

	/**
	 * @param nalozeno the nalozeno to set
	 */
	public void setNalozeno(boolean nalozeno) {
		this.nalozeno = nalozeno;
	}


	
	@Override
	public String toString() {

		String str = "";
		
		return str;
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
	 * @return the konec
	 */
	public boolean isKonec() {
		return this.konec;
	}



	/**
	 * @param konec the konec to set
	 */
	public void setKonec(boolean konec) {
		this.konec = konec;
	}
	
}
