package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{
	private IO io;
	
	public ComandoGuarda(IO io) {
		this.io=io;
	}
	
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		if(!partita.isFinita()) {
			this.io.mostraMessaggio("La partita e' ancora in corso. "
					+ partita.getGiocatore().getBorsaRaggruppataPerPeso()
					+ "\nCfu rimanenti:" + partita.getGiocatore().getCfu() );	
		}
	}
	
}
//aggiungere stampa in tutti i metodi di ordinamento attrezzi in Borsa