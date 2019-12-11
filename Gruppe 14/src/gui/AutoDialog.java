package gui;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Auto;
import gui.AutoFrame.NorthPanel.FirstLine;


import Model.Auto;

public class AutoDialog extends JDialog implements ActionListener{

	public boolean closedOK = false;	// true, wenn Dialog durch OK beendet wird

	// Referenz auf das Model-Objekt als Datenquelle:
	private Auto myAuto;

	// Dialogelemente als Instanzvariablen:
	private JTextField 		Autokennzeichen       =  new  JTextField(10);	// 10 Spalten
	private JLabel			lblAutokennzeichen    =  new JLabel("Autokennzeichen                                      ");

	private JTextField 		Datum      =   new JTextField(10);
	private JLabel			lblDatum   =   new JLabel("Datum                                                           ");
  
	//Aufzählung mehrerer Autostile
	private String wahl;
	private String[] texte ={"Bitte auswaehlen Sie","regnet","sonnig","bewölkt","Schnee","Nebel","windig"};

	//ComboBox hinzufügen 
	private JComboBox<String>		Wetter     = new JComboBox<String>(texte);
	private JLabel			lblWetter  = new JLabel("Wetter                                               ");
	
	//Checkbox hinzufügen
	private JCheckBox		Verstöße = new JCheckBox("Verstöße ");
	
	private String wahl1;
	private String[] texte1 ={"Bitte auswaehlen Sie","...","eine Rote Ampel überfahren","Übergeschwindigkeit","besoffen fahren","falsch parken","gegen Orientierung fahren",
			                  "Übergewicht","Ohne Fahrschein"};
	
	private JComboBox<String>		Stil     = new JComboBox<String>(texte1);
	private JLabel			     lblStil  = new JLabel("Stil                                         ");
	

	//Tonqualität
	private JTextField      MAXfahrgeschwindigkeit  = new JTextField(10);
	private JLabel          lblMAXfahrgeschwindigkeit  = new JLabel("MAXfahrgeschwindigkeit           ");
	private JLabel          lblEinheit  = new JLabel("km/h ");
	//Autoalbum
	private JTextField      Strafgeld  = new JTextField(10);
	private JLabel          lblStrafgeld  = new JLabel("Strafgeld                                         ");
	private JLabel          lblEinheit1  = new JLabel("Euro ");
	
	
	private JTextField 		Breitengrad      =   new JTextField(10);
	private JLabel			lblBreitengrad   =   new JLabel("Breitengrad                                    ");
	private JLabel          lblEinheit2  = new JLabel(" grad");
	
	
	private JTextField 		Längengrad     =   new JTextField(10);
	private JLabel			lblLängengrad  =   new JLabel("Längengrad                                   ");
	private JLabel          lblEinheit3  = new JLabel(" grad");
	
	//Button "Ok" und "Abbrechen
	private JButton			ok		  = new JButton("OK");
	private JButton			abbrechen = new JButton("Abbrechen");
	
	
	
	
	


