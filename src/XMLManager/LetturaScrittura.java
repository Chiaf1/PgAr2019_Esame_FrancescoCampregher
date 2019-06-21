package XMLManager;

import java.util.Map.Entry;

import javax.xml.stream.*;

import java.io.*;

class LetturaScrittura {
	private XMLInputFactory xmlif = null;
	private XMLStreamReader xmlr = null;
	private XMLOutputFactory xmlof = null;
	private XMLStreamWriter xmlw = null;
	StrutturaDati file;
	StrutturaDati vuoto = new StrutturaDati();

	/**
	 * metodo che traduce un file XML a StrutturaDati (file)
	 * 
	 * @return
	 */
	protected boolean leggiFile() {
		try {
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					file = letturaElemento();
				}
				xmlr.next();
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * metodo che legge un elemento del file XML e ritorna l'elemento in formato
	 * StrutturaDati
	 * 
	 * @return
	 */
	private StrutturaDati letturaElemento() {
		StrutturaDati newStruttura = null;
		boolean isPrimo = true;
		try {
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					return vuoto;
				case XMLStreamConstants.START_ELEMENT:
					if (isPrimo) {
						newStruttura = new StrutturaDati(xmlr.getLocalName());
						for (int i = 0; i < xmlr.getAttributeCount(); i++) {
							newStruttura.addTag(xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
						}
						isPrimo = false;
					} else {
						newStruttura.addAttributo(letturaElemento());
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					return newStruttura;
				case XMLStreamConstants.CHARACTERS:
					if (xmlr.getText().trim().length() > 0) {
						StrutturaDati newText = new StrutturaDati(xmlr.getText());
						newText.setIsText(true);
						newStruttura.addAttributo(newText);
					}
					break;
				case XMLStreamConstants.ATTRIBUTE:

					break;
				}
				xmlr.next();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return vuoto;
		}
		return vuoto;
	}

	/**
	 * restituisce la variabile file che contiene il file XML tradotto
	 * 
	 * @return
	 */
	protected StrutturaDati getFile() {
		return file;
	}

	/**
	 * setta il percorso del file XML
	 * 
	 * @param pathInputFile percorso del file
	 * @return ritorna true se il file è corretto altrimenti ritoena false
	 */
	protected boolean setPathInputFile(String pathInputFile) {
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathInputFile, new FileInputStream(pathInputFile));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
