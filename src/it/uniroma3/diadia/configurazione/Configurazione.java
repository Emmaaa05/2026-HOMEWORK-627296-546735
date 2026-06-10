package it.uniroma3.diadia.configurazione;
import java.io.InputStream;
import java.util.Properties;

public class Configurazione {
    private static Properties props = new Properties();
    static {
        try {
            InputStream is =
                Configurazione.class.getClassLoader()
                .getResourceAsStream("diadia.properties");
            props.load(is);
        } catch (Exception e) {
        	throw new RuntimeException("Errore caricamento properties");
        }
    }

    public static int getCFUIniziali() {
        return Integer.parseInt(props.getProperty("cfu_iniziali"));
    }

    public static int getPesoMaxBorsa() {
        return Integer.parseInt(props.getProperty("peso_max_borsa"));
    }
}
