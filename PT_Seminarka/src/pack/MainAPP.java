package pack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
public class MainAPP extends JFrame {

	final static JFrame parent = new JFrame();

	static JPanel tlacitka = new JPanel();
	static JPanel textBlok = new JPanel();
	static Mapa drawarea = new Mapa(700,550, false); //vytvori drawing areu o velikosti 500 x500
	static Scanner sc = new Scanner (System.in);
	static Random R = new Random();

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
		
		JTextArea dialogOkno = new JTextArea();
		textBlok.add(dialogOkno);
		frame.add(textBlok,BorderLayout.EAST);
		tlacitka.repaint();
		
		
		
		//tlacitko na vytvareni nove mapy
		tlacitkoNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Mapa.getPoleMest().clear();
				drawarea = new Mapa (500 , 500, true);
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
				if(Mapa.getPoleMest().size()!=0)
				{
					int x = 0;
					int y = 0;
					int index = Mapa.getPoleMest().size()-1;
					do
					{
						x = generujSour();
						y = generujSour();
					}while(Generator.porovnejMesta (x,y,index) == true);
					int obyv = (int) (5400 + 1600 * R.nextGaussian());
					Mapa.getPoleMest().add(new Mesto(x,y,obyv,true));
	
					index = Mapa.getPoleMest().size()-1;
					Mapa.getPoleMest().get(index).setObyvatel(obyv);
	
					//drawarea.repaint();
					frame.repaint();
	
					/*System.out.println("nove mesto: "+ Mapa.getPoleMest().get(index).getX() +" "
							+ Mapa.getPoleMest().get(index).getY() + " "
							+ Mapa.getPoleMest().get(index).getObyvatel());*/
				}
				else
				{
					JOptionPane.showMessageDialog(parent, "Neni nactena zadna mapa");
				}
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
				 cteni.vystupMapa(Mapa.getPoleMest(), Mapa.getPoleLetist(), souborF);
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
				Mapa.getPoleMest().clear();
				JFileChooser filec = new JFileChooser(); 

				filec.showOpenDialog(null);
				File souborF = filec.getSelectedFile(); 
				if(souborF != null){ 
					System.out.println(souborF.getAbsolutePath());
					cteni.vstupMapa(souborF);
				}
				
				drawarea = new Mapa (500 , 500, true);
				drawarea.repaint();
				frame.repaint();
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
	
	public static int generujSour ()
	{
		int x = R.nextInt(500);
		return x;
	}

}
