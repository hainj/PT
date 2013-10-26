package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class cteni {
	/**
	 * Vypise mesta a letiste do souboru
	 */
	public static void vystupMapa(ArrayList<Mesto> poleMest, Letiste let[], File f) {
		// TODO Auto-generated method stub

		FileOutputStream stream;
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int i = 0; i<let.length;i++){
				writer.append(let[i].getX() + " " +  let[i].getY() + " " + let[i].getJidlo());
				writer.newLine();
			}
			for(int i = 0; i<poleMest.size();i++){
				writer.append(poleMest.get(i).getX() + " " +  poleMest.get(i).getY() + " " + poleMest.get(i).getObyvatel());
				writer.newLine();
			}
			writer.append("exit");
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
	 * Nacte letiste a mesta ze vstupniho souboru
	 * @param f vstupni soubor
	 */
	static void vstupMapa(File f) {
		// TODO Auto-generated method stub

		FileInputStream stream;
		try {
			stream = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String str;
			Letiste[]letist = new Letiste[5];
			for(int i = 0; i<5;i++){
				str = reader.readLine();
				
				String[]s = str.split(" ");
				
			
				letist[i] = new Letiste(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				

			}	
			Mapa.setLetiste(letist);
			str = reader.readLine();
			Mapa.getPoleMest().clear();
			
			while(!str.equalsIgnoreCase("exit")){
			
				String[]s = str.split(" ");
				Mapa.addPoleMest(new Mesto(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2])));
				str = reader.readLine();
			}
					reader.close();
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
