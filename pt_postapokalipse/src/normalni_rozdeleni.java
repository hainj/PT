import java.util.*;
public class normalni_rozdeleni {
	static Scanner sc = new Scanner (System.in);
	static Random r = new Random();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double  [] mesto = new double [3000];
		int [] mesta = new int [3000];
		int mean = 5000;
		int std = 1300;
		int vysledek = 0;
		for (int i = 0; i<3000; i++)
		{
			mesto[i] = mean + std * r.nextGaussian();
			
			mesta [i] = (int)(mesto[i]);
			//System.out.println(mesta[i]);
			vysledek = vysledek + mesta [i];
			
			
			
		}

		Quick.serad(mesta);
		for (int i = 0; i < 3000; i++)
		{
			System.out.println(i+". mesto: "+mesta[i]);
		}
		System.out.println("Celkem lidi: "+vysledek);
	}

}



    class Quick {
	static int por;
	static int vym;
	
	static int [] serad (int [] pole)
	{
		por = 0;
		vym = 0;
		 quicksort(pole, 0, pole.length - 1);
		return pole;
	}
	
	public static void quicksort(int pole[], int start, int konec) 
	{
        int s = start;
        int k = konec;
        
        if (konec - start >= 1) 
        {
        
            int pivot = pole[start];
            
            while(k > s) 
            {
                
                while(pole[s] <= pivot && s <= konec && k > s)
                {
                	por++;
                    s++;
                }
                while(pole[k] > pivot && k >= start && k >= s)
                {
                	por++;
                    k--;
                }
                if(s < k)
                {
                    nahrada(pole, s, k);
                }
            }
            nahrada(pole, k, start);
            quicksort(pole, start, k - 1);
            quicksort(pole, ++k, konec);
        }
        else 
        {
            return;
        }
    }
	
	public static void nahrada(int[] pole, int start, int konec) 
	{
		int tmp = pole[konec];
	    pole[konec] = pole[start];
	    pole[start] = tmp;
	    vym++;
	}
	 
	public static void vypis()
	{
		System.out.println( " por: "+por + "/ vym: " + vym);
	}

}
