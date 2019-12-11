package Model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;




/**
 * Data Access Object für die Klasse Auto.
 */
public class Autodao extends Dao {


	/**
	 * Konstruktor um das Data Access Object mit einem Dateinamen zu initialisieren.
	 * 
	 * @param dateiName Dateiname
	 * @param openForWrite true wenn geschrieben werden soll
	 */
	public Autodao (String dateiName, boolean openForWrite) {

		super (dateiName, openForWrite);
	}


	/**
	 * Konstruktor um das Data Access Object mit bereits vorhandenen Streams zu initialisieren.
	 * 
	 * @param in InputStream oder null
	 * @param out OutputStream oder null
	 */
	public Autodao (DataInputStream in, DataOutputStream out) {

		super (in, out);
	}


	/**
	 * Daten des übergebenen Musik-Objekts schreiben. Das Data Access Object muss dazu zum
	 * Schreiben bereit sein.
	 * 
	 * @param s Referenz auf Musik-Objekt
	 * @throws IOException
	 */
	public void write (Object obj) throws IOException {

		if (out != null) {

			Auto m = (Auto)obj;

			out.writeUTF	(m.getAutokennzeichen());
			out.writeUTF	(m.getDatum());
			out.writeUTF	(m.getWetter());
			out.writeUTF	(m.getStil());
			out.writeDouble (m.getMAXfahrgeschwindigkeit());
			out.writeBoolean(m.getVerstöße());
			out.writeDouble (m.getStrafgeld());	
			out.writeDouble (m.getBreitengrad());	
			out.writeDouble (m.getLängengrad());	
		}
	}


	/**
	 * Daten des übergebenen Auto-Objekts lesen. Das Data Access Objekt muss dazu zum
	 * Lesen bereit sein.
	 * 
	 * @param s Referenz auf Auto-Objekt
	 * @throws IOException
	 */
	public void read (Object obj) throws IOException {

		if (in != null) {

			Auto m = (Auto)obj;

			m.setAutokennzeichen  	(in.readUTF());
			m.setDatum  	(in.readUTF());
			m.setWetter	    (in.readUTF());
			m.setStil	    (in.readUTF());
			m.setMAXfahrgeschwindigkeit	(in.readDouble());
			m.setVerstöße	(in.readBoolean());
			m.setStrafgeld	(in.readDouble());
			m.setBreitengrad(in.readDouble());
			m.setLängengrad	(in.readDouble());
			
			
		}
	}
}

