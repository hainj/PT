



package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
/**
* Generuje veskera potrebna dat k mape a k simulaci
* @author Jakub Hain, David Basta
*
*/


public class Generator {
	/**
	 * matice vzdalenosti
	 */
	public static double matice[][] = new double [3005][3000];
	/**
	 * random
	 */
	static Random r = new Random();
	/**
	 * 30% mest pod 2000 obyv
	 */
	public static int indexMestPod2 = 0;

	/**
	 * Zakladni konstruktor ktery vytvori mesta, letiste a jejich sousedy
	 */
	public Generator()
	{
		System.out.println("Generuje");
		generujLetiste();
		generujMesta();
		genSousMest(Mapa.poleMest);
		genSousLetist();	
		
		for(int i =0; i< Mapa.poleLetist.size(); i++){
			dijkstra(Mapa.poleLetist.get(i), i);
		}
		
		
		
		for(int i = 0; i<=indexMestPod2;i++){
			mestoBezSousVzdal(Mapa.getPoleMest().get(i),Mapa.getPoleMest());

		}
		
		for(int i = 0; i<Mapa.poleMest.size();i++){
			Collections.sort(Mapa.poleMest.get(i).getVzdLet(), new Komparator());
		}
	}
	
	/**
	 * Konstruktor s parametry
	 * @param poleMest  seznam mest
	 * @param poleLetist seznam letist
	 */
	public Generator(List<Mesto> poleMest, List<Letiste> poleLetist) {
		vytvorMatici(poleMest, poleLetist, indexMestPod2+1);

		for(int i =0; i< poleLetist.size(); i++){
			dijkstra(poleLetist.get(i), i);
		}
		
		
		
		for(int i = 0; i<=indexMestPod2;i++){
			mestoBezSousVzdal(Mapa.getPoleMest().get(i),Mapa.getPoleMest());

		}
		for(int i = 0; i<Mapa.poleMest.size();i++){
			Collections.sort(Mapa.poleMest.get(i).getVzdLet(), new Komparator());
			//System.out.println(Mapa.poleMest.get(i).getVzdLet().get(4).vzdalenost);
		}
		
		/*for (int i = 0; i < poleMest.size(); i++) {
			/Auto a = new Auto(new Udalost(true, 2, 3, 4, 5));
			System.out.println(a.toString());
		}*/
	}
	/**
	 * Vygeneruje x, y - souradnice pro 5 letist
	 */
	public static void generujLetiste ()
	{
		int x = 0;
		int y = 0;
		System.out.println("aaaabbb");

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
		System.out.println("aaaacccbbb");
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
	 * @return boolean  true pokud let muze byt pridano, false pokud ne
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
	 * @param poleMest seznam mest
	 * @return pocet mest
	 */
	public static int zjistiPod2 (List<Mesto> poleMest)
	{
		int i = 0;
		while(poleMest.get(i).getObyvatel() <= 2000)
		{
			i++;
		}
		i = (int) (i*0.7);
		return i;
	}

	/**
	 * Vygeneruje 10 sousedu kazdemu mestu, krome 30% mest pod 2000
	 * @param poleMesta seznam mest
	 */
	public static void genSousMest(List<Mesto> poleMesta)
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
	 *
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

	/**
	 * Vygeneruje vzdalenosti od letiste ke kazdemu mestu
	 * @param letiste letiste
	 * @param indexLet index letiste
	 */
	public static void dijkstra(Letiste letiste, int indexLet){
		Queue<Mesto> fronta=new LinkedList<>();
		Mesto pom;
		Mesto m;

		for(int i =0; i < letiste.getSousedi().size(); i++){
			Mesto l;
			l = letiste.getSousedi().get(i);
			double p = matice[indexLet][Mapa.getIndexMest(l)];
			//System.out.println(matice[indexLet][Mapa.getIndexMest(l)]);
			//l.getVzdLet().get(indexLet).setLet(letiste);
			//l.getVzdLet().get(indexLet).vzdalenost = p;
			l.getVzdLet().add(new Vzdalenost(null, Mapa.getPoleLetist().get(indexLet),p));
			//System.out.println(l.getVzdLet().get(indexLet).getLet().getJidlo().size());
			fronta.add(l);

		}
		double pomVzdal;
		while (!fronta.isEmpty()) {

			m = fronta.poll();
			pom = m.getSousedi().get(0);

			for(int i = 1; i <m.getSousedi().size();i++){
				if(pom.getVzdLet().size() ==indexLet){
					pom.getVzdLet().add(new Vzdalenost(null, letiste, 100000));
				}
				if((pomVzdal = m.getVzdLet().get(indexLet).vzdalenost + matice[5+Mapa.getIndexMest(m)][Mapa.getIndexMest(pom)]) < pom.getVzdLet().get(indexLet).vzdalenost){
					
					Vzdalenost vzd = pom.getVzdLet().get(indexLet);
					
					vzd.setMesto(m);
					vzd.vzdalenost = pomVzdal;
					
					fronta.add(pom);

				}
				pom = m.getSousedi().get(i);
			}

		}

		
	}
	/**
	 * Generuje vzdalenost od mest s heliportem k nasemu mestu
	 * @param mest mesto bez cest
	 * @param mesta seznam mest
	 */
	public static void mestoBezSousVzdal(Mesto mest, List<Mesto>mesta){

		List<Vzdalenost> vzdal= new ArrayList<>();
			Mesto pom;
			for(int i = 0; i < mesta.size(); i++){
				pom = mesta.get(i);
				if(pom.getHeliport()){
					double vzdalen =  Math.sqrt(Math.pow(mesta.get(i).getX() - mest.getX(), 2)+ 
							Math.pow(mesta.get(i).getY() - mest.getY(), 2));
							mest.addVzd(mesta.get(i), vzdalen);
					
					
				}
								
			}
			mest.setMaCesty(false);
	}


	/**
	 * Vytvori matici vzdalenosti mezi mesty
	 * @param list seznam mest
	 * @param list2 seznam letist
	 * @param min pocet mest bez cest
	 */
	public static void vytvorMatici(List<Mesto> list, List<Letiste> list2, int min){

		for(int i = 0; i< list2.size();i++){
			Letiste pomLet = list2.get(i);
			for(int q = 0; q< list2.get(i).getSousedi().size();q++){
				Mesto a = list2.get(i).getSousedi().get(q);
				matice[i][Mapa.getIndexMest(a)] = Math.sqrt(Math.pow(pomLet.getX() - a.getX(), 2)+ 
						Math.pow(pomLet.getY() - a.getY(), 2));
				//	System.out.print(matice[i][Mapa.getIndexMest(a)]+ " ");
			}	
			//System.out.println();
		}
		for(int i = min; i< 3000;i++){

			Mesto pomMest = list.get(i);

			for(int q = 0; q< pomMest.getSousedi().size();q++){

				Mesto a = list.get(i).getSousedi().get(q);

				matice[i+5][Mapa.getIndexMest(a)] = Math.sqrt(Math.pow(pomMest.getX() - a.getX(), 2)+ 
						Math.pow(pomMest.getY() - a.getY(), 2));
				//System.out.print(matice[i][Mapa.getIndexMest(a)]+ " ");
			}	
			//System.out.println();
		}


	}
	/*
	public static String cesta(Auto auto){
		ArrayList<String> str = new ArrayList<>();
		Letiste let;
		 int indexLet;
		for(int i = 0; i <= auto.getUdalost().getMesto().getVzdLet().size(); i++){
			let = auto.getUdalost().getMesto().getVzdLet().get(i).getLet();
			if(let.equals(auto.getUdalost().getOdkudNak())){
				indexLet = i;
			}
		}
		

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
	}*/
}
