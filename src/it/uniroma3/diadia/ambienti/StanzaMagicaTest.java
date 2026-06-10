package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private Stanza s1;
	private Attrezzo a;
	
	@BeforeEach
	void setUp() {
		s1 = new StanzaMagica("n11");
		a = new Attrezzo("martello",4);
	}
	
	/* Test addAttrezzo*/
	@Test
	void testAddAttrezzoNormale() {
		assertTrue(s1.addAttrezzo(a));
	}
	
	@Test
	void testAddAttrezzoStanzaPiena() {
		for(int i=0; i<10; i++)
	        s1.addAttrezzo(new Attrezzo("att"+i, 1));
		assertFalse(s1.addAttrezzo(a));
	}

	
	/*Test modificaAttrezzo*/
	@Test
	void testModificaAttrezzoDopoSoglia() {
		for(int i=0; i<3; i++) {
			Attrezzo att= new Attrezzo("att"+i,1);
			s1.addAttrezzo(att);
		}
		s1.addAttrezzo(this.a);
		assertNotNull(s1.getAttrezzo("olletram"));
		assertEquals(a.getPeso() * 2,s1.getAttrezzo("olletram").getPeso());
	}
	
	@Test
	void testModificaAttrezzoPrimaSoglia() {
		Attrezzo spada = new Attrezzo("spada",2);
		s1.addAttrezzo(spada);
		s1.addAttrezzo(this.a);
		assertNotNull(s1.getAttrezzo("martello"));
		assertEquals(a.getPeso(),s1.getAttrezzo("martello").getPeso());
	}
	
	@Test
	void testModificaAttrezzoSullaSoglia() {
		Attrezzo spada = new Attrezzo("spada",2);
		Attrezzo cacciavite = new Attrezzo("cacciavite", 4);
		s1.addAttrezzo(cacciavite);
		s1.addAttrezzo(spada);
		s1.addAttrezzo(this.a);
		assertNotNull(s1.getAttrezzo("martello"));
		assertEquals(a.getPeso(),s1.getAttrezzo("martello").getPeso());
	}
	
}
