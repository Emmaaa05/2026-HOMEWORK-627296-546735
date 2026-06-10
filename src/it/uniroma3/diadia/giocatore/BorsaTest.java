package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class BorsaTest {
	private Borsa b;
	private Attrezzo cacciavite;
	
	@BeforeEach
	void setup() {
		b=new Borsa();
		cacciavite = new Attrezzo("cacciavite",6);
	}
	
	@Test
	void getBorsaInizializzata() {
		assertTrue(b.isEmpty());
		assertEquals(10,b.getPesoMax());
		assertEquals("\nContenuto borsa: vuota", b.toString());
	}

	/*Test addAttrezzo*/
	@Test
	void addAttrezzo() {
		assertTrue(b.addAttrezzo(cacciavite));
	}
	
	@Test
	void addAttrezzoFalliscePeso() {
		Attrezzo pala= new Attrezzo("pala",10);
		b.addAttrezzo(pala);
		assertFalse(b.addAttrezzo(cacciavite));
	}
	
	/*Sparito causa refactoring -> Borsa non ha piu' peso max
	 * @Test
	void addAttrezzoFallisceSpazio() {
		for(int i=0;i<10;i++)
			b.addAttrezzo(new Attrezzo("att i",0));
		assertFalse(b.addAttrezzo(cacciavite));
	}
	*/
	
	/*Test getPeso */
	@Test
	void getPesoVuoto() {
		assertEquals(0,b.getPeso());
	}
	
	@Test
	void getPesoDopoAggiunta() {
		b.addAttrezzo(cacciavite);
		assertEquals(6,b.getPeso());
	}
	
	
	/* Test hasAttrezzo*/
	@Test
	void hasAttrezziVuota() {
		assertFalse(b.hasAttrezzo("osso"));
	}
	
	@Test
	void hasAttrezziNonTrova() {
		b.addAttrezzo(cacciavite);
		assertFalse(b.hasAttrezzo("osso"));
	}
	
	@Test
	void hasAttrezziTrova() {
		b.addAttrezzo(cacciavite);
		assertTrue(b.hasAttrezzo("cacciavite"));
	}
	
	/* Test addAttrezzo */
	@Test
	void getAttrezziVuota() {
		assertNull(b.getAttrezzo("osso"));
	}
	
	@Test
	void getAttrezziNonPresente() {
		b.addAttrezzo(cacciavite);
		assertNull(b.getAttrezzo("osso"));
	}
	
	@Test
	void getAttrezziPresente() {
		b.addAttrezzo(cacciavite);
		assertEquals(cacciavite,b.getAttrezzo("cacciavite"));
	}
	
	/* Test removeAttrezzo */
	@Test
	void removeAttrezzoVuoto() {
		assertNull(b.removeAttrezzo("cacciavite"));
	}
	
	@Test
	void removeAttrezzoAssente() {
		b.addAttrezzo(cacciavite);
		assertNull(b.removeAttrezzo("osso"));
	}
	
	@Test
	void removeAttrezzoPresente() {
		b.addAttrezzo(cacciavite);
		assertEquals(cacciavite,b.removeAttrezzo("cacciavite"));
	}
	
	/* Test getContenuto etc */
	@Test
	void getContenutoOrdinatoPerPeso() {
		b.addAttrezzo(cacciavite);
		Attrezzo pala= new Attrezzo("pala",1);
		b.addAttrezzo(pala);
		assertNotNull(b.getContenutoOrdinatoPerPeso());
		assertEquals(pala, b.getContenutoOrdinatoPerPeso().get(0));
		assertEquals(cacciavite, b.getContenutoOrdinatoPerPeso().get(1));
	}
	
	@Test
	void getContenutoOrdinatoPerNome() {
		b.addAttrezzo(cacciavite);
		Attrezzo pala= new Attrezzo("pala",1);
		b.addAttrezzo(pala);
		assertNotNull(b.getContenutoOrdinatoPerNome());
		assertEquals("cacciavite", b.getContenutoOrdinatoPerNome().first().getNome());
		assertEquals("pala", b.getContenutoOrdinatoPerNome().last().getNome());
	}
	
	@Test
	void getContenutoRaggruppatoPerPeso() {
		b.addAttrezzo(cacciavite);
		Attrezzo pala= new Attrezzo("pala",1);
		Attrezzo martello= new Attrezzo("martello",1);
		b.addAttrezzo(pala);
		b.addAttrezzo(martello);
		Map<Integer, Set<Attrezzo>> m = b.getContenutoRaggruppatoPerPeso();
		
		assertNotNull(m);
		assertEquals(2,m.size());
		assertEquals(1, m.get(6).size());
		assertEquals(2, m.get(1).size());

		assertTrue(m.get(1).contains(martello));
		assertTrue(m.get(1).contains(pala));
		assertTrue(m.get(6).contains(cacciavite));
	}
	
	@Test
	void getSortedOrdinatoPerPeso() {
		b.addAttrezzo(cacciavite);
		Attrezzo pala= new Attrezzo("pala",1);
		b.addAttrezzo(pala);
		assertNotNull(b.getContenutoOrdinatoPerPeso());
		assertEquals("pala", b.getContenutoOrdinatoPerNome().first().getNome());
		assertEquals("cacciavite", b.getContenutoOrdinatoPerNome().last().getNome());
	}
}
