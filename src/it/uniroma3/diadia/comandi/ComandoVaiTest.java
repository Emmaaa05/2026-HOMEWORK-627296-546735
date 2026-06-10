package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.*;

class ComandoVaiTest {
	private Partita partita;
	private IOConsole io;
	private Scanner scanner=new Scanner(System.in);
	
	@Test
	void testVaiInMonolacle() {
		Labirinto lab =Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Atrio")
				.build();
		this.partita=new Partita(lab);
		this.io=new IOConsole(scanner);
		ComandoVai com=new ComandoVai(io);
		com.setParametro("nord");
		com.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiInBilocale() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.build();
		this.partita=new Partita(lab);
		this.io=new IOConsole(scanner);
		ComandoVai com=new ComandoVai(io);
		com.setParametro("nord");
		com.esegui(partita);
		assertEquals("Biblioteca",partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiDirezioneInesistente() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.build();
		this.partita=new Partita(lab);
		this.io=new IOConsole(scanner);
		ComandoVai com=new ComandoVai(io);
		com.setParametro("sud");
		com.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
	}

	@Test
	void testVaiParametroAssente() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.build();
		this.partita=new Partita(lab);
		this.io=new IOConsole(scanner);
		ComandoVai com=new ComandoVai(io);
		com.esegui(partita);
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
	}
	
	void testVaiInTrilocale() {
		Labirinto lab =Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Aula 1")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.addAdiacenza("Biblioteca", "Aula 1", Direzione.est)
				.build();	
		this.partita=new Partita(lab);
		this.io=new IOConsole(scanner);
		ComandoVai com=new ComandoVai(io);
		com.setParametro("nord");
		com.esegui(partita);
		assertEquals("Biblioteca",partita.getStanzaCorrente().getNome());
		com.setParametro("est");
		com.esegui(partita);
		assertEquals("Aula 1",partita.getStanzaCorrente().getNome());
	}
}
