package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import java.util.Set;
import java.util.HashSet;

public abstract class AbstractComando implements Comando{
	protected String parametro;
	private static Set<String> registro=new HashSet<>();
	
	/**public AbstractComando() {
		registra();
	}**/
	
	protected static void registraComando(Class<? extends Comando> c) {
        registro.add( c.getSimpleName().replace("Comando", "").toLowerCase());
    }
        
	
	public static Set<String> getComandiDisponibili(){
		return registro;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public abstract void esegui(Partita partita);
	
}
