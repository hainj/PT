



package pack;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

@SuppressWarnings("serial")
public class Mapa extends JPanel {

	public static List<Mesto>poleMest = new ArrayList<>();
	public int width;
	public int height;
	public static List<Letiste>poleLetist = new ArrayList<>();
	static Boolean ovladani = false;
	

	/**
	 * @param ovladani the ovladani to set
	 */
	public static void setOvladani(Boolean ovladani) {
		Mapa.ovladani = ovladani;
	}
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

		Mapa.ovladani = ovladani;
	}
	/**
	 * Vraci pole letist
	 * @return pole letist
	 */
	public static List<Letiste> getPoleLetist() {
		return poleLetist;
	}

	/**
	 * Prida mesto do seznamu poli
	 * @param mesto
	 */
	public static void addPoleMest(Mesto mesto) {
		poleMest.add(mesto);
	}

	/**
	 * vraci index mesta v poliMest
	 * @param mest ktereho index zjistuji
	 * @return index mesta v poli
	 */
	public static int getIndexMest(Mesto mest){

		for(int i = 0; i<poleMest.size(); i++){
			if(mest.equals(poleMest.get(i))){

				return i;
			}
		}
		return -1;
	}

	public static List<Mesto> getPoleMest() {
		return poleMest;
	}

	public static void setMesta(List<Mesto> poleMest)
	{
		Mapa.poleMest = poleMest;
	}

	public static void setLetiste(List<Letiste> poleLetiste)
	{
		Mapa.poleLetist = poleLetiste;
	}

	/**
	 * zakladni kreslici metoda
	 */
	public void paint (Graphics g)
	{
		//super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		kresliMapu(g2, new Rectangle(0, 0, this.width, this.height));
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
		g2.fillRect(0, 0, this.width, this.height);

		g2.setStroke(new BasicStroke(2));

		//vykresleni centralni oblasti
		g2.setColor(Color.GREEN);
		g2.drawRect(225, 225, 50, 50);

		g2.setColor(Color.BLACK);

		//g2.drawRect(502, 0, 700, 550);
		System.out.println(ovladani);
		//ovladaci promenna - true program uz bezi a ma mapu nebo se spustil a je prazdny
		if(Mapa.ovladani)
		{
			//jeli spusteny program prazdny vygeneruje novou mapu
			if(poleMest.size()==0){
				System.out.println("aaaaaaaa");
				new Generator();
			}

			kresleni(g2);
		}
		//ovladaci promenna - false pri spusteni nacte predpripravenou mapu
		else
		{
			Cteni.vstupMapa(new File("ZakladniMapa.txt"));
			Generator generator = new Generator(poleMest, poleLetist);

			kresleni(g2);
		}

	}

	/**
	 * metoda vykreslujici mesta letiste a cesty
	 * zabranuje opakovani kodu
	 * @param g2
	 */
	public static void kresleni(Graphics2D g2)
	{
		//System.out.println("kresl");
		//vykresleni cest mezi mesty
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLUE);
		int indexMestPod2 = Generator.zjistiPod2(poleMest);

		for(int i = indexMestPod2+1; i <3000; i++)
		{
			for (int j = 0; j <10; j++)
			{
				//System.out.println("kresl");
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
				//System.out.println("kresl");
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
}
