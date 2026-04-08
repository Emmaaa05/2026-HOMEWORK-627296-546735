package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.io.IOConsole;

//import it.uniroma3.diadia.io.IOConsole;
//import it.uniroma3.it.diadia.ambienti.Stanza;


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
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.io = io;
		this.partita = new Partita();
	}

	@SuppressWarnings("resource")
	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi();
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa();
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");;
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio(" ");;
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare?");
			return; }
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

/*Comando "Prendi"
	 Chiede all'utente l'attrezzo che vuole rimuovere. Controlla se
	 l'attrezzo e'presente bella stanza e se la borsa non e' piena,
	 e lo fa prendere dal giocatore per inserirlo nella sua borsa,
	 altrimenti stampa messaggio di errore. 
	 */
	 
	private void prendi() {
	 	Stanza s=this.partita.getStanzaCorrente();
		if(s.getNumeroAttrezzi()==0) {
			io.mostraMessaggio("Non ci sono attrezzi da in questa stanza");
			return;
			}
			
		String nomeAtt;
		io.mostraMessaggio("Quale attrezzo vuoi prendere?");
		nomeAtt=io.leggiRiga();
		
		Borsa b = this.partita.getGiocatore().getBorsa();
		if(!s.hasAttrezzo(nomeAtt)){ 
			io.mostraMessaggio("L'attrezzo non e' presente nella stanza corrente");
			return;
		}
		Attrezzo attrezzo= s.getAttrezzo(nomeAtt);
		if(b.getPeso() + attrezzo.getPeso() > b.getPesoMax()){
			io.mostraMessaggio("La borsa e' piena, non puo' contenere ulteriori attrezzi");
			return;
		} else{
				if(b.addAttrezzo(attrezzo)) {
				s.removeAttrezzo(nomeAtt);
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
				io.mostraMessaggio("Hai preso l'attrezzo e l'hai messo nella tua borsa!");
				}
	 		}
	 }
		
		/*Comando "Posa"
		Chiede all'utente l'attrezzo che vuole posare. Controlla se
	 	l'attrezzo e'presente nella borsa e se la stanza non e' piena,
	 	e lo fa prendere dal giocatore, altrimenti stampa messaggio di errore.
		*/
		
		 private void posa() {
			Borsa b = this.partita.getGiocatore().getBorsa();
			if(b.isEmpty()) {
				io.mostraMessaggio("La borsa e' vuota");
				return;}
		
			String nomeAtt;
			io.mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAtt=io.leggiRiga();
			if(!b.hasAttrezzo(nomeAtt)){
				io.mostraMessaggio("L'attrezzo non e' presente nella borsa");
				return;
			} else{ 
				Attrezzo attrezzo= b.getAttrezzo(nomeAtt);
				if(!this.partita.getStanzaCorrente().addAttrezzo(attrezzo)){
					io.mostraMessaggio("Non puoi posare attrezzi in questa stanza, perche' e' piena!");
					return;
				} else{
					b.removeAttrezzo(nomeAtt);
					io.mostraMessaggio(partita.getStanzaCorrente().toString());
					io.mostraMessaggio("Hai preso l'attrezzo dalla tua borsa e l'hai posato nella stanza!");
				}
			}
		}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}
