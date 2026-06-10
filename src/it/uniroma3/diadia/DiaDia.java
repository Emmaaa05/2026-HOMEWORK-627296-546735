package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Stanza.Direzione;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Inoltre, puoi ordinare gli attrezzi nella tua borsa o persino raggrupparli!\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private Partita partita;
	private IO io;
	private Labirinto labirinto;

	public DiaDia(IO io, Labirinto labirinto) {
		this.io = io;
		this.labirinto=labirinto;
		this.partita = new Partita(this.labirinto);
	}

	//@SuppressWarnings("resource")
	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {		
			istruzione = io.leggiRiga();
			if(istruzione == null)
				break;
		}while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		
		FabbricaDiComandiFisarmonica factory=new FabbricaDiComandiFisarmonica(this.io);
		
		comandoDaEseguire = factory.costruisciComando(istruzione);		
		comandoDaEseguire.esegui(this.partita);
		
		if(this.partita.vinta())
				io.mostraMessaggio("Hai Vinto!");
		if(this.partita.getGiocatore().getCfu()== 0)
				io.mostraMessaggio("Hai finito i CFU :(");
		return this.partita.isFinita();
	}   

	public Partita getPartita() {
		return this.partita;
	}
	

	public static void main(String[] argc) {
		try (Scanner scanner = new Scanner(System.in)) {
			IO io = new IOConsole(scanner);
		
		Labirinto labirinto=Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
			    .addStanza("Aula N11")
			    .addStanza("Aula N10")
			    .addAttrezzo("lanterna", 3)
			    .addStanza("Laboratorio Campus")
			    .addStanzaVincente("Biblioteca")

			    .addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
			    .addAdiacenza("Atrio", "Aula N11", Direzione.est)
			    .addAdiacenza("Atrio", "Aula N10", Direzione.sud)
			    .addAdiacenza("Atrio", "Laboratorio Campus", Direzione.ovest)

			    .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.est)
			    .addAdiacenza("Aula N11", "Atrio", Direzione.ovest)

			    .addAdiacenza("Aula N10", "Atrio", Direzione.nord)
			    .addAdiacenza("Aula N10", "Aula N11", Direzione.est)
			    .addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.ovest)

			    .addAdiacenza("Laboratorio Campus", "Atrio", Direzione.est)
			    .addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.ovest)

			    .addAdiacenza("Biblioteca", "Atrio", Direzione.sud)

			    .build();
						
		DiaDia gioco = new DiaDia(io,labirinto);
		gioco.gioca();
		}
	}
}