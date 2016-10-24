package algo;

import java.util.Arrays;

import hjelpeklasser.Tabeller;
import obliger.Oblig1;
import obliger.Oblig1Tester;

public class Main {

	public static void main(String[] args) {
		
		int[] a = Oblig1Tester.randPerm(1000);
		
		System.out.println(Oblig1.ombyttinger(a));
		 
		//test();
	}
	
	public static void delsorteringNew(int[] a){
		
		
		
	}
	

	public static void test(){
		
		int antallFeil = 0;
		
		antallFeil += Oblig1Tester.oppgave4();
		antallFeil += Oblig1Tester.oppgave1();
		antallFeil += Oblig1Tester.oppgave2();
		antallFeil += Oblig1Tester.oppgave3();
		antallFeil += Oblig1Tester.oppgave5();
		antallFeil += Oblig1Tester.oppgave6();
		antallFeil += Oblig1Tester.oppgave7();
		antallFeil += Oblig1Tester.oppgave8();
		antallFeil += Oblig1Tester.oppgave9();
		antallFeil += Oblig1Tester.oppgave10();
/*		
 */


		
		if (antallFeil == 0) {
			System.out.println("Gratulerer!! Du passerte testen!");
		} else {
			System.out.println("Må forbedres! Du har minst " + antallFeil + " feil eller svakheter!");
		}
	}
}
