import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PartitaTest {
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		partita=new Partita();
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
		partita.setCfu(0);
		assertTrue(partita.isFinita());
	}
	
	/* Test cfu*/
	@Test
	void testGetCfuIniziale() {
		assertEquals(20,partita.getCfu());
	}
	
	@Test
	void testSetCfu() {
		partita.setCfu(5);
		assertEquals(5,partita.getCfu());
	}
	
	@Test
	void testCfuZero() {
		partita.setCfu(0);
		assertEquals(0,partita.getCfu());
	}
}
