package algo;

import java.util.Arrays;

import hjelpeklasser.Tabeller;
import obliger.Oblig1;
import obliger.Oblig1Tester;

public class Main {

	public static void main(String[] args) {
		 int[] a = {1,3,5};
		  //Oblig1.delsortering(a);
		  System.out.println(Arrays.toString(a));
		  // Utskrift: [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]
		
		
		
		  test();
	}

	public static void test(){
		
		int antallFeil = 0;
		antallFeil += Oblig1Tester.oppgave1();
		antallFeil += Oblig1Tester.oppgave2();
		antallFeil += Oblig1Tester.oppgave3();
		//antallFeil += Oblig1Tester.oppgave4();
		antallFeil += Oblig1Tester.oppgave5();
		antallFeil += Oblig1Tester.oppgave6();
		antallFeil += Oblig1Tester.oppgave7();
		//antallFeil += Oblig1Tester.oppgave8();
		
		if (antallFeil == 0) {
			System.out.println("Gratulerer!! Du passerte testen!");
		} else {
			System.out.println("Må forbedres! Du har minst " + antallFeil + " feil eller svakheter!");
		}
	}
}
