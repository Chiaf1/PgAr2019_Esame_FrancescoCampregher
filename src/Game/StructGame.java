package Game;

import java.util.*;

import Interface.UserInterface;
import Map.Box;
import Map.Edge;
import Map.Map;
import XMLManager.*;

public class StructGame {
	private static final String DICE = "dice";
	private static final String LADDER = "ladder";
	private static final String START = "start";
	private static final String STOP = "stop";
	private static final String FILE4 = "nucleo1_gioco_oca/B1+B2.xml";
	private static final String FILE3 = "nucleo1_gioco_oca/B2.xml";
	private static final String FILE2 = "nucleo1_gioco_oca/B1.xml";
	private static final String FILE1 = "nucleo1_gioco_oca/base.xml";
	private static final String END = "end";
	private static final String DROP = "drop";
	private static final String BRANCH = "branch";
	private static final String NORMAL = "normal";
	private static final String INITIAL = "initial";
	private static final int DICE_FACES = 6;
	private static final int NO_SUB_MAP = 0;
	private static final String TYPE_SUBMAPS = "submaps";
	private static final String TAG_TO = "to";
	private static final String TAG_ID = "id";
	private static final String TAG_VALUE = "value";
	private static final String TYPE_CONDITION = "condition";
	private static final String TYPE_OPTION = "option";
	private static final String TAG_TYPE = "type";
	private static final String TYPE_CELL = "cell";
	private static final String TAG_TITLE = "title";
	private ArrayList<Map> maps = new ArrayList<>();
	private DecodificatoreXML decoder = new DecodificatoreXML();
	private ArrayList<String> pathMaps = new ArrayList<String>();
	private int indexMap;
	private int actualBoxId;
	ArrayList<String> types = new ArrayList<>();

	/*
	 * costruttore per definire i tipi delle casella accettati dal programma e i
	 * percorsi dei file
	 */
	public StructGame() {
		types.add(INITIAL);
		types.add(NORMAL);
		types.add(BRANCH);
		types.add(DROP);
		types.add(END);
		types.add(DICE);
		types.add(LADDER);
		types.add(START);
		types.add(STOP);
		pathMaps.add(FILE1);
		pathMaps.add(FILE2);
		pathMaps.add(FILE3);
		pathMaps.add(FILE4);
	}

	/**
	 * funzione che funge da addo, chiede di premere un tasto e premere invio
	 * 
	 * @param size determina quante facce ha il dado
	 * 
	 * @return ritorna il numero uscito
	 */
	private int dice(int size) {
		UserInterface.readString("premi un tasto e poi invio per tirare il dado");
		Random rnd = new Random();
		int number = rnd.nextInt(5) + 1;
		UserInterface.write("Il dado ha totalizzato " + number);
		return number;
	}

	/**
	 * è il corpo centrale del programma gestisce tutto il gioco
	 */
	public void game() {
		for (String path : pathMaps) {
			decoder.leggiFile(path);
			StrutturaDati XMLMaps = new StrutturaDati();
			XMLMaps = decoder.getFile();
			createMaps(XMLMaps, false);
		}
		
		int go = 1;
		while (go == 1) {
			indexMap = UserInterface.initMatch(maps);
			actualBoxId = 0;
			execution();
			go = UserInterface.endMatch();
		}
	}

