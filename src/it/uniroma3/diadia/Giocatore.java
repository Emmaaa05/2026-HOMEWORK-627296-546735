package it.uniroma3.diadia;

public class Giocatore {
	
	
	private int cfu;
	private Borsa borsa;
	
	/* costruttore di Giocatore */
	public Giocatore(int CFU_INIZIALI) {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
		
	}
	
	/* getter e setter */
	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
		
	}

}
