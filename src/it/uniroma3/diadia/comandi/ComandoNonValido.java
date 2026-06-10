package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	private IO io;
	
	public ComandoNonValido(IO io) {
		this.io=io;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando non valido. Riprova!");
	}
	

}
