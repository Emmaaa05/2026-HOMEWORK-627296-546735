package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando{
	private IO io;
	private String nomeAtt;
	
	/**Comando "Posa"
	Chiede all'utente l'attrezzo che vuole posare. Controlla se
 	l'attrezzo e'presente nella borsa e se la stanza non e' piena,
 	e lo fa prendere dal giocatore, altrimenti stampa messaggio di errore.
	**/
	
	public ComandoPosa(IO io) {
		this.io=io;
	}
	
	@Override
	 public void esegui(Partita partita) {
		Borsa b = partita.getGiocatore().getBorsa();
		if(b.isEmpty()) {
			this.io.mostraMessaggio("La borsa e' vuota");
			return;}
	
		if (nomeAtt == null) {
	        this.io.mostraMessaggio("Devi specificare quale attrezzo posare!");
	        return;
	    }
		
		if(!b.hasAttrezzo(nomeAtt)){
			this.io.mostraMessaggio("L'attrezzo non e' presente nella borsa");
			return;
		} else{ 
			Attrezzo attrezzo= b.getAttrezzo(nomeAtt);
			if(!partita.getStanzaCorrente().addAttrezzo(attrezzo)){
				this.io.mostraMessaggio("Non puoi posare attrezzi in questa stanza, perche' e' piena!");
				return;
			} else{
				b.removeAttrezzo(nomeAtt);
				this.io.mostraMessaggio(partita.getStanzaCorrente().toString());
				this.io.mostraMessaggio("Hai preso l'attrezzo dalla tua borsa e l'hai posato nella stanza!");
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
