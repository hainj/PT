
package pack;

import java.util.ArrayList;

public class Mesto {

	private double jidlo = 0;
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
	private int zasKdy;
	private ArrayList<Vrtulnik> vrt = new ArrayList<Vrtulnik>();
	private ArrayList<Auto> aut = new ArrayList<Auto>();
	




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
	public double getJidlo() {
		return jidlo;
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

	@Override
	public String toString() {
		String str = "";
		double x = 0;
		if(this.getJidlo()>this.getJidlaTreba()){
			x = Math.floor(this.getJidlo());
		}
		else{
			x = Math.ceil(this.getJidlo());
		}
		if(this.isMaCesty()){
			str = "Jidlo: " + x + "\n" + 
					" Jidlo treba: " + this.getJidlaTreba() + "\n" + 
					" Hlad: " + this.getHlad() + "\n" + 
					" Vzdalenost: " + this.getVzdalenost();
		}
		else{
			str = "Jidlo: " + x + "\n" +  
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
		return zasKdy;
	}

	/**
	 * @param zasobPrv the zasobPrv to set
	 */
	public void setZasKdy(int zasobPrv) {
		this.zasKdy = zasobPrv;
	}

	/**
	 * @return the vrt
	 */
	public ArrayList<Vrtulnik> getVrt() {
		return vrt;
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
		return aut;
	}

	/**
	 * @param aut the aut to add
	 */
	public void addAut(ArrayList<Auto> aut) {
		for(int q = 0; q < aut.size(); q++){
			this.aut.add(aut.get(q));
			}
	}


}
