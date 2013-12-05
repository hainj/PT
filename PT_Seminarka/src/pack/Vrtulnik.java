package pack;

public class Vrtulnik {
	
	private double naklad;
	private double nakladTreba;
	private int poradi=0;
	private double ujetaVzdalenost = 0;
	private String status;
	boolean nalozeno = false;
	/**
	 * Konstruktor vrtulníku s poradim, nakladem
	 * @param jidloTreb 
	 */
	public Vrtulnik(String status, double jidloTreb){
		this.poradi++;
		this.naklad = 0;
		this.status = status;
		this.setNakladTreba(jidloTreb);
		
	}
	
	
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

	public double getNaklad() {
		return this.naklad;
	}
	
	public void setNaklad(double maxVyklad) {
		this.naklad += maxVyklad;
	}
	
	public int getPoradi() {
		return this.poradi;
	}
	
	public void setPoradi(int poradi) {
		this.poradi = poradi;
	}
	
	/**
	 * @return the ujetaVzdalenost
	 */
	public double getUjetaVzdalenost() {
		return this.ujetaVzdalenost;
	}
	
	/**
	 * @param ujetaVzdalenost the ujetaVzdalenost to set
	 */
	public void setUjetaVzdalenost(double ujetaVzdalenost) {
		this.ujetaVzdalenost += ujetaVzdalenost;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the nakladTreba
	 */
	public double getNakladTreba() {
		return this.nakladTreba;
	}


	/**
	 * @param nakladTreba the nakladTreba to set
	 */
	public void setNakladTreba(double nakladTreba) {
		this.nakladTreba = nakladTreba;
	}
	
	@Override
	public String toString() {

		String str = "Naklad = " + Math.floor(this.getNaklad()) + " \n" +
				" Potreba nalozit: " + this.getNakladTreba() + " \n" + 
				" Ujeta vzdalenost: " + this.getUjetaVzdalenost()+ " \n" +
				" Status: " + this.getStatus() + " Nalozeno: " + this.isNalozeno();
		
		return str;
	}
	
}
