package pack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
public class MainAPP extends JFrame {

	final static JFrame parent = new JFrame();

	static JPanel tlacitka = new JPanel();
	static Mapa drawarea = new Mapa (500 , 550); //vytvori drawing areu o velikosti 500 x500
	static Scanner sc = new Scanner (System.in);

	public static void main(String[] args) {
		final MainAPP frame = new MainAPP();
		frame.setTitle("Postapokalipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawarea);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JButton tlacitkoSave = new JButton ("save");
		JButton tlacitkoLoad = new JButton ("load");
		JButton tlacitkoNew = new JButton ("nova mapa");
		JButton tlacitkoStart = new JButton ("start");
		JButton tlacitkoExit = new JButton ("exit");
		JButton tlacitkoNewCiti = new JButton ("nove mesto");

		tlacitka.add(tlacitkoStart);
		tlacitka.add(tlacitkoNew);
		tlacitka.add(tlacitkoNewCiti);
		tlacitka.add(tlacitkoSave);
		tlacitka.add(tlacitkoLoad);
		tlacitka.add(tlacitkoExit);

		frame.add(tlacitka,BorderLayout.SOUTH);
		tlacitka.repaint();
		//tlacitko na vytvareni nove mapy
		tlacitkoNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Mapa.poleMest.clear();
				drawarea = new Mapa (500 , 500);
				drawarea.repaint();
				frame.repaint();
			}
		});

		//tlacitko na vytvareni noveho mesta
		tlacitkoNewCiti.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				int x = 0;
				int y = 0;
				int index = Mapa.poleMest.size()-1;
				do
				{
					x = Mapa.generujSour();
					y = Mapa.generujSour();
				}while(Mapa.porovnejMesta (x,y,Mapa.poleMest.size()) == true);
				int obyv = (int) (5400 + 1600 * Mapa.r.nextGaussian());
				Mapa.poleMest.add(new Mesto(x,y,obyv));

				
				Mapa.poleMest.get(index).setObyvatel(obyv);

				drawarea.repaint();
				frame.repaint();

				System.out.println("nove mesto: "+ Mapa.poleMest.get(index).getX() +" "
						+ Mapa.poleMest.get(index).getY() + " "
						+ Mapa.poleMest.get(index).getObyvatel());

			}
		});

		//tlacitko na ukladani mapy - poloha mest a letist
		tlacitkoSave.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
				JFileChooser filec = new JFileChooser(); 

				filec.showSaveDialog(null);
				File souborF = filec.getSelectedFile(); 
				if(souborF != null){ 
				 cteni.vystupMapa(Mapa.poleMest, Mapa.poleLetist, souborF);
			}
			}
			});

		//tlacitko na nacitani mapy - poloha mest a letist
		tlacitkoLoad.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//vyskoci popup okno kde napisu jmeno vystupniho souboru
				/*	String vstup = JOptionPane.showInputDialog(parent,"Jmeno vstupniho souboru",null);
				if (vstup == null) JOptionPane.showMessageDialog(parent, "Mapa se nenacetla");
				else 
					cteni.vstupMapa();*/
				JFileChooser filec = new JFileChooser(); 

				filec.showOpenDialog(null);
				File souborF = filec.getSelectedFile(); 
				if(souborF != null){ 
					System.out.println(souborF.getAbsolutePath());
					cteni.vstupMapa(souborF);
				}

			}
		});

		//tlacitko ukoncujici program
		tlacitkoExit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});

	}

}
