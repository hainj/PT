                                                                     
                                                                     
                                                                     
                                             
package pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Generator {
	public static ArrayList<Mesto>poleMest = new ArrayList<Mesto>();
	public static ArrayList<Letiste>poleLetist = new ArrayList<Letiste>();
	static Random R = new Random();
	public static int indexMestPod2 = 0;

	public Generator()
	{
		generujLetiste();
		generujMesta();
		genSousMest(poleMest);
		genSousLetist(poleLetist);
	}

	public static void generujLetiste ()
	{
		int x = 0;
		int y = 0;

		x = generujSour();
		y = generujSour();
		poleLetist.add(new Letiste(x,y));
		for(int i = 1; i <5; i++)
		{
			while(porovnejLetiste (x,y,i)== true)
			{
				x = generujSour();
				y = generujSour();
				poleLetist.add(new Letiste(x,y));
			}
		}

	}

	/**
	 * Metoda vytvarejici souradnice pro mesta
	 * Vytvorena mesta ulozi do ArrayListu
	 */
	public static void generujMesta ()
	{
		int x = generujSour();
		int y = generujSour();
		int stredniHodnota = 5400; //stredni hodnota pro vypocet poctu obyvatel
		int rozptyl = 1600; //nahodne zvoleny rozptyl tak aby nevznikala mesta se zapornym poctem obyv
		int obyv = 0;

		int obyvCelkem = 0; //kontrolni promenna  - soucet obyvatel vsech mest

		for(int i = 0; i <3000; i++)
		{
			if (i==0)
			{
				x = generujSour();
				y = generujSour();
				obyv = (int) (stredniHodnota + rozptyl * R.nextGaussian());//vypocet obyvatel pro mesto
				obyv = Math.abs(obyv);
				if(obyv >= 10000)
				{
					poleMest.add(new Mesto(x,y,obyv,true));
				}
				else
				{
					poleMest.add(new Mesto(x,y,obyv,false));
				}
				//poleMest.get(i).setObyvatel(obyv);
				//System.out.println(poleMest.get(i).getObyvatel());
				obyvCelkem +=poleMest.get(i).getObyvatel();
			}
			else
			{
				do
				{
					x = generujSour();
					y = generujSour();
				}while(porovnejMesta (x,y,i) == true);
				obyv = (int) (stredniHodnota + rozptyl * R.nextGaussian());
				obyv = Math.abs(obyv);
				if(obyv >= 10000)
				{
					poleMest.add(new Mesto(x,y,obyv,true));
				}
				else
				{
					poleMest.add(new Mesto(x,y,obyv,false));
				}

				/**if(obyv<=2000){
					System.out.println(obyv);
				}*/
				//poleMest.get(i).setObyvatel(obyv);
				obyvCelkem +=poleMest.get(i).getObyvatel();	
			}
			//System.out.println((i+1)+". mesto ma: "+ poleMest.get(i).getObyvatel()+ " obyvatel");
		}
		//MainAPP.dialogOkno.setText("Celkem obyv: "+ obyvCelkem);
		//MainAPP.dialogOkno.repaint();
		//System.out.println("Celkem obyv: "+ obyvCelkem);
		Collections.sort(poleMest, new KomparatorMest());
	}

	/**
	 * jednoducha metoda na vraceni nahodnych cisel
	 * pouzita pro generaci souradnic
	 * @return
	 */
	public static int generujSour ()
	{
		int x = R.nextInt(500);
		return x;
	}

	/**
	 * Metoda pro zajisteni podminky ze letiste budou mit min. vzdalenost 100km od sebe
	 * @param x - souradnice
	 * @param y - souradnice
	 * @param j - poloha v cyklu generujici nova letiste
	 * @return
	 */
	public static boolean porovnejLetiste (int x, int y, int j)
	{
		double vysledek = 0.0;
		int stredX = Math.abs(250 - x) ;
		int stredY = Math.abs(250 - y) ;

		for (int i = 0; i < j; i++)
		{
			vysledek = Math.sqrt(Math.pow(x - poleLetist.get(i).getX(), 2)+ Math.pow(y - poleLetist.get(i).getY(), 2));
			if (vysledek < 150.0 || (stredX < 25 && stredY < 25)) return true;
		}

		return false;
	}


	/**
	 * Metoda pro zajisteni podminky ze mesta budou mit min. vzdalenost 5km od sebe
	 * @param x - souradnice
	 * @param y - souradnice
	 * @param j - poloha v cyklu generujici nova mesta
	 * @return
	 */
	public static boolean porovnejMesta (int x, int y, int j)
	{
		double vysledekMesto = 0.0;
		double vysledekLetiste = 0.0;
		for (int i = 0; i < 5; i++)//vzdalenost mest a letist je take min. 5km

		{
			vysledekLetiste = Math.sqrt(Math.pow(x - poleLetist.get(i).getX(), 2)+ Math.pow(y - poleLetist.get(i).getY(), 2));
			if (vysledekLetiste < 5.0) return true;
		}

		for (int i = 0; i < j; i++)
		{
			vysledekMesto = Math.sqrt(Math.pow(x - poleMest.get(i).getX(), 2)+ Math.pow(y - poleMest.get(i).getY(), 2));
			if (vysledekMesto < 5.0) return true;
		}

		return false;
	}

	public static int zjistiPod2 (ArrayList<Mesto> mesta)
	{
		int i = 0;
		while(mesta.get(i).getObyvatel() <= 2000)
		{
			i++;
		}
		i = (int) (i*0.7);
		return i;
	}

	public static void genSousMest(ArrayList<Mesto> poleMesta)
	{
		indexMestPod2  = zjistiPod2(poleMest);
		ArrayList<Vzdalenost> vzdalenosti = new ArrayList<Vzdalenost>();
		
		for( int i = indexMestPod2+1; i < poleMesta.size(); i++)
		{

			//int maSous = poleMesta.get(i).getSousedi().size();
			for(int j = indexMestPod2+1; j<poleMesta.size(); j++)
			{
		
				double vysledekMesto = Math.sqrt(Math.pow(poleMest.get(i).getX() - poleMest.get(j).getX(), 2)+ 
						Math.pow(poleMest.get(i).getY() - poleMest.get(j).getY(), 2));
				vzdalenosti.add(new Vzdalenost (j,vysledekMesto));
			}
			Collections.sort(vzdalenosti, new Komparator());
			ArrayList<Mesto> pomoc = new ArrayList<Mesto>();

			/**for(int l = 0; l < vzdalenosti.size(); l++)
			{
				System.out.println(i+ ". mesto: "+vzdalenosti.get(l).vzdalenost);
			}*/

			for (int k = 1; k <11; k++)
			{
				pomoc.add(poleMesta.get(vzdalenosti.get(k).index));
			}
			poleMesta.get(i).setSousedi(pomoc);
			vzdalenosti.clear();
		}

	}
	
	public static void genSousLetist(ArrayList<Letiste> poleLetist)
	{
		ArrayList<Vzdalenost> vzdalenosti = new ArrayList<Vzdalenost>();
		for( int i = 0; i < poleLetist.size(); i++)
		{

			//int maSous = poleMesta.get(i).getSousedi().size();
			for(int j = indexMestPod2+1; j<poleMest.size(); j++)
			{
		
				double vysledekMesto = Math.sqrt(Math.pow(poleLetist.get(i).getX() - poleMest.get(j).getX(), 2)+ 
						Math.pow(poleLetist.get(i).getY() - poleMest.get(j).getY(), 2));
				vzdalenosti.add(new Vzdalenost (j,vysledekMesto));
			}
			Collections.sort(vzdalenosti, new Komparator());
			ArrayList<Mesto> pomoc = new ArrayList<Mesto>();

			for (int k = 0; k <60; k++)
			{
				pomoc.add(poleMest.get(vzdalenosti.get(k).index));
			}
			poleLetist.get(i).setSousedi(pomoc);
			vzdalenosti.clear();
		}
	}
}
