package pack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
public class MainAPP extends JFrame {

	final static JFrame parent = new JFrame();
	private final static String novaRadka = "\n";
	static JPanel tlacitka = new JPanel();
	static JTextArea textBlok = new JTextArea(20,20);
	static Mapa drawarea = new Mapa(700,550, false); //vytvori drawing areu o velikosti 500 x500
	static Scanner sc = new Scanner (System.in);
	static Random R = new Random();

	public static void main(String[] args) {
		final MainAPP frame = new MainAPP();
		frame.setTitle("Postapokalipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(750, 600));
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
					cteni.vystupMapa(Mapa.getPoleMest(), Mapa.getPoleLetist(), souborF,Generator.indexMestPod2);
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
				   try{
				    String str = f.getAbsolutePath();
				    System.out.println(str);
				    }
				   catch(NullPointerException except){
					   JOptionPane.showConfirmDialog(chooser, "Nevybrana zadna slozka", "Chooser", JOptionPane.DEFAULT_OPTION);
				   }
				   

				new Simulace(textBlok,Mapa.getPoleMest(), Mapa.getPoleLetist());				
			}
		});
	}



	public static int generujSour ()
	{
		int x = R.nextInt(500);
		return x;
	}

}
