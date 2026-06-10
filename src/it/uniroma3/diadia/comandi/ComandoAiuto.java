package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;

public class ComandoAiuto extends AbstractComando{
	/** Refactoring -> eliminato elenco perche' ereditato da AbstractComando
	static final private String[] elencoComandi = {"vai -> direzione", "aiuto", "fine",
	"prendi", "posa", "guarda"};**/
	private IO io;
	/**
	 * Stampa informazioni di aiuto.
	 */
	public ComandoAiuto(IO io) {
		this.io=io;
	}
	
	@Override
	public void esegui(Partita p) {
        for (String nome : AbstractComando.getComandiDisponibili()) {
            io.mostraMessaggio(nome);
        }
	}
	//da includere es 12, ma sto qui da 2h
}
