package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;



public class VypisLog {

	public static int pocVrt = 0;
	public static double celkNaklVrt = 0;
	public static int pocAut = 0;
	public static double celkNaklAut = 0;



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
					if(poleAut.get(t).getDokonKdy()>((den-1)*4320) && poleAut.get(t).getDokonKdy()
							<=den*4320 &&poleAut.get(t).getUdalost().getMesto().equals(poleMest.get(z))
							&& !poleAut.get(t).getUdalost().isVrtulnik()){

						pocetAut ++;
						celkNakladAut += poleAut.get(t).getUdalost().getDobaNakl()*1000/30;


					}
				}

			}
			writer.append("Do " + poleMest.size() + "mest bylo rozvezeno :");
			writer.newLine();
			writer.append("Pomoci "+ pocetVrt + " vrtulniku: " + celkNakladVrt);
			writer.newLine();
			writer.append("Pomoci "+ pocetAut + " aut: " + celkNakladAut);
			writer.newLine();
			writer.append("Celkove bylo pouzito " + (pocetAut+pocetVrt) + " a rovezlo se " + (celkNakladAut+celkNakladVrt));
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
	public static void zapisVrt(List<Vrtulnik> poleVrt, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDenVrt" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleVrt.size();t++){
				if(poleVrt.get(t).getDokonKdy()>((den-1)*1400) && poleVrt.get(t).getDokonKdy()<=den*1440){
					writer.append(poleVrt.get(t).vypisVrt(t));
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

	public static void zapisAut(List<Auto> poleAuta, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDenAuta" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleAuta.size();t++){
				writer.append(poleAuta.get(t).vypisAuto(t));
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

	public static void zapis(List<Mesto> poleMest, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDen" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleMest.size();t++){
				writer.append(poleMest.get(t).vypisMesto(t));
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
					if(poleAut.get(t).getDokonKdy()>(6*4320) && poleAut.get(t).getDokonKdy()
							<=7*1440 &&poleAut.get(t).getUdalost().getMesto().equals(poleMest.get(z))
							&& !poleAut.get(t).getUdalost().isVrtulnik()){

						pocetAut ++;
						celkNakladAut += poleAut.get(t).getUdalost().getDobaNakl()*1000/30;


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
			writer.append("Celkove bylo pouzito " + (VypisLog.pocAut+VypisLog.pocVrt) + " a rovezlo se " + (VypisLog.celkNaklAut+VypisLog.celkNaklVrt));
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
