package Interface;

import java.util.*;

import javax.swing.JTable.PrintMode;

import Map.Map;

public class UserInterface {
	/**
	 * scanner per la lettura da console
	 */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * metodo per la lettura di una stringa dalla console
	 * 
	 * @param msg (è il messaggio che viene scritto prima della lettura dei dati)
	 * @return la stringa letta dalla console
	 */
	public static String readString(String msg) {
		System.out.print(msg);
		return scanner.next();
	}

	/**
	 * metodo per la lettura di un intero da console, se il dato immesso non è nel
	 * formato corretto comunica l'errore e ripete l'operazione
	 * 
	 * @param msg (è il messaggio che viene scritto prima della lettura dei dati)
	 * @return l'intero che viene letto
	 */
	public static int readInt(String msg) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			System.out.print(msg);
			try {
				valoreLetto = scanner.nextInt();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println("il valore inserito non è nel formato corretto");
				scanner.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	/**
	 * scrive la stringa msg andando a capo alla fine
	 * 
	 * @param msg (la stringa da scrivere)
	 */
	public static void write(String msg) {
		System.out.println(msg);
	}

	/**
	 * inizializza il match restituisce a quale livello vuole giocare l'utenete
	 * 
	 * @param maps ArrayList di mappe da sottoporre all'utente
	 * @return numero intero che rappresenta l'indice della mappa
	 */
	public static int initMatch(ArrayList<Map> maps) {
		UserInterface.write("selezionare la mappa desiderata");
		printMaps(maps);
		return UserInterface.readInt("\n");
	}

	/**
	 * scrive in un elenco puntato le mappe e i loro nomi
	 * 
	 * @param maps ArrayList di mappe da scrivere a video
	 */
	private static void printMaps(ArrayList<Map> maps) {
		for (int i = 0; i < maps.size(); i++) {
			UserInterface.write(i + "-" + maps.get(i));
		}
	}

	/*
	 * dichiara la vittoria e restituisce 1 se il giocatore vuole giocare ancora 0
	 * se vuole finire
	 */
	public static int endMatch() {
		int answare;
		do {
			answare = UserInterface.readInt("hai vinto!! \nVuoi fare un'altra partita(si:1,no:0)?");
		} while (answare != 0 && answare != 1);
		return answare;
	}
}
