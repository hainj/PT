package pack;

public class Auto {
	
	private double naklad;
	
	private int pocet = 0;
	private double ujetaVzdalenost = 0.0;
	private String status;
	private final double POTREBANALOZIT;
	private boolean nalozeno = false;
	
	public boolean getNalozeno() {
		return this.nalozeno;
	}

	public void setNalozeno(boolean nalozeno) {
		this.nalozeno = nalozeno;
	}

	/**
	 * Konstruktor auta s poradim, nakladem
	 */
	public Auto(String status, final double POTREBANALOZIT){
		this.pocet++;
		this.naklad = 0;
		this.setStatus(status);
		this.nalozeno = false;
		this.POTREBANALOZIT = POTREBANALOZIT;
		
		
	}
	


	/**
	 * @return the POTREBANALOZIT
	 */
	public final double getPotrebaNalozit() {
		return this.POTREBANALOZIT;
	}

	public int getPocet() {
		return this.pocet;
	}

	public void setPocet(int pocet) {
		this.pocet = pocet;
	}

	public double getNaklad() {
		return this.naklad;
	}
	
	public void setNaklad(double maxVyklad) {
		this.naklad += maxVyklad;
	}
	

	
	/**
	public void setPoradi(int poradi) {
		this.poradi = poradi;
	}*/

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
		this.ujetaVzdalenost = ujetaVzdalenost;
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
	
	@Override
	public String toString() {
		String str = "Naklad = " + Math.floor(this.getNaklad()) + "\n" +
					"  Potreba nalozit: " + this.getPotrebaNalozit() + "\n" + 
					"  Ujeta vzdalenost: " + this.getUjetaVzdalenost()+ "\n" +
					"  Nalozeno: " + this.getNalozeno() + "\n" +
					"  Status: " + this.getStatus();
		return str;
	}
	
}
