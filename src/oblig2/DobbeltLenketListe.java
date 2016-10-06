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


/*
 * Fredrik Zander Kloster
 * Oblig 2
 * StudentNr: 
 */

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
		Objects.requireNonNull(verdi);
		
		if(indeks<0) throw new IndexOutOfBoundsException();
		else if(indeks>antall) throw new IndexOutOfBoundsException();
		
		if(antall==0){
			hode = hale = new Node<T>(verdi, null, null);
		}else if(indeks == 0){
			Node<T> prev = hode;
			Node<T> newNode = new Node<T>(verdi,null,prev);
			prev.forrige = newNode;
			hode = newNode;
		}else if(indeks == antall){
			Node<T> siste = hale;
			Node<T> nyNode = new Node<T>(verdi, siste, null);
			siste.neste = nyNode;
			hale = nyNode;
		}else{
			Node<T> prev = finnNode(indeks);
			Node<T> newNode = new Node<T>(verdi,prev.forrige,prev);
			prev.forrige.neste = newNode;
			prev.forrige = newNode;
		}
		
		antall++;
		endringer++;
	}

	@Override
	public boolean inneholder(T verdi) {
		if(indeksTil(verdi) != -1) return true;
		return false;
	}

	@Override
	public T hent(int indeks) {
		indeksKontroll(indeks,false);
		return finnNode(indeks).verdi;
	}

	@Override
	public int indeksTil(T verdi) {
		int indeks = 0;
		Node<T> iterator = hode;
		for(;indeks < antall;indeks++){
			if(iterator.verdi.equals(verdi))return indeks;
			iterator = iterator.neste;
		}
		return -1;
	}

	@Override
	public T oppdater(int indeks, T nyverdi) {
		if(indeks>=antall) throw new IndexOutOfBoundsException();
		Objects.requireNonNull(nyverdi);
		Node<T> oldNode = new Node<T>(finnNode(indeks).verdi);
		finnNode(indeks).verdi = nyverdi;
		endringer++;
		return oldNode.verdi;
	
	}

	@Override
	public boolean fjern(T verdi) {
		if(verdi == null) return false;
		Node<T> slettes = hode;
		int indeks = 0;
		for(; indeks<antall;indeks++){
			if(slettes.verdi.equals(verdi)){
				break;
			}
			if(slettes.neste == null) return false;
			slettes = slettes.neste;
		}
		sletting(slettes,indeks);
		return true;
	}

	@Override
	public T fjern(int indeks) {
		if(antall == 0) throw new ArrayIndexOutOfBoundsException();
		indeksKontroll(indeks,false);
		Node<T> slettes = finnNode(indeks);
		T value = slettes.verdi;
		sletting(slettes,indeks);
		return value;
	}
	
	private void sletting(Node<T> slettes,int indeks){
		if(indeks == 0){
			hode = slettes.neste;
		}else if(indeks == antall-1){
			hale = slettes.forrige;
		}else{
			slettes.neste.forrige = slettes.forrige;
			slettes.forrige.neste = slettes.neste;
		}
		slettes = null;
		antall--;
		endringer++;
	}

	@Override
	public void nullstill() {
		Node<T> i = hode;
		while(i != hale){
			fjern(0);
			i = i.neste;
		}
		fjern(0);
		hode = hale = null;
		antall = 0;
		endringer++;
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
		return new DobbeltLenketListeIterator();
	}																	

	public Iterator<T> iterator(int indeks) {
		indeksKontroll(indeks, false);
		return new DobbeltLenketListeIterator(indeks);
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
			denne = finnNode(indeks);
			fjernOK = false;
			iteratorendringer = endringer;
		}

		@Override
		public boolean hasNext() {
			return denne != null; // denne koden skal ikke endres!
		}

		@Override
		public T next(){
			if(iteratorendringer != endringer) throw new ConcurrentModificationException();
			if(hasNext() == false || antall == 0) throw new NoSuchElementException();
			fjernOK = true;
			T value = denne.verdi;
			denne = denne.neste;
			return value;
		}

		@Override
		public void remove() {
            if (!fjernOK) throw new IllegalStateException("M� kalle p� next f�rst");
			if(iteratorendringer != endringer) throw new ConcurrentModificationException();
			
			fjernOK = false;
			
			if(antall == 1){
				hode = hale = null;
			}else if (hode.neste == denne){
				hode = denne;
				hode.forrige = null;
			}else if(denne == null){
				hale = hale.forrige;
				hale.neste = null;
			}else{
				Node<T> p = denne.forrige;
				p.forrige.neste = p.neste;
				p.neste.forrige = p.forrige;
				p = null;
			}
			
            antall--;
            endringer++;
            iteratorendringer++;
		}

	} // DobbeltLenketListeIterator

} // DobbeltLenketListe
