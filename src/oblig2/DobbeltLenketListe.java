/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblig2;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activity.InvalidActivityException;

public class DobbeltLenketListe<T> implements Liste<T> {

	private static final class Node<T> // en indre nodeklasse
	{
		// instansvariabler
		private T verdi;
		private Node<T> forrige, neste;

		private Node(T verdi, Node<T> forrige, Node<T> neste) // konstruktør
		{
			this.verdi = verdi;
			this.forrige = forrige;
			this.neste = neste;
		}

		protected Node(T verdi) // konstruktør
		{
			this(verdi, null, null);
		}

	} // Node

	// instansvariabler
	private Node<T> hode; // peker til den første i listen
	private Node<T> hale; // peker til den siste i listen
	private int antall; // antall noder i listen
	private int endringer; // antall endringer i listen

	// hjelpemetode
	private Node<T> finnNode(int indeks) {
		Node<T> f;
		if (indeks < (antall / 2)) {
			f = hode;
			for (int i = 0; i < indeks; i++) {
				f = f.neste;
			}

		} else {
			f = hale;
			for (int i = antall; i > indeks+1; i--) {
				f = f.forrige;
			}
		}
		return f;
	}

	// konstruktør
	public DobbeltLenketListe() {

		hode = new Node<T>(null);
		hale = new Node<T>(null);
		antall = 0;
		endringer = 0;
	}

	// konstruktør
	public DobbeltLenketListe(T[] a) {

		if (a.length > 0) {
			Objects.requireNonNull(a);
			antall = 0;

			int teller = 0;
			for (int i = 0; i < a.length; i++) {
				teller++;
				if (a[i] != null) {
					hode = new Node<T>(a[i]);
					antall++;
					break;
				}
			}
			if (hode != null) {
				Node<T> temp = hode;
				for (int i = teller; i < a.length; i++) {
					if (a[i] != null) {
						Node<T> temp2 = new Node<T>(a[i]);
						temp2.forrige = temp;
						temp.neste = temp2;
						hale = temp2;
						temp = temp2;
						antall++;
					}
				}
			}

		}
	}

	// subliste
	public Liste<T> subliste(int fra, int til) {
		DobbeltLenketListe<T> sub = new DobbeltLenketListe<>();
		if(til-fra == 0) return sub;
		fratilKontroll(antall,fra,til);
		for(int i = fra; i<til;i++){
			sub.leggInn(hent(i));
		}
		return sub;
	}
	
	public static void fratilKontroll(int antall, int fra, int til)
	  {
	    if (fra < 0)                                  // fra er negativ
	      throw new IndexOutOfBoundsException
	        ("fra(" + fra + ") er negativ!");

	    if (til > antall)                          // til er utenfor tabellen
	      throw new IndexOutOfBoundsException
	        ("til(" + til + ") > antall(" + antall + ")");

	    if (fra > til)                                // fra er st�rre enn til
	      throw new IllegalArgumentException
	        ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
	  }

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean tom() {
		return antall == 0;
	}

	@Override
	public boolean leggInn(T verdi) {
		Objects.requireNonNull(verdi);
		if (antall == 0) {
			hode = hale = new Node<T>(verdi, null, null);
			antall++;
			endringer++;
			return true;
		}

		Node<T> siste = hale;
		Node<T> nyNode = new Node<T>(verdi, siste, null);
		siste.neste = nyNode;
		hale = nyNode;

		antall++;
		endringer++;
		return true;
	}

	@Override
	public void leggInn(int indeks, T verdi) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public boolean inneholder(T verdi) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public T hent(int indeks) {
		indeksKontroll(indeks,false);
		return finnNode(indeks).verdi;
	}

	@Override
	public int indeksTil(T verdi) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public T oppdater(int indeks, T nyverdi) {
		Objects.requireNonNull(nyverdi);
		Node<T> oldNode = new Node<T>(finnNode(indeks).verdi);
		finnNode(indeks).verdi = nyverdi;
		return oldNode.verdi;
	
	}

	@Override
	public boolean fjern(T verdi) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public T fjern(int indeks) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public void nullstill() {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public String toString() {
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (hode != null) {
			Node<T> current = hode;
			for (int i = 0; i < antall; i++) {
				s.add(current.verdi.toString());
				current = current.neste;
			}
		}

		return s.toString();
	}

	public String omvendtString() {
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (hode != null) {
			Node<T> current = hale;
			for (int i = antall; i > 0; i--) {
				s.add(current.verdi.toString());
				current = current.forrige;
			}
		}
		return s.toString();

	}

	public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	public Iterator<T> iterator(int indeks) {
		throw new UnsupportedOperationException("Ikke laget ennå!");
	}

	private class DobbeltLenketListeIterator implements Iterator<T> {
		private Node<T> denne;
		private boolean fjernOK;
		private int iteratorendringer;

		private DobbeltLenketListeIterator() {
			denne = hode; // denne starter på den første i listen
			fjernOK = false; // blir sann når next() kalles
			iteratorendringer = endringer; // teller endringer
		}

		private DobbeltLenketListeIterator(int indeks) {
			throw new UnsupportedOperationException("Ikke laget ennå!");
		}

		@Override
		public boolean hasNext() {
			return denne != null; // denne koden skal ikke endres!
		}

		@Override
		public T next() {
			throw new UnsupportedOperationException("Ikke laget ennå!");
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Ikke laget ennå!");
		}

	} // DobbeltLenketListeIterator

} // DobbeltLenketListe
