package pack;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Simulace humanitarni pomoci
 * @author Jakub Hain, David Basta
 *
 */

public class Simulace implements Runnable {
	/**
	 * doba trvani aplikace
	 */
	private final int CAS = 10080;
	/**
	 *Nalozeny naklad za minutu
	 */
	private final double nakladMinuta = 1000.0/30.0;
	/**
	 *Urazena vzdalenost auta za minutu
	 */
	private final double urazenaVzdal = 40.0/60.0;
	/**
	 *Urazena vzdalenost vrt za minutu
	 */
	private final double urazVzdalVrt = 150.0/60.0;
	/**
	 * Maximalni naklad auta
	 */
	private final double maxAuto = 12000.0;
	/**
	 * Maximalni naklad vrtulniku
	 */
	private final double maxVrt = 2000.0;
	/**
	 * seznam mest
	 */
	private List<Mesto> poleMest;
	/**
	 * seznam letist
	 */
	private List<Letiste> poleLetist;
	/**
	 * Cesta k vystupnim souborum
	 */
	private String cesta;
	/**
	 * Seznam aut
	 */
	private static List<Auto> auta = new ArrayList<>();

	/**
	 * Seznam vrtulniky
	 */
	private static List<Vrtulnik> vrtulniky = new ArrayList<>();


	/**Konstruktor simulace
	
	 * @param mesta pole mest
	 * @param letiste pole letist
	 * @param cesta cesta kam budou ulozeny logy
	 */
	public Simulace( List<Mesto> mesta,
			List<Letiste> letiste, String cesta) {


		this.poleMest = mesta;
		this.poleLetist = letiste;
		this.cesta = cesta;
	}








