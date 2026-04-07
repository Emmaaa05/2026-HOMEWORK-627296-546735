package it.uniroma3.diadia.io;
import java.util.Scanner;




public class IOConsole {
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
		@SuppressWarnings("resource")
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		/*scannerDiLinee.close(); */
		return riga;
	}
}