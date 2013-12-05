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


	public static void vypis(JTextArea textBlok) {

		textBlok.append(Mapa.poleMest.get(0).toString());

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
