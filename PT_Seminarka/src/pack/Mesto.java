package pack;

public class Mesto {

		public int obyvatel = 0;
		public int X = 0;
		public int Y = 0;
		
		public Mesto(int x, int y)
		{
			this.X = x;
			this.Y = y;
		}
		
		public Mesto (int x, int y, int obyvatel)
		{
			this.X = x;
			this.Y = y;
			this.obyvatel = obyvatel;
		}

		public int getObyvatel() {
			return obyvatel;
		}

		public void setObyvatel(int obyvatel) {
			this.obyvatel = obyvatel;
		}

		public int getX() {
			return X;
		}

		public void setX(int x) {
			X = x;
		}

		public int getY() {
			return Y;
		}

		public void setY(int y) {
			Y = y;
		}
		
		
}
