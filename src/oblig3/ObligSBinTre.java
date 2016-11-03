package oblig3;

import java.util.*;



public class ObligSBinTre<T> implements Beholder<T> {
	private static final class Node<T> // en indre nodeklasse
	{
		private T verdi; // nodens verdi
		private Node<T> venstre, høyre; // venstre og høyre barn
		private Node<T> forelder; // forelder

		// konstruktør
		private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
			this.verdi = verdi;
			venstre = v;
			høyre = h;
			this.forelder = forelder;
		}

		private Node(T verdi, Node<T> forelder) // konstruktør
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

	public ObligSBinTre(Comparator<? super T> c) // konstruktør
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
			p = cmp < 0 ? p.venstre : p.høyre; // flytter p
		}

		// p er nå null, dvs. ute av treet, q er den siste vi passerte

		p = new Node<>(verdi, q); // oppretter en ny node

		if (q == null)
			rot = p; // p blir rotnode
		else if (cmp < 0)
			q.venstre = p; // venstre barn til q
		else
			q.høyre = p; // høyre barn til q

		antall++; // én verdi mer i treet
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
				p = p.høyre;
			else
				return true;
		}

		return false;
	}

	@Override
	public boolean fjern(T verdi) {
		if (verdi == null)
			return false; // treet har ingen nullverdier

		Node<T> p = rot, q = null; // q skal være forelder til p

		while (p != null) // leter etter verdi
		{
			int cmp = comp.compare(verdi, p.verdi); // sammenligner
			if (cmp < 0) {
				q = p;
				p = p.venstre;
			} // går til venstre
			else if (cmp > 0) {
				q = p;
				p = p.høyre;
			} // går til høyre
			else
				break; // den søkte verdien ligger i p
		}
		if (p == null)
			return false; // finner ikke verdi

		Node<T> b = p.venstre != null ? p.venstre : p.høyre; // b for barn

		if (p.venstre == null && p.høyre == null) {
			if (p == rot) {
				rot = b;
			} else if (p == q.venstre) {
				q.venstre = null;
			} else {
				q.høyre = null;
			}
		} else if (p.venstre == null || p.høyre == null) {
			if (p == rot) {
				rot = b;
				b.forelder = null;
			} else if (p == q.venstre) {
				q.venstre = b;
				b.forelder = q;
			} else {
				q.høyre = b;
				b.forelder = q;
			}
		} else // Tilfelle 3)
		{
			Node<T> s = p, r = p.høyre; // finner neste i inorden
			while (r.venstre != null) {
				s = r; // s er forelder til r
				r = r.venstre;
			}

			p.verdi = r.verdi; // kopierer verdien i r til p

			if (s != p)
				s.venstre = r.høyre;
			else
				s.høyre = r.høyre;

		}

		antall--; // det er nå én node mindre i treet
		return true;
	}

	public int fjernAlle(T verdi) {
		int antallFjernet = 0;
		while (inneholder(verdi)) {
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
			p = cmp < 0 ? p.venstre : p.høyre;
		}
		return antall;
	}

	@Override
	public boolean tom() {
		return antall == 0;
	}

	@Override
	public void nullstill() {
		while (rot != null) {
			fjern(rot.verdi);
		}
	}

	private static <T> Node<T> nesteInorden(Node<T> p) {
		if (p.høyre != null) {
			p = p.høyre;
			while (p.venstre != null) {
				p = p.venstre;
			}
		} else {
			while (p.forelder != null && p.forelder.høyre == p) {
				p = p.forelder;
			}
			if (p.forelder == null)
				return null;
			p = p.forelder;
		}
		return p;
	}

	@Override
	public String toString() {
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (tom())
			return s.toString();

		Node<T> x = rot;
		while (x.venstre != null) {
			x = x.venstre;
		}

		for (int i = 0; i < antall; i++) {
			Node<T> temp = nesteInorden(x);
			s.add(x.verdi.toString());
			x = temp;
		}
		return s.toString();
	}

	public String omvendtString() {
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (tom())
			return s.toString();
		ArrayDeque<Node<T>> stakk = new ArrayDeque<Node<T>>();

		Node<T> p = rot;
		while (p.høyre != null) {
			stakk.add(p);
			p = p.høyre;
		}

		while (true) {

			s.add(p.toString());
			if (p.venstre != null) {
				p = p.venstre;
				while (p.høyre != null) {
					stakk.add(p);
					p = p.høyre;
				}
			} else if (!stakk.isEmpty()) {
				p = stakk.pollLast();
			} else {
				break;
			}
		}
		return s.toString();

	}

	public String høyreGren() {
		StringJoiner s = new StringJoiner(", ", "[", "]");

		if (tom())
			return s.toString();

		Node<T> x = rot;
		s.add(rot.verdi.toString());
		while (true) {
			if (x.høyre != null) {
				x = x.høyre;
				s.add(x.verdi.toString());
				continue;
			} else if (x.venstre != null) {
				x = x.venstre;
				s.add(x.verdi.toString());
				continue;
			}
			break;
		}

		return s.toString();
	}

	public String lengstGren() {
		StringJoiner s = new StringJoiner(", ", "[", "]");

		if (tom()) return s.toString();
		
		Node<T> endNode = omvendtNivåorden();
		
		Stack<Node<T>> stakk = new Stack<Node<T>>();
		
		while(endNode != null){
			stakk.push(endNode);
			endNode = endNode.forelder;
		}
		
		while(!stakk.isEmpty()){
			s.add(stakk.pop().verdi.toString());
		}

		return s.toString();
	}

	
	//Tatt fra 5.1.6 nivåorden - Byttet ut Kø med Java sin Queue
	public Node<T> omvendtNivåorden() // skal ligge i class BinTre
	{
		Queue<Node<T>> kø = new ArrayDeque<>(); // Se Avsnitt 4.2.2
		kø.add(rot); // legger inn roten
		Node<T> value = rot;
		
		while (!kø.isEmpty()) // så lenge som køen ikke er tom
		{
			Node<T> p = kø.remove(); // tar ut fra køen

			value = p;
			
			if (p.høyre != null)
				kø.add(p.høyre);
			if (p.venstre != null)
				kø.add(p.venstre);
			
		}
		return value;
		
	}

	public String[] grener() {
		List<Node<T>> list = new ArrayList<Node<T>>();
		finnBlader(rot,list);
		StringJoiner temp;
		Stack<Node<T>> stakk;
		
		String[] grener = new String[list.size()];
		for(int i = 0; i<list.size(); i++){
			temp = new StringJoiner(", ","[","]");
			stakk = new Stack<Node<T>>();
			
			Node<T> leaf = list.get(i);
			while(leaf != null){
				stakk.push(leaf);
				leaf = leaf.forelder;
			}
			while(!stakk.isEmpty()){
				temp.add(stakk.pop().verdi.toString());
			}
			
			grener[i] = temp.toString();
		}
		
		return grener;
	}
	
	//Tok inspirasjon fra metoden beskrevet under første svar i denne linken
	//http://stackoverflow.com/questions/15365042/print-leaf-nodes-of-binary-tree
	public void finnBlader(Node<T> t,List<Node<T>> s){
		if(t == null)return;
		if(t.venstre == null && t.høyre == null)s.add(t);
		finnBlader(t.venstre,s);
		finnBlader(t.høyre,s);
	}
	
	public void finnBlader(Node<T> t,StringJoiner s){
		if(t == null)return;
		if(t.venstre == null && t.høyre == null)s.add(t.verdi.toString());
		finnBlader(t.venstre,s);
		finnBlader(t.høyre,s);
	}
	
	

	public String bladnodeverdier() {
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (tom()) return s.toString();
		finnBlader(rot,s);
		return s.toString();
	}

	public String postString() {
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (tom()) return s.toString();
		Node<T> node =  finnPostNode(rot); //Finner første postorden Node
		
		while(true){
			s.add(node.toString());
			if(node.forelder == null)break;
			if(node.forelder.høyre == null || node.forelder.høyre == node){
				node = node.forelder;
			}else{
				node = node.forelder.høyre;
				node = finnPostNode(node);
			}
			
		}
		return s.toString();
	}
	
	private Node<T> finnPostNode(Node<T> node){
		while(true){
			if(node.venstre != null){
				node = node.venstre;
				continue;
			}else if(node.høyre != null){
				node = node.høyre;
				continue;
			}
			break;
		}
		return node;
	}
	


	@Override
	public Iterator<T> iterator() {
		return new BladnodeIterator();
	}

	private class BladnodeIterator implements Iterator<T> {
		private Node<T> p = rot, q = null;
		private boolean removeOK = false;
		private int iteratorendringer = endringer;

		private BladnodeIterator() // konstruktør
		{
			if(!hasNext()) return;
			p = finnPostNode(rot);
			q = rot;
		}
		
		

		@Override
		public boolean hasNext() {
			return p != null; // Denne skal ikke endres!
		}

		@Override
		public T next() {
			Node<T> node = p;
			while(true){
				if(node.forelder == null) throw new NoSuchElementException();
				if(node.forelder.høyre == null || node.forelder.høyre == node){
					node = node.forelder;
					continue;
				}else{
					node = node.forelder.høyre;
					p = finnPostNode(node);
					return p.verdi;
				}
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Ikke kodet ennå!");
		}

	} // BladnodeIterator

} // ObligSBinTre