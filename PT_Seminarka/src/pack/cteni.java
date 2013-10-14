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

public class cteni {




	public static void main(String[] args) {
		String vstup = null;
		vystupMapa(null, null);
		vstupMapa(vstup);

	}
/*
 * Vypise mesta a letiste do souboru
 */
	private static void vystupMapa(int mest[], int let[]) {
		// TODO Auto-generated method stub
		File f = new File("vystupMest.txt");
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
			for(int i = 0; i<let.length;i++){
				writer.write("x   y  sousedi\n");
				
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

	private static void vstupMapa(String vstup) {
		// TODO Auto-generated method stub
		FileInputStream stream;
		try {
			stream = new FileInputStream(new File(vstup));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		 reader.readLine();
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
