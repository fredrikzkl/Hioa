package oblig3;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T> {
	private static final class Node<T> // en indre nodeklasse
	{
		private T verdi; // nodens verdi
		private Node<T> venstre, h�yre; // venstre og h�yre barn
		private Node<T> forelder; // forelder

		// konstrukt�r
		private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
			this.verdi = verdi;
			venstre = v;
			h�yre = h;
			this.forelder = forelder;
		}

		private Node(T verdi, Node<T> forelder) // konstrukt�r
		{
			this(verdi, null, null, forelder);
		}

		@Override
		public String toString() {
			return "" + verdi;
		}

	} // class Node

	private Node<T> rot; // peker til rotnoden
	private int antall; // antall noder
	private int endringer; // antall endringer

	private final Comparator<? super T> comp; // komparator

	public ObligSBinTre(Comparator<? super T> c) // konstrukt�r
	{
		rot = null;
		antall = 0;
		comp = c;
	}

	@Override
	public boolean leggInn(T verdi) {
		Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

		Node<T> p = rot, q = null; // p starter i roten
		int cmp = 0; // hjelpevariabel

		while (p != null) // fortsetter til p er ute av treet
		{
			q = p; // q er forelder til p
			cmp = comp.compare(verdi, p.verdi); // bruker komparatoren
			p = cmp < 0 ? p.venstre : p.h�yre; // flytter p
		}

		// p er n� null, dvs. ute av treet, q er den siste vi passerte

		p = new Node<>(verdi, q); // oppretter en ny node

		if (q == null)
			rot = p; // p blir rotnode
		else if (cmp < 0)
			q.venstre = p; // venstre barn til q
		else
			q.h�yre = p; // h�yre barn til q

		antall++; // �n verdi mer i treet
		return true;
	}

	@Override
	public boolean inneholder(T verdi) {
		if (verdi == null)
			return false;

		Node<T> p = rot;

		while (p != null) {
			int cmp = comp.compare(verdi, p.verdi);
			if (cmp < 0)
				p = p.venstre;
			else if (cmp > 0)
				p = p.h�yre;
			else
				return true;
		}

		return false;
	}

	@Override
	public boolean fjern(T verdi) {
		if (verdi == null) return false;  // treet har ingen nullverdier

	    Node<T> p = rot, q = null;   // q skal v�re forelder til p

	    while (p != null)            // leter etter verdi
	    {
	      int cmp = comp.compare(verdi,p.verdi);      // sammenligner
	      if (cmp < 0) { q = p; p = p.venstre; }      // g�r til venstre
	      else if (cmp > 0) { q = p; p = p.h�yre; }   // g�r til h�yre
	      else break;    // den s�kte verdien ligger i p
	    }
	    if (p == null) return false;   // finner ikke verdi

	    Node<T> b = p.venstre != null ? p.venstre : p.h�yre;  // b for barn
	    
	    if(p.venstre == null && p.h�yre == null){
	    	if(p == rot){
	    		rot = b;
	    	} else if(p == q.venstre){
	    		q.venstre = null;
	    	}else{
	    		q.h�yre = null;
	    	}
	    }else if(p.venstre == null || p.h�yre == null){
	    	if(p == rot){
	    		rot = b;
	    		b.forelder = null;
	    	}else if( p == q.venstre){
	    		q.venstre = b;
	    		b.forelder = q;
	    	}else{
	    		q.h�yre = b;
	    		b.forelder = q;
	    	}
	    }
	    else  // Tilfelle 3)
	    {
	      Node<T> s = p, r = p.h�yre;   // finner neste i inorden
	      while (r.venstre != null)
	      {
	        s = r;    // s er forelder til r
	        r = r.venstre;
	      }

	      p.verdi = r.verdi;   // kopierer verdien i r til p

	      if (s != p) s.venstre = r.h�yre;
	      else s.h�yre = r.h�yre;
	      
	    }

	    antall--;   // det er n� �n node mindre i treet
	    return true;
	}

	public int fjernAlle(T verdi) {
		int antallFjernet = 0;
		while(inneholder(verdi)){
			fjern(verdi);
			antallFjernet++;
		}
		return antallFjernet;
	}

	@Override
	public int antall() {
		return antall;
	}

	public int antall(T verdi) {
		int antall = 0;
		Node<T> p = rot, q = null;
		int cmp = 0;
		while (p != null) {
			if (p.verdi.equals(verdi))
				antall++;
			q = p;
			cmp = comp.compare(verdi, p.verdi);
			p = cmp < 0 ? p.venstre : p.h�yre;
		}
		return antall;
	}

	@Override
	public boolean tom() {
		return antall == 0;
	}

	@Override
	public void nullstill() {
		while(rot != null){
			fjern(rot.verdi);
		}
	}

	private static <T> Node<T> nesteInorden(Node<T> p) {
		if (p.h�yre != null) {
			p = p.h�yre;
			while (p.venstre != null) {
				p = p.venstre;
			}
		} else {
			while (p.forelder != null && p.forelder.h�yre == p) {
				p = p.forelder;
			}
			if(p.forelder == null) return null;
			p = p.forelder;
		}
		return p;
	}

	@Override
	public String toString() {
		StringJoiner s = new StringJoiner(", " ,"[","]");
		if(tom()) return s.toString();
		
		Node<T> x = rot; 
		while(x.venstre != null){
			x = x.venstre;
		}
		
		for(int i = 0; i < antall; i++){
			Node<T> temp = nesteInorden(x);
			s.add(x.verdi.toString());
			x = temp;
		}
		return s.toString();
	}

	public String omvendtString() {
		StringJoiner s = new StringJoiner(", " ,"[","]");
		if(tom()) return s.toString();
		ArrayDeque<Node<T>> stakk = new ArrayDeque<Node<T>>();
		
		Node<T> p = rot;
		while(p.h�yre != null){
			stakk.add(p);
			p = p.h�yre;
		}
		
		while(true){
			
			s.add(p.toString());
			if(p.venstre != null){
				p = p.venstre;
				while(p.h�yre != null)
		        {
				  stakk.add(p);
				  p = p.h�yre;
		        }
			}else if(!stakk.isEmpty()){
				p = stakk.pollLast();
			}else{
				break;
			}
		}
		return s.toString();
		
	}

	public String h�yreGren() {
		StringJoiner s = new StringJoiner(", ", "[" , "]" );
		
		if(tom()) return s.toString();
		
		Node<T> x = rot; 
		s.add(rot.verdi.toString());
		while(true){
			if(x.h�yre != null){
				x = x.h�yre;
				s.add(x.verdi.toString());
				continue;
			}else if(x.venstre != null){
				x = x.venstre;
				s.add(x.verdi.toString());
				continue;
			}
			break;
		}
		
		return s.toString();
	}

	public String lengstGren() {
		StringJoiner s = new StringJoiner(", ", "[" , "]" );
		
		if(tom()) return s.toString();
		
		
		return s.toString();
	}

	public String[] grener() {
		throw new UnsupportedOperationException("Ikke kodet enn�!");
	}

	public String bladnodeverdier() {
		throw new UnsupportedOperationException("Ikke kodet enn�!");
	}

	public String postString() {
		throw new UnsupportedOperationException("Ikke kodet enn�!");
	}

	@Override
	public Iterator<T> iterator() {
		return new BladnodeIterator();
	}

	private class BladnodeIterator implements Iterator<T> {
		private Node<T> p = rot, q = null;
		private boolean removeOK = false;
		private int iteratorendringer = endringer;

		private BladnodeIterator() // konstrukt�r
		{ 
			throw new UnsupportedOperationException("Ikke kodet enn�!");
		}

		@Override
		public boolean hasNext() {
			return p != null; // Denne skal ikke endres!
		}

		@Override
		public T next() {
			throw new UnsupportedOperationException("Ikke kodet enn�!");
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Ikke kodet enn�!");
		}

	} // BladnodeIterator

} // ObligSBinTre