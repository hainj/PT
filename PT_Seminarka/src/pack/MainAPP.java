package pack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainAPP extends JFrame {
	
	private static boolean pause;
	/**
	 * @return the pause
	 */
	public static boolean isPause() {
		return pause;
	}



	/**
	 * @param pause the pause to set
	 */
	public static void setPause(boolean pause) {
		MainAPP.pause = pause;
	}



	private static Thread sim;
	private final static JFrame parent = new JFrame();
	private final static String novaRadka = "\n";
	private static JPanel tlacitka = new JPanel();
	private static JTextArea textBlok = new JTextArea(20,20);
	private static Mapa drawarea = new Mapa(700,550, false); //vytvori drawing areu o velikosti 500 x500
	private static Scanner sc = new Scanner (System.in);
	private static Random R = new Random();

	public static void main(String[] args) {
		setPause(false);
		
		
		final MainAPP frame = new MainAPP();
		frame.setTitle("Postapokalipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(750, 600));
		frame.add(drawarea);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


		JButton tlacitkoSave = new JButton ("Save");
		JButton tlacitkoLoad = new JButton ("Load");
		JButton tlacitkoNew = new JButton ("Nova mapa");
		JButton tlacitkoStart = new JButton ("Start");
		JButton tlacitkoExit = new JButton ("Exit");
		JButton tlacitkoNewCiti = new JButton ("Nove mesto");
		JButton tlacitkoPauza = new JButton ("Pauza");

		tlacitka.add(tlacitkoStart);
		tlacitka.add(tlacitkoPauza);
		tlacitka.add(tlacitkoNew);
		tlacitka.add(tlacitkoNewCiti);
		tlacitka.add(tlacitkoSave);
		tlacitka.add(tlacitkoLoad);
		tlacitka.add(tlacitkoExit);
	
		
		
		textBlok.setColumns(20);
		//textBlok.setBounds(515, 0, 40, 500);
		JScrollPane textScroller = new JScrollPane();
		//System.out.println(textBlok.getBounds().x);
		textBlok.setLineWrap(true);
		textBlok.setWrapStyleWord(true);
		textScroller.setViewportView(textBlok);
		frame.add(tlacitka,BorderLayout.SOUTH);
		frame.repaint(); 
		
		frame.add(textScroller,BorderLayout.EAST);
		textBlok.repaint();
		tlacitka.repaint();



		//tlacitko na vytvareni nove mapy
		tlacitkoNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Mapa.getPoleLetist().clear();
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

					//pop up okno na nacitani poctu obyvatel novemu mestu

					int obyv = 0;
					while(true)
					{
						try
						{
							String ob = JOptionPane.showInputDialog(parent, "Zadej pocit obyvatel: ");
							obyv = Integer.parseInt(ob);
							break;
						}catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(parent, "Prosim zadejte cislo");
						}
					}


					Mapa.getPoleMest().add(new Mesto(x,y,obyv,true));

					index = Mapa.getPoleMest().size()-1;

					Mapa.getPoleMest().get(index).setObyvatel(obyv);
					Mapa.getPoleMest().get(index).setHeliport(false);
					Generator.mestoBezSousVzdal(Mapa.getPoleMest().get(index), Mapa.getPoleMest());
					//drawarea.repaint();
					frame.repaint();
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
					//Cteni cteni = null;
					Cteni.vystupMapa(Mapa.getPoleMest(), Mapa.getPoleLetist(), souborF,Generator.indexMestPod2);
				}
			}
		});

		//tlacitko na nacitani mapy - poloha mest a letist
		tlacitkoLoad.addActionListener(new ActionListener()
		{
			

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Mapa.getPoleLetist().clear();
				Mapa.getPoleMest().clear();
				JFileChooser filec = new JFileChooser(); 

				filec.showOpenDialog(null);
				File souborF = filec.getSelectedFile(); 
				if(souborF != null){ 
					System.out.println(souborF.getAbsolutePath());
					//Cteni cteni = null;
					Cteni.vstupMapa(souborF);
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
		tlacitkoStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				  JFileChooser chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle("Slozka kam budou ulozeny logy");
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    chooser.showOpenDialog(null);
				  
				    File f = chooser.getSelectedFile();
				    String str = null;
				   try{
				    str = f.getAbsolutePath();
				    System.out.println(str);
				    }
				   catch(NullPointerException except){
					   JOptionPane.showConfirmDialog(chooser, "Nevybrana zadna slozka", "Chooser", JOptionPane.DEFAULT_OPTION);
					   return;
				   }
				   
				   sim = new Thread(new Simulace(textBlok,Mapa.getPoleMest(), Mapa.getPoleLetist(), str));
				   sim.start();
				//new Simulace(textBlok,Mapa.getPoleMest(), Mapa.getPoleLetist(), str);				
			}
		});
		
		tlacitkoPauza.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pause){
					pause = false;
				}
				else{
					pause = true;
				}
			}
		});
	}



	public static int generujSour ()
	{
		int x = R.nextInt(500);
		return x;
	}

}
