package hjelpeklasser;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import obliger.Oblig1;

public class Tabeller {

	public static int maks(int[] a) // a er en heltallstabell
	{
		if (a.length < 1)
			throw new java.util.NoSuchElementException("Tabellen a er tom!");

		int m = 0; // indeks til største verdi

		for (int i = 1; i < a.length; i++) // obs: starter med i = 1
		{
			if (a[i] > a[m])
				m = i; // indeksen oppdateres
		}

		return m; // returnerer indeksen/posisjonen til største verdi

	} // maks

	public static int min(int[] a) {
		if (a.length < 1)
			throw new NoSuchElementException("Tabellen er tom :)");

		int m = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] < a[m])
				m = i;
		}

		return m;
	}

	public static int[] minmaks(int[] a) {

		if (a.length < 1)
			throw new NoSuchElementException("Tabellen er tom :)");
		int maks = 0;
		int min = 0;

		for (int i = 1; i < a.length; i++) {
			if (a[i] < a[maks]) {
				maks = i;
			} else if (a[i] > a[min]) {
				min = i;
			}
		}

		return new int[] { maks, min };
	}
 
	/*
	 * 5 2*N
	 */

	public static long fak(int n) {
		if (n < 0)
			throw new IllegalArgumentException("n < 0, bro");
		for (int i = n; i != 1; i--) {
			n *= i - 1;
		}
		return n;
	}

	public static double harmonisk(int n) {
		double sum = 0.0;

		for (int i = 1; i <= n; i++)
			sum += 1.0 / i;

		return sum;
	}

	public static double euler(int n) {
		return harmonisk(n) - Math.log(n);
	}

	public static int maks(int[] a, int fra, int til) {
		if (fra < 0 || til > a.length || fra >= til) {
			throw new IllegalArgumentException("Illegalt intervall!");
		}

		int m = fra; // indeks til største verdi i a[fra:til>
		int maksverdi = a[fra]; // største verdi i a[fra:til>

		for (int i = fra + 1; i < til; i++) {
			if (a[i] > maksverdi) {
				m = i; // indeks til største verdi oppdateres
				maksverdi = a[m]; // største verdi oppdateres
			}
		}

		return m; // posisjonen til største verdi i a[fra:til>
	}

	public static int[] nestMaks(int[] a) { // dårlig versjon, karakter C
		if (a.length < 2)
			throw new NoSuchElementException("Ikke nok verdier");

		int m = maks(a);

		int nm = (m == 0) ? 1 : 0;
		int nestmaksverdi = a[nm];

		for (int i = 1; i < a.length; i++) {
			if (i != m && a[i] > nm) {
				nm = i;
				nestmaksverdi = a[i];
			}
		}

		return new int[] { m, nm };

	}

	public static int[] nestMaksPro(int[] a) { // bedre versjon, karakter A
		if (a.length < 2)
			throw new NoSuchElementException("Ikke nok verdier");

		int m = maks(a);

		int temp = a[m];
		a[m] = Integer.MIN_VALUE;

		int nm = 0;
		int nestmaksverdi = a[nm];

		for (int i = 1; i < a.length; i++) {
			if (a[i] > nm) {
				nm = i;
				nestmaksverdi = a[i];
			}
		}

		return new int[] { m, nm };

	}

	public static void bytt(int[] a, int i, int j)
	  {
	    int temp = a[i]; a[i] = a[j]; a[j] = temp;
	  }

	public static void snu(int[] a, int v, int h) // snur intervallet a[v:h]
	{
		while (v < h)
			bytt(a, v++, h--);
	}

	public static void snu(int[] a, int v) // snur fra og med v og ut tabellen
	{
		snu(a, v, a.length - 1);
	}

	public static void snu(int[] a) // snur hele tabellen
	{
		snu(a, 0, a.length - 1);
	}

	public static boolean nestePermutasjon(int[] a) {
		int i = a.length - 2;                    // i starter nest bakerst
	    while (i >= 0 && a[i] > a[i + 1]) i--;   // går mot venstre
	    if (i < 0) return false;                 // a = {n, n-1, . . . , 2, 1}

	    int j = a.length - 1;                    // j starter bakerst
	    while (a[j] < a[i]) j--;                 // stopper når a[j] > a[i] 
	    bytt(a,i,j); snu(a,i + 1);               // bytter og snur

	    return true;                             // en ny permutasjon
	}

	public static int[] randPerm(int n) // en effektiv versjon
	{
		Random r = new Random(); // en randomgenerator
		int[] a = new int[n]; // en tabell med plass til n tall

		Arrays.setAll(a, i -> i + 1); // legger inn tallene 1, 2, . , n

		for (int k = n - 1; k > 0; k--) // løkke som går n - 1 ganger
		{
			int i = r.nextInt(k + 1); // en tilfeldig tall fra 0 til k
			bytt(a, k, i); // bytter om
		}

		return a; // permutasjonen returneres
	}

	public static int inversjoner(int[] a) {
		int antall = 0; // antall inversjoner
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j])
					antall++; // en inversjon siden i < j
			}
		}
		return antall;
	}

	public static boolean erSortert(int[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i - 1] > a[i])
				return false;
		}
		return true;
	}

	public static int usortertsøk(int[] a, int verdi) // tabell og søkeverdi
	{
		for (int i = 0; i < a.length; i++) // går gjennom tabellen
			if (verdi == a[i])
				return i; // verdi funnet - har indeks i

		return -1; // verdi ikke funnet
	}

	public static int lineærtsøk(int[] a, int verdi) {
		int i = 0;
		for (; i < a.length && verdi <= a[i]; i++)
			;
		if (i == a.length || verdi != a[i]) {
			return -(1 + 1);
		} else {
			return -1;
		}
	}

	public static int binærsøk(int[] a, int fra, int til, int verdi) {

		if (!Oblig1.checkIfRising(a)) {
			throw new IllegalArgumentException("Bro, gotta be rising");
		}
		int v = fra, h = til - 1;
		while (v <= h) {
			int m = (v + h) / 2;
			int midtverdi = a[m];
			if (verdi == midtverdi)
				return m;
			else if (verdi > midtverdi)
				v = m + 1;
			else
				h = m - 1;
		}
		return -(v + 1);

	}

	public static int binærsøk(int[] a, int verdi) {
		return binærsøk(a, 0, a.length, verdi);
	}

	public static void innsettingssortering(int[] a) {
		for (int i = 1; i < a.length; i++) // starter med i = 1
		{
			int verdi = a[i], j = i - 1; // verdi er et tabellelemnet, j er en
											// indeks
			for (; j >= 0 && verdi < a[j]; j--)
				a[j + 1] = a[j]; // sammenligner og flytter
			a[j + 1] = verdi; // j + 1 er rett sortert plass
		}
	}

	public static void shell(int[] a, int k) {
		for (int i = k; i < a.length; i++) {
			int temp = a[i], j = i - k;
			for (; j >= 0 && temp < a[j]; j -= k)
				a[j + k] = a[j];
			a[j + k] = temp;
		}
	}

	public static int parter(int[] a, int v, int h, int skilleverdi) {
		while (v <= h && a[v] < skilleverdi)
			v++; // h er stoppverdi for v
		while (v <= h && a[h] >= skilleverdi)
			h--; // v er stoppverdi for h

		while (true) // stopper når v >= h
		{
			if (v < h)
				bytt(a, v++, h--); // bytter om a[v] og a[h]
			else
				return v; // partisjoneringen er ferdig
			while (a[v] < skilleverdi)
				v++; // flytter v mot høyre
			while (a[h] >= skilleverdi)
				h--; // flytter h mot venstre
		}
	}

	public static int sParter(int[] a, int v, int h, int indeks) {
		bytt(a, indeks, h); // skilleverdi a[indeks] flyttes bakerst
		int k = parter(a, v, h - 1, a[h]); // partisjonerer a[v:h 1]
		bytt(a, k, h); // bytter for å få skilleverdien på rett plass
		return k; // returnerer posisjonen til skilleverdien
	}

}
