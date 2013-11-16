



package pack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Generator {
	public static double matice[][] = new double [3005][3000];
	static Random R = new Random();
	public static int indexMestPod2 = 0;

	/**
	 * Zakladni konstruktor ktery vytvori mesta, letiste a jejich sousedy
	 */
	public Generator()
	{
		generujLetiste();
		generujMesta();
		genSousMest(Mapa.poleMest);
		genSousLetist(Mapa.poleLetist);	
		for(int i =0; i< Mapa.poleLetist.size(); i++){
			dijkstra(Mapa.poleLetist.get(i), i);
		}


	}
	/**
	 * Vygeneruje x, y - souradnice pro 5 letist
	 */
	public static void generujLetiste ()
	{
		int x = 0;
		int y = 0;

		x = generujSour();
		y = generujSour();
		Mapa.poleLetist.add(new Letiste(x,y));
		for(int i = 1; i <5; i++)
		{
			do
			{
				x = generujSour();
				y = generujSour();
			}while(porovnejLetiste (x,y,i)== true);
			Mapa.poleLetist.add(new Letiste(x,y));
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
			if (i==0)//vytvareni prvniho mesta
			{
				x = generujSour();
				y = generujSour();
				obyv = (int) (stredniHodnota + rozptyl * R.nextGaussian());//vypocet obyvatel pro mesto
				obyv = Math.abs(obyv);
				if(obyv >= 10000)//nastavovani heliportu pro mesta s obyv nad 10 000
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,true));
				}
				else
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,false));
				}
				obyvCelkem +=Mapa.poleMest.get(i).getObyvatel();
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

				if(obyv >= 10000)//nastavovani heliportu pro mesta s obyv nad 10 000
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,true));
				}
				else
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,false));
				}

				obyvCelkem +=Mapa.poleMest.get(i).getObyvatel();	
			}
		}
		//seradi Mapa.poleMest podle poctu obyvatel tridou KomparatorMest
		Collections.sort(Mapa.poleMest, new KomparatorMest());
	}

	/**
	 * jednoducha metoda na vraceni nahodnych cisel od 0 do 500
	 * pouzita pro generaci souradnic
	 * @return nahodne vygenerovane cislo
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
			//pocitani vzdalenosti pomoci vektoru
			vysledek = Math.sqrt(Math.pow(x - Mapa.poleLetist.get(i).getX(), 2)+ Math.pow(y - Mapa.poleLetist.get(i).getY(), 2));
			//podminka zajistujici vzdalenost letis 150km a polohu mimo centralni oblast
			if (vysledek < 150.0 || (stredX < 25 && stredY < 25)) return true;
		}

		return false;
	}


	/**
	 * Metoda pro zajisteni podminky ze mesta budou mit min. vzdalenost 5km od sebe
	 * @param x - souradnice
	 * @param y - souradnice
	 * @param j - poloha v cyklu generujici nova mesta
	 * @return	boolean hodnotu
	 */
	public static boolean porovnejMesta (int x, int y, int j)
	{
		double vysledekMesto = 0.0;
		double vysledekLetiste = 0.0;
		for (int i = 0; i < 5; i++)//vzdalenost mest a letist je take min. 5km
		{
			//pocitani vzdalenosti pomoci vektoru
			vysledekLetiste = Math.sqrt(Math.pow(x - Mapa.poleLetist.get(i).getX(),
							  2)+ Math.pow(y - Mapa.poleLetist.get(i).getY(), 2));
			if (vysledekLetiste < 5.0) return true;
		}

		for (int i = 0; i < j; i++)//vzdalenost mezi mesty
		{
			//pocitani vzdalenosti pomoci vektoru
			vysledekMesto = Math.sqrt(Math.pow(x - Mapa.poleMest.get(i).getX(), 2)+ Math.pow(y - Mapa.poleMest.get(i).getY(), 2));
			if (vysledekMesto < 5.0) return true;
		}

		return false;
	}

	/**
	 * Najde 30% mest pod 2000
	 * @param mesta seznam mest
	 * @return pocet mest
	 */
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

	/**
	 * Vygeneruje 10 sousedu kazdemu mestu, krome 30% mest pod 2000
	 * @param Mapa.poleMesta seznam mest
	 */
	public static void genSousMest(ArrayList<Mesto> poleMesta)
	{
		indexMestPod2  = zjistiPod2(Mapa.poleMest);
		ArrayList<Vzdalenost> vzdalenosti = new ArrayList<Vzdalenost>();

		//diky tomu ze je Mapa.poleMest serazene si mohu dovolit prvnich x preskocit
		for( int i = indexMestPod2+1; i < poleMesta.size(); i++)
		{
			for(int j = indexMestPod2+1; j<poleMesta.size(); j++)
			{

				double vysledekMesto = Math.sqrt(Math.pow(Mapa.poleMest.get(i).getX() - Mapa.poleMest.get(j).getX(), 2)+ 
						Math.pow(Mapa.poleMest.get(i).getY() - Mapa.poleMest.get(j).getY(), 2));
				vzdalenosti.add(new Vzdalenost (j,vysledekMesto));
			}
			//serazeni pole vzdalenosti podle vzdalenosti
			Collections.sort(vzdalenosti, new Komparator());
			ArrayList<Mesto> pomoc = new ArrayList<Mesto>();

			//naplnovani matice vzdalenosti
			for (int k = 1; k <11; k++)
			{
				matice[5+i][vzdalenosti.get(k).index] = vzdalenosti.get(k).vzdalenost;
				pomoc.add(poleMesta.get(vzdalenosti.get(k).index));
			}
			//nastavovani sousedu
			poleMesta.get(i).setSousedi(pomoc);
			//vycisteni vzdalenosti
			vzdalenosti.clear();
		}

	}

	/**
	 * Vygeneruje 60 sousedu kazdemu letisti
	 * @param Mapa.poleLetist seznam letist
	 */
	public static void genSousLetist(ArrayList<Letiste> poleLetist)
	{
		ArrayList<Vzdalenost> vzdalenosti = new ArrayList<Vzdalenost>();
		for( int i = 0; i < Mapa.poleLetist.size(); i++)
		{
			for(int j = indexMestPod2+1; j<Mapa.poleMest.size(); j++)
			{

				double vysledekMesto = Math.sqrt(Math.pow(Mapa.poleLetist.get(i).getX() - Mapa.poleMest.get(j).getX(), 2)+ 
						Math.pow(Mapa.poleLetist.get(i).getY() - Mapa.poleMest.get(j).getY(), 2));
				vzdalenosti.add(new Vzdalenost (j,vysledekMesto));
			}
			Collections.sort(vzdalenosti, new Komparator());
			ArrayList<Mesto> pomoc = new ArrayList<Mesto>();

			for (int k = 0; k <60; k++)
			{
				matice[i][vzdalenosti.get(k).index] = vzdalenosti.get(k).vzdalenost;
				pomoc.add(Mapa.poleMest.get(vzdalenosti.get(k).index));
			}
			Mapa.poleLetist.get(i).setSousedi(pomoc);
			vzdalenosti.clear();
		}
	}


	public static void dijkstra(Letiste letiste, int indexLet){
		Queue<Mesto> fronta=new LinkedList<Mesto>();
		Mesto pom;
		Mesto m;

		for(int i =0; i < letiste.getSousedi().size(); i++){
			Mesto l;
			l = letiste.getSousedi().get(i);
			l.setVzdalenost(matice[indexLet][Mapa.getIndexMest(l)]);
			l.setOdkud(letiste);
			fronta.add(l);

		}
		double pomVzdal;
		while (!fronta.isEmpty()) {

			m = fronta.poll();
			pom = m.getSousedi().get(0);

			for(int i = 0; i <m.getSousedi().size();i++){
				if((pomVzdal = m.getVzdalenost() + matice[5+Mapa.getIndexMest(m)][Mapa.getIndexMest(pom)])<pom.getVzdalenost()){
					pom.setPredchudce(m);
					pom.setVzdalenost(pomVzdal);
					fronta.add(pom);

				}
				pom = m.getSousedi().get(i);
			}

		}
		
		for(int i = 0; i<=indexMestPod2;i++){
			mestoBezSousVzdal(Mapa.getPoleMest().get(i),Mapa.getPoleMest());
			
		}
	}
	
	public static void mestoBezSousVzdal(Mesto mest, ArrayList<Mesto>mesta){
		
	ArrayList<Vzdalenost> vzdal= new ArrayList<Vzdalenost>();
		for(int i =0; i <mesta.size();i++ ){
			if(mesta.get(i).getHeliport()){
				double vysledekMesto = Math.sqrt(Math.pow(mesta.get(i).getX() - mest.getX(), 2)+ 
						Math.pow(mesta.get(i).getY() - mest.getY(), 2));
						vzdal.add(new Vzdalenost(i, vysledekMesto));
			}
			
			
		}
		Collections.sort(vzdal, new Komparator());
		mest.setPredchudce(mesta.get(vzdal.get(0).index));
		mest.setOdkud(mesta.get(vzdal.get(0).index).getOdkud());
		
		
	}
	
	
	
	public static void vytvorMatici(ArrayList<Mesto> mest, ArrayList<Letiste> letist){
		/*
		 * nevim co chci
		 */
		
		
		
	}
}