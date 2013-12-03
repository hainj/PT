package pack;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Simulace {
	private final int CAS = 10080;
	private  double nakladMinuta = 1000.0/30.0;
	private  double urazenaVzdal = 40.0/60.0;
	private  double urazVzdalVrt = 5.0;
	private  double maxVyklad = 1000.0;
	private  double maxAuto = 12000.0;
	private  double maxVrt = 1000.0;
	private  ArrayList<Udalost> udalosti = new ArrayList<Udalost>();
	private int k = 1;
	private int z = 0;
	private int m = 0;
	//private int l = 0;



	public Simulace(JTextArea textBlok, ArrayList<Mesto> poleMest, ArrayList<Letiste> poleLetist, String cesta){
		/*for(int i = 0; i< poleMest.size();i++){
			System.out.println(poleMest.get(i).getVzdalenost());
		}*/
		k = 1;
		z = 0;
		m = 0;
		vytvorUdalosti(poleMest);
		//simulacni cyklus pocita cas
		for(int i = 0; i<=CAS;i++ ){
			System.out.println(poleMest.size() + "   " +udalosti.size());
			//System.out.println(i);
			//////////////////////////////////////******zacatek simulace******///////////////////////////////////
			if(i == 60*k){
				mestaHlad(poleMest);
				nakladejLetiste(i,poleLetist);
				vytvorUdalosti(poleMest);

				vypisLog.vypis(textBlok);
				k++;
			}

			if(i == z*30){
				nakladej();
				z++;


			}


			for(int j = 0; j < udalosti.size(); j++){

				if(udalosti.get(j).isVrtulnik()){
					for(int l = 0; l < udalosti.get(j).getVrtulniky().size(); l++){
						Vrtulnik vrt = udalosti.get(j).getVrtulniky().get(l);
						if(vrt.isNalozeno()){
							//System.out.println();
							if(vrt.getUjetaVzdalenost() >= udalosti.get(j).getMesto().getVzdalenost() ){
								vykladejVrtulnik(udalosti.get(j), vrt);
							}
							else{
								vrt.setUjetaVzdalenost(urazVzdalVrt);
								vrt.setStatus("Leti");
							}
						}
						else{
							for(int q = 0;q < udalosti.get(j).getAuta().size() ;q++){
								Auto pomAut = udalosti.get(j).getAuta().get(q);
								//System.out.println("ahoj");
								//System.out.println(pomAut.getUjetaVzdalenost()+ "  "+ udalosti.get(j).getMesto().getVzdalenost());
								if(pomAut.getNalozeno()){
									if((pomAut.getUjetaVzdalenost() <= udalosti.get(j).getOdkudVrtulnik().getVzdalenost())){
										//System.out.println(udalosti.get(j).getMesto().getVzdalenost());
										jed(udalosti.get(j));
									}
									else{
										prekladka(pomAut, udalosti.get(j));
										/*if(pomAut.getNaklad()==0){
											udalosti.get(j).getAuta().remove(q);
										}*/
									}
								}

							}
						}
					}


				}

				else{
					for(int q = 0;q < udalosti.get(j).getAuta().size() ;q++){
						Auto pomAut = udalosti.get(j).getAuta().get(q);
						//System.out.println("ahoj");
						//System.out.println(pomAut.getUjetaVzdalenost()+ "  "+ udalosti.get(j).getMesto().getVzdalenost());
						if(pomAut.getNalozeno()){
							if((pomAut.getUjetaVzdalenost() <= udalosti.get(j).getMesto().getVzdalenost())){
								//System.out.println(udalosti.get(j).getMesto().getVzdalenost());
								//System.out.println(pomAut.getUjetaVzdalenost());
								jed(udalosti.get(j));
							}
							else{
								//System.out.println("avc");
								//System.out.println(pomAut.getNalozeno());
								vykladej(udalosti.get(j));
								//System.out.println(i + "q");

							}
						}
					}
				}
			}

			if(i == m*1440){

				vypisLog.zapis(udalosti, cesta, m);
				m++;
			}


			smazUdalost(udalosti);


		}
	}









	private  void mestaHlad(ArrayList<Mesto> poleMest) {
		for(int i = 0; i < poleMest.size() ;i++){
			if(poleMest.get(i).getHlad()){
				//todo kontrala 3 dni od posledni dodavky
			}

		}

	}




	private void prekladka(Auto pomAut, Udalost udalost) {
		if(pomAut.getNaklad() <=0 ){
			return;
		}
		
		for(int i = 0; i<udalost.getVrtulniky().size();i++){
			Vrtulnik vrt = udalost.getVrtulniky().get(i);
			
			if((vrt.getNaklad() + nakladMinuta) >= vrt.getNakladTreba()){

				if(pomAut.getNaklad() >= (vrt.getNakladTreba() - vrt.getNaklad())){
					pomAut.setNaklad(-vrt.getNakladTreba() + vrt.getNaklad());
					vrt.setNaklad(vrt.getNakladTreba() - vrt.getNaklad());
					vrt.setStatus("Ceka");
					vrt.setNalozeno(true);
				}
				else{
					
					vrt.setNaklad(pomAut.getNaklad());
					
					pomAut.setNaklad(-pomAut.getNaklad());
					pomAut.setStatus("Preklada");
					vrt.setStatus("Naklllada");
				}
			}
			else{
				if(pomAut.getNaklad()<nakladMinuta){
					vrt.setNaklad(pomAut.getNaklad());
					pomAut.setNaklad(-pomAut.getNaklad());
					pomAut.setStatus("Preklada");
					vrt.setStatus("Nakllllllada");
				}
				else{
					vrt.setNaklad(nakladMinuta);
					pomAut.setNaklad(-nakladMinuta);
					pomAut.setStatus("Preklada");
					vrt.setStatus("Nakllllllllllllllada");
				}
			}
			if(Math.abs(vrt.getNakladTreba()-vrt.getNaklad())<0.1){
				
				
				vrt.setStatus("Ceka");
				vrt.setNalozeno(true);
			}
			//System.out.println(vrt.getNaklad() + "  " + udalost.getMesto().getJidlaTreba());

		}


	}



	private  void smazUdalost(ArrayList<Udalost> udalosti2) {

		/*for(int i = 0; i < udalosti2.size();i++){



			ArrayList<Boolean> dokon = new ArrayList<Boolean>(); 
			for(int q = 0; q<udalosti2.get(i).getAuta().size();q++){
				Auto aut = udalosti2.get(i).getAuta().get(q);
				if(aut.getStatus().compareTo("Dokonceno")==0){
					dokon.add(true);

				}else{
					break;
				}


			}
			if(dokon.size()==udalosti2.get(i).getAuta().size()){

				udalosti2.remove(i);	
				i--;
			}

		}*/

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
	private  void vykladejVrtulnik(Udalost ud, Vrtulnik vrt){


		if(vrt.getNaklad()<nakladMinuta){
			ud.getMesto().setJidlo(vrt.getNaklad());
			vrt.setNaklad(-vrt.getNaklad());
			vrt.setStatus("Vyklada");
		}
		else{
			ud.getMesto().setJidlo(nakladMinuta);
			vrt.setNaklad(-nakladMinuta);
			vrt.setStatus("Vyklada");
		}
		if(Math.abs(vrt.getNakladTreba() - vrt.getNaklad())<0.1){
			vrt.setStatus("Dokonceno");
		}


	}
	private  void vykladej(Udalost ud){
		//System.out.println(ud.getAuta().get(0).getNaklad());
		for(int i = 0; i < ud.getAuta().size(); i++){

			Auto pomAut = ud.getAuta().get(i);
			if(Math.abs(pomAut.getPotrebaNalozit() - pomAut.getNaklad()) < 0.1){
				pomAut.setStatus("Dokonceno");
			}else{
				if(ud.getMesto().getJidlo() + nakladMinuta > ud.getMesto().getJidlaTreba()){
					pomAut.setNaklad(ud.getMesto().getJidlo()-ud.getMesto().getJidlaTreba());
					ud.getMesto().setJidlo(ud.getMesto().getJidlaTreba()-ud.getMesto().getJidlo());
					
				}
				else{
					if(pomAut.getNaklad()<nakladMinuta){
						ud.getMesto().setJidlo(pomAut.getNaklad());
						pomAut.setNaklad(pomAut.getNaklad()*-1.0);
						pomAut.setStatus("Vyklada");

					}
					else{

						pomAut.setNaklad(nakladMinuta*-1.0);
						pomAut.setStatus("Vyklada");
						ud.getMesto().setJidlo(nakladMinuta);
					}
					//System.out.println(ud.getMesto().getJidlo());


				}

			}
			if(pomAut.getNaklad() <=0){

				pomAut.setStatus("Dokonceno");
			}
		}

		//System.out.println(ud.getAuta().get(0).getNaklad());
	}


	private  void nakladej() {
		for(int i = 0; i <udalosti.size();i++){
			//System.out.println(i + "pokracuje");
			Udalost ud = udalosti.get(i);

			for(int j = 0; j <ud.getAuta().size();j++){
				Auto auto = ud.getAuta().get(j);
				ArrayList<Jidlo> pomJidlo = ud.getMesto().getOdkud().getJidlo();

				if(ud.getMesto().getOdkud().getJidlo().size()>0){

					if(pomJidlo.get(0).getJidlo()==0){

						pomJidlo.remove(0);
					}

					else if(auto.getNalozeno()){
						auto.setStatus("Ceka");
					}
					
					else{
						if(Math.abs(auto.getPotrebaNalozit() - auto.getNaklad())<0.1){
							//System.out.println("ahoj");
							auto.setNalozeno(true);

						}
						else{
							ud.getAuta().get(j).setStatus("Naklada");
							//ud.getAuta().get(j).getNaklad() + 
							double z;
							if((z = auto.getPotrebaNalozit() - auto.getNaklad())>maxVyklad){
								auto.setNaklad(maxVyklad);
								pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - maxVyklad);
							}else{
								auto.setNaklad(z);
								pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - z);
							}
							//System.out.println(ud.getAuta().get(j).getNaklad());

							//System.out.println(pomJidlo.get(0).getJidlo());
						}

					}


				}
				else{
					//System.out.println(i + "pomoc");
					break;
				}
			}
		}

	}

	private  void jed(Udalost ud){

		for(int i = 0; i < ud.getAuta().size(); i++){
			Auto pomAut = ud.getAuta().get(i);
			pomAut.setUjetaVzdalenost(pomAut.getUjetaVzdalenost()+urazenaVzdal);
			pomAut.setStatus("Jede");
			//System.out.println(pomAut.getUjetaVzdalenost());

		}

	}


	private  void vytvorUdalosti(ArrayList<Mesto> poleMest) {
		for(int i = 0; i<poleMest.size();i++){

			Mesto pomMest = poleMest.get(i);
			ArrayList<Auto>aut = new ArrayList<Auto>();	
			ArrayList<Vrtulnik>vrtulniky = new ArrayList<Vrtulnik>();	
			double zasobTreba =pomMest.getJidlaTreba();

			if(pomMest.getHlad()){

				if(pomMest.isMaCesty()){
					double jidAuta = pomMest.getJidlaTreba()/maxAuto;
					int pocetAut = (int) Math.ceil(jidAuta);
					//System.out.println(" aut" + pocetAut);
					//System.out.println(jidAuta);
					for(int q = 0; q < pocetAut; q++){
						double jidloTreb;
						double x;

						if((x= zasobTreba % maxAuto) == 0){
							jidloTreb= maxAuto;
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
					udalosti.add(new Udalost(pomMest,aut, false, false));


				}
				else{
					double jidAuta = pomMest.getJidlaTreba()/maxAuto;
					//System.out.println(jidAuta);
					int pocetAut = (int) Math.ceil(jidAuta);
					//System.out.println(pocetAut);
					//System.out.println(jidAuta);
					for(int j = 0; j < pocetAut; j++){
						double jidloTreb;
						double x;

						if((x= zasobTreba % maxAuto) == 0){
							jidloTreb = maxAuto;
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
					zasobTreba =pomMest.getJidlaTreba();
					int pocetVrtulniku = (int)Math.ceil(pomMest.getJidlaTreba()/maxVrt);
					//System.out.println(pocetVrtulniku+ "vrt");

					for(int q = 0; q < pocetVrtulniku; q++){
						double jidloTreb;
						double x;
						if((x= zasobTreba % maxVrt) == 0){
							jidloTreb = maxVrt;
						}else{
							jidloTreb = x;
							zasobTreba = zasobTreba - x;
						}

						vrtulniky.add(new Vrtulnik("Ceka", jidloTreb));
					}

					udalosti.add(new Udalost(pomMest, pomMest.getPredchudce(), aut,vrtulniky, true, false));				
				}

				pomMest.setHlad(false);
			}


		}
	}
}


/*
 * 
 * 			if(i == 800){
				for(int j=0; j<udalosti.get(50).getAuta().size();j++){
					System.out.println(udalosti.get(50).getAuta().get(j).getNaklad());
					System.out.println(udalosti.get(50).getAuta().get(j).getStatus());
					MainAPP.textBlok.append(udalosti.get(50).getAuta().get(j).getStatus()+"\n");
				}

			}
 * 
 * 
 */