	/**
	 * converte i file da XML a strutture dati Map
	 * 
	 * @param XMLMaps  il file XML da convertire
	 * @param isSubMap true se è una sotto mappa false se è una mappa principale
	 */
	private void createMaps(StrutturaDati XMLMaps, boolean isSubMap) {
		boolean isAlreadyCreate = false;
		Map newMap = new Map(types, XMLMaps.getTag(TAG_TITLE));
		for (StrutturaDati box : XMLMaps.getAttributi()) {
			if (box.getNome().equals(TYPE_CELL)) {
				String boxType = box.getTag(TAG_TYPE);
				//switch che serve per fare il parcing sul file XML
				switch (boxType) {
				case INITIAL:
					newMap.addBox(boxType, NO_SUB_MAP);
					int from5 = Integer.valueOf(box.getTag(TAG_ID));
					int to5 = Integer.valueOf(box.getAttributi().get(0).getTag(TAG_TO));
					ArrayList<Integer> conditions5 = new ArrayList<>();
					newMap.addEdge(from5, to5, conditions5);
					break;
				case DICE:
					newMap.addBox(boxType, NO_SUB_MAP);
					int from11 = Integer.valueOf(box.getTag(TAG_ID));
					int to11 = Integer.valueOf(box.getAttributi().get(1).getTag(TAG_TO));
					ArrayList<Integer> conditions11 = new ArrayList<>();
					Box lastBox = newMap.getBox(newMap.getBoxesNumber() - 1);
					lastBox.setDice(Integer.valueOf(box.getAttributi().get(0).getTag(0)));
					newMap.addEdge(from11, to11, conditions11);
					break;
				case LADDER:
					newMap.addBox(boxType, NO_SUB_MAP);
					int from6 = Integer.valueOf(box.getTag(TAG_ID));
					int to6 = Integer.valueOf(box.getAttributi().get(1).getTag(TAG_TO));
					ArrayList<Integer> conditions6 = new ArrayList<>();
					newMap.addEdge(from6, to6, conditions6);
					break;
				case START:
					newMap.addBox(boxType, NO_SUB_MAP);
					ArrayList<Integer> conditions = new ArrayList<>();
					Integer from = Integer.valueOf(box.getTag(TAG_ID));
					Integer to = 0;
					newMap.addEdge(from, to, conditions);
					break;
				case STOP:
					newMap.addBox(boxType, NO_SUB_MAP);
					int from1 = Integer.valueOf(box.getTag(TAG_ID));
					int to1 = Integer.valueOf(box.getAttributi().get(0).getTag(TAG_TO));
					ArrayList<Integer> conditions1 = new ArrayList<>();
					newMap.addEdge(from1, to1, conditions1);
					break;
				case DROP:
					Integer subMapId = Integer.valueOf(box.getAttributi().get(0).getTag(0));
					newMap.addBox(boxType, subMapId);
					ArrayList<Integer> conditions4 = new ArrayList<>();
					Integer from4 = Integer.valueOf(box.getTag(TAG_ID));
					Integer to4 = Integer.valueOf(box.getAttributi().get(1).getTag(0));
					newMap.addEdge(from4, to4, conditions4);
					break;
				case END:
					newMap.addBox(boxType, NO_SUB_MAP);
					break;
				case NORMAL:
					newMap.addBox(boxType, NO_SUB_MAP);
					int from7 = Integer.valueOf(box.getTag(TAG_ID));
					int to7 = Integer.valueOf(box.getAttributi().get(0).getTag(TAG_TO));
					ArrayList<Integer> conditions7 = new ArrayList<>();
					newMap.addEdge(from7, to7, conditions7);
					break;
				case BRANCH:
					newMap.addBox(boxType, NO_SUB_MAP);
					for (StrutturaDati edge : box.getAttributi()) {

						ArrayList<Integer> conditions3 = new ArrayList<>();
						for (StrutturaDati cond : edge.getAttributi()) {
							if (cond.getNome().equals(TYPE_CONDITION)) {
								Integer value = Integer.valueOf(cond.getTag(TAG_VALUE));
								conditions3.add(value);
							}
						}
						int from3 = Integer.valueOf(box.getTag(TAG_ID));
						int to3 = Integer.valueOf(edge.getTag(TAG_TO));
						newMap.addEdge(from3, to3, conditions3);

					}
					break;
				}
			} else if (box.getNome().equals(TYPE_SUBMAPS)) {
				maps.add(newMap);
				isAlreadyCreate = true;
				createMaps(box, true);
			}
		}
		if (!isSubMap) {
			if (!isAlreadyCreate) {
				maps.add(newMap);
			}
		} else {
			int lastNewMap = maps.size() - 1;
			maps.get(lastNewMap).addSubMap(newMap);
		}
	}

