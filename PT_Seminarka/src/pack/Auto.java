package pack;

public class Auto {
	private int rychlost;
	private int naklad;
	private int poradi=0;
	/**
	 * Konstruktor auta s poradim, nakladem, a zakladni rychlosti
	 */
	public Auto(int poradi){
		this.poradi = poradi;
		this.naklad = 0;
		this.rychlost = 40;
		
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
	public int getRychlost() {
		return rychlost;
	}
	
}
