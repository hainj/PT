package pack;


public class Vzdalenost{
	
	public int index = 0;
	public double vzdalenost = 0;
	private Mesto mesto;
	private Letiste let;
	
	public Vzdalenost (int index, double vzd)
	{
		this.index = index;
		this.vzdalenost = vzd;
	}
	
	public Vzdalenost (Mesto mesto, Letiste let, double vzd)
	{
		this.mesto = mesto;
		this.let = let;
		this.vzdalenost = vzd;
	}
	
	public Vzdalenost(Mesto mesto, double vzd) {
		// TODO Auto-generated constructor stub
		this.mesto = mesto;
		this.vzdalenost = vzd;
	}

	/**
	 * @return the mesto
	 */
	public Mesto getMesto() {
		return mesto;
	}
	
	/**
	 * @param mesto the mesto to set
	 */
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	/**
	 * @return the let
	 */
	public Letiste getLet() {
		return let;
	}
	
	/**
	 * @param let the let to set
	 */
	public void setLet(Letiste let) {
		this.let = let;
	}
}
