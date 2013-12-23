package pack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import java.util.concurrent.CancellationException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Hlavni trida vytvari tlacitka a vola vse dulezite
 * @author Jakub Hain
 *
 */
public class MainAPP extends JFrame {

	
	
	/**
	 * pauza simulace
	 */
	static boolean pause;
	/**getr pause
	 * @return pause
	 */
	public static boolean isPause() {
		return pause;
	}



	/**Setr pause
	 * @param pause pauza simulace
	 */
	public static void setPause(boolean pause) {
		MainAPP.pause = pause;
	}



	static Thread sim;
	final static JFrame parent = new JFrame();
	private static JPanel tlacitka = new JPanel();
	static Mapa drawarea = new Mapa(700,550, false); //vytvori drawing areu o velikosti 500 x500
	private static Random r = new Random();

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

		JButton tlacitkoInfo = new JButton("Stat mest/aut/vrt");
		JButton tlacitkoSave = new JButton ("Save");
		JButton tlacitkoLoad = new JButton ("Load");
		JButton tlacitkoNew = new JButton ("Nova mapa");
		JButton tlacitkoStart = new JButton ("Start");
		JButton tlacitkoExit = new JButton ("Exit");
		JButton tlacitkoNewCiti = new JButton ("Nove mesto");
		JButton tlacitkoPauza = new JButton ("Pauza");

		tlacitka.add(tlacitkoStart);
		tlacitka.add(tlacitkoPauza);
		tlacitka.add(tlacitkoInfo);
		tlacitka.add(tlacitkoNew);
		tlacitka.add(tlacitkoNewCiti);
		tlacitka.add(tlacitkoSave);
		tlacitka.add(tlacitkoLoad);
		tlacitka.add(tlacitkoExit);




		frame.add(tlacitka,BorderLayout.SOUTH);
		frame.repaint(); 

		tlacitka.repaint();


		tlacitkoInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] moznosti = new String[] {"Auto", "Vrtulnik", "Mesto", "Cancel"};
				int mozn = JOptionPane.showOptionDialog(null, "Statistiky", "Vyberte o cem chcete neco vedet", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, moznosti, moznosti[0]);
				if(mozn ==2){
					int index = 0;
					try{	
						String ob = JOptionPane.showInputDialog(parent, "Vyberte mesto, o kterém chcete vìdìt(0 do" + (Mapa.poleMest.size()-1)+ "): ");
						index = Integer.parseInt(ob);
						Mesto mesto = Mapa.poleMest.get(index);
						JOptionPane.showMessageDialog(parent, mesto.vypisMesto(index));
					}catch(NumberFormatException exc)
					{
						JOptionPane.showMessageDialog(parent, "Prosim zadejte cislo");
					}

				}
				if(mozn ==1){
					int index = 0;
					try{	
						String ob = JOptionPane.showInputDialog(parent, "Vyberte vrtulnik, o kterém chcete vìdìt(0 do" + (Simulace.getVrtulniky().size()-1)+ "): ");
						index = Integer.parseInt(ob);
						Vrtulnik vrt = Simulace.getVrtulniky().get(index);
						JOptionPane.showMessageDialog(parent, vrt.vypisVrt(index));
					}catch(NumberFormatException exc)
					{
						JOptionPane.showMessageDialog(parent, "Zadejte prosim cislo");
					}

				}
				if(mozn == 0){
					int index = 0;
					try{
						String ob = JOptionPane.showInputDialog(parent, "Vyberte auto, o kterém chcete vìdìt(0 do" + (Simulace.getAuta().size()-1)+ "): ");
						index = Integer.parseInt(ob);
						Auto auto = Simulace.getAuta().get(index);
						JOptionPane.showMessageDialog(parent, auto.vypisAuto(index));
					}catch(NumberFormatException exc)
					{
						JOptionPane.showMessageDialog(parent, "Prosim zadejte cislo");
					}

				}
			}
		});



		//tlacitko na vytvareni nove mapy
		tlacitkoNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("aaaaa");
				Mapa.getPoleLetist().clear();
				Mapa.getPoleMest().clear();
				//drawarea = new Mapa (500 , 500, true);
				Mapa.setOvladani(true);
				//new Generator();
				//System.out.println(Mapa.poleLetist.size() + "   " + Mapa.poleMest.size());
				//Mapa.kresleni(Mapa.g2);
				drawarea.repaint();
				frame.add(drawarea);

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
					int index = Mapa.getPoleMest().size();
					do
					{
						x = generujSour();
						y = generujSour();
					}while(Generator.porovnejMesta (x,y,index));

					//pop up okno na nacitani poctu obyvatel novemu mestu

					int obyv = 0;
					
						try
						{
							String ob = JOptionPane.showInputDialog(parent, "Zadej pocit obyvatel(max 2000): ");
							System.out.println(ob);
							obyv = Integer.parseInt(ob);
							if(obyv > 2000 || obyv <=0 ){
								System.out.println("Zadali jste vice nebo mene nez 2000 obyv");
							}
							else{
								Mapa.poleMest.add(new Mesto(x,y,obyv,true));
								System.out.println(Mapa.getPoleMest().size());
								index = Mapa.getPoleMest().size()-1;
								Mapa.setOvladani(true);
								Mapa.getPoleMest().get(index).setObyvatel(obyv);
								Mapa.getPoleMest().get(index).setHeliport(false);
								Generator.mestoBezSousVzdal(Mapa.getPoleMest().get(index), Mapa.getPoleMest());
								JOptionPane.showMessageDialog(parent, "Pridano mesto");

							}
							
						}catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(parent, "Prosim priste zadejte cislo");
							
						}
						catch(CancellationException e){
							JOptionPane.showMessageDialog(parent, "Tak zadny mesto");
						}
					

					
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
				
				JFileChooser filec = new JFileChooser(); 

				filec.showOpenDialog(null);
				File souborF = filec.getSelectedFile(); 
				if(souborF != null){ 
					Mapa.getPoleLetist().clear();
					Mapa.getPoleMest().clear();
					System.out.println(souborF.getAbsolutePath());
					//Cteni cteni = null;
					Cteni.vstupMapa(souborF);
				}

				//drawarea = new Mapa (500 , 500, true);
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
		//simulace tlac
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
					sim = new Thread(new Simulace(Mapa.getPoleMest(), Mapa.getPoleLetist(), str));
					sim.start();

				}
				catch(NullPointerException except){
					JOptionPane.showConfirmDialog(chooser, "Nevybrana zadna slozka", "Chooser", JOptionPane.DEFAULT_OPTION);
					return;
				}


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


	/**
	 * generuje nahodne cislo
	 * @return nahodne cislo
	 */
	public static int generujSour ()
	{
		int x = r.nextInt(500);
		return x;
	}

}
