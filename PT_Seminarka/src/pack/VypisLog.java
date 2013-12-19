package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class VypisLog {


	public static void vypis(JTextArea textBlok, Mesto mesto, ArrayList<Udalost> udalosti) {
	
	
	
	}
	
	public static void zapis(List<Mesto> poleMest, String cesta, int den) {
		FileOutputStream stream;
		File f = new File(cesta + "\\LogDen" + den + ".txt");
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int t = 0; t<poleMest.size();t++){
				writer.append(poleMest.get(t).toString());
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


}
