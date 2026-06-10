package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.*;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_SALUTATA = "Con una mia magica azione," +
			" ti trasferiro' in una stanza con molti attrezzi. Cogli l'occasione per armarti!";
	private static final String MESSAGGIO_NONSALUTATA = "Giovane, dove sono le tue buone maniere?"
			+ "Fuori dalla mia vista!";
	private static final String MESSAGGIO_REGALO = "Vuoi regalarmi qualcosa?..." + 
			" AHAHAH ed io che pesnavo sarebbe stato qualcosa di grandioso!" + 
			"Lo terro' dato che mi hai fatto molto ridere";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
	Stanza corrente=partita.getStanzaCorrente();
	Stanza scelta=null;
	
	for(Stanza s:corrente.getStanzeAdiacenti().values()) {
		if(scelta==null) {
			scelta=s;
		}else if(!this.haSalutato() 
				&& s.getNumeroAttrezzi() < scelta.getNumeroAttrezzi()) {
			scelta=s;
		}else if(this.haSalutato() 
				&& s.getNumeroAttrezzi() > scelta.getNumeroAttrezzi()) {
			scelta=s;
			}
		}
	if(scelta!=null)
		partita.setStanzaCorrente(scelta);
	if(this.haSalutato())
		return MESSAGGIO_SALUTATA;
	
	return MESSAGGIO_NONSALUTATA;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo,Partita partita) {
		return MESSAGGIO_REGALO;
	}
	
}
