package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_AVVERTIMENTO = "Oh no! " +
			"Il cane ti ha morso, ed hai perso 1 CFU. Attento!";
	private static final String MESSAGGIO_ATTREZZO = "Il cane ha " +
			"scambiato l'attrezzo per un giocattolo e l'ha buttato a terra!";
	private static final String MESSAGGIO_CIBO = "Wow, hai trovato " +
			"il suo cibo preferito! Ti e' molto grato";
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MESSAGGIO_AVVERTIMENTO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome()!="Cibo per cani") {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return MESSAGGIO_ATTREZZO;
		}
		return MESSAGGIO_CIBO;
	}
}