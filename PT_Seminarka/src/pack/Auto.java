package pack;

public class Auto {
	
	private int naklad;
	
	private int pocet = 0;
	private double ujetaVzdalenost = 0;
	private String status;
	private boolean nalozeno = false;
	
	public boolean getNalozeno() {
		return nalozeno;
	}

	public void setNalozeno(boolean nalozeno) {
		this.nalozeno = nalozeno;
	}

	/**
	 * Konstruktor auta s poradim, nakladem
	 */
	public Auto(String status){
		this.pocet++;
		this.naklad = 0;
		this.setStatus(status);
		this.nalozeno = false;
		
		
	}
	


	public int getPocet() {
		return pocet;
	}

	public void setPocet(int pocet) {
		this.pocet = pocet;
	}

	public int getNaklad() {
		return naklad;
	}
	
	public void setNaklad(int naklad) {
		this.naklad += naklad;
	}
	

	
	/**
	public void setPoradi(int poradi) {
		this.poradi = poradi;
	}*/

	/**
	 * @return the ujetaVzdalenost
	 */
	public double getUjetaVzdalenost() {
		return ujetaVzdalenost;
	}
	
	/**
	 * @param ujetaVzdalenost the ujetaVzdalenost to set
	 */
	public void setUjetaVzdalenost(double ujetaVzdalenost) {
		this.ujetaVzdalenost = ujetaVzdalenost;
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
	
	
}