	/**
	 * esegue la parte effettiva della partita
	 */
	private void execution() {
		Map actualMap = maps.get(indexMap);
		Box actualBox = actualMap.getBox(actualBoxId);
		Map subMap = null;
		Box subBox = null;
		int subBoxId;
		int rollValue = 0;
		boolean isSubMap = false;
		boolean isDice = false;
		int dice = 0;
		while (actualBoxId < actualMap.getBoxesNumber() - 1 || isSubMap) {
			if (rollValue == 0) {
				rollValue = dice(DICE_FACES) + dice;
				dice = 0;
			}
			Box prevBox = actualBox;
			Box subPrevBox = subBox;
			String boxType = actualBox.getType();
			//switch che gestisce i tipi di caselle
			switch (boxType) {
			case DICE:
				isDice = true;
				if (isSubMap) {
					dice = subBox.getDice();
					subBoxId = subMap.getEdge(subBox.getLink(0)).getTo();
					subBox = subMap.getBox(subBoxId);
					UserInterface.write("casella in cui sei:\n" + subPrevBox + "\ncasella di arrivo:\n" + subBox);
				} else {
					dice = actualBox.getDice();
					actualBoxId = actualMap.getEdge(actualBox.getLink(0)).getTo();
					actualBox = actualMap.getBox(actualBoxId);
					UserInterface.write("casella in cui sei:\n" + prevBox + "\ncasella di arrivo:\n" + actualBox);
				}
				break;
			case LADDER:
				if (isSubMap) {
					UserInterface.write("sei capitato in una casella di tipo dado:\n" + subPrevBox);

					rollValue += dice(DICE_FACES);
					subBoxId = subMap.getEdge(subBox.getLink(0)).getTo();
					subBox = subMap.getBox(subBoxId);
					UserInterface.write("la casella di arrivo è:\n" + subBox);
				} else {
					rollValue += dice(DICE_FACES);
					actualBoxId = actualMap.getEdge(actualBox.getLink(0)).getTo();
					actualBox = actualMap.getBox(actualBoxId);
					UserInterface.write("sei capitato in una casella di tipo dado:\n" + prevBox
							+ "\ncasella di arrivo:\n" + actualBox);
				}
				break;
			case START:
				if (isSubMap) {
					subBoxId = 0;
					subBox = subMap.getBox(subBoxId);
					UserInterface.write("casella in cui sei:\n" + subPrevBox + "\ncasella di arrivo:\n" + subBox);
				} else {
					actualBoxId = 0;
					actualBox = actualMap.getBox(actualBoxId);
					UserInterface.write("casella in cui sei:\n" + prevBox + "\ncasella di arrivo:\n" + actualBox);
				}
				break;
			case STOP:
				rollValue = 0;
				if (isSubMap) {
					subBoxId = subMap.getEdge(subBox.getLink(0)).getTo();
					subBox = subMap.getBox(subBoxId);
					UserInterface.write("casella in cui sei:\n" + subPrevBox + "\ncasella di arrivo:\n" + subBox);
				} else {
					actualBoxId = actualMap.getEdge(actualBox.getLink(0)).getTo();
					actualBox = actualMap.getBox(actualBoxId);
					UserInterface.write("casella in cui sei:\n" + prevBox + "\ncasella di arrivo:\n" + actualBox);
				}
				break;
			case INITIAL:
				if (isSubMap) {
					subBoxId = subMap.getEdge(subBox.getLink(0)).getTo();
					subBox = subMap.getBox(subBoxId);
					UserInterface.write("casella di partenza\n" + subPrevBox + "\ncasella di arrivo:\n" + subBox);
				} else {
					actualBoxId = actualMap.getEdge(actualBox.getLink(0)).getTo();
					actualBox = actualMap.getBox(actualBoxId);
					UserInterface.write("casella di partenza\n" + prevBox + "\ncasella di arrivo:\n" + actualBox);
				}
				break;
			case NORMAL:
				if (isSubMap) {
					subBoxId = subMap.getEdge(subBox.getLink(0)).getTo();
					subBox = subMap.getBox(subBoxId);
					UserInterface.write("casella in cui sei:\n" + subPrevBox + "\ncasella di arrivo:\n" + subBox);
				} else {
					actualBoxId = actualMap.getEdge(actualBox.getLink(0)).getTo();
					actualBox = actualMap.getBox(actualBoxId);
					UserInterface.write("casella in cui sei:\n" + prevBox + "\ncasella di arrivo:\n" + actualBox);
				}
				break;
			case DROP:
				isSubMap = true;
				subMap = maps.get(actualBox.getIdSubMap());
				subBoxId = 0;
				subBox = subMap.getBox(subBoxId);
				actualBoxId = actualMap.getEdge(actualBox.getLink(0)).getTo();
				actualBox = actualMap.getBox(actualBoxId);
				UserInterface.write("\n\ningresso nel pozzo\n\n");
				break;
			case END:
				if (isSubMap) {
					UserInterface.write("\n\nuacita dal pozzo\n\n");
					isSubMap = false;
				}
				break;
			case BRANCH:
				if (isSubMap) {
					UserInterface.write("sei capitato in una casella di tipo bivio:\n" + subPrevBox);
					Integer roll = dice(DICE_FACES);
					for (int edgeId : subBox.getLinks()) {
						Edge edge = subMap.getEdge(edgeId);
						if (edge.getConditions().contains(roll)) {
							subBoxId = edge.getTo();
							subBox = subMap.getBox(subBoxId);
							UserInterface.write("la casella di arrivo è:\n" + subBox);
							break;
						}
					}
				} else {
					UserInterface.write("sei capitato in una casella di tipo bivio:\n" + prevBox);
					Integer roll = dice(DICE_FACES);
					for (int edgeId : actualBox.getLinks()) {
						Edge edge = actualMap.getEdge(edgeId);
						if (edge.getConditions().contains(roll)) {
							actualBoxId = edge.getTo();
							actualBox = actualMap.getBox(actualBoxId);
							UserInterface.write("la casella di arrivo è:\n" + actualBox);
							break;
						}
					}
				}
				break;
			}
			rollValue--;
		}
	}
}
