package pack;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Simulace {
	private final int CAS = 10080;
	private final double URAZENAVZDAL = 40/60;
	private ArrayList<Udalost> udalosti = new ArrayList<Udalost>();
	private int k = 1;
	private int z = 0;
	private int m = 0;



	public Simulace(JTextArea textBlok){

		vytvorUdalosti();
		//simulacni cyklus pocita cas
		for(int i = 0; i<=CAS;i++ ){
//////////////////////////////////////******zacatek simulace******///////////////////////////////////
			if(i == 60*k){
				nakladejLetiste(i);
				vytvorUdalosti();

				vypisLog.vypis(textBlok);
			}

			if(i == z*30){
				nakladej();
			}


			for(int j = 0; j < udalosti.size(); j++){
				Auto pomAut = udalosti.get(j).getAuta().get(0);

				if(pomAut.getNalozeno()){
					
					if(!(pomAut.getUjetaVzdalenost() >= udalosti.get(j).getMesto().getVzdalenost())){
						jed(udalosti.get(j));
					}
					else{
						if(i == z*30){
							vykladej(udalosti.get(j));
						}
					}
				}
			}			
			
			if(i == m*1440){
				for(int j = 0; j < Mapa.poleMest.size(); j++){
					Mesto pomMes = Mapa.poleMest.get(j);
					pomMes.setJidlo((-1)*(pomMes.getObyvatel()*6));
				}
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

	private void vykladej(Udalost ud){

		for(int i = 0; i < ud.getAuta().size(); i++){
			Auto pomAut = ud.getAuta().get(i);
			pomAut.setNaklad(-1000);
			pomAut.setStatus("Vyklada");
			ud.getMesto().setJidlo(1000);
		}

	}

	private void nakladej() {
		for(int i = 0; i <udalosti.size();i++){
			//System.out.println(i + "pokracuje");
			Udalost ud = udalosti.get(i);

			for(int j = 0; j <ud.getAuta().size();j++){

				ArrayList<Jidlo> pomJidlo = ud.getMesto().getOdkud().getJidlo();

				if(ud.getMesto().getOdkud().getJidlo().size()>0){

					if(pomJidlo.get(0).getJidlo()==0){

						pomJidlo.remove(0);
					}

					else if(ud.getAuta().get(j).getNalozeno()){
						ud.getAuta().get(j).setStatus("Ceka");
					}
					//ud.getAuta().get(j).getStatus().compareToIgnoreCase("Ceka")==0 ||
					//ud.getAuta().get(j).getStatus().compareToIgnoreCase("Naklada")==0
					else{
						if(ud.getAuta().get(j).getNaklad() == 12000){
							ud.getAuta().get(j).setNalozeno(true);
						}
						else{
							ud.getAuta().get(j).setStatus("Naklada");
							//ud.getAuta().get(j).getNaklad() + 
							ud.getAuta().get(j).setNaklad(1000);

							pomJidlo.get(0).setJidlo(pomJidlo.get(0).getJidlo() - 1000);
						}

					}


				}
				else{
					//System.out.println(i + "pomoc");
					break;
				}
			}
		}
		z++;
	}

	private void jed(Udalost ud){

		for(int i = 0; i < ud.getAuta().size(); i++){
			Auto pomAut = ud.getAuta().get(i);
			pomAut.setUjetaVzdalenost(pomAut.getUjetaVzdalenost()+URAZENAVZDAL);
			pomAut.setStatus("Jede");
		}

	}


	private void vytvorUdalosti() {
		for(int i = 0; i<Mapa.getPoleMest().size();i++){

			Mesto pomMest = Mapa.getPoleMest().get(i);
			ArrayList<Auto>aut = new ArrayList<Auto>();	
			ArrayList<Vrtulnik>vrtulniky = new ArrayList<Vrtulnik>();	

			if(pomMest.getHlad()){

				if(pomMest.isMaCesty()){

					int pocetAut = (int) Math.ceil(pomMest.getJidlaTreba()/12000);

					for(int q = 0; q <= pocetAut; q++){
						aut.add(new Auto("Ceka"));
					}
					udalosti.add(new Udalost(pomMest,aut, false));


				}
				else{
					double pocetAut = Math.ceil(pomMest.getJidlaTreba()/12000);

					for(int q = 0; q <= pocetAut; q++){
						aut.add(new Auto("Ceka"));
					}
					double pocetVrtulniku = Math.ceil(pomMest.getJidlaTreba()/2000);
					for(int q = 0; q <= pocetVrtulniku; q++){
						vrtulniky.add(new Vrtulnik("Ceka"));
					}

					udalosti.add(new Udalost(pomMest,pomMest.getPredchudce(), aut,vrtulniky, true));				
				}

			}

			pomMest.setHlad(false);
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
