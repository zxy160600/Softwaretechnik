package Model;

import java.io.IOException;



/**
 * Data Access Object für die Klasse Autos.
 */
public class AutosDao  extends Dao {
	
	
	/**
	 * Konstruktor um das Data Access Object mit einem Dateinamen zu initialisieren.
	 * 
	 * @param dateiName Dateiname
	 * @param openForWrite true wenn geschrieben werden soll
	 */
	public AutosDao (String dateiName, boolean openForWrite) {

		super (dateiName, openForWrite);
	}
	
	
	public void write (Object obj) throws IOException {
		
		if (out != null) {
			
			Autos Protokell = (Autos)obj;
			// Anzahl Protokell speichern:
			out.writeInt(Protokell.size());
			
			// Nun die einzelnen Autos speichern:
			Autodao ProtekellDAO = new Autodao (null, out);
			
			for (Auto m: Protokell) {
				
				ProtekellDAO.write(m);
			}
		}
	}
	
	/**
	 * Neues Auto-Objekt erzeugen und Daten aus Datei lesen.
	 */	
	public void read (Object obj) throws IOException {
		
		if (in != null) {
			
			Autos Protokell = (Autos)obj;
			
			// Anzahl der Protokell lesen:
			int nProtokell = in.readInt();
			
			// Nun die einzelnen Protokell lesen:
			Autodao ProtekellDAO = new Autodao (in, null);
		
			for (int i=0; i<nProtokell; ++i) {
				Auto m = new Auto();
				ProtekellDAO.read(m);
				Protokell.add(m);
			}
		}
	}
	
}