	/**
	 * Pokusi se zastavit beh simulace, pri stisku tlacitka pauza
	 */
	private void pauza(){
		try{

			while(MainAPP.isPause()){
				Thread.sleep(1);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Prubeh programu
	 */
	@Override
	public void run() {

		reset();
		int op = 0;
		int m = 1;
		int u = 1;
		int dny3 = 1;
		for(int i = 0; i <= this.CAS; i++){
			if(i == u *1440){
				VypisLog.zapis(this.poleMest, this.cesta, u);
				VypisLog.zapisAut(Simulace.auta, this.cesta, u);
				VypisLog.zapisVrt(Simulace.vrtulniky, this.cesta, u);
				u++;
			}
			if(i == (dny3*4320)){
				VypisLog.zapis3Dny(this.poleMest, Simulace.auta, Simulace.vrtulniky, this.cesta, dny3);
				dny3++;
			}
			/*for(int z = 0; z<5;z++){
				System.out.println(Mapa.getPoleLetist().get(z).getMaxZasoben());
			}*/
			nalozeniMest(i);
			if(i == m*60){
				pridejZasLet(i);
				m++;
			}
			//for(int i = 0; i <=1; i++){
			pauza();
			vytvorUdalosti();

			//System.out.println(this.auta.size());
			for(int q =0; q < Simulace.getAuta().size(); q++){
				Auto auto = Simulace.getAuta().get(q);
				if(auto.isNaklada() || auto.isCeka()){


					naklAuto(auto);

				}
				if(auto.isJede()){
					jedeAuto(auto);
				}
				if(auto.isVyklada()){
					if(auto.getUdalost().isVrtulnik()){
						preklAuto(auto, q);
						/*try{System.out.println(vrtulniky.get(this.auta.get(6).getUdalost().getIndex().get(0)).getDobaNaklada() + "  " + vrtulniky.get(this.auta.get(6).getUdalost().getIndex().get(0)).getUdalost().getDobaNakl() + "   " + auto.getDobaPreklada());
						}catch(IndexOutOfBoundsException e){

						}*/
					}else{
						vyklAuto(auto);
					}

				}
				if(auto.isDokonceno() && !auto.getUdalost().isVrtulnik()){
					auto.getUdalost().getMesto().setJidlo(auto.getUdalost().getMesto().getJidlo() + auto.getUdalost().getDobaNakl()*this.nakladMinuta);
					//System.out.println(auto.getUdalost().getMesto().getJidlo() + " " + auto.getUdalost().getMesto().getJidlaTreba());
					auto.getUdalost().getOdkudNak().setMaxZasoben(auto.getUdalost().getOdkudNak().getMaxZasoben()-1);
					auto.setDokonceno(false);
					auto.setDokonKdy(i);
					auto.setKonec(true);
				}

				//System.out.println(i+" " + q + " " + auto.isCeka() + " " + auto.getDobaJede() + " " + auto.getUdalost().getDobaJizd());

			}

			for(int o =0; o < Simulace.getVrtulniky().size(); o++){

				Vrtulnik vrt = Simulace.getVrtulniky().get(o);
				if(vrt.isJede()){
					letiHeli(vrt);
					//System.out.println(vrt.getDobaJede() + "  " + vrt.getUdalost().getDobaLetu()+ vrt.getUdalost().getMesto());
				}
				if(vrt.isVyklada()){
					vyklHeli(vrt);
				}
				if(vrt.isDokonceno()){
					vrt.getUdalost().getMesto().setJidlo(vrt.getUdalost().getMesto().getJidlo() + vrt.getUdalost().getDobaNakl()*this.nakladMinuta);
					vrt.setDokonceno(false);
					vrt.setDokonKdy(i);
					vrt.setKonec(true);
				}

			}
			op=i;
			//System.out.println(i + "  " +this.auta.get(9600).getDobaJede() +" " + this.auta.get(9600).getDobaNaklada() +" " + this.auta.get(9599).isDokonceno() );
		}
		VypisLog.souhrn(this.poleMest, auta, vrtulniky, this.cesta);
		JOptionPane.showMessageDialog(MainAPP.parent, "Simulace Dokoncena", "Simulace",JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Konec Simulace " + op + Simulace.getAuta().size() +" " + Simulace.getVrtulniky().size());
	}

	private void reset() {
		// TODO Auto-generated method stub
		Simulace.getAuta().clear();
		Simulace.getVrtulniky().clear();
		for(int i = 0; i < this.poleMest.size();i++){
			this.poleMest.get(i).setJidlo(0);
			this.poleMest.get(i).setHlad(true);
			this.poleMest.get(i).setNas(1);
			this.poleMest.get(i).setZasobeno(false);
			this.poleMest.get(i).setZasKdy(Integer.MIN_VALUE);
			
		}
		for(int q = 0; q <poleLetist.size();q++){
			poleLetist.get(q).getJidlo().clear();
			poleLetist.get(q).getJidlo().add(new Jidlo(15000,0));
		}
	}








	/**
	 * naklada mesta, stara se o nastaveni hladu a odcita zasoby
	 * @param i soucasny cas
	 */
	private void nalozeniMest(int i) {
		for(int n = 0; n < this.poleMest.size();n++){
			Mesto pomMesto = this.poleMest.get(n);
			if(pomMesto.getZasKdy() == i - pomMesto.getNas()*1300){
				if(Math.abs(pomMesto.getJidlo() - pomMesto.getJidlaTreba()*2/3)<=0.002 ){
					pomMesto.setHlad(true);
				}
			}
			if(pomMesto.getZasKdy() == i - pomMesto.getNas()*1440){

				//pomMesto.setZasobeno(false);
				pomMesto.setNas(pomMesto.getNas() +1);
				pomMesto.setJidlo(pomMesto.getJidlo()-pomMesto.getJidlaTreba()/3);



			}
			else if(Math.abs(pomMesto.getJidlo()- pomMesto.getJidlaTreba()) <0.002){
				if(!pomMesto.isZasobeno()){
					pomMesto.setJidlo(pomMesto.getJidlaTreba());
					pomMesto.setZasKdy(i);
					pomMesto.setNas(1);
					pomMesto.setZasobeno(true);
				}



			}

			if(pomMesto.getJidlo()<0){
				System.out.println("sakra");
			}

		}

	}








	/**
	 * Vykladani helikoptery 
	 * @param vrt vrtulnik
	 */
	private static void vyklHeli(Vrtulnik vrt) {
		if(vrt.getDobaVyklada()>vrt.getUdalost().getDobaNakl()){
			vrt.setDobaVyklada(vrt.getUdalost().getDobaNakl());
			vrt.setVyklada(false);
			vrt.setDokonceno(true);
		}
		else{
			vrt.setDobaVyklada(vrt.getDobaVyklada() + 1.0);
		}

	}








	/**
	 * Stara se o let helikoptery do mesta
	 * @param vrt vrtulnik
	 */
	private static void letiHeli(Vrtulnik vrt) {
		if(vrt.getDobaJede()>vrt.getUdalost().getDobaLetu()){
			vrt.setDobaJede(vrt.getUdalost().getDobaLetu());
			vrt.setJede(false);
			vrt.setVyklada(true);

		}
		else{
			vrt.setDobaJede(vrt.getDobaJede() + 1.0);
		}

	}








	/**
	 * Stara se o prekladani nakladu vozidla do vrtulniku
	 * @param auto auto, z ktereho se preklada
	 * @param q index auta
	 */
	private void preklAuto(Auto auto, int q) {
		if(!auto.isHeli()){
			vytvorHeli(auto, q);
		}

		if(!autoPrelozeno(auto))
			for(int z = 0; z < auto.getUdalost().getIndex().size(); z++){
				Vrtulnik vrt = Simulace.getVrtulniky().get(auto.getUdalost().getIndex().get(z));
				if(vrt.isNaklada()){
					//System.out.println(vrt.getUdalost().getDobaNakl());
					if(vrt.getDobaNaklada()> vrt.getUdalost().getDobaNakl()){
						vrt.setDobaNaklada(vrt.getUdalost().getDobaNakl());
						vrt.setNaklada(false);
						vrt.setJede(true);
					}
					else if(vrt.getUdalost().getDobaNakl() - vrt.getDobaNaklada()<1){
						vrt.setDobaNaklada(vrt.getDobaNaklada() + 1.0);
						//System.out.println(vrt.getDobaNaklada());
						auto.setDobaPreklada(auto.getDobaPreklada() +vrt.getUdalost().getDobaNakl() - vrt.getDobaNaklada());
					}
					else{
						vrt.setDobaNaklada(vrt.getDobaNaklada() + 1.0);
						//System.out.println(vrt.getDobaNaklada() + " " + q);
						auto.setDobaPreklada(auto.getDobaPreklada() + 1.0);
					}
				}

			}
	}
	/**
	 * Zjistuje zda auto naplnilo vsechny vrtulniky
	 * @param auto auto
	 * @return boolean 
	 */
	private static boolean autoPrelozeno(Auto auto) {
		boolean q = true;
		for(int z = 0; z < auto.getUdalost().getIndex().size(); z++){
			Vrtulnik vrt = Simulace.getVrtulniky().get(auto.getUdalost().getIndex().get(z));
			if(vrt.isNaklada()){
				return false;		
			}	
		}
		auto.setDokonceno(true);
		auto.getUdalost().getOdkudNak().setMaxZasoben(auto.getUdalost().getOdkudNak().getMaxZasoben()-1);
		auto.setVyklada(false);
		return q;

	}









	/**
	 * Vytvori vrtulniky
	 * @param auto auto
	 * @param q2 index auta
	 */
	private void vytvorHeli(Auto auto, int q2) {
		double zasJidla = auto.getDobaNaklada()*this.nakladMinuta;
		double jidVrt =zasJidla/this.maxVrt;
		double pocetVrt =  Math.ceil(jidVrt);
		double jidloTreb;
		double x;
		for(int r = 0; r < pocetVrt; r++){
			if((x= zasJidla % this.maxVrt) == 0){
				jidloTreb= this.maxVrt;

			}else{
				jidloTreb = x;
				zasJidla = zasJidla - x;
				//System.out.println(zasJidla);
			}
			jidloTreb = jidloTreb/this.nakladMinuta;
			//System.out.println(jidloTreb*this.nakladMinuta);
			Udalost ud = new Udalost(auto.getUdalost().getMesto(), true, jidloTreb);
			Vrtulnik vrt = new Vrtulnik(ud);
			vrt.getUdalost().getIndex().add(q2);
			vrt.setNaklada(true);
			//System.out.println(jidloTreb +"  " + auto.getDobaNaklada());
			vrt.getUdalost().setDobaLetu(auto.getUdalost().getDobaLetu());
			Simulace.getVrtulniky().add(vrt);
			auto.getUdalost().getIndex().add(Simulace.getVrtulniky().size()-1);


		}
		auto.setHeli(true);

	}






	/**
	 * pridava zasoby na letiste
	 * @param i soucasny cas
	 */
	private void pridejZasLet(int i) {

		for(int q = 0; q < this.poleLetist.size(); q++){
			kontZkazene(this.poleLetist.get(q), i);
			this.poleLetist.get(q).addJidlo(15000.0, i);	
		}


	}








	/**
	 * kontroluje zkazenost potravy
	 * @param letiste letiste
	 * @param i cas
	 */
	private static void kontZkazene(Letiste letiste, int i) {
		for(int w = 0; w < letiste.getJidlo().size();w++){
			if(letiste.getJidlo().get(w).getDodano()+4320==i){
				letiste.getJidlo().remove(w);
				w--;
				//System.out.println("aaaaa  " +i);

			}

		}

	}

















	/**
	 * vyklada auto
	 * @param auto auto
	 */
	private static void vyklAuto(Auto auto) {
		if(auto.getDobaVyklada()>auto.getUdalost().getDobaNakl()){
			auto.setDobaVyklada(auto.getUdalost().getDobaNakl());
			auto.setVyklada(false);
			auto.setDokonceno(true);
		}
		else{
			auto.setDobaVyklada(auto.getDobaVyklada() + 1.0);
		}

	}








	/**
	 * Jízda auta
	 * @param auto auto
	 */
	private static void jedeAuto(Auto auto) {
		if(auto.getDobaJede()>auto.getUdalost().getDobaJizd()){
			auto.setJede(false);
			auto.setVyklada(true);
			auto.setDobaJede(auto.getUdalost().getDobaJizd());
		}
		else{
			auto.setDobaJede(auto.getDobaJede() + 1.0);
		}

	}








	/**
	 * Rozhoduje o nakladani auta z letiste
	 * @param auto auto
	 */
	private void naklAuto(Auto auto) {
		if(auto.isCeka()){
			zkontZasoby(auto);
		}
		//System.out.println(auto.isCeka());
		if(!auto.isCeka()){

			pridejNaklad(auto);		
		}

	}








	/**
	 * Naklada auto
	 * @param auto auto
	 */
	private static void pridejNaklad(Auto auto) {
		List<Jidlo> jid = auto.getUdalost().getOdkudNak().getJidlo();	
		if(jid.size() ==0){
			return;
		}
		else{
			while(jid.get(0).getJidlo() < 1.0){
				jid.remove(0); 
				if(jid.size() ==0){
					return;
				}
			}
			if(auto.getDobaNaklada() > auto.getUdalost().getDobaNakl()){
				auto.setNaklada(false);
				auto.setDobaNaklada(auto.getUdalost().getDobaNakl());
				auto.setJede(true);
			}
			else if((auto.getUdalost().getDobaNakl() - auto.getDobaNaklada())<1.0){

				auto.getUdalost().getOdkudNak().getJidlo().get(0).setJidlo(auto.getUdalost().getDobaNakl() - auto.getDobaNaklada());
				auto.setDobaNaklada(auto.getDobaNaklada()+1.0);
				//System.out.println("abcfg" + auto.getDobaNaklada());
			}else{
				//System.out.println("abc" + auto.getDobaNaklada());

				auto.setDobaNaklada(auto.getDobaNaklada()+1.0);
				auto.getUdalost().getOdkudNak().getJidlo().get(0).setJidlo(-1.0);
				//System.out.println("abcf" + auto.getDobaNaklada());
			}


		}
	}








	/**
	 * Zjistuje odkud se budou auta zasobovat
	 * @param auto auto
	 */
	private void zkontZasoby(Auto auto) {


		//System.out.println(vzd.get(i).getLet().getJidlo().size());
		//System.out.println( vzd.get(i).getLet());
		if(auto.getUdalost().isVrtulnik()){
			double minVzdSeZas=10000.0;
			int indexVzd = 0;
			int indexMesta = 0;
			double p = 0;


			for(int q = 0; q < auto.getUdalost().getMesto().getVzdLet().size();q++){
				Mesto pomMesto = auto.getUdalost().getMesto().getVzdLet().get(q).getMesto(); 




				for(int l = 0; l <pomMesto.getVzdLet().size();l++){
					boolean a = kontrolaZasobLet(pomMesto.getVzdLet().get(l).getLet());

					if(a){


						if(minVzdSeZas>
						(p = auto.getUdalost().getMesto().getVzdLet().get(q).vzdalenost
						+ pomMesto.getVzdLet().get(l).vzdalenost)){


							minVzdSeZas = p;
							indexVzd = l;
							indexMesta = q;

						}




					}

				}

			}

			Letiste let =auto.getUdalost().getMesto().getVzdLet().
					get(indexMesta).getMesto().getVzdLet().get(indexVzd).getLet();

			auto.getUdalost().setDobaLetu(auto.getUdalost().getMesto().getVzdLet().
					get(indexMesta).vzdalenost/this.urazVzdalVrt);

			auto.getUdalost().setDobaJizd(auto.getUdalost().getMesto().getVzdLet().
					get(indexMesta).getMesto().getVzdLet().get(indexVzd).vzdalenost/this.urazenaVzdal);

			auto.getUdalost().setOdkudNak(let);
			let.setMaxZasoben(let.getMaxZasoben()+1);
			auto.setCeka(false);
			auto.setNaklada(true);
		}
		else{
			List <Vzdalenost> vzd = auto.getUdalost().getMesto().getVzdLet();
			for(int i = 0; i < vzd.size(); i++){
				List<Jidlo> jid = vzd.get(i).getLet().getJidlo();

				if(jid.size() !=0){

					while(jid.get(0).getJidlo() < 1){
						jid.remove(0); 
						if(jid.size() ==0){
							break;
						}
					}
					if(jid.size()==0){
						continue;

					}else {
						auto.getUdalost().setDobaJizd(vzd.get(i).vzdalenost/this.urazenaVzdal);
						auto.getUdalost().setOdkudNak(vzd.get(i).getLet());
						auto.setCeka(false);
						vzd.get(i).getLet().setMaxZasoben(vzd.get(i).getLet().getMaxZasoben() + 1);
						auto.setNaklada(true);
						break;
					}
				}

			}
		}


	}








	/**
	 * Zda jsou na letisti nejake zasoby
	 * @param let letiste
	 * @return boolean
	 */
	private static boolean kontrolaZasobLet(Letiste let) {
		if(let.getJidlo().size() ==0){
			return false;
		}
		else{
			while(let.getJidlo().get(0).getJidlo() < 1.0){
				let.getJidlo().remove(0); 
				if(let.getJidlo().size() ==0){
					return false;
				}
			}
		}
		return true;
	}








	/**
	 * Vytvori vsechy udalosti aut
	 */
	private void vytvorUdalosti() {
		for(int i = this.poleMest.size()- 1; i >= 0;i--){

			Mesto pomMesto = this.poleMest.get(i);
			if(pomMesto.getHlad()){
				Udalost ud;
				int zasTreba = (int) (pomMesto.getJidlaTreba() - pomMesto.getJidlo());
				double jidAuta =zasTreba/this.maxAuto;
				int pocetAut = (int) Math.ceil(jidAuta);
				//System.out.println(" aut" + pocetAut);
				//System.out.println(jidAuta);
				for(int q = 0; q < pocetAut; q++){
					double jidloTreb;
					double x;
					double dobaNakl;

					if((x= zasTreba % this.maxAuto) == 0){
						jidloTreb= this.maxAuto;

					}else{
						jidloTreb = x;
						zasTreba = (int) (zasTreba - x);
						//System.out.println(x);


					}
					dobaNakl = jidloTreb/this.nakladMinuta;
					//System.out.println(x);

					if(pomMesto.isMaCesty()){
						ud = new Udalost(pomMesto, false, dobaNakl);
					}
					else{
						ud = new Udalost(pomMesto, true, dobaNakl);
					}

					Auto auto = new Auto(ud);
					auto.setCeka(true);
					Simulace.getAuta().add(auto);
				}

				//System.out.println(i + " " + this.auta.size() + " " + this.auta.get(i).getUdalost().getDobaNakl()*this.nakladMinuta);
				pomMesto.setHlad(false);

			}

		}



	}









	/** getr seznamu vrtulniku
	 * @return the vrtulniky
	 */
	public static List<Vrtulnik> getVrtulniky() {
		return vrtulniky;
	}









	/**setr seznamu vrtulniku
	 * @param vrtulniky seznam vrtulniku
	 */
	public static void setVrtulniky(List<Vrtulnik> vrtulniky) {
		Simulace.vrtulniky = vrtulniky;
	}









	/**getr seznamu aut
	 * @return the auta
	 */
	public static List<Auto> getAuta() {
		return auta;
	}









	/**setr seznamu aut
	 * @param auta the auta to set
	 */
	public static void setAuta(List<Auto> auta) {
		Simulace.auta = auta;
	}














}


