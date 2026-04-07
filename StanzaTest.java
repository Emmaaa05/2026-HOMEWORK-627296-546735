import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaTest {
	private Stanza s1;
	private Stanza s2;
	private Attrezzo a;
	
	@BeforeEach
	void setUp() {
		s1 = new Stanza("n11");
		s2 = new Stanza("n10");
		a = new Attrezzo("martello",5);
	}
	
	/* Test addAttrezzo*/
	@Test
	void testAddAttrezzoNormale() {
		assertTrue(s1.addAttrezzo(a));
	}
	
	void testAddAttrezzoStanzaPiena() {
		for(int i=0; i<10; i++)
	        s1.addAttrezzo(new Attrezzo("att"+i, 1));
		assertFalse(s1.addAttrezzo(a));
	}

	/* Test hasAttrezzo*/
	@Test
	void testHasAttrezzoPresente() {
		s1.addAttrezzo(a);
		assertTrue(s1.hasAttrezzo(a.getNome()));
	}
	
	void testHasAttrezzoAssente() {
		assertFalse(s1.hasAttrezzo("cacciavite"));
	}
	
	void testHasAttrezzoDopoAggiunta() {
		s1.addAttrezzo(a);
		assertTrue(s1.hasAttrezzo("martello"));
	}
	
	
	/* Test getAttrezzo */
	@Test
	void testGetAttrezzoPresente() {
		s1.addAttrezzo(a);
		assertEquals(a,s1.getAttrezzo(a.getNome()));
	}
	
	void testGetAttrezzoAssente() {
		s1.addAttrezzo(a);
		assertNull(s1.getAttrezzo("cacciavite"));
	}
	
	void testGetAttrezzoDopoAggiunta() {
		s1.addAttrezzo(a);
		assertNotNull(s1.getAttrezzo(a.getNome()));
	}
	
	
	/* Test setStanzaAdiacente*/
	@Test
	void testSetStanzAdiacentePresente() {
		s1.impostaStanzaAdiacente("nord",s2);
		assertEquals(s2,s1.getStanzaAdiacente("nord"));
	}
	
	void testSetStanzAdiacenteAssente() {
		assertNull(s1.getStanzaAdiacente("nord"));
	}
	
	void testSetStanzAdiacenteDirezioneDiversa() {
		s1.impostaStanzaAdiacente("nord",s2);
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	
	
}
}
