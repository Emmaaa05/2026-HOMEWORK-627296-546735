package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

class PartitaTest {
	private Partita partita;
	private Labirinto l;
	
	@BeforeEach
	void setUp() {
	this.l = Labirinto.newBuilder()
			.addStanzaIniziale("salotto")
			.addStanzaVincente("camera")
			.addAttrezzo("letto", 2)
			.addAdiacenza("salotto", "camera", Direzione.nord)
			.build();
		this.partita=new Partita(this.l);
	}
	
	/* Test getStanzaCorrente/setStanzaCorrente */
	@Test
	void testGetStanzaCorrenteIniziale() {
		assertNotNull(partita.getStanzaCorrente());
	}

	@Test
	void testSetStanzaCorrente() {
		Stanza nuova = new Stanza("Nuova");
		partita.setStanzaCorrente(nuova);
		assertEquals(nuova,partita.getStanzaCorrente());
	}
	
	@Test
	void testGestStanzaCorrenteNull() {
		partita.setStanzaCorrente(null);
		assertNull(partita.getStanzaCorrente());
	}
	
	/* Test vinta*/
	@Test
	void testPartitaNonVinta() {
		assertFalse(partita.vinta());
	}
	
	@Test
	void testPartitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	void testPartitaVintaDopoSpostamento() {
		Stanza vittoria = partita.getStanzaVincente();
		partita.setStanzaCorrente(vittoria);
		assertTrue(partita.vinta());
	}
	
	/* Test isFinita*/
	@Test
	void testPartitaNonFinita() {
		assertFalse(partita.isFinita());
	}
	
	@Test
	void testPartitaFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	
	@Test
	void testPartitaFinitaDopoSpostamento() {
		Stanza vittoria = partita.getStanzaVincente();
		partita.setStanzaCorrente(vittoria);
		assertTrue(partita.isFinita());
	}
	
	@Test
	void testPartitaFinitaCfuZero() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}
	
	/* Test cfu*/
	@Test
	void testGetCfuIniziale() {
		assertEquals(20,partita.getGiocatore().getCfu());
	}
	
	@Test
	void testSetCfu() {
		partita.getGiocatore().setCfu(5);
		assertEquals(5,partita.getGiocatore().getCfu());
	}
	
	@Test
	void testCfuZero() {
		partita.getGiocatore().setCfu(0);
		assertEquals(0,partita.getGiocatore().getCfu());
	}
}
