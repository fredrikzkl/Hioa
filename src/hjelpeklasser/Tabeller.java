package hjelpeklasser;

import java.util.NoSuchElementException;

public class Tabeller {
	
	
	 public static int maks(int[] a)  // a er en heltallstabell
	  {
	    if (a.length < 1)
	      throw new java.util.NoSuchElementException("Tabellen a er tom!");

	    int m = 0;  // indeks til største verdi

	    for (int i = 1; i < a.length; i++) // obs: starter med i = 1
	    {
	      if (a[i] > a[m]) m = i;  // indeksen oppdateres
	    }

	    return m;  // returnerer indeksen/posisjonen til største verdi

	  } // maks
	 
	 
	 public static int min(int[] a){
		 if(a.length < 1)
			 throw new NoSuchElementException("Tabellen er tom :)");
		 
		 int m = 0; 
		 for(int i = 1;i<a.length; i++){
			 if(a[i]<a[m]) m = i;
		 }
		 
		 return m;
	 }
	 
	 public static int[] minmaks(int[] a){
		 
		 if(a.length < 1)
			 throw new NoSuchElementException("Tabellen er tom :)");
		 int maks = 0;
		 int min = 0;
		 
		 for(int i = 1; i<a.length; i++){
			 if(a[i]<a[maks]){
				 maks = i;
			 }else if(a[i]>a[min]){
				 min = i;
			 }
		 }
		 
		 return new int[] {maks,min};
	 }
	 
	 /*
	  * 5
	  * 2*N
	  */
	 
	 public static long fak(int n){
		 if(n<0) throw new IllegalArgumentException("n < 0, bro");
		 for(int i = n; i!=1; i--){
			 n *= i-1;
		 }
		 return n;
	 }
	 
	 
	 public static double harmonisk(int n){
		 double sum = 0.0;
		 
		 for(int i = 1;i <=n; i++)
			 sum += 1.0/i;
		 
		 return sum;
	 }
	 
	 public static double euler(int n){
		 return harmonisk(n)-Math.log(n);
	 }
	 
	 
	 public static int maks(int[] a, int fra, int til)
	  {
	    if (fra < 0 || til > a.length || fra >= til)
	    {
	      throw new IllegalArgumentException("Illegalt intervall!");
	    }

	    int m = fra;              // indeks til største verdi i a[fra:til>
	    int maksverdi = a[fra];   // største verdi i a[fra:til>

	    for (int i = fra + 1; i < til; i++)
	    {
	      if (a[i] > maksverdi)
	      {
	        m = i;                // indeks til største verdi oppdateres
	        maksverdi = a[m];     // største verdi oppdateres
	      }
	    }

	    return m;  // posisjonen til største verdi i a[fra:til>
	  }
	 
	 public static int [] nestMaks(int[] a){ // dårlig versjon, karakter C
		if(a.length < 2 ) throw new NoSuchElementException("Ikke nok verdier");
		
		int m = maks(a);
		
		int nm = (m == 0) ? 1:0;
		int nestmaksverdi = a[nm];
		
		for(int i = 1;i<a.length;i++){
			if(i != m && a[i]>nm){
				nm = i;
				nestmaksverdi = a[i];
			}
		}
		 
		return new int[] {m,nm};
		 
	 }
	 
	 
	 public static int [] nestMaksPro(int[] a){ // bedre versjon, karakter A
			if(a.length < 2 ) throw new NoSuchElementException("Ikke nok verdier");
			
			int m = maks(a);

			int temp = a[m];
			a[m] = Integer.MIN_VALUE;
			
			int nm = 0;
			int nestmaksverdi = a[nm];
			
			for(int i = 1;i<a.length;i++){
				if(a[i]>nm){
					nm = i;
					nestmaksverdi = a[i];
				}
			}
			 
			return new int[] {m,nm};
			 
		 }
}
