package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	static final private Direzione DIREZIONE_BLOCCATA=Direzione.nord;
	static final private String CHIAVE="osso";
	
	private Direzione direzioneBloccata;
	private String chiave;
	
	public StanzaBloccata(String nome,Direzione direzione,String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata=direzione;
		this.chiave=nomeAttrezzo;
	}
	
	public StanzaBloccata(String nome,Direzione direzione) {
		this(nome,direzione,CHIAVE);
	}
	
	public StanzaBloccata(String nome) {
		this(nome,DIREZIONE_BLOCCATA,CHIAVE);
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione == this.direzioneBloccata) {
			if(!super.hasAttrezzo(this.chiave))
				return this;
			else	
				return super.getStanzaAdiacente(direzione);
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder stringa = new StringBuilder();
		stringa.append("\nLa stanza corrente ha la direzione "
				+ this.direzioneBloccata + " bloccata");
		stringa.append("\nE' possibile sbloccarla qualora sia presente "
				+ "nella stanza questo oggetto:" + this.chiave);
		stringa.append(super.toString());
		return stringa.toString();
	}

}
