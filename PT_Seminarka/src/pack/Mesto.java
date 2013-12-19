
package pack;

import java.util.ArrayList;
import java.util.List;

public class Mesto {

	private boolean zasobeno = false;
	private int nas = 1;;
	private double jidlo = 0.0;
	private double jidlaTreba = 0.0;
	private int obyvatel = 0;
	private int x = 0;
	private int y = 0;
	private List<Mesto> sousedi;
	private boolean heliport;

	

	private List<Vzdalenost> vzdLet = new ArrayList<>();
	
	private boolean hlad = true;
	private boolean maCesty = true;
	private int zasKdy = Integer.MIN_VALUE;
	private int zasDny = 2;
	private final List<Vrtulnik> vrt = new ArrayList<>();
	private List<Auto> aut = new ArrayList<>();
	



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
		super();
		this.obyvatel = obyvatel;
		this.x = x;
		this.y = y;
		this.sousedi = sousedi;
		//mesto potrebuje 2kg jidla na obyv na den - je mozno skladovat 3 dny
		this.jidlaTreba = obyvatel*2*3;
	}



	/**
	 * @return the jidlo
	 */
	public double getJidlo() {
		return this.jidlo;
	}

	/**
	 * @param maxVyklad the jidlo to set
	 */
	public void setJidlo(double maxVyklad) {
		this.jidlo = maxVyklad;
	}

	/**
	 * @return the jidlaTreba
	 */
	public double getJidlaTreba() {
		return this.jidlaTreba;
	}

	/**
	 * @param jidlaTreba the jidlaTreba to set
	 */
	public void setJidlaTreba(double jidlaTreba) {
		this.jidlaTreba = jidlaTreba;
	}

	/**
	 * @return the maCesty
	 */
	public boolean isMaCesty() {
		return this.maCesty;
	}

	/**
	 * @param maCesty the maCesty to set
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

	public void setHeliport(boolean heliport) {
		this.heliport = heliport;
	}

	public List<Mesto> getSousedi() {
		return this.sousedi;
	}

	public void setSousedi(List<Mesto> sousedi) {
		this.sousedi = sousedi;
	}

	public int getObyvatel() {
		return this.obyvatel;
	}

	public void setObyvatel(int obyvatel) {
		this.obyvatel = obyvatel;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the hlad
	 */
	public boolean getHlad() {
		return this.hlad;
	}

	/**
	 * @param hlad the hlad to set
	 */
	public void setHlad(boolean hlad) {
		this.hlad = hlad;
	}

	@Override
	public String toString() {
		String str = "";
	
		if(this.isMaCesty()){
			str = "Jidlo: " + this.getJidlo() + "\n" + 
					" Jidlo treba: " + this.getJidlaTreba() + "\n" + 
					" Hlad: " + this.getHlad() + "\n";
		}
		else{
			str = "Jidlo: " + this.getJidlo() + "\n" +  
					" Jidlo treba: " + this.getJidlaTreba() + "\n" + 
					" Hlad: " + this.getHlad() + "\n" ;

		}

		return str;
	}


	/**
	 * @param zasobPrv the zasobPrv to set
	 */
	public void setZasKdy(int zasobPrv) {
		this.zasKdy = zasobPrv;
	}

	/**
	 * @return seznam vrtulniku 
	 * 
	 */
	public List<Vrtulnik> getVrt() {
		return this.vrt;
	}

	/**
	 * @param vrt the vrt to set
	 */
	public void addVrt(ArrayList<Vrtulnik> vrt) {
		for(int q = 0; q < vrt.size(); q++){
			this.vrt.add(vrt.get(q));
		}
	}

	/**
	 * @return the aut
	 */
	public List<Auto> getAut() {
		return this.aut;
	}

	/**
	 * @param aut the aut to add
	 */
	public void addAut(ArrayList<Auto> aut) {
		for(int q = 0; q < this.vrt.size(); q++){
			this.aut.add(aut.get(q));
		}
	}

	public double zaokr(){
		double p = 0;
		if(this.getJidlo()>this.getJidlaTreba()){
			p = Math.floor(this.getJidlo());
		}
		else{
			p = Math.ceil(this.getJidlo());
		}
		return p;

	}
	

	/**
	 * @return the zasDny
	 */
	public int getZasDny() {
		return this.zasDny;
	}

	/**
	 * @param zasDny the zasDny to set
	 */
	public void setZasDny(int zasDny) {
		this.zasDny = zasDny;
	}

	/**
	 * @return the vzdLet
	 */
	public List<Vzdalenost> getVzdLet() {
		return this.vzdLet;
	}

	/**
	 * @param vzdLet the vzdLet to set
	 */
	public void setVzdLet(List<Vzdalenost> vzdLet) {
		this.vzdLet = vzdLet;
	}
	public void addVzd(Mesto mesto, double vzdalen) {
		this.vzdLet.add(new Vzdalenost(mesto, vzdalen));
		
	}
	/**
	 * @return the zasobeno
	 */
	public boolean isZasobeno() {
		return this.zasobeno;
	}
	public void setZasobeno(boolean zas){
		this.zasobeno = zas;
	}
	/**
	 * @return the zasKdy
	 */
	public int getZasKdy() {
		return this.zasKdy;
	}

	/**
	 * @return the nas
	 */
	public int getNas() {
		return nas;
	}

	/**
	 * @param nas the nas to set
	 */
	public void setNas(int nas) {
		this.nas = nas;
	}

	


}
