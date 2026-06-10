package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private Stanza s1;
	private Attrezzo a;
	
	@BeforeEach
	void setUp() {
		s1 = new StanzaBuia("n11");
		a = new Attrezzo("lanterna",6);
	}
	
	/*Test getDescrizione*/
	@Test
	void testGetDescrizioneConAttrezzo() {
		s1.addAttrezzo(this.a);
		assertNotEquals("Qui c'e' buio pesto",s1.getDescrizione());
	}
	
	@Test
	void testGetDescrizioneSenzaAttrezzo() {
		String descrizione = s1.getDescrizione();
		assertTrue(descrizione.contains("buio"));
		assertTrue(descrizione.contains("pesto"));
	}
	
}

