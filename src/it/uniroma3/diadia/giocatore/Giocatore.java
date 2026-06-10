package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	private Borsa borsa;
	private int cfu;
	
	public Giocatore(int cfu_iniziali) {
		this.borsa = new Borsa();
		this.cfu=cfu_iniziali;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public String getBorsaOrdinataPerPeso() {
		return this.borsa.toStringOrdinatoPerPeso();
	}
	
	public String getBorsaOrdinataPerNome() {
		return this.borsa.toStringOrdinatoPerNome();
	}
	
	public String getBorsaRaggruppataPerPeso() {
		return this.borsa.toStringRaggruppatoPerPeso();
	}
	
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Cfu a disposizione: " + this.getCfu() + "\n");
		s.append(this.borsa.toString());
		return s.toString();
	}
	
}
