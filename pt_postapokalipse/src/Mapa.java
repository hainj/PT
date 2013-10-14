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

	static Random r = new Random();
	public static int width;
	public static int height;
	public static Mesto [] poleMest = new Mesto [3000];
	public static Letiste [] poleLetist = new Letiste [5];
	public static Random R = new Random();


	public Mapa(int sirka, int vyska) 
	{
		this.setSize(sirka, vyska);
		this.setPreferredSize(this.getSize());
		this.width = sirka;
		this.height = vyska;
		generujLetiste();
		generujMesta();
		//for (int i= 0; i < poleMest.length; i++)
		//{
		///	System.out.println(poleMest[i].getX() + " "+ " " +poleMest[i].getY());
		//}
	}

	public void paint (Graphics g)
	{
		
		
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		kresliMapu(g2, new Rectangle(0, 0, width, height));
	}

	public void kresliMapu(Graphics2D g2, Rectangle drawingArea)
	{
		// vymazani platna
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);

		g2.setStroke(new BasicStroke(2));
		

		g2.setColor(Color.GREEN);
		g2.drawRect(225, 225, 50, 50);
		
		g2.setColor(Color.BLACK);
		//kresleni mest
		for(int i = 0; i<poleMest.length; i++)
		{
			//g2.drawOval(poleMest[i].getX(), poleMest[i].getY(), 1, 1);
			//g2.fillOval(poleMest[i].getX(), poleMest[i].getY(), 3, 3);
			g2.fillRect(poleMest[i].getX(), poleMest[i].getY(), 2, 2);
		}
		//kresleni letist
		g2.setColor(Color.RED);
		
		for(int i = 0; i<poleLetist.length; i++)
		{
			//g2.drawOval(poleMest[i].getX(), poleMest[i].getY(), 1, 1);
			//g2.fillOval(poleMest[i].getX(), poleMest[i].getY(), 3, 3);
			g2.fillRect(poleLetist[i].getX(), poleLetist[i].getY(), 5, 5);
		}
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

	public static void generujMesta ()
	{
		int x = 0;
		int y = 0;
		int stredniHodnota = 4950;
		int rozptyl = 1300;
		int obyv = 0;
		
		int obyvCelkem = 0;
		
		x = generujSour();
		y = generujSour();
		//poleMest[0]= new Mesto(x,y);
		for(int i = 0; i <poleMest.length; i++)
		{
			if (i==0)
			{
				x = generujSour();
				y = generujSour();
				obyv = (int) (stredniHodnota + rozptyl * r.nextGaussian());
				poleMest[i]= new Mesto(x,y,obyv);
				System.out.println(poleMest[i].getObyvatel());
				obyvCelkem +=poleMest[i].getObyvatel();
			}
			while(porovnejMesta (x,y,i)== true)
			{
				x = generujSour();
				y = generujSour();
				poleMest[i]= new Mesto(x,y);				
			}
			obyv = (int) (stredniHodnota + rozptyl * r.nextGaussian());
			poleMest[i].setObyvatel(obyv);
			obyvCelkem +=poleMest[i].getObyvatel();
			System.out.println((i+1)+". mesto ma: "+ poleMest[i].getObyvatel()+ " obyvatel");
			//System.out.println("mesto v poradi: "+i+". " + poleMest[i].getX() + " "+ " " +poleMest[i].getY());
		}
		System.out.println("Celkem obyv: "+ obyvCelkem);
	}

	public static int generujSour ()
	{
		int x = R.nextInt(500);
		return x;
	}
	
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
	
	public static boolean porovnejMesta (int x, int y, int j)
	{
		//int vysledekX = 0;
		//int vysledekY = 0;
		double vysledekMesto = 0.0;
		double vysledekLetiste = 0.0;
		for (int i = 0; i < 5; i++)
		{
			vysledekLetiste = Math.sqrt(Math.pow(x - poleLetist[i].getX(), 2)+ Math.pow(y - poleLetist[i].getY(), 2));
			if (vysledekLetiste < 5.0) return true;
		}
		for (int i = 0; i < j; i++)
		{
			vysledekMesto = Math.sqrt(Math.pow(x - poleMest[i].getX(), 2)+ Math.pow(y - poleMest[i].getY(), 2));
			if (vysledekMesto < 5.0) return true;
		}
		return false;
	}
}
