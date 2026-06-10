package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.configurazione.Configurazione;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.HashSet;

public class Borsa{ 
		//public final static int DEFAULT_PESO_MAX_BORSA = 10; -> Refactor
		private Map<String,Attrezzo> attrezzi; 
		private int pesoMax; 
		
		public Borsa() { 
				this(Configurazione.getPesoMaxBorsa()); 
		} 

	public Borsa(int pesoMax) { 
				this.pesoMax = pesoMax; 
				this.attrezzi = new HashMap<>();
	} 
	public boolean addAttrezzo(Attrezzo attrezzo) { 
				if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) 
					return false; 
				if(this.attrezzi.containsKey(attrezzo.getNome()))
					return false; 
				this.attrezzi.put(attrezzo.getNome(),attrezzo);
				return true;
	} 

	public int getPesoMax() { 
				return pesoMax; 
	} 
	public Attrezzo getAttrezzo(String nomeAttrezzo) { 
				return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
				int peso = 0; 
				for(Attrezzo a: this.attrezzi.values())
					peso += a.getPeso();
				return peso;
	}
 
	public boolean isEmpty() { 
				return this.attrezzi.isEmpty(); 
	}
 
	public boolean hasAttrezzo(String nomeAttrezzo) { 
				return this.attrezzi.containsKey(nomeAttrezzo); 
	} 

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public String toString() { 
			StringBuilder s = new StringBuilder();
			s.append("\nContenuto borsa");
			if(this.attrezzi.isEmpty())
				return s.append(": vuota").toString();
			s.append("(" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for(Attrezzo a: this.attrezzi.values())
				s.append(a.toString() + " ");
			return s.toString();		
	} 
	
	public String toStringOrdinatoPerPeso() { 
		StringBuilder s = new StringBuilder();
		s.append("\nContenuto borsa");
		if(this.attrezzi.isEmpty())
			return s.append(": vuota").toString();
		s.append("(" + this.getPeso() + "kg/");
		s.append(this.getPesoMax() + "kg): " );
		s.append(this.getContenutoOrdinatoPerPeso());
		return s.toString();		
	}
	
	public String toStringOrdinatoPerNome() { 
		StringBuilder s = new StringBuilder();
		s.append("\nContenuto borsa");
		if(this.attrezzi.isEmpty())
			return s.append(": vuota").toString();
		s.append("(" + this.getPeso() + "kg/");
		s.append(this.getPesoMax() + "kg): " );
		s.append(this.getContenutoOrdinatoPerNome());
		return s.toString();	
	} 
	
	public String toStringRaggruppatoPerPeso() { 
		StringBuilder s = new StringBuilder();
		s.append("\nContenuto borsa");
		if(this.attrezzi.isEmpty())
			return s.append(": vuota").toString();
		s.append("(" + this.getPeso() + "kg/");
		s.append(this.getPesoMax() + "kg): " );
		Map<Integer,Set<Attrezzo>> m = this.getContenutoRaggruppatoPerPeso(); 
		for(Integer peso : m.keySet()) {
	        s.append("(" + peso + ", { ");
	        for(Attrezzo a : m.get(peso)) {
	            s.append(a.toString()).append(" ");
	        }
	        s.append("}) ; ");
	    }
		return s.toString();	
	} 
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> l = new ArrayList<>(this.attrezzi.values());
		Collections.sort(l);
		return l;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> l = new TreeSet<>(new ComparatorePerNome());
		l.addAll(this.attrezzi.values());
		return l;
	}
	
	public static class ComparatorePerNome implements Comparator<Attrezzo> {
	    @Override
	    public int compare(Attrezzo a1, Attrezzo a2) {
	        return a1.getNome().compareTo(a2.getNome());
	    }
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<>();
		for(Attrezzo a: this.attrezzi.values()) {
			int peso=a.getPeso();
			if(!mappa.containsKey(peso))
				mappa.put(peso, new HashSet<>() );
			
			mappa.get(peso).add(a);
		}
		return mappa;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> l = new TreeSet<>();
		l.addAll(this.attrezzi.values());
		return l;
	}
	
}

