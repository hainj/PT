
package pack;

import java.util.ArrayList;
import java.util.List;
/**
 * Trida konstruktoru mesta
 * @author Cybra
 *
 */
public class Mesto {
	/**
	 *Zda je mesto plne zasobeno
	 */
	private boolean zasobeno = false;
	/**
	 * nasobic dnu
	 */
	private int nas = 1;
	/**
	 * soucasny stav jidla
	 */
	private double jidlo = 0.0;
	/**
	 * jidlo potreba
	 */
	private double jidlaTreba = 0.0;
	/**
	 * pocet obyvatel
	 */
	private int obyvatel = 0;
	/**
	 * x-ova souradnice mesta
	 */
	private int x = 0;
	/**
	 * y-ova souradnice mesta
	 */
	private int y = 0;
	/**
	 * seznam sousedu mesta
	 */
	private List<Mesto> sousedi;
	/**
	 * Zda ma mesto heliport
	 */
	private boolean heliport;


	/**
	 * seznam vzdalenosti od letist
	 */
	private List<Vzdalenost> vzdLet = new ArrayList<>();
	/**
	 * Hlad mesta
	 */
	private boolean hlad = true;
	/**
	 * Mesto s cestami nebo bez
	 */
	private boolean maCesty = true;
	/**
	 * Kdy musi byt mesto opet zasobeno
	 */
	private int zasKdy = Integer.MIN_VALUE;
	
	




	/**
	 * Konstruktor mest pro jeho polohu a pocet obyvatel
	 * @param x x-ova souradnice
	 * @param y y-ova souradnice
	 * @param obyvatel pocet obyv
	 */
	public Mesto(int x, int y, int obyvatel, boolean heli)
	{

		this.x = x;
		this.y = y;
		this.obyvatel = obyvatel;
		this.heliport = heli;
		//mesto potrebuje 2kg jidla na obyv na den - je mozno skladovat 3 dny
		this.jidlaTreba = obyvatel*2.0*3.0;

	}

	/**
	 *  Konstruktor mesta s polohou, poctem obyvatel a jeho sousedy
	 * @param x x-ova souradnice
	 * @param y y-ova souradnice
	 * @param obyvatel pocet obyv
	 * @param sousedi pole sousedu mesta
	 */
	public Mesto( int x, int y, int obyvatel,ArrayList<Mesto> sousedi) {

		this.obyvatel = obyvatel;
		this.x = x;
		this.y = y;
		this.sousedi = sousedi;
		//mesto potrebuje 2kg jidla na obyv na den - je mozno skladovat 3 dny
		this.jidlaTreba = obyvatel*2*3;
	}



	/**Getr soucasneho stavu jidla ve meste
	 * @return the jidlo
	 */
	public double getJidlo() {
		return this.jidlo;
	}

	/**Jidlo soucasne ve meste
	 * @param maxVyklad kolik jidla mame
	 */
	public void setJidlo(double maxVyklad) {
		this.jidlo = maxVyklad;
	}

	/**Getr kolik jidla je potreba pro mesto
	 * @return the jidlaTreba
	 */
	public double getJidlaTreba() {
		return this.jidlaTreba;
	}

	/**Setr kolik jidla je treba dovezt
	 * @param jidlaTreba the jidlaTreba to set
	 */
	public void setJidlaTreba(double jidlaTreba) {
		this.jidlaTreba = jidlaTreba;
	}

	/**Getr prommene cesty
	 * @return the maCesty
	 */
	public boolean isMaCesty() {
		return this.maCesty;
	}

	/**Setr urcujici zda ma mesto cesty
	 * @param maCesty true ma false nema
	 */
	public void setMaCesty(boolean maCesty) {
		this.maCesty = maCesty;
	}


