package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private Stanza s1;
	private Stanza s2;
	private Stanza s3;
	private Direzione dir;
	private Attrezzo a;
	
	@BeforeEach
	void setUp() {
		s1 = new StanzaBloccata("n11",Direzione.nord,"chiave");
		s2 = new Stanza("n10");
		s3 = new Stanza("mensa");
		dir = Direzione.nord;
		a = new Attrezzo("chiave",6);
		s1.impostaStanzaAdiacente(dir, s2);
		s1.impostaStanzaAdiacente(Direzione.sud, s3);
	}
	
	/*Test getStanzaAdiacente*/
	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertEquals(s1,s1.getStanzaAdiacente(dir));
		s1.addAttrezzo(this.a);
		assertEquals(s2,s1.getStanzaAdiacente(dir));
	}
	
	@Test
	void testGetStanzaAdiacenteNonBloccata() {
		assertEquals(s3,s1.getStanzaAdiacente(Direzione.sud));
		s1.addAttrezzo(this.a);
		assertEquals(s3,s1.getStanzaAdiacente(Direzione.sud));
	}
	
	/*Test getDescrizione*/
	@Test
	void testGetDescrizioneStanzaBloccata() {
		String descrizione = s1.getDescrizione();
		assertTrue(descrizione.contains("bloccata"));
	}
}
