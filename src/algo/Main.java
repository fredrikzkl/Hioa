package algo;

import java.util.Arrays;

import hjelpeklasser.Tabeller;
import obliger.Oblig1;

public class Main {

	public static void main(String[] args) {
		
		
		 
		 /*
		  * 
		 int[] a = {6,10,9,4,1,3,8,5,2,7,11,2};
		 System.out.println(Arrays.toString(a));
		 Oblig1.delsortering(a);
		 System.out.println(Arrays.toString(a));
		 
		char[] test = {};
		System.out.println(Arrays.toString(test));
		Oblig1.rotasjon(test,1);
		System.out.println(Arrays.toString(test));
		
		 
		  */
		 
		int antallFeil = 0;
		antallFeil += oppgave6();
		System.out.println("FEIL: " + antallFeil);
		 
	}
	
	 public static int oppgave6()
	  {
	    int antallFeil = 0;

	    char[] a = {};

	    try
	    {
	      Oblig1.rotasjon(a,1);  // kaller metoden
	    }
	    catch (Exception e)
	    {
	      System.out.println(e);
	      System.out.println
	        ("Oppgave 6: a) Skal ikke kaste unntak for en tom tabell!!");
	        antallFeil++;
	    }

	    char[] b = {'A'};
	    char[] b0 = {'A'};
	    Oblig1.rotasjon(b,0);
	    if (!Arrays.equals(b, b0))
	    {
	      System.out.println("Oppgave 6: b) Feil hvis tabellen har ett element!");
	      antallFeil++;
	    }

	    Oblig1.rotasjon(b,1);
	    if (!Arrays.equals(b, b0))
	    {
	      System.out.println("Oppgave 6: c) Feil hvis tabellen har ett element!");
	      antallFeil++;
	    }

	    Oblig1.rotasjon(b,-1);
	    if (!Arrays.equals(b, b0))
	    {
	      System.out.println("Oppgave 6: d) Feil hvis tabellen har ett element!");
	      antallFeil++;
	    }

	    char[] c = {'A','B'};
	    char[] c0 = {'B','A'};
	    Oblig1.rotasjon(c,1);

	    if (!Arrays.equals(c, c0))
	    {
	      System.out.println("Oppgave 6: e) Feil hvis tabellen har to elementer!");
	      antallFeil++;
	    }

	    c = new char[] {'A','B'};

	    Oblig1.rotasjon(c,-1);
	    if (!Arrays.equals(c, c0))
	    {
	      System.out.println("Oppgave 6: f) Feil hvis tabellen har to elementer!");
	      antallFeil++;
	    }

	    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
	    char[] d0 = {'G','H','I','J','A','B','C','D','E','F'};

	    Oblig1.rotasjon(d,4);
	    if (!Arrays.equals(d, d0))
	    {
	      System.out.println("Oppgave 6: g) Feil hvis tabellen har flere elementer!");
	      antallFeil++;
	    }

	    d = new char[]{'A','B','C','D','E','F','G','H','I','J'};
	    Oblig1.rotasjon(d,-6);
	    if (!Arrays.equals(d, d0))
	    {
	      System.out.println("Oppgave 6: h) Feil hvis tabellen har flere elementer!");
	      antallFeil++;
	    }

	    char[] x = new char[100_000];
	    long tid = System.currentTimeMillis();
	    Oblig1.rotasjon(x,99_999);
	    tid = System.currentTimeMillis() - tid;

	    if (tid > 20)
	    {
	      System.out.println("Oppgave 6: i) Metoden "
	        + "er for ineffektiv. Må forbedres!");
	      antallFeil++;
	    }

	    tid = System.currentTimeMillis();
	    Oblig1.rotasjon(x,199_999);
	    tid = System.currentTimeMillis() - tid;

	    if (tid > 20)
	    {
	      System.out.println("Oppgave 6: j) Metoden "
	        + "er for ineffektiv. Må forbedres!");
	      antallFeil++;
	    }

	    tid = System.currentTimeMillis();
	    Oblig1.rotasjon(x,50_000);
	    tid = System.currentTimeMillis() - tid;

	    if (tid > 20)
	    {
	      System.out.println("Oppgave 6: k) Metoden "
	        + "er for ineffektiv. Må forbedres!");
	      antallFeil++;
	    }

	    tid = System.currentTimeMillis();
	    Oblig1.rotasjon(x,-40_000);
	    tid = System.currentTimeMillis() - tid;

	    if (tid > 20)
	    {
	      System.out.println("Oppgave 6: l) Metoden "
	        + "er for ineffektiv. Må forbedres!");
	      antallFeil++;
	    }

	    return antallFeil;
	  }


}
