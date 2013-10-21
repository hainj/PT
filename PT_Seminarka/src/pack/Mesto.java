package pack;



public class Mesto {

		private int obyvatel = 0;
	

		private int x = 0;
		private int y = 0;
		private Mesto[]sousedi;
		
	
		public Mesto(int x, int y, int obyvatel)
		{
			this.x = x;
			this.y = y;
			this.obyvatel = obyvatel;
		}
		
		
		public Mesto(int obyvatel, int x, int y, Mesto[] sousedi) {
			super();
			this.obyvatel = obyvatel;
			this.x = x;
			this.y = y;
			this.sousedi = sousedi;
		}
		
		
		public Mesto[] getSousedi() {
			return sousedi;
		}
		
		public void setSousedi(Mesto[] sousedi) {
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
