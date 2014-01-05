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
			System.out.println(i);
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

				for(int d = 0;d < auto.getUdalost().size(); d++){

					Udalost udalostAuto = auto.getUdalost().get(d);
					if(udalostAuto.isSoucasUdal() && !udalostAuto.isKonec() && !auto.isKonec()){
						if(udalostAuto.isNaklada() || udalostAuto.isCeka()){


							naklAuto(auto, udalostAuto);

						}
						if(udalostAuto.isJede()){
							jedeAuto(auto,udalostAuto);
						}
						if(udalostAuto.isVyklada()){
							if(auto.getUdalost().get(d).isVrtulnik()){
								preklAuto(auto, q);
								/*try{System.out.println(vrtulniky.get(this.auta.get(6).getUdalost().getIndex().get(0)).getDobaNaklada() + "  " + vrtulniky.get(this.auta.get(6).getUdalost().getIndex().get(0)).getUdalost().getDobaNakl() + "   " + auto.getDobaPreklada());
						}catch(IndexOutOfBoundsException e){

						}*/
							}else{
								vyklAuto(auto, udalostAuto, d);
							}

						}
						if(udalostAuto.isDokonceno() && !udalostAuto.isVrtulnik()){
							udalostAuto.getMesto().setJidlo(udalostAuto.getMesto().getJidlo() + udalostAuto.getDobaNakl()*this.nakladMinuta);
							//System.out.println(auto.getUdalost().getMesto().getJidlo() + " " + auto.getUdalost().getMesto().getJidlaTreba());
							//udalostAuto.getOdkudNak().setMaxZasoben(udalostAuto.getOdkudNak().getMaxZasoben()-1);
							udalostAuto.setDokonceno(false);
							udalostAuto.setDokonKdy(i);
							udalostAuto.setKonec(true);
						}

						//System.out.println(i+" " + q + " " + auto.isCeka() + " " + auto.getDobaJede() + " " + auto.getUdalost().getDobaJizd());
					}
				}
				dokonAuto(auto);
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
		}
		VypisLog.souhrn(this.poleMest, auta, vrtulniky, this.cesta);
		JOptionPane.showMessageDialog(MainAPP.parent, "Simulace Dokoncena", "Simulace",JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Konec Simulace " + op + Simulace.getAuta().size() +" " + Simulace.getVrtulniky().size());
	}

	private static void dokonAuto(Auto auto) {
		boolean dokon = true;
		for(int o =0; o <auto.getUdalost().size(); o++){
			if(!auto.getUdalost().get(o).isKonec()){
				dokon = false;
			}
		}
		if(dokon){
			auto.setKonec(true);
		}
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
		for(int q = 0; q <this.poleLetist.size();q++){
			this.poleLetist.get(q).getJidlo().clear();
			this.poleLetist.get(q).getJidlo().add(new Jidlo(15000,0));
		}
	}








	/**
	 * naklada mesta, stara se o nastaveni hladu a odcita zasoby
	 * @param i soucasny cas
	 */
	private void nalozeniMest(int i) {
		for(int n = 0; n < this.poleMest.size();n++){

			Mesto pomMesto = this.poleMest.get(n);
			if(!pomMesto.getCasVytUd().isEmpty() && (pomMesto.getCasVytUd().size() > pomMesto.getIndexVytUd())){
				if(pomMesto.getCasVytUd().get(pomMesto.getIndexVytUd()) == i){
					{
						pomMesto.setHlad(true);
						pomMesto.setIndexVytUd(pomMesto.getIndexVytUd() + 1);
					}
				}
			}
			if(!pomMesto.getCasOdcZas().isEmpty() && (pomMesto.getCasOdcZas().size()> pomMesto.getIndexOdc())){
				if(pomMesto.getCasOdcZas().get(pomMesto.getIndexOdc()) == i){

					//pomMesto.setZasobeno(false);
					pomMesto.setIndexOdc(pomMesto.getIndexOdc() + 1);
					pomMesto.setJidlo(pomMesto.getJidlo()-pomMesto.getJidlaTreba()/3);



				}
			}
			if(Math.abs(pomMesto.getJidlo()- pomMesto.getJidlaTreba()) <0.002){
				if(!pomMesto.isZasobeno()){
					pomMesto.setJidlo(pomMesto.getJidlaTreba());
					int k = 0;
					int den = 1440;
					int celk = 0;
					while((celk = k*den+i)<this.CAS){
						pomMesto.getCasOdcZas().add(celk);
						k++;
					}
					for(int r = 2; r < pomMesto.getCasOdcZas().size(); r += 2){
						pomMesto.getCasVytUd().add((int) (pomMesto.getCasOdcZas().get(r) - (pomMesto.getJidlaTreba()/3)/this.nakladMinuta));

					}
					pomMesto.setNas(1);
					pomMesto.setZasobeno(true);
				}



			}


			if(pomMesto.getJidlo()<0){
				System.out.println("sakra " + Mapa.getIndexMest(pomMesto) + " " + pomMesto.getJidlo());
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
			for(int z = 0; z < auto.getUdalost().get(0).getIndex().size(); z++){
				Vrtulnik vrt = Simulace.getVrtulniky().get(auto.getUdalost().get(0).getIndex().get(z));
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
		for(int z = 0; z < auto.getUdalost().get(0).getIndex().size(); z++){
			Vrtulnik vrt = Simulace.getVrtulniky().get(auto.getUdalost().get(0).getIndex().get(z));
			if(vrt.isNaklada()){
				return false;		
			}	
		}
		auto.getUdalost().get(0).setDokonceno(true);
		auto.getUdalost().get(0).getOdkudNak().setMaxZasoben(auto.getUdalost().get(0).getOdkudNak().getMaxZasoben()-1);
		auto.getUdalost().get(0).setVyklada(false);
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
			Udalost ud = new Udalost(auto.getUdalost().get(0).getMesto(), true, jidloTreb);
			Vrtulnik vrt = new Vrtulnik(ud);
			vrt.getUdalost().getIndex().add(q2);
			vrt.setNaklada(true);
			//System.out.println(jidloTreb +"  " + auto.getDobaNaklada());
			vrt.getUdalost().setDobaLetu(auto.getUdalost().get(0).getDobaLetu());
			Simulace.getVrtulniky().add(vrt);
			auto.getUdalost().get(0).getIndex().add(Simulace.getVrtulniky().size()-1);


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
	 * @param udalostAuto 
	 * @param d 
	 */
	private static void vyklAuto(Auto auto, Udalost udalostAuto, int d) {
		if(auto.getDobaVyklada()>udalostAuto.getDobaNakl()){
			auto.setDobaVyklada(udalostAuto.getDobaNakl());
			udalostAuto.setVyklada(false);
			udalostAuto.setDokonceno(true);
			if(d ==0 && auto.getUdalost().size()==2){
				auto.getUdalost().get(0).setSoucasUdal(false);
				auto.getUdalost().get(1).setSoucasUdal(true);
				auto.getUdalost().get(1).setJede(true);
				auto.setDobaJede(0);
				auto.setDobaVyklada(0);
			}
		}
		else{
			auto.setDobaVyklada(auto.getDobaVyklada() + 1.0);
		}

	}








	/**
	 * Jízda auta
	 * @param auto auto
	 * @param udalostAuto 
	 */
	private static void jedeAuto(Auto auto, Udalost udalostAuto) {
		if(auto.getDobaJede()>udalostAuto.getDobaJizd()){
			udalostAuto.setJede(false);
			udalostAuto.setVyklada(true);
			auto.setDobaJede(udalostAuto.getDobaJizd());
		}
		else{
			auto.setDobaJede(auto.getDobaJede() + 1.0);
		}

	}








	/**
	 * Rozhoduje o nakladani auta z letiste
	 * @param auto auto
	 */
	private void naklAuto(Auto auto, Udalost udal) {
		if(udal.isCeka()){
			zkontZasoby(auto, udal);
		}
		//System.out.println(auto.isCeka());
		if(!udal.isCeka()){

			pridejNaklad(auto, udal);		
		}

	}








	/**
	 * Naklada auto
	 * @param auto auto
	 * @param udal 
	 */
	private static void pridejNaklad(Auto auto, Udalost udal) {
		List<Jidlo> jid = udal.getOdkudNak().getJidlo();	
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
			double celkDobaNakl = 0;
			for(int o = 0; o < auto.getUdalost().size();o++){

				celkDobaNakl += auto.getUdalost().get(o).getDobaNakl();

			}

			if(auto.getDobaNaklada() > celkDobaNakl){
				udal.setNaklada(false);
				auto.setDobaNaklada(celkDobaNakl);
				udal.setJede(true);
			}
			else if((celkDobaNakl - auto.getDobaNaklada())<1.0){

				udal.getOdkudNak().getJidlo().get(0).setJidlo(celkDobaNakl - auto.getDobaNaklada());
				auto.setDobaNaklada(auto.getDobaNaklada()+1.0);
				//System.out.println("abcfg" + auto.getDobaNaklada());
			}else{
				//System.out.println("abc" + auto.getDobaNaklada());

				auto.setDobaNaklada(auto.getDobaNaklada()+1.0);
				udal.getOdkudNak().getJidlo().get(0).setJidlo(-1.0);
				//System.out.println("abcf" + auto.getDobaNaklada());
			}


		}
	}








	/**
	 * Zjistuje odkud se budou auta zasobovat
	 * @param auto auto
	 * @param udal 
	 */
	private void zkontZasoby(Auto auto, Udalost udal) {


		//System.out.println(vzd.get(i).getLet().getJidlo().size());
		//System.out.println( vzd.get(i).getLet());
		if(udal.isVrtulnik()){
			double minVzdSeZas=10000.0;
			int indexVzd = 0;
			int indexMesta = 0;
			double p = 0;


			for(int q = 0; q < udal.getMesto().getVzdLet().size();q++){
				Mesto pomMesto = udal.getMesto().getVzdLet().get(q).getMesto(); 




				for(int l = 0; l <pomMesto.getVzdLet().size();l++){
					boolean a = kontrolaZasobLet(pomMesto.getVzdLet().get(l).getLet());

					if(a){


						if(minVzdSeZas>
						(p = udal.getMesto().getVzdLet().get(q).vzdalenost
						+ pomMesto.getVzdLet().get(l).vzdalenost)){


							minVzdSeZas = p;
							indexVzd = l;
							indexMesta = q;

						}




					}

				}

			}

			Letiste let =udal.getMesto().getVzdLet().
					get(indexMesta).getMesto().getVzdLet().get(indexVzd).getLet();

			udal.setDobaLetu(udal.getMesto().getVzdLet().
					get(indexMesta).vzdalenost/this.urazVzdalVrt);

			udal.setDobaJizd(udal.getMesto().getVzdLet().
					get(indexMesta).getMesto().getVzdLet().get(indexVzd).vzdalenost/this.urazenaVzdal);

			udal.setOdkudNak(let);
			let.setMaxZasoben(let.getMaxZasoben()+1);
			udal.setCeka(false);
			udal.setNaklada(true);
		}
		else{
			List <Vzdalenost> vzd = udal.getMesto().getVzdLet();
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
						udal.setDobaJizd(vzd.get(i).vzdalenost/this.urazenaVzdal);
						udal.setOdkudNak(vzd.get(i).getLet());
						udal.setCeka(false);
						vzd.get(i).getLet().setMaxZasoben(vzd.get(i).getLet().getMaxZasoben() + 1);
						udal.setNaklada(true);
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
				if(Mapa.getIndexMest(pomMesto)==5){
					System.out.println("vytvoreno");
				}
				boolean prodluz = false;
				int zasTreba ; 

				if(pomMesto.isPrvniUd()){
					zasTreba = (int) (pomMesto.getJidlaTreba() - pomMesto.getNalJinAut());
					pomMesto.setPrvniUd(false);
				}else{
					zasTreba = (int) (pomMesto.getJidlaTreba()*2/3 -pomMesto.getNalJinAut());

				}

				double jidAuta =zasTreba/this.maxAuto;
				int pocetAut = (int) Math.ceil(jidAuta);
				//System.out.println(" aut" + pocetAut);
				//System.out.println(jidAuta);

				for(int q = 0; q < pocetAut; q++){
					double jidloTreb;
					double x;
					double dobaNakl;
					List<Udalost> pomoc = new ArrayList<>(); 

					if((x= zasTreba % this.maxAuto) == 0){
						jidloTreb= this.maxAuto;
						prodluz = false;

					}else{
						jidloTreb = x;
						zasTreba = (int) (zasTreba - x);
						prodluz = true;


					}

					dobaNakl = jidloTreb/this.nakladMinuta;
					//System.out.println(x);

					if(pomMesto.isMaCesty()){
						pomoc.add(new Udalost(pomMesto, false, dobaNakl));
						if (prodluz) {
							Udalost ud = najdinezaMesto(pomMesto, x);

							if(ud != null){
								ud.setCeka(false);
								ud.setSoucasUdal(false);
								ud.setDobaJizd(Generator.matice[5+Mapa.getIndexMest(pomMesto)][Mapa.getIndexMest(ud.getMesto())]/this.nakladMinuta);
								pomoc.add(ud);
							}
						}
					}
					else{
						pomoc.add(new Udalost(pomMesto, true, dobaNakl));
					}

					Auto auto = new Auto(pomoc);
					//auto.setCeka(true);
					Simulace.getAuta().add(auto);
				}

				//System.out.println(i + " " + this.auta.size() + " " + this.auta.get(i).getUdalost().getDobaNakl()*this.nakladMinuta);
				pomMesto.setHlad(false);
				pomMesto.setNalJinAut(0);

			}

		}



	}









	private Udalost najdinezaMesto(Mesto pomMesto, double x) {

		double p;
		for(int z = 0; z < pomMesto.getSousedi().size(); z++){
			Mesto docasMesto = pomMesto.getSousedi().get(z);
			if((p = docasMesto.getJidlaTreba()%this.maxAuto) !=0 && docasMesto.getHlad() && docasMesto.getNalJinAut() == 0){
				if(( x + p) < this.maxAuto){
					docasMesto.setNalJinAut(p);
					//System.out.println(docasMesto.getNalJinAut());
					Udalost ud = new Udalost(docasMesto, false, p/this.nakladMinuta);					
					return ud;
				}	
			}	
		}
		return null;
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


