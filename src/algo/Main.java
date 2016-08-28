package algo;

import java.util.Arrays;

import hjelpeklasser.Tabeller;

public class Main {

	public static void main(String[] args) {
		
		int[] a = {1,3,2,1,5,6,2,5};
		int m[] = Tabeller.nestMaks(a);
		System.out.println(Arrays.toString(m));
		
		
		int[] ye = {8,4,17,10,6,20,1,11,15,3,18,9,2,7,18};
		int minste = Tabeller.min(ye);
		System.out.println(minste);
		
		int[] liste = {8,4,17,10,6,20,1,11,15,3,18,9,2,7,18};
		int[] sortert = Tabeller.minmaks(liste);
		System.out.println(Arrays.toString(sortert));
		
		System.out.println("Factorio: " + Tabeller.fak(10));
	}

}
