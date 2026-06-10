package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	private IO io;
	
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io=io;
	}
	
	@Override
    public Comando costruisciComando(String istruzione){
		String nome=null;
		String parametro=null;
		Comando comando=null;
	
		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione);
		
		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		if (nome == null) 
			return new ComandoNonValido(this.io);
		
		try {
		StringBuilder nomeClasse=new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nome.charAt(0)));
		nomeClasse.append(nome.substring(1));
		Class<?> classe = Class.forName(nomeClasse.toString());
		comando=(Comando)classe.getDeclaredConstructor(IO.class).newInstance(this.io);
		
		/** Refactoring -> reso introspettiva la classe
		else if (nome.equals("vai"))
			comando = new ComandoVai(this.io);
		else if (nome.equals("prendi"))
			comando = new ComandoPrendi(this.io);
		else if (nome.equals("posa"))
			comando = new ComandoPosa(this.io);
		else if (nome.equals("aiuto"))
			comando = new ComandoAiuto(this.io);
		else if(nome.equals("fine"))
			comando = new ComandoFine(this.io); 
		else if(nome.equals("guarda"))
			comando = new ComandoGuarda(this.io); 
		else
			comando = new ComandoNonValido(this.io);
		**/
		comando.setParametro(parametro);
		return comando;
			} catch(Exception e) {
				return new ComandoNonValido(this.io);
			}
    }
}