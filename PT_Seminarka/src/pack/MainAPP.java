package pack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class MainAPP extends JFrame {

	final static JFrame parent = new JFrame();
	
	static JPanel tlacitka = new JPanel();
	static Mapa drawarea = new Mapa (500 , 550); //vytvori drawing areu o velikosti 500 x500
	static Scanner sc = new Scanner (System.in);
	
	public static void main(String[] args) {
		MainAPP frame = new MainAPP();
		frame.setTitle("Postapokalipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawarea);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JButton tlacitkoSave = new JButton ("save");
		JButton tlacitkoLoad = new JButton ("load");
		JButton tlacitkoNew = new JButton ("nova mapa");
		JButton tlacitkoStart = new JButton ("start simulace");
		JButton tlacitkoExit = new JButton ("exit");
		
		tlacitka.add(tlacitkoStart);
		tlacitka.add(tlacitkoNew);
		tlacitka.add(tlacitkoSave);
		tlacitka.add(tlacitkoLoad);
		tlacitka.add(tlacitkoExit);

		frame.add(tlacitka,BorderLayout.SOUTH);
		
		//tlacitko na vytvareni nove mapy
		tlacitkoNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		//tlacitko na ukladani mapy - poloha mest a letist
		tlacitkoSave.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//vyskoci popup okno kde napisu jmeno vystupniho souboru
				String vystup = JOptionPane.showInputDialog(parent,"Jmeno vystupniho souboru",null);
				cteni.vystupMapa(Mapa.poleMest, Mapa.poleLetist, vystup);
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
