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
	
	/* per testare la funzione addAttrezzo, normalmente e al limite*/
	@Test
	void testAddAttrezzo() {
		assertTrue(s1.addAttrezzo(a));
		
		for(int i=0; i<10; i++)
	        s1.addAttrezzo(new Attrezzo("att"+i, 1));
		assertFalse(s1.addAttrezzo(a));
	}

	/* per testare la funzione hasAttrezzo*/
	@Test
	void testHasAttrezzo() {
		s1.addAttrezzo(a);
		assertTrue(s1.addAttrezzo(a));
		assertTrue(s1.hasAttrezzo(a.getNome()));
		assertFalse(s1.hasAttrezzo("cacciavite"));
	}
	
	/* per testare la funzione getAttrezzo */
	@Test
	void testGetAttrezzo() {
		s1.addAttrezzo(a);
		assertTrue(s1.addAttrezzo(a));
		assertEquals(a,s1.getAttrezzo(a.getNome()));
	}
	
	
	@Test
	void testSetStanzAdiacente() {
		s1.impostaStanzaAdiacente("nord",s2);
		assertEquals(s2,s1.getStanzaAdiacente("nord"));
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	
}