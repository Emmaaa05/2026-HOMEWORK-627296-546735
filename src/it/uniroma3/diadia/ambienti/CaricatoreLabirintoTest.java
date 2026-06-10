package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class CaricatoreLabirintoTest {

	@Test
	void testMonolocale() throws IOException{
		String input=
				"Stanze:\n"+ "Atrio\n"+
				"Estremi:\n"+ "Atrio\n"+ "Atrio\n"+
				"Attrezzi:\n"+ "Osso 1 Atrio\n";
				
		CaricatoreLabirinto c = new CaricatoreLabirinto(new StringReader(input));
		c.carica();
		Labirinto l = c.getLabirinto();	
		assertNotNull(l.getStanzaIniziale());
		assertNotNull(l.getStanzaVincente());
	}

	@Test
	void testBilocale() throws IOException{
		String input=
				"Stanze:\n"+ "Atrio\n"+ "Biblioteca\n"+
				"Estremi:\n"+ "Atrio\n"+ "Biblioteca\n"+
				"Attrezzi:\n"+ "Osso 1 Atrio\n" +
				"Uscite:\n"+ "Atrio nord Biblioteca\n";
		
		CaricatoreLabirinto c = new CaricatoreLabirinto(new StringReader(input));
		c.carica();
		Labirinto l = c.getLabirinto();	
		assertNotNull(l.getStanzaVincente());
	}
	
	@Test
	void testPersonaggi() throws IOException {
	    String input =
	        "Stanze:\n" + "Atrio\n" +
	        "Estremi:\n" + "Atrio\n" + "Atrio\n" +
	        "Personaggi:\n" + "Strega Morgana Atrio\n";

	    CaricatoreLabirinto c =
	        new CaricatoreLabirinto(new StringReader(input));
	    c.carica();
	    Labirinto l = c.getLabirinto();

	    assertNotNull(l.getStanzaIniziale());
	}
	
	@Test
	void testStanzeParticolari() throws IOException {
	    String input =
	        "Stanze:\n" + "Atrio\n" + "Biblioteca\n"+
	        "Estremi:\n" + "Atrio\n" + "Biblioteca\n" +
	        "Uscite:\n"+ "Atrio nord Biblioteca\n"+
	        "Stanze Buie:\n" + "Atrio cacciavite\n"+
	    	"Stanze Chiuse:\n" + "Biblioteca sud lanterna\n";

	    CaricatoreLabirinto c =
	        new CaricatoreLabirinto(new StringReader(input));
	    c.carica();
	    Labirinto l = c.getLabirinto();

	    assertNotNull(l.getStanzaIniziale());
	}
	
}
