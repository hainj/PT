package pack;

public class Auto {
	
	private int naklad;
	private int poradi=0;
	private double ujetaVzdalenost = 0;
	private String status;
	
	/**
	 * Konstruktor auta s poradim, nakladem
	 */
	public Auto(String status){
		poradi++;
		this.naklad = 0;
		this.setStatus(status);
		
		
	}
	
	public int getNaklad() {
		return naklad;
	}
	
	public void setNaklad(int naklad) {
		this.naklad += naklad;
	}
	
	public int getPoradi() {
		return poradi;
	}
	
	public void setPoradi(int poradi) {
		this.poradi = poradi;
	}
	
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
