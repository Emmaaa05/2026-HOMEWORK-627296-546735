package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class FabbricaComandiIntrospettivaTest {
	private IOConsole io;
	private Scanner scanner = new Scanner(System.in);
	private FabbricaDiComandiFisarmonica f;
	
	@BeforeEach
	void setUp() {
		this.io=new IOConsole(scanner);
		this.f = new FabbricaDiComandiFisarmonica(io);
	}
	
	/*Test su AbstractComando*/
	@Test
	void testAbstractComando() {
		ComandoVai cv = new ComandoVai(io);
		cv.setParametro("est");
		assertEquals("est",cv.getParametro());
		
		ComandoGuarda cg = new ComandoGuarda(io);
		Labirinto l = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Atrio")
				.build();
		Partita p = new Partita(l);
		cg.esegui(p);
	}
	
	/*Test vari comandi*/
	@Test
	void testComandoInesistente() {
		Comando c = f.costruisciComando("salto");
		assertTrue(ComandoNonValido.class.isInstance(c));
	}
	
	@Test
	void testComandoGuarda() {
		Comando c = f.costruisciComando("guarda");
		assertTrue(ComandoGuarda.class.isInstance(c));
	}

	@Test
	void testComandoAiuto() {
		Comando c = f.costruisciComando("aiuto");
		assertTrue(ComandoAiuto.class.isInstance(c));
	}

	@Test
	void testComandoFine() {
		Comando c = f.costruisciComando("fine");
		assertTrue(ComandoFine.class.isInstance(c));
	}
	
	@Test
	void testCostruzioneComandoVai() {
		Comando c = f.costruisciComando("vai est");
		assertTrue(ComandoVai.class.isInstance(c));
		ComandoVai cv = (ComandoVai) c;
		assertEquals("est",cv.getParametro());
	}

	@Test
	void testCostruzioneComandoPrendi() {
		Comando c = f.costruisciComando("prendi osso");
		assertTrue(ComandoPrendi.class.isInstance(c));
		ComandoPrendi cv = (ComandoPrendi) c;
		assertEquals("osso",cv.getParametro());
	}
	
	@Test
	void testCostruzioneComandoPosa() {
		Comando c = f.costruisciComando("posa osso");
		assertTrue(ComandoPosa.class.isInstance(c));
		ComandoPosa cv = (ComandoPosa) c;
		assertEquals("osso",cv.getParametro());
	}
	
}
