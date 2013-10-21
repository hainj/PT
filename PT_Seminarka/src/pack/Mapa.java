package pack;
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mapa extends JPanel {
	static ArrayList<Mesto>poleMest = new ArrayList<Mesto>();
	static Random r = new Random();
	public static int width;
	public static int height;
	
	public static Letiste [] poleLetist = new Letiste [5]; //neni treba arraylist, pocet letist se nemeni
	public static Random R = new Random();

	/**
	 * Konstruktor tridy mapa 
	 * @param sirka
	 * @param vyska
	 */
	public Mapa (int sirka, int vyska)
	{
		this.setSize(sirka, vyska);
		this.setPreferredSize(this.getSize());
		this.width = sirka;
		this.height = vyska;
		generujLetiste();
		generujMesta();
	}
	/**
	 * zakladni kreslici metoda
	 */
	public void paint (Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		kresliMapu(g2, new Rectangle(0, 0, width, height));
	}

	/**
	 * Dale rozvedena kreslici metoda, kresli pomoci Graphics2D
	 * @param g2
	 * @param drawingArea
	 */
	public void kresliMapu(Graphics2D g2, Rectangle drawingArea)
	{
		// vymazani platna
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);

		g2.setStroke(new BasicStroke(2));
		
		//vykresleni centralni oblasti
		g2.setColor(Color.GREEN);
		g2.drawRect(225, 225, 50, 50);
		
		g2.setColor(Color.BLACK);
		//kresleni mest
		for(int i = 0; i<poleMest.size()-1; i++)
		{
			g2.fillRect(poleMest.get(i).getX(), poleMest.get(i).getY(), 2, 2);
		}
		//kresleni letist
		g2.setColor(Color.RED);
		
		for(int i = 0; i<poleLetist.length; i++)
		{
			g2.fillRect(poleLetist[i].getX(), poleLetist[i].getY(), 5, 5);
		}
	}
	
	/**
	 * Metoda vytvarejici souradnice pro letiste
	 * Vytvorene letiste rovnou uklada do pole
	 */
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
				obyv = (int) (stredniHodnota + rozptyl * r.nextGaussian());//vypocet obyvatel pro mesto
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
				obyv = (int) (stredniHodnota + rozptyl * r.nextGaussian());
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

}
