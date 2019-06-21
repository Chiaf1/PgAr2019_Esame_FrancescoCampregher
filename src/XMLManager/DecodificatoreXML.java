package XMLManager;

public class DecodificatoreXML {
	/**
	 * istanza per lavorare sui file XML
	 */
	private LetturaScrittura inputFile = new LetturaScrittura();

	/**
	 * metodo che traduce il file XML in struttura dati presente in input file
	 * 
	 * @param pathInputFile percorso del file da leggere
	 * @return
	 */
	public boolean leggiFile(String pathInputFile) {
		if (!inputFile.setPathInputFile(pathInputFile))
			return false;
		if (!inputFile.leggiFile())
			return false;
		return true;
	}

	/**
	 * restituisce il file XML tradotto in StrutturaDati
	 * 
	 * @return
	 */
	public StrutturaDati getFile() {
		try {
			return inputFile.getFile();
		} catch (Exception e) {
			StrutturaDati vuoto = new StrutturaDati();
			return vuoto;
		}
	}

}
