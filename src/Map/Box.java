package Map;

import java.util.ArrayList;

public class Box {
	private int id;
	private ArrayList<Integer> links = new ArrayList<Integer>();
	private String type;
	private int idSubMap;
	private int dice;

	/**
	 * costruttore della classe box definisce il tipo della casella e il suo id
	 * 
	 * @param _type tipo della casella
	 * @param _id   id della casella
	 */
	protected Box(String _type, int _id) {
		type = _type;
		id = _id;
	}

	/**
	 * setta il parametro dice che dice di quanto sarà aumentato il tiro di dado
	 * successivo
	 * 
	 * @param dice numero che definisce di quanto verrà aumetato il tiro successivo
	 */
	public void setDice(int dice) {
		this.dice = dice;
	}

	/**
	 * ritorna il valore di quanto aumentare il tiro di dado
	 * 
	 * @return
	 */
	public int getDice() {
		return dice;
	}

	/**
	 * ritorna l'id della sub map a cui punta
	 * 
	 * @return
	 */
	public int getIdSubMap() {
		return idSubMap;
	}

	/**
	 * setta il tipo della casella
	 * 
	 * @param type
	 */
	protected void setType(String type) {
		this.type = type;
	}

	/**
	 * restituise l'id della casella
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * restituisce l'ArrayList di archi che partono da lui
	 * 
	 * @return
	 */
	public ArrayList<Integer> getLinks() {
		return links;
	}

	/**
	 * restituisce l'arco per indice
	 * 
	 * @param index indice dell'arco
	 * @return
	 */
	public Integer getLink(int index) {
		return links.get(index);
	}

	/**
	 * restituisce il tipo della casella
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * aggiunge l'indice di un arco all'ArrayList degli indici arco
	 * 
	 * @param _link
	 */
	protected void addLink(Integer _link) {
		links.add(_link);
	}

	/**
	 * rimuove un arco per indice
	 * 
	 * @param index indice dell'arco
	 */
	protected void removeLink(Integer index) {
		links.remove(index);
	}

	/**
	 * setta il valore dell'id della sub map a cui è riferito
	 * 
	 * @param idSubMap id della sub map
	 */
	protected void setIdSubMap(int idSubMap) {
		this.idSubMap = idSubMap;
	}

	@Override
	public String toString() {
		String text;
		text = "id: " + id + "\ntipo: " + type;
		return text;
	}
}
