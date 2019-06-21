package Map;

import java.util.ArrayList;

public class Edge {
	private int form;
	private int to;
	private int id;
	private ArrayList<Integer> conditions = new ArrayList<Integer>();

	/**
	 * costruttore della classe arco
	 * 
	 * @param _from       casella di partenza
	 * @param _to         casella di arrivo
	 * @param _id         id dell'arco
	 * @param _conditions numeri che fanno si che questo arco sia "eseguito"
	 */
	protected Edge(int _from, int _to, int _id, ArrayList<Integer> _conditions) {
		form = _from;
		to = _to;
		id = _id;
		conditions = _conditions;
	}

	/**
	 * ritorna la casella di partenza
	 * 
	 * @return
	 */
	public int getForm() {
		return form;
	}

	/**
	 * ritorna la casella di arrivo
	 * 
	 * @return
	 */
	public int getTo() {
		return to;
	}

	/**
	 * ritorna lArrayList delle condizioni
	 * 
	 * @return
	 */
	public ArrayList<Integer> getConditions() {
		return conditions;
	}

	/**
	 * verifica se il numero passato sia una condizione
	 * 
	 * @param number numero da verificare
	 * @return ritorna true se è una condizione false se non lo è
	 */
	public boolean isCondition(Integer number) {
		return conditions.contains(number);
	}
}
