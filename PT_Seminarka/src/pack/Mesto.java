                                                                     
                                                                     
                                                                     
                                             
package pack;

import java.util.ArrayList;



public class Mesto {

		private int obyvatel = 0;
		private int x = 0;
		private int y = 0;
		private ArrayList<Mesto> sousedi;
		private boolean heliport;
		


	/**
	 * Konstruktor mest pro jeho polohu a pocet obyvatel
	 * @param x x-ova souradnice
	 * @param y y-ova souradnice
	 * @param obyvatel pocet obyv
	 */
		public Mesto(int x, int y, int obyvatel, boolean heli)
		{
			this.x = x;
			this.y = y;
			this.obyvatel = obyvatel;
			this.heliport = heli;
		}
		
		/**
		 *  Konstruktor mesta s polohou, poctem obyvatel a jeho sousedy
		 * @param x x-ova souradnice
		 * @param y y-ova souradnice
		 * @param obyvatel pocet obyv
		 * @param sousedi pole sousedu mesta
		 */
		public Mesto( int x, int y, int obyvatel,ArrayList<Mesto> sousedi) {
			super();
			this.obyvatel = obyvatel;
			this.x = x;
			this.y = y;
			this.sousedi = sousedi;
		}
		
		public boolean getHeliport() {
			return heliport;
		}

		public void setHeliport(boolean heliport) {
			this.heliport = heliport;
		}
		
		public ArrayList<Mesto> getSousedi() {
			return sousedi;
		}
		
		public void setSousedi(ArrayList<Mesto> sousedi) {
			this.sousedi = sousedi;
		}
		
		public int getObyvatel() {
			return obyvatel;
		}

		public void setObyvatel(int obyvatel) {
			this.obyvatel = obyvatel;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.x = y;
		}
		
		
}
