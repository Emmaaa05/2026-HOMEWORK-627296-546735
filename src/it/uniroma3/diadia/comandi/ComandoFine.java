package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	private IO io;
	/**
	 * Comando "Fine".
	 */
	public ComandoFine(IO io) {
		this.io=io;
	}

	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

}
