package obliger;

import java.util.Arrays;
import java.util.NoSuchElementException;

/*
 *	Oblig 1 
 *	Fredrik Zander Kloster
 *	Studnr: s188078
 *	
 */

public class Oblig1 {

	/*
	 * Oppgave 1
	 */

	public static int maks(int[] a) {
		if (a.length == 0) {
			throw new NoSuchElementException("Listen er tom!");
		}
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				bytt(a, i, i + 1);
			}
		}
		return a[a.length - 1];
	}

	public static int ombyttinger(int[] a) {
		int var = 0;
		if (a.length == 0) {
			throw new NoSuchElementException("Listen er tom!");
		}
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				bytt(a, i, i + 1);
				var++;
			}
		}

		return var;
	}

	// Kopiert fra Kompendiet
	public static void bytt(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/*
	 * Hvilken er raskest av denne eller den andre maks?
	 */

	/*
	 * Oppgave 2
	 */

	public static int antallUlikeSortert(int[] a) {
		int counter = 0;
		if (checkIfRising(a)) {
			if (a.length != 0)
				counter++;
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] != a[i + 1])
					counter++;
			}
		} else {
			throw new IllegalStateException("Tabellen må være stigende");
		}
		return counter;
	}

	public static boolean checkIfRising(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Oppgave 3
	 */

	public static int antallUlikeUsortert(int[] a) {
		int counter = 0;

		for (int i = 0; i < a.length; i++) {
			if (!countedBefore(a, i)) {
				counter++;
			}
		}
		return counter;
	}

	private static boolean countedBefore(int[] a, int i) {
		for (int j = i; j > 0; j--) {
			if (a[j - 1] == a[i]) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Oppgave 4
	 */

	public static void delsortering(int[] a) {
		if (a.length > 1) {
			int next = 0;
			int iterator = 0;
			
			boolean onlyOdd = true;
			boolean onlyEven = true;
			
			int counter = 0;
			while (iterator != a.length) {
				if (a[iterator] % 2 != 0) {
					bytt(a, iterator, next);
					next++;
					iterator = next;
					onlyEven=false;
				} else {
					iterator++;
					onlyOdd = false;
				}
				counter++;

			}
			
			
			
			
			
			if(!onlyEven)sortOdd(a, next);
			if(!onlyOdd)sortEven(a, next);
		}
	}

	private static void sortEven(int[] a, int next) {
		int iterator = next;
		while (iterator != a.length - 1) {
			if (a[iterator + 1] < a[iterator]) {
				bytt(a, iterator + 1, iterator);
				iterator = next;
			} else {
				iterator++;
			}
		}

	}

	private static void sortOdd(int[] a, int next) {
		int iterator = 0;
		while (iterator != next - 1) {
			if (a[iterator + 1] < a[iterator]) {
				bytt(a, iterator + 1, iterator);
				iterator = 0;
			} else {
				iterator++;
			}
		}

	}

	/*
	 * Oppgave 5
	 */

	public static void rotasjon(char[] a) {
		int temp = a[a.length - 1];
		for (int i = a.length - 1; i > 0; i--) {
			char swap = (char) a[i - 1];
			a[i] = swap;
		}
		a[0] = (char) temp;
	}

	/*
	 * Oppgave 6
	 */

	public static void rotasjon(char[] a, int k) {
		if (a.length != 0) {
			if (k > a.length) {
				k = k % a.length;
			}
			if (k >= 0) {
				char[] temp = new char[k];
				int iterator = (a.length) - k;
				for (int i = 0; i < k; i++) { // Setter inn de siste k verdiene
												// i en egen liste
					temp[i] = (char) a[iterator];
					iterator++;
				}

				for (int i = a.length - (k + 1); i >= 0; i--) { // Flytter alle
																// andre en
																// plass
																// fremover
					char swap = (char) a[i];
					a[i + k] = swap;
				}

				// Setter inn temp
				for (int i = 0; i < k; i++) {
					a[i] = temp[i];
				}

			} else {
				k = k * -1;
				char[] temp = new char[k];
				for (int i = 0; i < k; i++) { // Setter inn de siste k verdiene
												// i en egen liste
					temp[i] = (char) a[i];
				}

				for (int i = k; i < a.length; i++) { // Flytter alle andre en
														// plass fremover
					char swap = (char) a[i];
					a[i - k] = swap;
				}

				// Setter inn temp
				int iterator = 0;
				for (int i = a.length - k; i <= a.length - 1; i++) {
					a[i] = temp[iterator];
					iterator++;
				}
			}

		}
	}

	/*
	 * Oppgave 7
	 */

	public static String flett(String s, String t) {
		int loopAmount = 0;
		int i = 0;
		String spareString;
		String flettetString = "";

		if (s.length() < t.length()) {
			loopAmount = s.length();
			spareString = t;
		} else {
			loopAmount = t.length();
			spareString = s;
		}

		for (; i < loopAmount; i++) {
			flettetString += s.charAt(i);
			flettetString += t.charAt(i);
		}
		return flettetString += spareString.substring(i);
	}

	public static String flett(String... s) {
		String fletteString = "";
		String storsteString = "";
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > storsteString.length()) {
				storsteString = s[i];
			}
		}
		int iterator = 0;
		while (iterator != storsteString.length()) {
			for (int i = 0; i < s.length; i++) {
				if (s[i].length() > iterator) {
					fletteString += s[i].charAt(iterator);
				}
			}
			iterator++;
		}
		return fletteString;
	}

	/*
	 * Oppgave 8
	 */

	public static int[] indekssortering(int[] a) {
		int[] copyA = new int[a.length];
		for (int i = 0; i < a.length; i++)
			copyA[i] = a[i];
		int[] index = new int[a.length];

		int positionCurrentSmallest = 0;
		int smallest = Integer.MAX_VALUE;

		for (int j = 0; j < a.length; j++) {
			for (int i = 0; i < copyA.length; i++) {
				if (copyA[i] < smallest) {
					smallest = (int) copyA[i];
					positionCurrentSmallest = i;
				}
			}
			smallest = Integer.MAX_VALUE - 1;
			copyA[positionCurrentSmallest] = Integer.MAX_VALUE;
			index[j] = positionCurrentSmallest;
		}

		return index;
	}

}
