
package pack;

import java.util.ArrayList;

public class Mesto {

	private double jidlo = 0;
	private double jidlaTreba = 0;
	private int obyvatel = 0;
	private int x = 0;
	private int y = 0;
	private ArrayList<Mesto> sousedi;
	private boolean heliport;
	private double vzdalenost = 100000;
	private Mesto predchudce;
	private Letiste odkud;
	
	private boolean hlad = true;
	private boolean maCesty = true;
	private int zasKdy = Integer.MIN_VALUE;
	private int zasDny = 2;
	private final ArrayList<Vrtulnik> vrt = new ArrayList<>();
	private ArrayList<Auto> aut = new ArrayList<>();





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
		this.jidlo += maxVyklad;
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
	 * @return the vzdalenost
	 */
	public double getVzdalenost() {
		return this.vzdalenost;
	}

	/**
	 * @param vzdalenost the vzdalenost to set
	 */
	public void setVzdalenost(double vzdalenost) {
		this.vzdalenost = vzdalenost;
	}

	/**
	 * @return the predchudce
	 */
	public Mesto getPredchudce() {
		return this.predchudce;
	}

	/**
	 * @param predchudce the predchudce to set
	 */
	public void setPredchudce(Mesto predchudce) {
		this.predchudce = predchudce;
	}

	/**
	 * @return the odkud
	 */
	public Letiste getOdkud() {
		return this.odkud;
	}

	/**
	 * @param odkud the odkud to set
	 */
	public void setOdkud(Letiste odkud) {
		this.odkud = odkud;
	}

	/**
	 * Vraci boolean, zda mesto ma nebo nema heliport
	 * @return
	 */
	public boolean getHeliport() {
		return this.heliport;
	}

	public void setHeliport(boolean heliport) {
		this.heliport = heliport;
	}

	public ArrayList<Mesto> getSousedi() {
		return this.sousedi;
	}

	public void setSousedi(ArrayList<Mesto> sousedi) {
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
		double p = 0;
		if(this.getJidlo()>this.getJidlaTreba()){
			p = Math.floor(this.getJidlo());
		}
		else{
			p = Math.ceil(this.getJidlo());
		}
		if(this.isMaCesty()){
			str = "Jidlo: " + p + "\n" + 
					" Jidlo treba: " + this.getJidlaTreba() + "\n" + 
					" Hlad: " + this.getHlad() + "\n" + 
					" Vzdalenost: " + this.getVzdalenost();
		}
		else{
			str = "Jidlo: " + p + "\n" +  
					" Jidlo treba: " + this.getJidlaTreba() + "\n" + 
					" Hlad: " + this.getHlad() + "\n" + 
					" Vzdalenost: od mesta s heliportem " + this.getVzdalenost()+ "\n" +
					" Vzdalenost od letiste: " + (this.getVzdalenost() + this.getPredchudce().getVzdalenost());

		}

		return str;
	}

	/**
	 * @return the zasobPrv
	 */
	public int getZasobKdy() {
		return this.zasKdy;
	}

	/**
	 * @param zasobPrv the zasobPrv to set
	 */
	public void setZasKdy(int zasobPrv) {
		this.zasKdy = zasobPrv;
	}

	/**
	 * @return 
	 * @return the vrt
	 */
	public ArrayList<Vrtulnik> getVrt() {
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
	public ArrayList<Auto> getAut() {
		return this.aut;
	}

	/**
	 * @param aut the aut to add
	 */
	public void addAut(ArrayList<Auto> aut) {
		for(int q = 0; q < vrt.size(); q++){
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
	 * @return the prubeh
	 */


}
