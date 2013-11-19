
package pack;

import java.util.ArrayList;

public class Mesto {

	private int jidlo = 0;
	private int jidlaTreba = 0;
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
		this.jidlaTreba = obyvatel*2*3;
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
	public int getJidlo() {
		return jidlo;
	}

	/**
	 * @param jidlo the jidlo to set
	 */
	public void setJidlo(int jidlo) {
		this.jidlo = jidlo;
	}

	/**
	 * @return the jidlaTreba
	 */
	public int getJidlaTreba() {
		return jidlaTreba;
	}

	/**
	 * @param jidlaTreba the jidlaTreba to set
	 */
	public void setJidlaTreba(int jidlaTreba) {
		this.jidlaTreba = jidlaTreba;
	}

	/**
	 * @return the maCesty
	 */
	public boolean isMaCesty() {
		return maCesty;
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
		return vzdalenost;
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
		return predchudce;
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
		return odkud;
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
		return heliport;
	}

	public void setHeliport(boolean heliport) {
		this.heliport = heliport;
	}

	public ArrayList<Mesto> getSousedi() {
		return sousedi;
	}

	public void setSousedi(ArrayList<Mesto> sousedi) {
		this.sousedi = sousedi;
	}

	public int getObyvatel() {
		return obyvatel;
	}

	public void setObyvatel(int obyvatel) {
		this.obyvatel = obyvatel;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.x = y;
	}

	/**
	 * @return the hlad
	 */
	public boolean getHlad() {
		return hlad;
	}

	/**
	 * @param hlad the hlad to set
	 */
	public void setHlad(boolean hlad) {
		this.hlad = hlad;
	}


}
