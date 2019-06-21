package Map;

import java.util.*;

public class Map {
	private String name;
	private ArrayList<Box> boxes = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	private int idAttBox = 0;
	private int idAttEdge = 0;
	private ArrayList<String> types = new ArrayList<>();
	private ArrayList<Map> subMaps = new ArrayList<>();

	@Override
	public String toString() {
		return name;
	}

	/**
	 * costruisce la mappa richiede i tipi di caselle presenti e il suo nome
	 * 
	 * @param _types ArrayList di tipi di caselle
	 * @param _name  nome della mappa
	 */
	public Map(ArrayList<String> _types, String _name) {
		types = _types;
		name = _name;
	}

	/**
	 * aggiunge una casella alla mappa
	 * 
	 * @param _type    tipo della casella
	 * @param idSubMap numero della sub map a cui rimanda
	 * @return ritorna true se va a buon fine l'aggiunta false se il tipo non è
	 *         corretto
	 */
	public boolean addBox(String _type, int idSubMap) {
		if (!types.contains(_type)) {
			return false;
		}
		Box newBox = new Box(_type, idAttBox);
		if (_type.equals("drop")) {
			newBox.setIdSubMap(idSubMap);
		}
		idAttBox++;
		boxes.add(newBox);
		return true;
	}

	/**
	 * aggiunge un arco alla mappa
	 * 
	 * @param _from       casella di partenza
	 * @param _to         casella di arrivo
	 * @param _conditions ArrayList di condizioni in cui si verifica questo arco
	 * @return ritorna true se l'aggiunta va a buon fine e false se non esiste la
	 *         casella from
	 */
	public boolean addEdge(int _from, int _to, ArrayList<Integer> _conditions) {
		if (_from >= boxes.size()) {
			return false;
		}
		Edge newEdge = new Edge(_from, _to, idAttEdge, _conditions);
		idAttEdge++;
		edges.add(newEdge);
		int edgeIndex = edges.size() - 1;
		boxes.get(_from).addLink(edgeIndex);
		return true;
	}

	/**
	 * rimuove una casella
	 * 
	 * @param index indice della casella da rimuovere
	 * @return ritorna true se la casella è stata rimossa e false se la casella non
	 *         esiste
	 */
	public boolean removeBox(Integer index) {
		if (index >= boxes.size()) {
			return false;
		}
		for (Edge edge : edges) {
			if (edge.getTo() == index) {
				int fromBoxInd = edge.getForm();
				boxes.get(fromBoxInd).removeLink(index);
				edges.remove(edge);
			}
		}
		return true;
	}

	/**
	 * rimuove un arco in base al suo indice
	 * 
	 * @param index indice dell'arco da rimuovere
	 * @return ritorna true se la rimozione va a buon fine e false se l'arco non
	 *         esiste
	 */
	public boolean removeEdge(int index) {
		if (index >= edges.size()) {
			return false;
		}
		int fromBoxInd = edges.get(index).getForm();
		try {
			boxes.get(fromBoxInd).removeLink(index);
		} catch (Exception e) {
			System.out.print("errore già eliminato");
		}
		return true;
	}

	/**
	 * ritorna una casella per il suo indice
	 * @param index indice della casella 
	 * @return ritorna la casella
	 */
	public Box getBox(int index) {
		return boxes.get(index);
	}

	/**
	 * ritorna l'ArrayList delle casella
	 * @return
	 */
	public ArrayList<Box> getBoxes() {
		return boxes;
	}

	/**
	 * ritorna il nome della mappa
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * aggiunge sub map alla mappa
	 * @param subMap
	 */
	public void addSubMap(Map subMap) {
		subMaps.add(subMap);
	}

	/**
	 * ritorna la size dell'ArrayList delle caselle
	 * @return
	 */
	public int getBoxesNumber() {
		return boxes.size();
	}

	/**
	 * ritorna un arco per indice
	 * @param index indice dell'arco
	 * @return
	 */
	public Edge getEdge(int index) {
		return edges.get(index);
	}

	/**
	 * ritorna l'ArrayList degli archi
	 * @return
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
