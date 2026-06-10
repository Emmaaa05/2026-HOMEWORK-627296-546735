package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Stanza.Direzione;

public class ComandoVai extends AbstractComando {
	private Direzione direzione;
	private IO io;		
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	public ComandoVai(IO io) {
		this.io=io;
	}

	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(this.direzione==null) {
			this.io.mostraMessaggio("Dove vuoi andare? Specifica una direzione!");
			return; }
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione=Direzione.valueOf(parametro);
	}
	
	public String getParametro() {
		return this.direzione.toString();
	}
}
