package oblig3;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, h�yre;    // venstre og h�yre barn
    private Node<T> forelder;          // forelder

    // konstrukt�r
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; h�yre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstrukt�r
    {
      this(verdi, null, null, forelder);
    }

    @Override
    public String toString(){ return "" + verdi;}

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder
  private int endringer;                          // antall endringer

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstrukt�r
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

		p = new Node<>(verdi,q); // oppretter en ny node

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
  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.h�yre;
      else return true;
    }

    return false;
  }
  
  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public int fjernAlle(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  @Override
  public int antall()
  {
    return antall;
  }
  
  public int antall(T verdi)
  {
	  int antall = 0;
	  Node<T> p = rot,q  = null;
	  int cmp = 0;
	  while (p != null)
	  {
		  if(p.verdi.equals(verdi)) antall++;
		  q = p; 
		  cmp = comp.compare(verdi, p.verdi); 
		  p = cmp < 0 ? p.venstre : p.h�yre; 
	  }
	  return antall;
  }
  
  @Override
  public boolean tom()
  {
    return antall == 0;
  }
  
  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  private static <T> Node<T> nesteInorden(Node<T> p)
  {
	  
	  
	  
	
  }
  
  @Override
  public String toString()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public String omvendtString()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public String h�yreGren()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public String lengstGren()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  public String postString()
  {
    throw new UnsupportedOperationException("Ikke kodet enn�!");
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    private int iteratorendringer = endringer;
    
    private BladnodeIterator()  // konstrukt�r
    {
      throw new UnsupportedOperationException("Ikke kodet enn�!");
    }
    
    @Override
    public boolean hasNext()
    {
      return p != null;  // Denne skal ikke endres!
    }
    
    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke kodet enn�!");
    }
    
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke kodet enn�!");
    }

  } // BladnodeIterator

} // ObligSBinTre