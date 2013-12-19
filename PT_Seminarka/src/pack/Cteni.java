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
import java.util.List;
/**
* Nacte a zapise data do souboru
* @author Jakub Hain, David Basta
*
*/
public class Cteni {
	/**
	 * Vypise data do souboru
	 * @param list seznam mest
	 * @param list2 seznam letist
	 * @param f cilovy soubor
	 * @param bezSous pocet mest bez sousedu
	 */
	public static void vystupMapa(List<Mesto> list, List<Letiste> list2, File f, int bezSous) {
		// TODO Auto-generated method stub

		FileOutputStream stream;
		try {
			stream = new FileOutputStream(f);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

			writer.append(String.valueOf(bezSous));
			writer.newLine();
			//vypise x, y a jidlo  letiste
			for(int i = 0; i<list2.size();i++){
				writer.append(list2.get(i).getX() + " " +  list2.get(i).getY() + " " + list2.get(i).getJidlo());
				writer.newLine();
			}
			//vypise x, y, pocet obyv mesta
			for(int i = 0; i<list.size();i++){
				Mesto mesto = list.get(i);

				writer.append(mesto.getX() + " " +  mesto.getY() + " " + mesto.getObyvatel() + " " + mesto.getHeliport());
				writer.newLine();
			}
			writer.append("sousedi letist");

			writer.newLine();

			//vypise sousedy letist
			for (int j = 0; j<list2.size(); j++){

				String str = "";
				for(int z = 0; z<list2.get(j).getSousedi().size();z++){
					str = str + Mapa.getIndexMest(list2.get(j).getSousedi().get(z)) + " ";

				}
				writer.append(str);
				writer.newLine();

			}

			writer.write("sousedi mest");
			writer.newLine();

			//vypise sousedy mest
			for(int i =  Generator.indexMestPod2+1; i<list.size();i++){
				Mesto mesto = list.get(i);
				String str = "";
				//System.out.println(i);
				try {
					int pocetSous = mesto.getSousedi().size();
					//System.out.println(pocetSous);
					for (int j =0; j<pocetSous;j++){
						str = str + Mapa.getIndexMest(mesto.getSousedi().get(j)) + " ";	

					}
					writer.append(str);
					writer.newLine();
				} catch (Exception e) {
					System.out.println("mesto bez sousedu");
				}

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
			Mapa.getPoleLetist().clear();


			int a = Integer.valueOf(reader.readLine());
			Generator.indexMestPod2 = a;
			//nacte letiste
			for(int i = 0; i<5;i++){
				str = reader.readLine();

				String[]s = str.split(" ");


				Mapa.getPoleLetist().add(new Letiste(Integer.parseInt(s[0]), Integer.parseInt(s[1])));


			}
			/*for(int i = 0; i<5;i++){
				System.out.println(Mapa.getPoleLetist().get(i).getJidlo().size());
			}*/
			//Mapa.setLetiste(letist);
			str = reader.readLine();
			Mapa.getPoleMest().clear();
			//nacte mesta

			while(!str.equalsIgnoreCase("sousedi letist")){


				String[]s = str.split(" ");

				Mapa.addPoleMest(new Mesto(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Boolean.parseBoolean(s[3])));
				str = reader.readLine();
			}


			//sousedi letist
			str = reader.readLine();
			int g =0;
			while(!str.equalsIgnoreCase("sousedi mest")){

				ArrayList<Mesto> pomoc = new ArrayList<>();
				String[]s = str.split(" ");
				//	 

				//System.out.println(s.length);
				for (int i = 0; i < s.length;i++){
					int k = Integer.parseInt(s[i]);
					pomoc.add(Mapa.getPoleMest().get(k));
				}

				Mapa.getPoleLetist().get(g).setSousedi(pomoc);

				str = reader.readLine();

				g++;
			}




			//sousedi mest
			str = reader.readLine();
			int z = a+1;
			while(!str.equalsIgnoreCase("exit")){


				ArrayList<Mesto> pomoc = new ArrayList<>();

				String[]s = str.split(" ");
				for (int i = 0; i<s.length; i++){
					int k = Integer.parseInt(s[i]);
					pomoc.add(Mapa.getPoleMest().get(k));

				}
				Mapa.getPoleMest().get(z).setSousedi(pomoc);
				str = reader.readLine();

				z++;
			}
			z=a+1;
			//System.out.println(z);
			Generator.vytvorMatici(Mapa.getPoleMest(), Mapa.getPoleLetist(),z);
			for(int i = 0; i < Mapa.getPoleLetist().size(); i++){
				Generator.dijkstra(Mapa.getPoleLetist().get(i), i);

			}
			if(Mapa.getPoleMest().size()>3000){
				for(int i = 3000; i < Mapa.getPoleMest().size(); i++){
					Generator.mestoBezSousVzdal(Mapa.getPoleMest().get(i),Mapa.getPoleMest());
				}
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
