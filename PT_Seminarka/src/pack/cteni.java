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

public class cteni {
/**
 * Vypise mesta a letiste do souboru
 */
	public static void vystupMapa(ArrayList<Mesto> poleMest, Letiste let[], String vystup) {
		// TODO Auto-generated method stub
		File f = new File(vystup+".txt");
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

	static void vstupMapa(String vstup) {
		// TODO Auto-generated method stub
		FileInputStream stream;
		try {
			stream = new FileInputStream(new File(vstup));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
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
