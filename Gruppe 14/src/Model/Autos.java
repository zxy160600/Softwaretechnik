package Model;

import java.util.ArrayList;



/**
 * Diese Klasse stellt eine Liste von Autos dar.
 */
public class Autos extends ArrayList<Auto> {

	
	/**
	 * Fügt einen neues Auto zur Liste hinzu.
	 * @param s Referenz auf Auto-Objekt.
	 */
	public void addAuto (Auto m) {
		
		super.add(m);
	}

	
	
}