	/**
	 * Konstruktor initialisiert den Dialog.
	 * Das Dialogfenster hat keine Daten, wenn ein neues Auto angelegt wird. In diesem
	 * Fall ist das übergebenen Auto-Objekt leer.
	 * Wird ein vorhandener Auto bearbeitet, werden dessen Daten zu Initialisierung
	 * der GUI-Elemente verwendet.
	 */
	public AutoDialog (Window parent, Auto m) {

		super (parent, "Daten bearbeiten", Dialog.ModalityType.APPLICATION_MODAL ); // Modaler Dialog, Titel wird spï¿½ter gesetzt
		myAuto = m;

	
		Autokennzeichen.setText(m.getAutokennzeichen());
		Datum.setText(m.getDatum());  
		Wetter.setSelectedItem(""+m.getWetter());
		Verstöße.setSelected(m.getVerstöße());
		Stil.setSelectedItem(""+m.getStil());

		
		// double-Wert für Note in formatierten String mit 2 Dezimalstellen:
				DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
				dfs.setDecimalSeparator(',');	// Dezimalkomma statt Punkt
				DecimalFormat df = new DecimalFormat ("0.00", dfs);  // 2 Dezimalstellen
				MAXfahrgeschwindigkeit.setText(df.format(m.getMAXfahrgeschwindigkeit()));
				
		// double-Wert für Note in formatierten String mit 2 Dezimalstellen:
				DecimalFormatSymbols dfs1 = DecimalFormatSymbols.getInstance();
				dfs1.setDecimalSeparator(',');	// Dezimalkomma statt Punkt
				DecimalFormat df1 = new DecimalFormat ("0.00", dfs1);  // 2 Dezimalstellen
				Strafgeld.setText(df1.format(m.getStrafgeld()));
				
	    // double-Wert für Note in formatierten String mit 2 Dezimalstellen:
				DecimalFormatSymbols dfs2 = DecimalFormatSymbols.getInstance();
				dfs2.setDecimalSeparator(',');	// Dezimalkomma statt Punkt
				DecimalFormat df2 = new DecimalFormat ("0.00", dfs2);  // 2 Dezimalstellen
				Breitengrad.setText(df2.format(m.getBreitengrad()));
				
		// double-Wert für Note in formatierten String mit 2 Dezimalstellen:
				DecimalFormatSymbols dfs3 = DecimalFormatSymbols.getInstance();
				dfs3.setDecimalSeparator(',');	// Dezimalkomma statt Punkt
				DecimalFormat df3 = new DecimalFormat ("0.00", dfs3);  // 2 Dezimalstellen
				Längengrad.setText(df3.format(m.getLängengrad()));
				
				
		// Layout festlegen und GUI-Elemente hinzufügen:
		setLayout (new FlowLayout());	
 
		add (lblAutokennzeichen);		
		add (Autokennzeichen);
		add (lblDatum);		
		add (Datum);
		add (lblWetter);
		add (Wetter);
		add (lblStil);
		add (Stil);
		add (Verstöße);
		add (lblMAXfahrgeschwindigkeit);
		add (MAXfahrgeschwindigkeit);
		add (lblStrafgeld);
		add (Strafgeld);
		add (lblBreitengrad);
		add (Breitengrad);
		add (lblLängengrad);
		add (Längengrad);
		
		add (ok);
		add (abbrechen);
		pack();
		
		 

		// Layout festlegen und GUI-Elemente hinzufugen
		class WestPanel extends Box {
			/**
			 * Layout in FirstLine entwerfen.
			 */
			class FirstLine extends JPanel {
				public FirstLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblAutokennzeichen);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Autokennzeichen );
				}
			}
			class SecondLine extends JPanel {
				/**
				 * Layout in SecondLine entwerfen.
				 */
				public SecondLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblDatum);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Datum);
				}
			}
			class ThirdLine extends JPanel {
				/**
				 * Layout in ThirdLine entwerfen.
				 */
				public ThirdLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblWetter);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Wetter);
				}
			}
			
			
			class FouthLine extends JPanel {
				/**
				 * Layout in FouthLine entwerfen.
				 */
				public FouthLine() {
					
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (Verstöße);
				}
			}
			
			
			
			class FifthLine extends JPanel {
				/**
				 * Layout in FouthLine entwerfen.
				 */
				public FifthLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblStil);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Stil);
				}
			}
			
			
			
			
			class SixthLine extends JPanel {
				/**
				 * Layout in FouthLine entwerfen.
				 */
				public SixthLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblMAXfahrgeschwindigkeit);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (MAXfahrgeschwindigkeit);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (lblEinheit);
				}
			}
			
			class SeventhLine extends JPanel {
				/**
				 * Layout in FouthLine entwerfen.
				 */
				public SeventhLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblStrafgeld);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Strafgeld);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (lblEinheit1);
					
					
				}
			}
			class EighthLine extends JPanel {
				/**
				 * Layout in FouthLine entwerfen.
				 */
				public EighthLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblBreitengrad);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Breitengrad);
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (lblEinheit2);
				}
			}
			class NinthLine extends JPanel {
				/**
				 * Layout in FouthLine entwerfen.
				 */
				public NinthLine() {
					setLayout (new FlowLayout(FlowLayout.LEFT));
					add (lblLängengrad );
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (Längengrad );
					setLayout (new FlowLayout(FlowLayout.RIGHT));
					add (lblEinheit3);
				}
			}
		
			
		//	private JTextField      verstöße = new JTextField(10);
			//private JLabel          lblVerstöße = new JLabel("Verstöße");
			
			
			
			/**
			 * BoxLayout in WestPanel entwerfen.
			 */
			public WestPanel () {

				// 9 Zeilen sukzessiv aufkommen
				super (BoxLayout.Y_AXIS);
				add (new FirstLine());
				add (new SecondLine());
				add (new ThirdLine());
				add (new FouthLine());
				add (new FifthLine());
				add (new SixthLine());
				add (new SeventhLine());
				add (new EighthLine());
				add (new NinthLine());

			}
		}

		// Am SouthPanel gibt es zwei Button "OK" und "Abbrechen" und FlowLayout genutzt wird.
		class SouthPanel extends JPanel {
			/**
			 * Layout in SouthPanel entwerfen.
			 */
			public SouthPanel() {
				setLayout (new FlowLayout(FlowLayout.RIGHT));
				add (ok);
				add (abbrechen);
			}
		}


		

		setLayout (new BorderLayout());
		add (new WestPanel(), BorderLayout.WEST);
		add (new SouthPanel(), BorderLayout.SOUTH);

		// Event-Handler installieren:
		ok.addActionListener (this);
		abbrechen.addActionListener (this);
		this.setTitle ("Neue Auto hinzufügen");
		this.setLocation(300,50);   //Koordination: x=300, y=50;
		this.setSize (400, 500);	// Breite: 400 Pixel; Hoehe: 500 Pixel

		// Dialogfenster sichtbar machen:
		setVisible (true);	
	}


	/**
	 *  Klicken auf Button "Ok" (neue Autotitel hinzufügen) oder "Abbrechen" auswerten
	 */
	public void actionPerformed (ActionEvent e) {

		//  Schaltfläche entnehmen:
		Object source = e.getSource();
		if (source == ok) {
			// Werte des Auto-Objekts entsprechend den Dialogelementen aktualisieren:

			myAuto.setAutokennzeichen (Autokennzeichen.getText());
			myAuto.setDatum(Datum.getText());
		    myAuto.setWetter((String) Wetter.getSelectedItem());
			myAuto.setStil((String) Stil.getSelectedItem());
		
			
			//	SpieldauerZeit hat Dezimalkomma.	
			double Fahrgeschwindigkeit = 0;  // Voreinstellung(Spielauer =0.0), falls Textumwandlung schief geht
		
			
		    
			NumberFormat nf = NumberFormat.getInstance();
			try {
				Number sd = nf.parse(MAXfahrgeschwindigkeit.getText());
				Fahrgeschwindigkeit  = sd.doubleValue();
				
			}
			catch (ParseException ex) {
			
		}
			myAuto.setMAXfahrgeschwindigkeit(Fahrgeschwindigkeit );
			
			
			
			//	SpieldauerZeit hat Dezimalkomma.	
			double Geld = 0;  // Voreinstellung(Spielauer =0.0), falls Textumwandlung schief geht
		
			//To format a number for a different locale. specify it in the call to getInstance.
		    //Formalieren Sie eine Nummer für eine andere Locale,geben Sie es in dem Aufruf von getInstance.
			NumberFormat nf1 = NumberFormat.getInstance();
			try {
				Number sd = nf1.parse(Strafgeld.getText());
				Geld  = sd.doubleValue();
		
			}
			catch (ParseException ex) {
			
		}
			myAuto.setStrafgeld(Geld);
			
			myAuto.setVerstöße(Verstöße.isSelected());
		
			
             double Breiten = 0;  // Voreinstellung(Spielauer =0.0), falls Textumwandlung schief geht
		
			
		    
			NumberFormat nf2 = NumberFormat.getInstance();
			try {
				Number sd = nf2.parse(Breitengrad.getText());
				Breiten  = sd.doubleValue();
				
			}
			catch (ParseException ex) {
				
		}
			
			myAuto.setBreitengrad(Breiten);
			
			double Längen = 0;  // Voreinstellung(Spielauer =0.0), falls Textumwandlung schief geht
		
		    
			NumberFormat nf3 = NumberFormat.getInstance();
			try {
				Number sd = nf3.parse(Längengrad.getText());
				Längen  = sd.doubleValue();
				
			}
			catch (ParseException ex) {
			
		}
			myAuto.setLängengrad(Längen);
			

			//true,wenn Dialog durch Ok beendet wird.
			closedOK = true;
		}
		// Dialog schließen:
		setVisible (false);
	}
}



