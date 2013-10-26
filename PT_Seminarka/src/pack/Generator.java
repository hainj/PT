package pack;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	public static ArrayList<Mesto>poleMest = new ArrayList<Mesto>();
	public static Letiste [] poleLetist = new Letiste [5];  //neni treba arraylist, pocet letist se nemeni
	static Random R = new Random();

	public Generator()
	{
		generujLetiste();
		generujMesta();
	}

	public static void generujLetiste ()
	{
		int x = 0;
		int y = 0;

		x = generujSour();
		y = generujSour();
		poleLetist[0]= new Letiste(x,y);
		for(int i = 1; i <poleLetist.length; i++)
		{
			while(porovnejLetiste (x,y,i)== true)
			{
				x = generujSour();
				y = generujSour();
				poleLetist[i]= new Letiste(x,y);
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
				poleMest.add(new Mesto(x,y,obyv));
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
				poleMest.add(new Mesto(x,y,obyv));

				if(obyv<=2000){
					System.out.println(obyv);
				}
				//poleMest.get(i).setObyvatel(obyv);
				obyvCelkem +=poleMest.get(i).getObyvatel();	
			}
			//System.out.println((i+1)+". mesto ma: "+ poleMest.get(i).getObyvatel()+ " obyvatel");
		}
		System.out.println("Celkem obyv: "+ obyvCelkem);
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
			vysledek = Math.sqrt(Math.pow(x - poleLetist[i].getX(), 2)+ Math.pow(y - poleLetist[i].getY(), 2));
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
			vysledekLetiste = Math.sqrt(Math.pow(x - poleLetist[i].getX(), 2)+ Math.pow(y - poleLetist[i].getY(), 2));
			if (vysledekLetiste < 5.0) return true;
		}
		
		for (int i = 0; i < j; i++)
		{
			vysledekMesto = Math.sqrt(Math.pow(x - poleMest.get(i).getX(), 2)+ Math.pow(y - poleMest.get(i).getY(), 2));
			if (vysledekMesto < 5.0) return true;
		}
		
		return false;
	}

	
	
	public static void genSousMest(ArrayList<Mesto> poleMesta){
		
		for( int i = 0; i <poleMesta.size(); i++){
			
			int maSous = poleMesta.get(i).getSousedi().size();
			
			for(int j = 0+maSous; j<10; j++){
				
			}
		}
	}
}
