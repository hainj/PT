                                                                     
                                                                     
                                                                     
                                             
package pack;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mapa extends JPanel {
	
	private static ArrayList<Mesto>poleMest = new ArrayList<Mesto>();
	public static int width;
	public static int height;
	private static ArrayList<Letiste>poleLetist = new ArrayList<Letiste>();
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
	/**
	 * Vraci pole letist
	 * @return pole letist
	 */
	public static ArrayList<Letiste> getPoleLetist() {
		return poleLetist;
	}
	
	/**
	 * Prida mesto do seznamu poli
	 * @param mesto
	 */
	public static void addPoleMest(Mesto mesto) {
		poleMest.add(mesto);
	}
	
	public static int getIndexMest(Mesto mest){

		for(int i = 0; i<poleMest.size(); i++){
			if(mest.equals(poleMest.get(i))){

				return i;
			}
		}

		return -1;

	}
	public static ArrayList<Mesto> getPoleMest() {
		return poleMest;
	}

	public void setMesta(ArrayList<Mesto> poleMest)
	{
		this.poleMest = poleMest;
	}
	public static void setLetiste(ArrayList<Letiste> poleLetiste)
	{
		poleLetist = poleLetiste;
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
			if(poleMest.size()==0){
				Generator gen = new Generator();
				this.setLetiste(gen.poleLetist);
				this.setMesta(gen.poleMest);
			}
			//vykresleni cest mezi mesty
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.BLUE);

			int indexMestPod2 = Generator.zjistiPod2(poleMest);
			
			for(int i = indexMestPod2+1; i <3000; i++)
			{
				for (int j = 0; j <10; j++)
				{
					g2.drawLine(poleMest.get(i).getX(), poleMest.get(i).getY(),
							poleMest.get(i).getSousedi().get(j).getX(), 
							poleMest.get(i).getSousedi().get(j).getY());
				}
			}

			//kresleni cest z letist
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.RED);

			for(int i = 0; i <poleLetist.size(); i++)
			{
			//	System.out.println("ahoj " + poleLetist.get(i).getSousedi().size());
				for (int j = 0; j <60; j++)
				{	
					//System.out.println(j);
					g2.drawLine(poleLetist.get(i).getX(), poleLetist.get(i).getY(),
							poleLetist.get(i).getSousedi().get(j).getX(), 
							poleLetist.get(i).getSousedi().get(j).getY());
				}
			}
			
			//kresleni mest
			g2.setColor(Color.BLACK);
			for(int i = 0; i<poleMest.size()-1; i++)
			{
				g2.fillRect(poleMest.get(i).getX()-1, poleMest.get(i).getY()-1, 3, 3);
			}
			//kresleni letist
			g2.setColor(Color.RED);

			for(int i = 0; i<poleLetist.size(); i++)
			{
				g2.setColor(Color.RED);
				g2.fillRect(poleLetist.get(i).getX()-2, poleLetist.get(i).getY()-2, 5, 5);
				g2.setColor(Color.BLACK);
				g2.drawRect(poleLetist.get(i).getX()-2, poleLetist.get(i).getY()-2, 5, 5);
			}
		}
		else
		{
			/*cteni.vstupMapa(new File("ZakladniMapa.txt"));

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
			 */
		}

	}


	/**
	 * Metoda vytvarejici souradnice pro letiste
	 * Vytvorene letiste rovnou uklada do pole
	 */

}
