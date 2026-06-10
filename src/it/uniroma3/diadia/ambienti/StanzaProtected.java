package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class StanzaProtected {
	
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	
    protected Map<String,Attrezzo> attrezzi;
    
    protected Map<String,Stanza> stanzeAdiacenti;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.size() >= NUMERO_MASSIMO_DIREZIONI &&
    			!this.stanzeAdiacenti.containsKey(direzione))
    		return;
    	this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public String getAttrezzi() {
    	StringBuilder risultato = new StringBuilder();
    	if(this.attrezzi.size() != 0) {
    	risultato.append("\nAttrezzi nella stanza: " +this.attrezzi.size());
    	for(Attrezzo a: this.attrezzi.values())
    		risultato.append(a.toString() + " ");
    	} else
    		risultato.append("non ci sono atrezzi");
    	return risultato.toString();
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi.put(attrezzo.getNome(),attrezzo);
        	return true;
        }
        else {
        	return false;
        }
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	if(this.stanzeAdiacenti.size()!=0) {
    		risultato.append(this.getDirezioni());
    	}else
    		risultato.append("\nUscite: non ci sono uscite");
    	
    	if(this.attrezzi.size()!=0)
    		risultato.append(this.getAttrezzi());
    	else
    		risultato.append("\nAttrezzi nella stanza: non ci sono atrezzi");
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	/**
     * Restituisce le direzioni delle stanze adiacenti alla stanza corrente.
     * @return lista di direzioni.
     */
	public String getDirezioni() {
		StringBuilder risultato = new StringBuilder();
    	if(this.stanzeAdiacenti.size() != 0) {
    	risultato.append("\nUscite: ");
    	for(String s: this.stanzeAdiacenti.keySet())
    		risultato.append(s.toString() + " ");
    	} else
    		risultato.append("non ci sono uscite");
    	return risultato.toString();
    }

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
}
