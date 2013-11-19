package pack;

import java.util.ArrayList;

public class Simulace {
	private final int CAS = 10080;
	private final double URAZENAVZDAL = 40/60;
	private ArrayList<Udalost> udalosti = new ArrayList<Udalost>();
	private int k = 1;
	private int z = 0;



	public Simulace(){

		vytvorUdalosti();
		//simulacni cyklus pocita cas
		for(int i = 0; i<=CAS;i++ ){
			//////////////////////////////////////******zacatek simulace******///////////////////////////////////
			if(i == 60*k){
				nakladejLetiste(i);
				vytvorUdalosti();
			}

			if(i == z*30){
				nakladej();
			}

			//////////////////////////////////////******konec simulace******///////////////////////////////////
		}
	}


	private void nakladejLetiste(int minuta) {
		for(int i = 0; i< Mapa.getPoleLetist().size();i++){

			Letiste pomLet = Mapa.getPoleLetist().get(i);


			for(int j = 0; j < pomLet.getJidlo().size();j++){
				if(pomLet.getJidlo().get(j).getDodano() == minuta - 4320){
					pomLet.getJidlo().remove(j);
				}


			}


			pomLet.addJidlo(500000, minuta);
		}


		k++;

	}

	private void nakladej() {
		for(int i = 0; i <udalosti.size();i++){
			Udalost ud = udalosti.get(i);
			for(int j = 0; j <ud.getAuta().size();j++){

				ArrayList<Jidlo> pomJidlo = ud.getMesto().getOdkud().getJidlo();

				if(pomJidlo.get(0).getJidlo()==0){
					pomJidlo.remove(0);
				}

				else if(ud.getAuta().get(j).getStatus().compareToIgnoreCase("Ceka")==0 ||
						ud.getAuta().get(j).getStatus().compareToIgnoreCase("Naklada")==0){
					ud.getAuta().get(j).setStatus("Naklada");
					ud.getAuta().get(j).setNaklad(ud.getAuta().get(j).getNaklad() + 1000);

					pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - 1000);
				}
				

			}

		}
		z++;
	}


	private void vytvorUdalosti() {
		for(int i = 0; i<Mapa.getPoleMest().size();i++){
			Mesto pomMest = Mapa.getPoleMest().get(i);
			ArrayList<Auto>aut = new ArrayList<Auto>();	
			ArrayList<Vrtulnik>vrtulniky = new ArrayList<Vrtulnik>();	
			if(pomMest.getHlad()){
				if(pomMest.isMaCesty()){
					double pocetAut = Math.ceil(pomMest.getJidlaTreba()/12);

					for(int q = 0; q < pocetAut; q++){
						aut.add(new Auto("Ceka"));
					}
					udalosti.add(new Udalost(pomMest,aut, false));


				}else{
					double pocetAut = Math.ceil(pomMest.getJidlaTreba()/12);

					for(int q = 0; q < pocetAut; q++){
						aut.add(new Auto("Ceka"));
					}
					double pocetVrtulniku = Math.ceil(pomMest.getJidlaTreba()/2);
					for(int q = 0; q < pocetVrtulniku; q++){
						vrtulniky.add(new Vrtulnik("Ceka"));
					}

					udalosti.add(new Udalost(pomMest,pomMest.getPredchudce(), aut,vrtulniky, true));				
				}

			}

			pomMest.setHlad(false);
		}
	}
}
