package pack;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Simulace {
	private final int CAS = 10080;
	private double URAZENAVZDAL = 40.0/60.0;
	private ArrayList<Udalost> udalosti = new ArrayList<Udalost>();
	private int k = 1;
	private int z = 0;
	private int m = 0;
	//private int l = 0;



	public Simulace(JTextArea textBlok, ArrayList<Mesto> poleMest, ArrayList<Letiste> poleLetist){
		/*for(int i = 0; i< poleMest.size();i++){
			System.out.println(poleMest.get(i).getVzdalenost());
		}*/
		
		vytvorUdalosti(poleMest);
		//simulacni cyklus pocita cas
		for(int i = 0; i<=CAS;i++ ){
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

				if(udalosti.get(j).isVrtulník()){
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
								jed(udalosti.get(j));
							}
							else{
								//System.out.println("avc");
								//System.out.println(pomAut.getNalozeno());
								vykladej(udalosti.get(j));


							}
						}
					}
				}
			}

			if(i == m*1440){
				for(int j = 0; j < poleMest.size(); j++){
					Mesto pomMes = poleMest.get(j);
					pomMes.setJidlo((-1)*(pomMes.getObyvatel()*6));
					
				}
				vypisLog.zapis(udalosti);
			}
			
			
			smazUdalost(udalosti);
			
			
		}
	}




	private void mestaHlad(ArrayList<Mesto> poleMest) {
		for(int i = 0; i < poleMest.size() ;i++){
			if(poleMest.get(i).getHlad()){
				//todo kontrala 3 dni od posledni dodavky
			}
			
		}
		
	}




	private void prekladka(Auto pomAut, Udalost udalost) {


	}



	private void smazUdalost(ArrayList<Udalost> udalosti2) {

		for(int i = 0; i < udalosti2.size();i++){



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

		}

	}



	private void nakladejLetiste(int minuta, ArrayList<Letiste> poleLetist) {
		for(int i = 0; i< poleLetist.size();i++){

			Letiste pomLet = poleLetist.get(i);


			for(int j = 0; j < pomLet.getJidlo().size();j++){
				if(pomLet.getJidlo().get(j).getDodano() == minuta - 4320){
					pomLet.getJidlo().remove(j);
				}


			}


			pomLet.addJidlo(500000, minuta);
			//System.out.println(poleLetist.get(0).getJidlo().get(1).getJidlo());
		}




	}

	private void vykladej(Udalost ud){
		//System.out.println(ud.getAuta().get(0).getNaklad());
		for(int i = 0; i < ud.getAuta().size(); i++){

			Auto pomAut = ud.getAuta().get(i);

			if(pomAut.getNaklad()<1000){
				ud.getMesto().setJidlo(pomAut.getNaklad());
				pomAut.setNaklad(-pomAut.getNaklad());
				pomAut.setStatus("Vyklada");

			}
			else{

				pomAut.setNaklad(-1000);
				pomAut.setStatus("Vyklada");
				ud.getMesto().setJidlo(1000);
			}
			//System.out.println(ud.getMesto().getJidlo());

			if(pomAut.getNaklad() ==0){
				pomAut.setStatus("Dokonceno");
			}
		}


		//System.out.println(ud.getAuta().get(0).getNaklad());
	}


	private void nakladej() {
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
					//ud.getAuta().get(j).getStatus().compareToIgnoreCase("Ceka")==0 ||
					//ud.getAuta().get(j).getStatus().compareToIgnoreCase("Naklada")==0
					else{
						if(auto.getNaklad() == auto.getPotrebaNalozit()){
							auto.setNalozeno(true);

						}
						else{
							ud.getAuta().get(j).setStatus("Naklada");
							//ud.getAuta().get(j).getNaklad() + 
							int z;
							if((z = auto.getPotrebaNalozit() - auto.getNaklad())>1000){
								auto.setNaklad(1000);
								pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - 1000);
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

	private void jed(Udalost ud){

		for(int i = 0; i < ud.getAuta().size(); i++){
			Auto pomAut = ud.getAuta().get(i);
			pomAut.setUjetaVzdalenost(pomAut.getUjetaVzdalenost()+URAZENAVZDAL);
			pomAut.setStatus("Jede");
			//System.out.println(pomAut.getUjetaVzdalenost());

		}

	}


	private void vytvorUdalosti(ArrayList<Mesto> poleMest) {
		for(int i = 0; i<poleMest.size();i++){

			Mesto pomMest = poleMest.get(i);
			ArrayList<Auto>aut = new ArrayList<Auto>();	
			ArrayList<Vrtulnik>vrtulniky = new ArrayList<Vrtulnik>();	
			double zasobTreba =pomMest.getJidlaTreba();

			if(pomMest.getHlad()){

				if(pomMest.isMaCesty()){
					double jidAuta = pomMest.getJidlaTreba()/12000.0;
					int pocetAut = (int) Math.ceil(jidAuta);
					//System.out.println(pocetAut);
					//System.out.println(jidAuta);
					for(int q = 0; q < pocetAut; q++){
						double jidloTreb;
						double x;

						if((x= zasobTreba % 12000.0) == 0){
							jidloTreb= 12000.0;
						}else{
							jidloTreb = x;
							zasobTreba = zasobTreba - x;
						}

						//System.out.println(jidloTreb);
						jidloTreb = Math.ceil(jidloTreb);
						//System.out.println(jidloTreb);
						aut.add(new Auto("Ceka",(int)jidloTreb));
						//System.out.println(aut.get(q).getPotrebaNalozit());
					}
					udalosti.add(new Udalost(pomMest,aut, false, false));


				}
				else{
					double jidAuta = pomMest.getJidlaTreba()/12000.0;
					//System.out.println(jidAuta);
					int pocetAut = (int) Math.ceil(jidAuta);
					//System.out.println(pocetAut);
					//System.out.println(jidAuta);
					for(int j = 0; j < pocetAut; j++){
						double jidloTreb;
						double x;

						if((x= zasobTreba % 12000.0) == 0){
							jidloTreb = 12000.0;
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
						aut.add(new Auto("Ceka",(int)jidloTreb));
						//System.out.println(aut.get(j).getPotrebaNalozit());
					}

					double pocetVrtulniku = Math.ceil(pomMest.getJidlaTreba()/2000);

					for(int q = 0; q < pocetVrtulniku; q++){
						vrtulniky.add(new Vrtulnik("Ceka"));
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
