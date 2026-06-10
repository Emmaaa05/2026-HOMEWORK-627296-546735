package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import java.util.List;

class IOSimulatorTest {
	private DiaDia gioco;
	private IOSimulator io;
	private Labirinto labBilocale;
	private Labirinto labTrilocale;
	
	@BeforeEach
	void setUp() {
		this.labBilocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 2)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.build();
		
		this.labTrilocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 2)
				.addStanza("Aula 1")
				.addAttrezzo("libro", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.addAdiacenza("Biblioteca", "Atrio", Direzione.sud)
				.addAdiacenza("Atrio", "Aula 1", Direzione.est)
				.addAdiacenza("Aula 1", "Atrio", Direzione.ovest)
				.build();	
	}
	
	/* test partite*/
	@Test
	void testPartitaSemplice() {
		this.io = new IOSimulator(List.of("guarda","vai nord") );		
		this.gioco=new DiaDia(io,labBilocale);
		this.gioco.gioca();
		
		assertTrue(gioco.getPartita().vinta());
		}

	@Test
	void testPartitaFinita() {
		this.io = new IOSimulator(List.of("aiuto","fine") );
		this.gioco=new DiaDia(io,labBilocale);
		this.gioco.gioca();
		assertTrue(gioco.getPartita().isFinita());
		}
	
	@Test
	void testPartitaComandoVaiNonValido() {
		this.io = new IOSimulator(List.of("vai est","fine"));
		this.gioco=new DiaDia(io,labBilocale);
		this.gioco.gioca();
		boolean trovato=false;
		List<String> output = io.getOutput();
		for(String s : output) {
			if(s != null && s.contains("Direzione inesistente")) {
				trovato=true;
				break;
				}
			}
		assertTrue(trovato);
		}
	
	@Test
	void testPartitaPrendoAttrezzo() {
		this.io = new IOSimulator(List.of("prendi osso","vai est","posa osso","fine"));
		this.gioco=new DiaDia(io,labTrilocale);
		this.gioco.gioca();
		boolean trovato=false;
		List<String> output = io.getOutput();
		for(String s : output) {
			if(s != null && s.contains("Hai preso")) {
				trovato=true;
				break;
				}
			}
		assertTrue(trovato);
		trovato=false;
		for(String s : output) {
			if(s != null && s.contains("l'hai posato")) {
				trovato=true;
				break;
				}
			}
		assertTrue(trovato);
		assertTrue(gioco.getPartita().isFinita());
	}
	
	@Test
	void testPartitaUtilizzoCfu() {
		this.io = new IOSimulator(List.of("guarda","vai est","vai ovest","vai est","vai ovest","fine") );		
		this.gioco=new DiaDia(io,labTrilocale);
		this.gioco.gioca();
		
		assertEquals(16,gioco.getPartita().getGiocatore().getCfu());
		assertTrue(gioco.getPartita().isFinita());
		}
	
	void testPartitaCompleta() {
		this.io = new IOSimulator(List.of(
				"aiuto","guarda","prendi osso","vai est",
				"posa osso","prendi libro","vai ovest","fine") );		
		this.gioco=new DiaDia(io,labTrilocale);
		this.gioco.gioca();
		
		boolean trovato=false;
		List<String> output = io.getOutput();
		for(String s : output) {
			if(s != null && s.contains("libro")) {
				trovato=true;
				break;
				}
			}
		assertTrue(trovato);
		assertTrue(gioco.getPartita().isFinita());
		}
}
