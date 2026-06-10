package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando{
	private IO io;
	private String nomeAtt;
	
	/** Comando "Prendi"
	 Chiede all'utente l'attrezzo che vuole rimuovere. Controlla se
	 l'attrezzo e'presente bella stanza e se la borsa non e' piena,
	 e lo fa prendere dal giocatore per inserirlo nella sua borsa,
	 altrimenti stampa messaggio di errore. 
	 **/
	 
	public ComandoPrendi(IO io) {
		this.io=io;
	}
	
	
	@Override
	public void esegui(Partita partita) {
	 	Stanza s= partita.getStanzaCorrente();
		if(s.getNumeroAttrezzi()==0) {
			this.io.mostraMessaggio("Non ci sono attrezzi da in questa stanza");
			return;
			}
		
		if (nomeAtt == null) {
	        this.io.mostraMessaggio("Devi specificare quale attrezzo prendi!");
	        return;
	    }
		
		Borsa b = partita.getGiocatore().getBorsa();
		if(!s.hasAttrezzo(nomeAtt)){ 
			this.io.mostraMessaggio("L'attrezzo non e' presente nella stanza corrente");
			return;
		}
		Attrezzo attrezzo= s.getAttrezzo(nomeAtt);
		if(b.getPeso() + attrezzo.getPeso() > b.getPesoMax()){
			this.io.mostraMessaggio("La borsa e' piena, non puo' contenere ulteriori attrezzi");
			return;
		} else{
				if(b.addAttrezzo(attrezzo)) {
				s.removeAttrezzo(nomeAtt);
				this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
				this.io.mostraMessaggio("Hai preso l'attrezzo e l'hai messo nella tua borsa!");
				}
	 		}
	 }
	
	public void setParametro(String parametro) {
		this.nomeAtt=parametro;
	}
	
	public String getParametro() {
		return this.nomeAtt;
	}
}
