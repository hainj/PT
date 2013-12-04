package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class vypisLog {


	public static void vypis(JTextArea textBlok) {

		textBlok.append(Mapa.poleMest.get(0).toString());
		
	}

	public static void zapis(ArrayList<Udalost> udalosti, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDen" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			
			for(int i = 0; i < udalosti.size(); i++){
				System.out.println(i);
				writer.append("Mesto " + i);
				writer.newLine();
				writer.append(udalosti.get(i).getMesto().toString());
				writer.newLine();
				writer.append("Auta");
				writer.newLine();
				for(int q = 0; q<udalosti.get(i).getAuta().size(); q++){
					writer.append("Auto " + q);
					writer.newLine();
					writer.append(udalosti.get(i).getAuta().get(q).toString());
					writer.newLine();
				}
				if(!udalosti.get(i).getMesto().isMaCesty()){
					writer.append("Vrtulniky");
					writer.newLine();
					for(int l = 0; l <udalosti.get(i).getVrtulniky().size(); l++){
						writer.append("Vrtulnik " + l);
						writer.newLine();
						writer.append(udalosti.get(i).getVrtulniky().get(l).toString());
						writer.newLine();
						
					}
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


}
