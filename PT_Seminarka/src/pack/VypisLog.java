package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * vypisuje jednotlive logy
 * @author jakub Hain, David Basta
 *
 */

public class VypisLog {
	/**
	 * pocet vrtulniku
	 */
	public static int pocVrt = 0;
	/**
	 * Celkovy naklad vrt
	 */
	public static double celkNaklVrt = 0;
	/**
	 * pocet aut
	 */
	public static int pocAut = 0;
	/**
	 * celkovy naklad aut
	 */
	public static double celkNaklAut = 0;


	/**
	 * Souhrny log za 3 dny
	 * @param poleMest pole mest
	 * @param poleAut pole Aut
	 * @param poleVrt pole Vrtulniku
	 * @param cesta cilova slozka
	 * @param den 3/6 den
	 */
	public static void zapis3Dny(List<Mesto>poleMest, List<Auto> poleAut, List<Vrtulnik> poleVrt, String cesta, int den) {
		FileOutputStream stream;
		int pocetVrt = 0;
		double celkNakladVrt = 0;
		int pocetAut = 0;
		double celkNakladAut = 0;
		File f = new File(cesta + "\\Log3Dny" + (den*3) + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			writer.append("Souhrné statistiky za 3 dny");
			writer.newLine();
			for(int z = 0; z<poleMest.size();z++){


				if(!poleMest.get(z).isMaCesty()){
					for(int t = 0; t<poleVrt.size();t++){

						if(poleVrt.get(t).getDokonKdy()>((den-1)*4320) && poleVrt.get(t).getDokonKdy()
								<=den*4320 &&poleVrt.get(t).getUdalost().getMesto().equals(poleMest.get(z))){


							pocetVrt++;
							celkNakladVrt += poleVrt.get(t).getUdalost().getDobaNakl()*1000/30;

							pocetAut += poleVrt.get(t).getUdalost().getIndex().size();

						}

					}
				}

				for(int t = 0; t<poleAut.size();t++){
					for(int k = 0; k< poleAut.get(t).getUdalost().size(); k++){
						Udalost ud = poleAut.get(t).getUdalost().get(k);
						if(ud.getDokonKdy()>((den-1)*4320) && ud.getDokonKdy()
								<=den*4320 &&ud.getMesto().equals(poleMest.get(z))
								&& !ud.isVrtulnik()){

							pocetAut ++;
							celkNakladAut += ud.getDobaNakl()*1000/30;


						}
					}
				}

			}
			writer.append("Do " + poleMest.size() + "mest bylo rozvezeno :");
			writer.newLine();
			writer.append("Pomoci "+ pocetVrt + " vrtulniku: " + celkNakladVrt);
			writer.newLine();
			writer.append("Pomoci "+ pocetAut + " aut: " + celkNakladAut);
			writer.newLine();
			writer.append("Celkove bylo pouzito " + (pocetAut+pocetVrt) + " vozidel a rovezlo se " + (celkNakladAut+celkNakladVrt));
			writer.newLine();
			VypisLog.pocAut +=pocetAut; 
			VypisLog.pocVrt +=pocetVrt;
			VypisLog.celkNaklAut +=celkNakladAut;
			VypisLog.celkNaklVrt += celkNakladVrt;

			writer.close();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Log vrtulniku za kazdy den
	 * @param poleVrt vrtulniky
	 * @param cesta cilova slozka
	 * @param den ktery den
	 */
	public static void zapisVrt(List<Vrtulnik> poleVrt, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDenVrt" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleVrt.size();t++){
				if(poleVrt.get(t).getDokonKdy()>((den-1)*1400) && poleVrt.get(t).getDokonKdy()<=den*1440){
					writer.append(poleVrt.get(t).vypisVrt(t, poleVrt.get(t)));
					writer.newLine();


				}


			}
			writer.close();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Logy aut za den
	 * @param poleAuta pole aut
	 * @param cesta cilova slozka
	 * @param den den
	 */
	public static void zapisAut(List<Auto> poleAuta, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDenAuta" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleAuta.size();t++){
				/**----DOPIS TO NA AUTO A INDEX + OPRAV TY 0---**/
				writer.append(poleAuta.get(t).vypisAuto(t, poleAuta.get(t)));
				writer.newLine();



			}
			writer.close();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Logy mest za den
	 * @param poleMest pole mest
	 * @param cesta cilova slozka
	 * @param den den
	 */
	public static void zapis(List<Mesto> poleMest, List<Auto> auta, List<Vrtulnik> vrtulniky, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDen" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleMest.size();t++){
				writer.append(poleMest.get(t).vypisMesto(t));
				writer.newLine();
				for(int k = 0; k<auta.size();k++){
					for(int q = 0; q < auta.get(k).getUdalost().size(); q++){
						Udalost ud =auta.get(k).getUdalost().get(q);
						Mesto mesto = auta.get(k).getUdalost().get(q).getMesto();
						if((ud.getDokonKdy()>((den-1)*1434) && ud.getDokonKdy()
								<=den*1440) || ud.getCasDokon() ==0){
							if(mesto.equals(poleMest.get(t))){
								/**----DOPIS TO NA AUTO A INDEX---**/
								writer.append("Auto " + k);
								writer.newLine();


							}
						}
					}
				}
				if(!poleMest.get(t).isMaCesty()){
					
					writer.newLine();
				}

				for(int q = 0; q < vrtulniky.size(); q++){
					Mesto mesto = vrtulniky.get(q).getUdalost().getMesto();
					if(mesto.equals(poleMest.get(t))){
						/**----DOPIS TO NA VRTULNIK A INDEX---**/
						if((vrtulniky.get(q).getDokonKdy()>((den-1)*1434) && vrtulniky.get(q).getDokonKdy()
								<=den*1440) || vrtulniky.get(q).getDokonKdy() ==0){
							writer.append("Vrtulnik " + q);
							writer.newLine();
						}
					}



				}
				writer.newLine();
			}


			writer.close();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Souhrny lod za celou simulaci
	 * @param poleMest pole mest
	 * @param poleAut pole Aut
	 * @param poleVrt pole Vrtulniku
	 * @param cesta cilova slozka
	 */
	public static void souhrn(List<Mesto> poleMest, List<Auto> poleAut,
			List<Vrtulnik> poleVrt, String cesta) {
		FileOutputStream stream;
		int pocetVrt = 0;
		double celkNakladVrt = 0;
		int pocetAut = 0;
		double celkNakladAut = 0;
		File f = new File(cesta + "\\Souhrn.txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int z = 0; z<poleMest.size();z++){


				if(!poleMest.get(z).isMaCesty()){
					for(int t = 0; t<poleVrt.size();t++){

						if(poleVrt.get(t).getDokonKdy()>((6)*1440) && poleVrt.get(t).getDokonKdy()
								<=7*1440 &&poleVrt.get(t).getUdalost().getMesto().equals(poleMest.get(z))){


							pocetVrt++;
							celkNakladVrt += poleVrt.get(t).getUdalost().getDobaNakl()*1000/30;

							pocetAut += poleVrt.get(t).getUdalost().getIndex().size();

						}

					}
				}

				for(int t = 0; t<poleAut.size();t++){
					boolean autoZapoc = false;
					for(int q =0; q<poleAut.get(t).getUdalost().size();q++){
						Udalost ud = poleAut.get(t).getUdalost().get(q);
						if(ud.getDokonKdy()>(6*4320) && ud.getDokonKdy()
								<=7*1440 &&ud.getMesto().equals(poleMest.get(z))
								&& !ud.isVrtulnik()){
							if(!autoZapoc){
								pocetAut ++;
							}
							celkNakladAut += ud.getDobaNakl()*1000/30;
							autoZapoc = true;
						}
					}
				}


			}
			VypisLog.pocAut +=pocetAut; 
			VypisLog.pocVrt +=pocetVrt;
			VypisLog.celkNaklAut +=celkNakladAut;
			VypisLog.celkNaklVrt += celkNakladVrt;
			writer.append("Do " + poleMest.size() + "mest bylo rozvezeno :");
			writer.newLine();
			writer.append("Pomoci "+ VypisLog.pocVrt + " vrtulniku: " + VypisLog.celkNaklVrt);
			writer.newLine();
			writer.append("Pomoci "+ VypisLog.pocAut + " aut: " + VypisLog.celkNaklAut);
			writer.newLine();
			writer.append("Celkove bylo pouzito " + (VypisLog.pocAut+VypisLog.pocVrt) + " vozidel a rovezlo se " + (VypisLog.celkNaklAut+VypisLog.celkNaklVrt));
			writer.newLine();




			writer.close();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
