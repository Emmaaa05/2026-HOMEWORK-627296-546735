package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.attrezzi.*;

public class ComandoRegala extends AbstractComando{
	private static final String MESSAGGIO_CON_CHI =	
			"Con chi dovrei interagire?...";
	private IO io;
	private String regalo;
	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		AbstractPersonaggio p = corrente.getPersonaggio();
		if (p == null) {
	    	io.mostraMessaggio(MESSAGGIO_CON_CHI);
	    	return;}
		Borsa b = partita.getGiocatore().getBorsa();
		if(b.isEmpty()) {
			this.io.mostraMessaggio("La borsa e' vuota");
			return; }
	
		if (regalo == null) {
	        this.io.mostraMessaggio("Devi specificare quale attrezzo vuoi regalare!");
	        return;
	    }
		if(!b.hasAttrezzo(regalo)){
			this.io.mostraMessaggio("L'attrezzo non e' presente nella borsa");
			return;
		} else {
			Attrezzo a = b.removeAttrezzo(regalo);
			this.messaggio=p.riceviRegalo(a,partita);
			io.mostraMessaggio(messaggio);
		}
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.regalo=parametro;
		}
	
}
