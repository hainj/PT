import javax.swing.JFrame;

public class MainAPP extends JFrame {

	//static JFrame frame = new JFrame();
	static Mapa drawarea = new Mapa (500 , 500);
	
	//public int LIDI = 15000000;
	public static void main(String[] args) {
		MainAPP frame = new MainAPP();
		frame.setTitle("Postapokalipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawarea);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}
