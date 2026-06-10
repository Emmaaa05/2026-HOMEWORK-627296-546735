/* Classe Refattoriazzata -> resa classe interna di Labirinto*/
package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.attrezzi.*;
import java.util.Map;
import java.util.HashMap;
import it.uniroma3.diadia.personaggi.*;

public class LabirintoBuilder {
	private Map<String, Stanza> stanze=new HashMap<>();
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	
	public LabirintoBuilder() {
		this.stanzaIniziale=null;
		this.stanzaVincente=null;
		this.stanzaCorrente=null;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		if(!stanze.containsKey(nome))
			stanze.put(nome, new Stanza(nome));
		this.stanzaCorrente=stanze.get(nome);
		return this;
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		if(!stanze.containsKey(nome))
			stanze.put(nome, new Stanza(nome));
		this.stanzaIniziale=stanze.get(nome);
		this.stanzaCorrente=stanze.get(nome);
		return this;
	}
	public LabirintoBuilder addStanzaVincente(String nome) {
		if(!stanze.containsKey(nome))
			stanze.put(nome, new Stanza(nome));
		this.stanzaVincente=stanze.get(nome);
		this.stanzaCorrente=stanze.get(nome);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nome1, String nome2 , Direzione dir) {
		if(!stanze.containsKey(nome1))
			stanze.put(nome1, new Stanza(nome1));
		if(!stanze.containsKey(nome2))
			stanze.put(nome2, new Stanza(nome2));
		
		stanze.get(nome1).impostaStanzaAdiacente(dir, stanze.get(nome2));
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo a = new Attrezzo(nome,peso);
		stanzaCorrente.addAttrezzo(a);
		return this;
	}

	public LabirintoBuilder addPersonaggio(AbstractPersonaggio p, String stanza) {
		if(stanze.containsKey(stanza))
			stanze.get(stanza).setPersonaggio(p);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String nomeAtt) {
		if(!stanze.containsKey(nome))
			stanze.put(nome, new StanzaBuia(nome,nomeAtt));
		this.stanzaCorrente=stanze.get(nome);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, Direzione dir, String chiave) {
		if(!stanze.containsKey(nome))
			stanze.put(nome, new StanzaBloccata(nome,dir,chiave));
		this.stanzaCorrente=stanze.get(nome);
		return this;
	}
	
	//public Labirinto getLabirinto() {
	//	return new Labirinto(stanzaIniziale,stanzaVincente);
	//}
}