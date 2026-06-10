package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI =	
			"Con chi dovrei interagire?...";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
	    AbstractPersonaggio p = corrente.getPersonaggio();

	    if (p == null) {
	    	io.mostraMessaggio(MESSAGGIO_CON_CHI);
	    }
	    io.mostraMessaggio(p.saluta());
	}
	
	@Override
	public void setParametro(String parametro) {}
}