	/**
	 * Vraci boolean, zda mesto ma nebo nema heliport
	 * @return true ma heliport false nema heliport
	 */
	public boolean getHeliport() {
		return this.heliport;
	}
	/**
	 * Setr heliportu
	 * @param heliport heliport
	 */
	public void setHeliport(boolean heliport) {
		this.heliport = heliport;
	}
	/**
	 *  Getr seznamu sousedu
	 * @return seznam sousedu
	 */
	public List<Mesto> getSousedi() {
		return this.sousedi;
	}
	/**
	 * Setr seznamu sousedu mesta
	 * @param sousedi seznam sousedu
	 */
	public void setSousedi(List<Mesto> sousedi) {
		this.sousedi = sousedi;
	}
	/**
	 * Getr poctu obyvatel
	 * @return pocet obyv
	 */
	public int getObyvatel() {
		return this.obyvatel;
	}
	/**
	 * Setr poctu obyvatel
	 * @param obyvatel pocet obyvatel
	 */
	public void setObyvatel(int obyvatel) {
		this.obyvatel = obyvatel;
	}
	/**
	 * Getr x-ove sour
	 * @return x-ova sour
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * Setr x-ove sour
	 * @param x x-ova sour
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getr y-ove sour
	 * @return y-ova sour
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * Str y-ove sour
	 * @param y y-ova sour
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**Getr hladu
	 * @return the hlad
	 */
	public boolean getHlad() {
		return this.hlad;
	}

	/**Setr hladu
	 * @param hlad the hlad to set
	 */
	public void setHlad(boolean hlad) {
		this.hlad = hlad;
	}
	/**
	 * Vypise mesto
	 * @param i index mesta
	 * @return string s info
	 */
	public String vypisMesto(int i){
		String str = "";


		str = "Mesto "+ i + "\n" + 
				"Obyvatel: "+ this.getObyvatel() + "\n" + 
				"Jidlo: " + this.getJidlo() + "\n" + 
				"Jidlo treba: " + this.getJidlaTreba() + "\n" + 
				"Hlad: " + this.getHlad() + "\n" + 
				"Cesty: " + this.isMaCesty() + "\n" +
				"Heliport: " + this.getHeliport() + "\n";




		return str;
	}


	/**
	 * @param zasobPrv the zasobPrv to set
	 */
	public void setZasKdy(int zasobPrv) {
		this.zasKdy = zasobPrv;
	}



	


	

	/**Getr pole vzdalenosti
	 * @return the vzdLet pole vzdalenosti
	 */
	public List<Vzdalenost> getVzdLet() {
		return this.vzdLet;
	}

	/**Nastavuje pole vzdalenosti od letiste
	 * @param vzdLet pole vzdalenosti
	 */
	public void setVzdLet(List<Vzdalenost> vzdLet) {
		this.vzdLet = vzdLet;
	}
	/**
	 * Prida do pole vzdalenosti dalsiho clena
	 * @param mesto predchudce
	 * @param vzdalen vzdalenost od letiste
	 */
	public void addVzd(Mesto mesto, double vzdalen) {
		this.vzdLet.add(new Vzdalenost(mesto, vzdalen));

	}
	/**Boolean zda je mesto plne zasobeno
	 * @return the zasobeno
	 */
	public boolean isZasobeno() {
		return this.zasobeno;
	}
	public void setZasobeno(boolean zas){
		this.zasobeno = zas;
	}
	/**Cas zasobeni mesta
	 * @return kdy bylo mesto zasobeno
	 */
	public int getZasKdy() {
		return this.zasKdy;
	}

	/**Getr nasobice
	 * @return nasobic dnu
	 */
	public int getNas() {
		return nas;
	}

	/**Nasobic dne
	 * @param nastavuje nasobic dne
	 */
	public void setNas(int nas) {
		this.nas = nas;
	}
	/**
	 * Vypise informace o mestu
	 * @return sting informaci
	 */
	public String vypisMesto() {
		String str = "";


		str ="Obyvatel: "+ this.getObyvatel() + "\n" + 
				"Jidlo: " + this.getJidlo() + "\n" + 
				"Jidlo treba: " + this.getJidlaTreba() + "\n" + 
				"Hlad: " + this.getHlad() + "\n" + 
				"Cesty: " + this.isMaCesty() + "\n" +
				"Heliport: " + this.getHeliport() + "\n";




		return str;
	}
}





