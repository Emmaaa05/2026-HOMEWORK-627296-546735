package it.uniroma3.diadia.ambienti;

import java.io.IOException;
import java.io.Reader;

import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

import java.io.BufferedReader;

public class CaricatoreLabirinto {
	private BufferedReader reader;
	private Labirinto.LabirintoBuilder builder; 
	private int numEstremi=0;
	
	public CaricatoreLabirinto(Reader reader) {
		this.reader = new BufferedReader(reader);
		this.builder= Labirinto.newBuilder();
	}
	
	public void carica() throws IOException {
		String sezione = "";
		String linea;
		
		while((linea=reader.readLine() ) != null) {
			linea=linea.trim();
			if(linea.isEmpty())
				continue;
			if(linea.equals("Stanze:")) {
				sezione="STANZE";
				continue;
			}
			
			if(linea.equals("Estremi:")) {
				sezione="ESTREMI";
				continue;
			}
			
			if(linea.equals("Attrezzi:")) {
				sezione="ATTREZZI";
				continue;
			}
			
			if(linea.equals("Uscite:")) {
				sezione="USCITE";
				continue;
			}
			
			if(linea.equals("Personaggi:")) {
				sezione="PERSONAGGI";
				continue;
			}
			
			if(linea.equals("Stanze Buie:")) {
				sezione="STANZE_BUIE";
				continue;
			}
			
			if(linea.equals("Stanze Chiuse:")) {
				sezione="STANZE_CHIUSE";
				continue;
			}
			
			switch(sezione) {
			case "STANZE":
				leggiStanza(linea);
				break;
				
			case "ESTREMI":
				leggiEstremi(linea);
				break;
			
			case "ATTREZZI":
				leggiAttrezzo(linea);
				break;
				
			case "USCITE":
				leggiUscite(linea);
				break;
			
			case "PERSONAGGI":
				leggiPersonaggi(linea);
				break;
			case "STANZE_BUIE":
				leggiStanzeBuie(linea);
				break;
			
			case "STANZE_CHIUSE":
				leggiStanzeChiuse(linea);
				break;
			
			}
		}
	}
	
	private void leggiStanza(String linea) {
		builder.addStanza(linea);
	}
	
	private void leggiEstremi(String linea) {
		if(numEstremi==0)
			builder.addStanzaIniziale(linea);
		else
			builder.addStanzaVincente(linea);
		numEstremi++;
	}
	
	private void leggiAttrezzo(String linea) {
		String []parole=linea.split("\\s+");
		String nome=parole[0];
		int peso=Integer.parseInt(parole[1]);
		builder.addAttrezzo(nome,peso);
	}
	
	private void leggiUscite(String linea) {
		String []parole=linea.split("\\s+");
		Direzione dir=Direzione.valueOf(parole[3].toLowerCase());
		builder.addAdiacenza(parole[0],parole[1],dir);
	}
	
	private void leggiPersonaggi(String linea) {
		String []parole=linea.split("\\s+");
		String tipo=parole[0];
		String nome=parole[1];
		String stanza=parole[2];
		
		AbstractPersonaggio personaggio = null;

	    switch (tipo) {
	        case "Strega":
	            personaggio = new Strega(nome, "Sono una strega!");
	            break;

	        case "Cane":
	            personaggio = new Cane(nome, "Sono un cane fedele!");
	            break;

	        case "Mago":
	        	Attrezzo att=new Attrezzo(parole[3],Integer.parseInt(parole[4]) );
	            personaggio = new Mago(nome, "Sono un mago potente!",att);
	            break;

	        default:
	            return; // tipo sconosciuto
	    }

	    builder.addPersonaggio(personaggio, stanza);
	}
	
	private void leggiStanzeBuie(String linea) {
		String []parole=linea.split("\\s+");
		String stanza=parole[0];
		String nomeAtt=parole[1];
		builder.addStanzaBuia(stanza,nomeAtt);
	}
	
	private void leggiStanzeChiuse(String linea) {
		String []parole=linea.split("\\s+");
		String stanza=parole[0];
		Direzione dir=Direzione.valueOf(parole[1].toLowerCase());
		String nomeAtt=parole[2];
		builder.addStanzaBloccata(stanza,dir,nomeAtt);
	}
	
	public Labirinto getLabirinto() {
		return builder.build();	
	}
}
