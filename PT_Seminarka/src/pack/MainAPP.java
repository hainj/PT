package pack;

import javax.swing.JFrame;
import java.util.*;
public class MainAPP extends JFrame {

	//static JFrame frame = new JFrame();
	static Mapa drawarea = new Mapa (500 , 500);
	static Scanner sc = new Scanner (System.in);
	
	//public int LIDI = 15000000;
	public static void main(String[] args) {
		MainAPP frame = new MainAPP();
		frame.setTitle("Postapokalipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawarea);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		System.out.println("Pojmenuj vystup: ");
		String vystup = sc.next();
		cteni.vystupMapa(Mapa.poleMest, Mapa.poleLetist, vystup);
		

	}

}
