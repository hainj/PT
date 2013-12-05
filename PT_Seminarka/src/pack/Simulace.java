package pack;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Simulace implements Runnable {
	private final int CAS = 10080;
	private final double nakladMinuta = 1000.0/30.0;
	private final double urazenaVzdal = 40.0/60.0;
	private final double urazVzdalVrt = 150.0/60.0;
	private final double maxAuto = 12000.0;
	private final double maxVrt = 2000.0;

	private final ArrayList<Udalost> udalosti = new ArrayList<>();
	//private final ArrayList<Udalost> dokonUdalosti = new ArrayList<>();

	//private int l = 0;
	private JTextArea textBlok;

	private ArrayList<Mesto> poleMest;
	private ArrayList<Letiste> poleLetist;
	private String cesta;
	private int index;

	/**
	 * @param textblok
	 * @param poleMest
	 * @param poleLetist
	 * @param cesta
	 */
	public Simulace(JTextArea textBlok, ArrayList<Mesto> poleMest,
			ArrayList<Letiste> poleLetist, String cesta, int index) {
		super();
		this.textBlok = textBlok;
		this.poleMest = poleMest;
		this.poleLetist = poleLetist;
		this.cesta = cesta;
		this.index = index;
	}




	private void odectiZasoby(Mesto mesto, int minuta) {
		int z = 0;
		if((minuta-mesto.getZasobKdy())%1440==0){
			z = (minuta-mesto.getZasobKdy())/1440;
			//System.out.println(z);
		}
		/*if(mesto.getZasobKdy()>0){
		double  z = 
		System.out.println(mesto.getZasobKdy());}*/
		if(mesto.getZasobKdy()>0){
			if(z>0){
				if(mesto.getZasobKdy() + 1440*z == minuta){
					mesto.setJidlo(-mesto.getObyvatel()*2.0);
					if(mesto.getZasDny() == 1){
						mesto.setHlad(true);
					}
					if(mesto.getZasDny() ==2 && z == 2){
						mesto.setHlad(true);
						mesto.setZasDny(1);
					}

				}

				/*if(mesto.getZasobKdy() + 2880 == minuta){
				mesto.setJidlo(-mesto.getObyvatel()*2);
				if(mesto.getZasDny() ==2){
					mesto.setHlad(true);
					mesto.setZasDny(1);
				}

				//System.out.println(mesto.getHlad());

			}*/
			}
		}
	}

	private void prekladka(Auto pomAut, Udalost udalost) {
		if(pomAut.getNaklad() <0.01 ){
			pomAut.setStatus("Dokonceno");
			return;
		}

		for(int i = 0; i<udalost.getVrtulniky().size();i++){
			Vrtulnik vrt = udalost.getVrtulniky().get(i);
			//System.out.println(pomAut.getNaklad());
			//System.out.println(vrt.getNaklad());
			if(!vrt.isNalozeno()){
				if((vrt.getNaklad() + this.nakladMinuta - vrt.getNakladTreba()) > 0){
					
					/*if(this.nakladMinuta <= pomAut.getNaklad()){

						vrt.setNaklad(pomAut.getNaklad());
						pomAut.setNaklad(-pomAut.getNaklad());
						//System.out.println("A");
						pomAut.setStatus("Preklada");
						vrt.setStatus("Naklada");
					}
					else */if(pomAut.getNaklad()>=(vrt.getNakladTreba() - vrt.getNaklad())) {
						pomAut.setNaklad(vrt.getNaklad() - vrt.getNakladTreba());
						vrt.setNaklad(vrt.getNakladTreba() - vrt.getNaklad());
						vrt.setStatus("Ceka");
						vrt.setNalozeno(true);
						//System.out.println("B");
					}

				}
				else{
					if(pomAut.getNaklad()<this.nakladMinuta){
						vrt.setNaklad(pomAut.getNaklad());
						pomAut.setNaklad(-pomAut.getNaklad());
						pomAut.setStatus("Preklada");
						vrt.setStatus("Naklada");
						//System.out.println("C");
					}
					else{
						vrt.setNaklad(this.nakladMinuta);
						pomAut.setNaklad(-this.nakladMinuta);
						pomAut.setStatus("Preklada");
						vrt.setStatus("Naklada");
					}
				}
			}
			if(Math.abs(vrt.getNakladTreba()-vrt.getNaklad())<0.001){


				vrt.setStatus("Ceka");
				vrt.setNalozeno(true);
			}
			if(pomAut.getNaklad() <0.01 ){
				pomAut.setStatus("Dokonceno");
			}
		}

	}



	private static  boolean smazUdalost(Udalost udalost) {

		boolean dokon = true;
		for(int j = 0; j < udalost.getAuta().size();j++){
			if(udalost.getAuta().get(j).getStatus().compareTo("Dokonceno")!=0 && udalost.getAuta().get(j).getStatus().compareTo("Preklada")!=0){
				dokon =false;
			}
		}
		if(udalost.isVrtulnik()){
			for(int j = 0; j < udalost.getVrtulniky().size();j++){
				if(udalost.getVrtulniky().get(j).getStatus().compareTo("Dokonceno")!=0){
					dokon =false;
				}
			}
		}
		//System.out.println(dokon);
		return dokon;



	}



	private  void nakladejLetiste(int minuta, ArrayList<Letiste> poleLetist) {
		for(int i = 0; i< poleLetist.size();i++){

			Letiste pomLet = poleLetist.get(i);


			for(int j = 0; j < pomLet.getJidlo().size();j++){
				if(pomLet.getJidlo().get(j).getDodano() == minuta - 4320){
					pomLet.getJidlo().remove(j);
				}


			}


			pomLet.addJidlo(500000.0, minuta);
			//System.out.println(poleLetist.get(0).getJidlo().get(1).getJidlo());
		}




	}
	private  void vykladejVrtulnik(Udalost ud, Vrtulnik vrt,int minuta){

		//System.out.println(ud.getMesto().getJidlo() + "   " + vrt.getNaklad());
		if(ud.getMesto().getZasobKdy() < 0 && ud.getMesto().zaokr() == ud.getMesto().getJidlaTreba()){
			ud.getMesto().setZasKdy(minuta);
			//System.out.println("vrt" + minuta);
		}

		if(vrt.getNaklad()<this.nakladMinuta){
			ud.getMesto().setJidlo(vrt.getNaklad());
			vrt.setNaklad(vrt.getNaklad()*(-1.0));
			vrt.setStatus("Vyklada");
		}
		else{
			ud.getMesto().setJidlo(this.nakladMinuta);
			vrt.setNaklad(- this.nakladMinuta);
			vrt.setStatus("Vyklada");
		}
		if(Math.abs(vrt.getNaklad())<0.001){
			vrt.setStatus("Dokonceno");
		}
		//System.out.println(ud.getMesto().getJidlo() + "   " + vrt.getNaklad());

	}
	private  void vykladej(Udalost ud, Auto pomAut, int minuta){
		//System.out.println(ud.getAuta().get(0).getNaklad());
		if(ud.getMesto().getZasobKdy() < 0 && ud.getMesto().zaokr() ==ud.getMesto().getJidlaTreba()){
			ud.getMesto().setZasKdy(minuta);
			//System.out.println(minuta);
		}

		if(pomAut.getNaklad()<this.nakladMinuta){
			ud.getMesto().setJidlo(pomAut.getNaklad());
			pomAut.setNaklad(-pomAut.getNaklad());
			pomAut.setStatus("Vyklada");

		}
		else{
			ud.getMesto().setJidlo(this.nakladMinuta);
			pomAut.setNaklad(-this.nakladMinuta);
			pomAut.setStatus("Vyklada");

		}
		if(Math.abs(pomAut.getNaklad()) <0.001){

			pomAut.setStatus("Dokonceno");
		}
		//System.out.println(ud.getMesto().getJidlo());


	}

	//}



	//System.out.println(ud.getAuta().get(0).getNaklad());





	private  void jed(Auto pomAut){


		pomAut.setUjetaVzdalenost(pomAut.getUjetaVzdalenost()+this.urazenaVzdal);
		pomAut.setStatus("Jede");
		//System.out.println(pomAut.getUjetaVzdalenost());

	}




	private  void vytvorUdalosti(ArrayList<Mesto> poleMest) {

		for(int i = 0; i < poleMest.size() ;i++){

			Mesto pomMest = poleMest.get(i);
			ArrayList<Auto>aut = new ArrayList<>();	
			ArrayList<Vrtulnik>vrtulniky = new ArrayList<>();	
			double zasobTreba =pomMest.getJidlaTreba() - pomMest.getJidlo();


			if(pomMest.getHlad()){

				if(pomMest.isMaCesty()){
					double jidAuta =zasobTreba/this.maxAuto;
					int pocetAut = (int) Math.ceil(jidAuta);
					//System.out.println(" aut" + pocetAut);
					//System.out.println(jidAuta);
					for(int q = 0; q < pocetAut; q++){
						double jidloTreb;
						double x;

						if((x= zasobTreba % this.maxAuto) == 0){
							jidloTreb= this.maxAuto;
						}else{
							jidloTreb = x;
							zasobTreba = zasobTreba - x;
						}

						//System.out.println(jidloTreb);
						jidloTreb = Math.ceil(jidloTreb);
						//System.out.println(jidloTreb);
						aut.add(new Auto("Ceka",jidloTreb));
						//System.out.println(aut.get(q).getPotrebaNalozit());
					}
					this.udalosti.add(new Udalost(pomMest,aut, false));


				}
				else{
					zasobTreba =pomMest.getJidlaTreba() - pomMest.getJidlo();
					double jidAuta = zasobTreba/this.maxAuto;
					//System.out.println(jidAuta);
					int pocetAut = (int) Math.ceil(jidAuta);
					//System.out.println(pocetAut);
					//System.out.println(jidAuta);
					for(int j = 0; j < pocetAut; j++){
						double jidloTreb;
						double x;

						if((x= zasobTreba % this.maxAuto) == 0){
							jidloTreb = this.maxAuto;
							//System.out.println(jidloTreb);
							//System.out.println(jidloTreb);
						}else{
							jidloTreb = x;
							//System.out.println(zasobTreba);
							zasobTreba = zasobTreba - x;
							//System.out.println(jidloTreb);
						}


						jidloTreb = Math.ceil(jidloTreb);
						//System.out.println(jidloTreb);
						aut.add(new Auto("Ceka",jidloTreb));
						//System.out.println(aut.get(j).getPotrebaNalozit());
					}
					zasobTreba = pomMest.getJidlaTreba() - pomMest.getJidlo();
					int pocetVrtulniku = (int)Math.ceil(zasobTreba/this.maxVrt);
					//System.out.println(pocetVrtulniku+ "vrt");

					for(int q = 0; q < pocetVrtulniku; q++){
						double jidloTreb;
						double x;
						if((x= zasobTreba % this.maxVrt) == 0){
							jidloTreb = this.maxVrt;
						}else{
							jidloTreb = x;
							zasobTreba = zasobTreba - x;
						}

						vrtulniky.add(new Vrtulnik("Ceka", jidloTreb));
					}

					this.udalosti.add(new Udalost(pomMest, pomMest.getPredchudce(), aut,vrtulniky, true));				
				}

				pomMest.setHlad(false);
			}


		}

	}



	private void pauza(){
		try{

			while(MainAPP.isPause()){
				Thread.sleep(1);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void run() {

		int k = 1;

		int m = 0;
		//vytvorUdalosti(this.poleMest);

		for(int i = 0; i <= this.CAS; i++ ){
			//System.out.println(i);
			pauza();
			vytvorUdalosti(this.poleMest);
			if(i == 60*k){

				nakladejLetiste(i,this.poleLetist);
				
				VypisLog.vypis(this.textBlok, this.poleMest.get(this.index),this.udalosti);
				k++;
			}
			/*
			if(i == z*30){
				//nakladej();
				z++;
			}*/

			for(int j = 0; j < this.udalosti.size(); j++){
				//System.out.println(i);
				//System.out.println(udalosti.size());
				prubehUdalosti(this.udalosti.get(j), i);
			}

			if(i == m*1440){
				//System.out.println(udalosti.size());
				VypisLog.zapis(this.udalosti, this.cesta, m);

				for(int q = 0; q < this.udalosti.size(); q++){
					//odectiZasoby(this.poleMest.get(Mapa.getIndexMest(this.udalosti.get(q).getMesto())), i);
					boolean smaz = smazUdalost(this.udalosti.get(q));
					if(smaz){
						int e = Mapa.getIndexMest(this.udalosti.get(q).getMesto());
						this.poleMest.get(e).addAut(this.udalosti.get(q).getAuta());
						this.poleMest.get(e).addVrt(this.udalosti.get(q).getVrtulniky());
						this.udalosti.remove(q);
						//System.out.println(this.dokonUdalosti.size());
						q--;

					}
				}
				m++;
				//System.out.println(i + "   " + this.dokonUdalosti.size());
			}
			for(int q = 0; q < this.poleMest.size(); q++){
				odectiZasoby(this.poleMest.get(q), i);
			}



			/*
			 */
		}

	}




	private void prubehUdalosti(Udalost udalost, int i) {
		//i = 5;
		if(udalost.isVrtulnik()){
			for(int l = 0; l < udalost.getVrtulniky().size(); l++){
				Vrtulnik vrt = udalost.getVrtulniky().get(l);
				if(vrt.isNalozeno()){

					if(vrt.getUjetaVzdalenost() >= udalost.getMesto().getVzdalenost() ){

						vykladejVrtulnik(udalost, vrt, i);
					}
					else{
						vrt.setUjetaVzdalenost(this.urazVzdalVrt);
						vrt.setStatus("Leti");
					}
				}
				else{
					for(int q = 0;q < udalost.getAuta().size() ;q++){
						Auto pomAut = udalost.getAuta().get(q);

						if(pomAut.getNalozeno()){
							if((pomAut.getUjetaVzdalenost() <= udalost.getOdkudVrtulnik().getVzdalenost())){

								jed(pomAut);
							}
							else{

								prekladka(pomAut, udalost);

							}
						}else{
							nakladej(pomAut, udalost);
						}

					}
				}
			}


		}

		else{
			for(int q = 0;q < udalost.getAuta().size() ;q++){
				Auto pomAut = udalost.getAuta().get(q);

				if(pomAut.getNalozeno()){
					if((pomAut.getUjetaVzdalenost() <= udalost.getMesto().getVzdalenost())){


						jed(pomAut);
					}
					else{

						vykladej(udalost, pomAut, i);
					}
				}else{
					nakladej(pomAut, udalost);
				}
			}

		}

	}




	private void nakladej(Auto pomAut, Udalost udalost) {

		if(udalost.getMesto().getOdkud().getJidlo().size()>0){

			if(udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo()<=0){

				udalost.getMesto().getOdkud().getJidlo().remove(0);
			}



			if(!pomAut.getNalozeno() && udalost.getMesto().getOdkud().getJidlo().size() > 0 ){
				//if(1166.0 +this.nakladMinuta<1200.0)System.out.println("aaaaaaaaa");;
				if(Math.abs(pomAut.getPotrebaNalozit() - pomAut.getNaklad())<1){
					//System.out.println(auto.getNaklad());
					pomAut.setNalozeno(true);
					pomAut.setStatus("Nalozeno");

				}
				else{

					/*//ud.getAuta().get(j).getNaklad() + 

					if(this.nakladMinuta > udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo()){

						if((this.nakladMinuta + pomAut.getNaklad()) < pomAut.getPotrebaNalozit()){
							pomAut.setNaklad(this.nakladMinuta);
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-this.nakladMinuta);
						}
						else{

							pomAut.setNaklad(pomAut.getPotrebaNalozit()-pomAut.getNaklad());
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-pomAut.getPotrebaNalozit()+pomAut.getNaklad());

						}
					}
					else{
						if((this.nakladMinuta + pomAut.getNaklad() )< pomAut.getPotrebaNalozit()){
							double malo = udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo();
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-malo);
							boolean a = zkontJidlo(udalost.getMesto().getOdkud().getJidlo());
							if(a){
								pomAut.setNaklad(this.nakladMinuta);
								udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-this.nakladMinuta + malo);
							}else{
								pomAut.setNaklad(malo);
							}
						}
						else{
							double malo = udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo();
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-malo);
							boolean a = zkontJidlo(udalost.getMesto().getOdkud().getJidlo());
							if(a){
							pomAut.setNaklad(pomAut.getPotrebaNalozit()-pomAut.getNaklad());
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-pomAut.getPotrebaNalozit()+pomAut.getNaklad());
							}
							else{
								pomAut.setNaklad(malo);
							}

						}
					}*/
					double pres;
					if(this.nakladMinuta <= udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo() &&
							(pomAut.getNaklad() +this.nakladMinuta)<= pomAut.getPotrebaNalozit()){	
						pomAut.setStatus("Naklada");
						pomAut.setNaklad(this.nakladMinuta);
						udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-this.nakladMinuta);

					}

					else if((pres = pomAut.getPotrebaNalozit() - pomAut.getNaklad()) <= udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo() &&
							(pomAut.getNaklad() +this.nakladMinuta) > pomAut.getPotrebaNalozit()){
						pomAut.setStatus("Naklllada");
						double nas = pres / this.nakladMinuta;
						pomAut.setNaklad(this.nakladMinuta*nas);
						udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-this.nakladMinuta*nas);
					}
					else if((pomAut.getNaklad() +this.nakladMinuta)> pomAut.getPotrebaNalozit()&&
							(pres = pomAut.getPotrebaNalozit() - pomAut.getNaklad()) >= udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo()){
						double zbytek = udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo();
						boolean jid = zkontJidlo(udalost.getMesto().getOdkud().getJidlo());
						if(jid){
							pomAut.setNaklad(zbytek + (pres-zbytek));
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-pres+zbytek);
						}
						else{
							pomAut.setNaklad(udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo());
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo
							(-udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo());

						}
					}

					else if(this.nakladMinuta > udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo() &&
							(pomAut.getNaklad() +this.nakladMinuta)< pomAut.getPotrebaNalozit()){
						double zbytek = udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo();
						boolean jid = zkontJidlo(udalost.getMesto().getOdkud().getJidlo());
						if(jid){
							pomAut.setNaklad(zbytek + (this.nakladMinuta-zbytek));
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo(-this.nakladMinuta+zbytek);
						}
						else{
							pomAut.setNaklad(udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo());
							udalost.getMesto().getOdkud().getJidlo().get(0).setJidlo
							(-udalost.getMesto().getOdkud().getJidlo().get(0).getJidlo());
						}
					}



					if(Math.abs(pomAut.getPotrebaNalozit() - pomAut.getNaklad())<0.1){
						//System.out.println(auto.getNaklad());
						pomAut.setNalozeno(true);
						pomAut.setStatus("Nalozeno");

					}

				}


			}

		}
		/*private  void nakladej() {
	for(int i = 0; i <this.udalosti.size();i++){
		//System.out.println(i + "pokracuje");
		Udalost ud = this.udalosti.get(i);

		for(int j = 0; j <ud.getAuta().size();j++){

			Auto auto = ud.getAuta().get(j);
			ArrayList<Jidlo> pomJidlo = ud.getMesto().getOdkud().getJidlo();

			if(pomJidlo.size()>0){

				if(pomJidlo.get(0).getJidlo()<=0){

					pomJidlo.remove(0);
				}



				if(!auto.getNalozeno() && ud.getMesto().getOdkud().getJidlo().size() > 0 ){

					if(Math.abs(auto.getPotrebaNalozit() - auto.getNaklad())<0.1){
						//System.out.println(auto.getNaklad());
						auto.setNalozeno(true);
						auto.setStatus("Nalozeno");

					}
					else{
						ud.getAuta().get(j).setStatus("Naklada");
						//ud.getAuta().get(j).getNaklad() + 
						double z;
						if((z = auto.getPotrebaNalozit() - auto.getNaklad())>=this.maxVyklad &&
								this.maxVyklad <= pomJidlo.get(0).getJidlo()){

							auto.setNaklad(this.maxVyklad);
							pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - this.maxVyklad);

						}else if(z<=pomJidlo.get(0).getJidlo()){
							auto.setNaklad(z);
							pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - z);

						}
						else{
							auto.setNaklad(pomJidlo.get(0).getJidlo());
							pomJidlo.remove(0);
						}
						//System.out.println(ud.getAuta().get(j).getNaklad());

						//System.out.println(pomJidlo.get(0).getJidlo());
					}
					if(Math.abs(auto.getPotrebaNalozit() - auto.getNaklad())<0.1){
						//System.out.println(auto.getNaklad());
						auto.setNalozeno(true);
						auto.setStatus("Nalozeno");

					}

				}


			}
			else{
				//System.out.println(i + "pomoc");
				break;
			}
		}
	}

}*/
	}


	public boolean zkontJidlo(ArrayList<Jidlo> jidlo){

		if(jidlo.get(0).getJidlo()<=0){
			jidlo.remove(0);
			if(jidlo.size()>0){
				return true;
			}
		}
		return false;
	}
}


