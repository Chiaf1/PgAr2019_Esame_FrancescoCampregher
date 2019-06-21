package XMLManager;

import java.util.*;

public class StrutturaDati {
	private String nome;
	private HashMap<String, String> tag = new HashMap<String, String>();
	private ArrayList<String> indiciKey = new ArrayList<String>();
	private ArrayList<StrutturaDati> attributi = new ArrayList<StrutturaDati>();
	private boolean isText = false;

	/**
	 * costruttore della classe StrutturaDati che vi assegna anche un nome
	 * 
	 * @param nome nome dell'istanza
	 */
	public StrutturaDati(String nome) {
		this.nome = nome;
	}

	/**
	 * costruttore della classe StrutturaDati
	 */
	public StrutturaDati() {
		nome = "vuoto";
	}

	/**
	 * metodo che restituisce il nome dell'istanza Struttura dati
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * metodo che ritorna che se l'istanza della struttura dati è solo un testo
	 * oppure un elemento
	 * 
	 * @return true se è un testo false se è un elemento
	 */
	public boolean isText() {
		return isText;
	}

	/**
	 * ritorna l'HashMap delle tag della struttura dati
	 * 
	 * @return
	 */
	public HashMap<String, String> getTag() {
		return tag;
	}

	/**
	 * ritorna il valore della tag per key
	 * 
	 * @param key key della tag desiderata
	 * @return
	 */
	public String getTag(String key) {
		return tag.get(key);
	}

	/**
	 * ritorna il valore di una tag per ordine di creazione
	 * 
	 * @param i numero che indca il nuomero della tag
	 * @return
	 */
	public String getTag(int i) {
		return tag.get(this.indiciKey.get(i));
	}

	/**
	 * ritorna l'ArrayList degli attributi
	 * 
	 * @return
	 */
	public ArrayList<StrutturaDati> getAttributi() {
		return attributi;
	}

	/**
	 * setta il nome dell'istanza
	 * 
	 * @param nome nuovo nome per l'istanza
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * aggiunga una tag
	 * 
	 * @param tag    key della tag
	 * @param valore testo della tag
	 */
	public void addTag(String tag, String valore) {
		indiciKey.add(tag);
		this.tag.put(tag, valore);
	}

	/**
	 * setta il valore di una tag per key
	 * 
	 * @param tag    key della tag
	 * @param valore testo della tag
	 * @return
	 */
	public boolean setTag(String tag, String valore) {
		try {
			this.tag.replace(tag, valore);
		} catch (Exception i) {
			return false;
		}
		return true;
	}

	/**
	 * rimuove una tag per key
	 * 
	 * @param tag key della tag
	 */
	public void removeTag(String tag) {
		indiciKey.remove(tag);
		this.tag.remove(tag);
	}

	/**
	 * rimuove una tag per indice di creazione
	 * 
	 * @param i indice di creazione della tag
	 */
	public void removeTag(int i) {
		this.tag.remove(this.indiciKey.get(i));
		indiciKey.remove(i);
	}

	/**
	 * aggiunge degli attributi in blocco sovrascrivendo quelli esistenti
	 * 
	 * @param attributi
	 */
	public void addAttributi(ArrayList<StrutturaDati> attributi) {
		this.attributi = attributi;
	}

	/**
	 * aggiunge un attributo a quelli esistenti
	 * 
	 * @param attributo attributo da aggiungere
	 */
	public void addAttributo(StrutturaDati attributo) {
		this.attributi.add(attributo);
	}

	/**
	 * setta il paramettro isText
	 * 
	 * @param isText nuovo valore del parametro isText
	 */
	public void setIsText(boolean isText) {
		this.isText = isText;
	}

}
