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
	static ArrayList<Mesto>poleMest = new ArrayList<Mesto>();;
	public static int width;
	public static int height;
	public static Letiste [] poleLetist;
	static Boolean ovladani = false;

	/**
	 * Konstruktor tridy mapa 
	 * @param sirka
	 * @param vyska
	 */
	public Mapa (int sirka, int vyska, Boolean ovladani)
	{
		this.setSize(sirka, vyska);
		this.setPreferredSize(this.getSize());
		this.width = sirka;
		this.height = vyska;
		this.ovladani = ovladani;
	}
	
	public void setMesta(ArrayList<Mesto> poleMest)
	{
		this.poleMest = poleMest;
	}
	public void setLetiste(Letiste [] poleLetist)
	{
		this.poleLetist = poleLetist;
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
		
		g2.drawRect(502, 0, 700, 550);
		
		if(this.ovladani == true)
		{
			if(poleMest.size() == 0)
			{
				Generator mapa = new Generator();
				this.setLetiste(mapa.poleLetist);
				this.setMesta(mapa.poleMest);
			}

			
			//kresleni mest
			g2.setColor(Color.BLACK);
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
		else
		{
			//cteni.vstupMapa("ZakladniMapa.txt");//////////////TUTO/////////////////
			
			//kresleni mest
			g2.setColor(Color.BLACK);
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
		
	}
	
	/**
	 * Metoda vytvarejici souradnice pro letiste
	 * Vytvorene letiste rovnou uklada do pole
	 */
	
}
