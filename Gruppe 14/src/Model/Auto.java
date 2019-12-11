package Model;

public class Auto {

	//Alle Elemente von Musiktitel initialisieren.
	private String Autokennzeichen;
	private String Datum;
	private String Wetter;
	private String Stil;
	private double MAXfahrgeschwindigkeit;
	private boolean Verstöße ;
	private double Strafgeld;
    private double  Breitengrad;
	private double Längengrad;
	
	
	public Auto() {
	}
	/**
	 * Hier werden alle Elemente des Autotitels definiert.
	 */
	public Auto(String Autokennzeichen, String Wetter, String Stil, double MAXfahrgeschwindigkeit, boolean Verstöße, String Datum, double Strafgeld,double  Breitengrad,double Längengrad) {
		
		this.Autokennzeichen = Autokennzeichen;
		this.Datum = Datum;
		this.Wetter = Wetter;
		this.Stil = Stil;
		this.MAXfahrgeschwindigkeit = MAXfahrgeschwindigkeit;
		this.Verstöße = Verstöße;
		this.Strafgeld = Strafgeld;
		this.Breitengrad =  Breitengrad;
		this.Längengrad = Längengrad;
		
		
	}
	public String getAutokennzeichen() {
		return Autokennzeichen;
	}
	public void setAutokennzeichen(String Autokennzeichen) {
		this.Autokennzeichen = Autokennzeichen;
	}
	public String getWetter() {
		return  Wetter;
	}
	public void setWetter(String Wetter) {
		this. Wetter =  Wetter;
	}
	public String getStil() {
		return Stil;
	}
    public void setStil(String Stil){
	    this.Stil = Stil;
    }
	public  double getMAXfahrgeschwindigkeit() {
		return MAXfahrgeschwindigkeit;
	}
	public void setMAXfahrgeschwindigkeit(double MAXfahrgeschwindigkeit) {
		this.MAXfahrgeschwindigkeit = MAXfahrgeschwindigkeit;
	}
	public boolean getVerstöße () {
		return Verstöße;
	}
	public void setVerstöße(boolean hatVerstöße) {
		this.Verstöße = hatVerstöße;
	}
	
	public String getDatum() {
		return Datum;

	}
	public void setDatum(String Datum) {
		this.Datum = Datum;
	}
	public double getStrafgeld() {
		return Strafgeld;

	}
	public void setStrafgeld(double Strafgeld) {
		this.Strafgeld = Strafgeld;
	}
	
	public double getBreitengrad() {
		return Breitengrad;

	}
	public void setBreitengrad(double Breitengrad) {
		this.Breitengrad = Breitengrad;
	}
	public double getLängengrad() {
		return Längengrad;

	}
	public void setLängengrad(double Längengrad) {
		this.Längengrad = Längengrad;
	}
}


	
	


