package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	private Giocatore giocatore;
	
	
	@BeforeEach
	void setUp() {
		giocatore = new Giocatore(20);
	}
	
	@Test
	void getCfu() {
		assertNotNull(giocatore.getCfu());
	}
	
	@Test
	void setCfu() {
		giocatore.setCfu(5);
		assertEquals(5,giocatore.getCfu());
	}
	
	@Test
	void getBorsa() {
		assertNotNull(giocatore.getBorsa());
	}
	
	@Test
	void toStringInizializzato() {
		assertEquals(20,giocatore.getCfu());
	}
	
	@Test
	void toStringDopoModifica() {
		giocatore.setCfu(5);
		assertEquals(5,giocatore.getCfu());
	}

}
