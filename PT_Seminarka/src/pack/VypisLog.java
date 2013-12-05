package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class VypisLog {


	public static void vypis(JTextArea textBlok, Mesto mesto, ArrayList<Udalost> udalosti) {
		ArrayList<Integer> listInt = new ArrayList<>();
		textBlok.append("Mesto " + Mapa.getIndexMest(mesto));
		textBlok.append(mesto.toString());
		for(int i = 0; i < mesto.getAut().size() ;i++){
			textBlok.append(mesto.getAut().get(i).toString());
			for(int q = 0; q < udalosti.size(); q++){
				if(Mapa.getIndexMest(udalosti.get(q).getMesto()) == Mapa.getIndexMest(mesto)){
					listInt.add(q);
					for(int p = 0; p < udalosti.get(q).getAuta().size(); p++){
						textBlok.append(udalosti.get(q).getAuta().get(p).toString());
					}
				}
			}
		}
		if(!mesto.isMaCesty()){
			for(int i = 0; i < mesto.getVrt().size() ;i++){
				textBlok.append(mesto.getVrt().get(i).toString());
				for(int q = 0; q < listInt.size(); q++){
					for(int p = 0; p < udalosti.get(q).getVrtulniky().size(); p++){
						textBlok.append(udalosti.get(q).getVrtulniky().get(p).toString());
					}
				}
			}
		}
		textBlok.append(" ");
	}

	public static void zapis(ArrayList<Udalost> udalosti, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDen" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));


			for(int l = 0; l < udalosti.size(); l++){
				//System.out.println(l);
				writer.append("Mesto " + Mapa.getIndexMest(udalosti.get(l).getMesto()));
				writer.newLine();
				writer.append(udalosti.get(l).getMesto().toString());
				writer.newLine();
				writer.append("Auta");
				writer.newLine();
				for(int q = 0; q<udalosti.get(l).getAuta().size(); q++){
					writer.append("Auto " + q);
					writer.newLine();
					writer.append(udalosti.get(l).getAuta().get(q).toString());
					writer.newLine();
				}
				if(!udalosti.get(l).getMesto().isMaCesty()){
					writer.append("Vrtulniky");
					writer.newLine();
					for(int p = 0; p <udalosti.get(l).getVrtulniky().size(); p++){
						writer.append("Vrtulnik " + p);
						writer.newLine();
						writer.append(udalosti.get(l).getVrtulniky().get(p).toString());
						writer.newLine();

					}
				}


			}
			System.out.println(udalosti.size());
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
