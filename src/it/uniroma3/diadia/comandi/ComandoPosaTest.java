package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.ambienti.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ComandoPosaTest {
	private Partita partita;
	private Attrezzo cacciavite;
	private Comando comando;
	private IO io;
	private Scanner scanner=new Scanner(System.in);

	@BeforeEach
	void setUp() {
		Labirinto lab=Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Atrio")
				.build();
		this.partita= new Partita(lab);
		this.cacciavite= new Attrezzo("cacciavite",2);
		this.io=new IOConsole(scanner);
		this.comando = new ComandoPosa(this.io);
	}
	
	@Test
	void posaAttrezzoPresente() {
		partita.getGiocatore().getBorsa().addAttrezzo(cacciavite);
		comando.setParametro(cacciavite.getNome());
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void posaAttrezzoNonPresente() {
		partita.getGiocatore().getBorsa().addAttrezzo(cacciavite);
		assertEquals(cacciavite.getPeso(),partita.getGiocatore().getBorsa().getPeso());
		comando.setParametro("osso");
		comando.esegui(partita);
		assertEquals(cacciavite.getPeso(),partita.getGiocatore().getBorsa().getPeso());
	}
	
	@Test
	void posaAttrezzoBorsaVuota() {
		comando.setParametro(cacciavite.getNome());
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void posaAttrezzoNullo() {
		partita.getGiocatore().getBorsa().addAttrezzo(cacciavite);
		comando.setParametro(null);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void posaAttrezzoStanzaPiena() {
		partita.getGiocatore().getBorsa().addAttrezzo(cacciavite);
		for(int i=0; i<9; i++) {
			Attrezzo att= new Attrezzo("att"+i,1);
			partita.getStanzaCorrente().addAttrezzo(att);
		}
		comando.setParametro(cacciavite.getNome());
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().isEmpty());
	}
	
}
