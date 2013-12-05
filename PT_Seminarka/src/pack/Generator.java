



package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Generator {
	public static double matice[][] = new double [3005][3000];
	static Random r = new Random();
	public static int indexMestPod2 = 0;

	/**
	 * Zakladni konstruktor ktery vytvori mesta, letiste a jejich sousedy
	 */
	public Generator()
	{
		generujLetiste();
		generujMesta();
		genSousMest(Mapa.poleMest);
		genSousLetist();	
		for(int i =0; i< Mapa.poleLetist.size(); i++){
			dijkstra(Mapa.poleLetist.get(i), i);
		}


	}
	public Generator(ArrayList<Mesto> poleMest, ArrayList<Letiste> poleLetist) {
		vytvorMatici(poleMest, poleLetist, indexMestPod2+1);

		for(int i =0; i< poleLetist.size(); i++){
			dijkstra(poleLetist.get(i), i);
		}
		/*for (int i = 0; i < poleMest.size(); i++) {
			System.out.println(poleMest.get(i).getVzdalenost());
		}*/
	}
	/**
	 * Vygeneruje x, y - souradnice pro 5 letist
	 */
	public static void generujLetiste ()
	{
		int x = 0;
		int y = 0;

		for(int i = 0; i <5; i++)
		{
			do
			{
				x = generujSour();
				y = generujSour();
			}while(porovnejLetiste (x,y,i));
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

		for(int i = 0; i <3000; i++)
		{
			if (i==0)//vytvareni prvniho mesta
			{
				x = generujSour();
				y = generujSour();
				obyv = (int) (stredniHodnota + rozptyl * r.nextGaussian());//vypocet obyvatel pro mesto
				obyv = Math.abs(obyv);
				if(obyv >= 10000)//nastavovani heliportu pro mesta s obyv nad 10 000
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,true));
				}
				else
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,false));
				}
			}
			else
			{
				do
				{
					x = generujSour();
					y = generujSour();
				}while(porovnejMesta (x,y,i));

				obyv = (int) (stredniHodnota + rozptyl * r.nextGaussian());
				obyv = Math.abs(obyv);

				if(obyv >= 10000)//nastavovani heliportu pro mesta s obyv nad 10 000
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,true));
				}
				else
				{
					Mapa.poleMest.add(new Mesto(x,y,obyv,false));
				}	
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
		int x = r.nextInt(500);
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
			if (vysledek < 150.0 || (stredX < 25 && stredY < 25)){ return true;}
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
			if (vysledekLetiste < 5.0) {return true;}
		}

		for (int i = 0; i < j; i++)//vzdalenost mezi mesty
		{
			//pocitani vzdalenosti pomoci vektoru
			vysledekMesto = Math.sqrt(Math.pow(x - Mapa.poleMest.get(i).getX(), 2)+ Math.pow(y - Mapa.poleMest.get(i).getY(), 2));
			if (vysledekMesto < 5.0){ return true;}
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
		ArrayList<Vzdalenost> vzdalenosti = new ArrayList<>();

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
			ArrayList<Mesto> pomoc = new ArrayList<>();

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
	public static void genSousLetist()
	{
		ArrayList<Vzdalenost> vzdalenosti = new ArrayList<>();
		for( int i = 0; i < Mapa.poleLetist.size(); i++)
		{
			for(int j = indexMestPod2+1; j<Mapa.poleMest.size(); j++)
			{

				double vysledekMesto = Math.sqrt(Math.pow(Mapa.poleLetist.get(i).getX() - Mapa.poleMest.get(j).getX(), 2)+ 
						Math.pow(Mapa.poleLetist.get(i).getY() - Mapa.poleMest.get(j).getY(), 2));
				vzdalenosti.add(new Vzdalenost (j,vysledekMesto));
			}
			Collections.sort(vzdalenosti, new Komparator());
			ArrayList<Mesto> pomoc = new ArrayList<>();

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
		Queue<Mesto> fronta=new LinkedList<>();
		Mesto pom;
		Mesto m;

		for(int i =0; i < letiste.getSousedi().size(); i++){
			Mesto l;
			l = letiste.getSousedi().get(i);
			l.setVzdalenost(matice[indexLet][Mapa.getIndexMest(l)]);
			//System.out.println(matice[indexLet][Mapa.getIndexMest(l)]);
			l.setOdkud(letiste);
			fronta.add(l);

		}
		double pomVzdal;
		while (!fronta.isEmpty()) {

			m = fronta.poll();
			pom = m.getSousedi().get(0);

			for(int i = 1; i <m.getSousedi().size();i++){
				if((pomVzdal = m.getVzdalenost() + matice[5+Mapa.getIndexMest(m)][Mapa.getIndexMest(pom)])<pom.getVzdalenost()){
					//System.out.println(pomVzdal);
					pom.setPredchudce(m);
					pom.setOdkud(letiste);
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

		ArrayList<Vzdalenost> vzdal= new ArrayList<>();
		for(int i =0; i <mesta.size();i++ ){
			if(mesta.get(i).getHeliport()){
				double vysledekMesto = Math.sqrt(Math.pow(mesta.get(i).getX() - mest.getX(), 2)+ 
						Math.pow(mesta.get(i).getY() - mest.getY(), 2));
				vzdal.add(new Vzdalenost(i, vysledekMesto));
			}


		}
		mest.setMaCesty(false);
		Collections.sort(vzdal, new Komparator());
		mest.setPredchudce(mesta.get(vzdal.get(0).index));
		mest.setVzdalenost(vzdal.get(0).vzdalenost);
		mest.setOdkud(mesta.get(vzdal.get(0).index).getOdkud());


	}



	public static void vytvorMatici(ArrayList<Mesto> mest, ArrayList<Letiste> letist, int min){

		for(int i = 0; i< letist.size();i++){
			Letiste pomLet = letist.get(i);
			for(int q = 0; q< letist.get(i).getSousedi().size();q++){
				Mesto a = letist.get(i).getSousedi().get(q);
				matice[i][Mapa.getIndexMest(a)] = Math.sqrt(Math.pow(pomLet.getX() - a.getX(), 2)+ 
						Math.pow(pomLet.getY() - a.getY(), 2));
				//	System.out.print(matice[i][Mapa.getIndexMest(a)]+ " ");
			}	
			//System.out.println();
		}
		for(int i = min; i< 3000;i++){

			Mesto pomMest = mest.get(i);

			for(int q = 0; q< pomMest.getSousedi().size();q++){

				Mesto a = mest.get(i).getSousedi().get(q);

				matice[i+5][Mapa.getIndexMest(a)] = Math.sqrt(Math.pow(pomMest.getX() - a.getX(), 2)+ 
						Math.pow(pomMest.getY() - a.getY(), 2));
				//System.out.print(matice[i][Mapa.getIndexMest(a)]+ " ");
			}	
			//System.out.println();
		}


	}

	public static String cesta(Mesto mesto){
		ArrayList<String> str = new ArrayList<>();

		str.add(String.valueOf(Mapa.getIndexMest(mesto.getPredchudce())));
		str = rekurCesta(mesto.getPredchudce(), str);

		return null;


	}
	private static ArrayList<String> rekurCesta(Mesto predchudce,
			ArrayList<String> str) {
		try{
		str.add(String.valueOf(Mapa.getIndexMest(predchudce.getPredchudce())));
		}catch(NullPointerException e){
			System.out.println("letiste");
			str.add(String.valueOf(predchudce.getOdkud()));
		}
		return str;
	}
}
