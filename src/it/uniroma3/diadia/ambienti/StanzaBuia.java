package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	static final private String ATTREZZO_NECESSARIO="lanterna";
	
	private String attrezzoNecessario;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.attrezzoNecessario = nomeAttrezzo;
	}
	
	public StanzaBuia(String nome) {
		this(nome,ATTREZZO_NECESSARIO);
	}
	
	@Override
	public String getDescrizione() {
		if(!super.hasAttrezzo(this.attrezzoNecessario))
			return "Qui c'e' buio pesto";
		return super.toString();
	}

}
