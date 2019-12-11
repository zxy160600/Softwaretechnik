package gui;

import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.ListModel;
import javax.swing.plaf.ListUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.xml.soap.Text;


import Model.Auto;
import Model.Autos;
import Model.AutosDao;



/**
 * Anwendungsfenster mit Menüs. Klicken auf die Menüs öffnet
 * jeweils ein Dialogfenster. Es gibt vorgefertigte Standarddialoge
 * und selbst zusammengestellte Dialoge.
 * Hauptfenster mit einigen Button-Funktionen.
 * Klicken auf den Button "Hinzufügen",wird ein Dialog gegeben. Mann kann hier einen neuen Autotitel hinzufügen.(Funktion: Hinzufügen eines neues Autotitels)
 * Klicken auf den Button "Ändern", der in der Liste stellt, kann man diesen Autotitel ändern.
 * (Funktion: Ändern eines vorhandenen Autotitels)
 * Auswählen einen Autotitel,der in der Liste stellt. Danach klicken auf den Button"Löschen", kann man diesen Autotitel löschen.
 * (Funktion: Entfernen eines vorhandenen Autotitels)
 * Schreiben den ganzen Autotitel und klicken auf den Button "Suchen", kann man diesen autoktitel suchen. Der gesuchte Autotitel
 * wird markiert.(Funktion: Suchen eines Titels)
 *
 */

public class AutoFrame extends JFrame implements ActionListener {

	Autos auto = new Autos();  // package Sichtbarkeit, damit GUIAutosliste zugreifen kann

	// GUI-Elemente als Instanzvariablen:
	private GUIAutosliste guiListe = new GUIAutosliste(this);

	private JTextField 		Autokennzeichen   = new JTextField(10);
	private JLabel			lblAutokennzeichen   = new JLabel("Autokennzeichen");

    private JButton			btnNeuAuto		 = new JButton("Hinzufügen");

	private JButton			btnAendernAuto		 = new JButton("Ändern");

	private JButton			btnLoeschenAuto		 = new JButton("Löschen");

	private JTextField 		txtTitelEingeben   = new JTextField(15);// 15 Spalten
	private JLabel			lblTitelEingeben	 = new JLabel("Bitte Sie einen Autokennzeichen eingeben");
	private JButton         btnSuchenAuto		 = new JButton("Suchen");


	/**
	 * Menüleiste für das Anwendungsfenster.
	 */
	class MenuLeiste extends JMenuBar {

		/**
		 * Konstruktor initialisiert die Menüleiste.
		 */
		public MenuLeiste () {

			add (new DateiMenu());
		}


		/**
		 * Menü "Datei".
		 */
		class DateiMenu extends JMenu {

			/**
			 * Konstruktor initialisiert das Menü.
			 */
			public DateiMenu () {

				super ("Datei"); //Konstruktur der SuperKlasse wird angerufen.

				add (new DateiOeffnenItem());//Hinzufügrn eine neue DateiOeffenItem;
				addSeparator();
				add (new DateiSpeichernItem());//Hinzufügrn eine neue DateiOeffenItem;
			}


			/**
			 * Menüeintrag für "Datei Öffnen"
			 */
			class DateiOeffnenItem extends JMenuItem implements ActionListener {

				/**
				 * Konstruktor initialisiert den Menüeintrag.
				 */
				public DateiOeffnenItem () {
					super ("Öffnen");			// Verbinden Öffnen-MenuItem mit MeunItem.
					addActionListener (this);	// Reaktion auf Klicken
				}

				/**
				 * Reagieren auf Klick. 
				 */
				public void actionPerformed(ActionEvent e) {

					// Standarddialog zum Auswählen einer Datei:
					JFileChooser fc = new JFileChooser();
					int returnValue = fc.showOpenDialog(AutoFrame.this);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						String fileName = file.toString();
						AutosDao dao = new AutosDao (fileName, false);
						try {
							dao.read(auto);
							dao.close();
						} 
						catch (IOException ex) {
						}
						updateGUI();
					}
				}
			}

			/**
			 * Menüeintrag für "Datei Speichern"
			 */
			class DateiSpeichernItem extends JMenuItem implements ActionListener {

				/**
				 * Konstruktor initialisiert den Menüeintrag.
				 */
				public DateiSpeichernItem () {
					super ("Speichern unter");
					addActionListener (this);
				}

				/**
				 * Reagieren auf Klick. 
				 */
				public void actionPerformed(ActionEvent e) {

					// Standarddialog zum Auswählen einer Datei:
					JFileChooser fc = new JFileChooser();
					int returnValue = fc.showOpenDialog(AutoFrame.this);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						String fileName = file.toString();
						AutosDao dao = new AutosDao (fileName, true);
						try {
							dao.write(auto);
							dao.close();
						} 
						catch (IOException ex) {
						}
					}
				}
			}


		}

	}

	
	/**
	 * Layout entwerfen(BorderLayout und FlowLayout)
	 */

	class NorthPanel extends Box {

		/**
		 * Layout in FirstLine entwerfen.
		 */
		class FirstLine extends JPanel {
			public FirstLine() {
				//Rechts werden Suchen-Funktion gezeigt.
				setLayout (new FlowLayout(FlowLayout.LEFT));

				
				this.add(txtTitelEingeben);
				add(btnSuchenAuto);
				this.add(lblTitelEingeben);

			}
		}
		
		/**
		 * BoxLayout in NorthPanel entwerfen.
		 */
		public NorthPanel () {

			// 1 Zeilen auf Boxlayout hinzufügen:
			super (BoxLayout.Y_AXIS);
			add (new FirstLine());
			

		}
	}


	class SouthPanel extends JPanel {
		/**
		 * Layout  entwerfen.
		 */
		public SouthPanel() {
			setLayout (new FlowLayout(FlowLayout.LEFT));
			add (btnNeuAuto);
			add (btnAendernAuto);
			add (btnLoeschenAuto);

		}
	}


	/**
	 * Konstruktor initialisiert Anwendungsfenster.
	 */
	public AutoFrame () {

		// Reaktion beim Schließen des Fensters festlegen (statt WindowEventHandler):
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		// Menüzeile installieren:
		setJMenuBar (new MenuLeiste());

		// Autosdatenliste in GUI-Liste übertragen:
		guiListe.setAutos(auto);

		// Layout setzen und GUI-Elemente hinzufügen:
		setLayout (new BorderLayout());
		add (new NorthPanel(), BorderLayout.NORTH);
		add (guiListe        , BorderLayout.CENTER);
		add (new SouthPanel(), BorderLayout.SOUTH);

		// Event-Handler installieren:
		btnNeuAuto.addActionListener (this);
		btnLoeschenAuto.addActionListener(this);
		btnSuchenAuto.addActionListener(this);
		btnAendernAuto.addActionListener(this);


		// Eigenschaften des Fensters festlegen:

		this.setIconImage((new ImageIcon("image\\Auto.jpg")).getImage());//Logo hinzufügen
		setTitle ("Willkommen zum Fahrtenbuchsystem!");
		setSize (1100, 650);	// Breite: 1050 Pixel; Hoehe: 750 Pixel
		setLocation(400, 200);  //Location: (400,200)
		setVisible (true);	// Fensterrahmen sichtbar machen
	}

	/**
	 * Klicken auf Button "Hinzufügen" auswerten:
	 */
	public void actionPerformed (ActionEvent e) {

		if(e.getActionCommand().equals("Hinzufügen")){
			// Neues Auto-Objekt erzeugen:
			Auto m = new Auto();
			// Dialog zum Bearbeite des neuen Objekts zeigen:
			AutoDialog dlg = new AutoDialog(this, m);

			if (dlg.closedOK) {
				// Neuen Autos hinzufügen:
				auto.addAuto (m);
				// GUI-Elemente mit Daten aktualisieren:
				updateGUI();


			}
		}
		
		/**
		 * Klicken auf Button "Ändern" auswerten:
		 */
		else if (e.getActionCommand().equals("Ändern")){
			//Auto-Objekt wählen:
			int[] items = guiListe.getSelectedIndices();
			
			//das auswählende Auto-Objekt ändern:
			int i = items[0];
			Auto m = auto.get(i);
			AutoDialog dlg = new AutoDialog(this,m);
			if(dlg.closedOK){
				
				// GUI-Elemente mit Daten aktualisieren:
				updateGUI();
			}
		}

		
		/**
		 * Klicken auf Button "Löschen" auswerten:
		 */
		else if(e.getActionCommand().equals("Löschen")){
			
			//Auto-Objekt wählen:
			int[] items = guiListe.getSelectedIndices();
			int decrease = 0;
			
			//Mehre Auto-Objekts löschen:
			for(int item:items){
				auto.remove(item - decrease);
				decrease++;
			}
			// GUI-Elemente mit Daten aktualisieren:
			updateGUI();
		}



		/**
		 * Klicken auf Button "Suchen" auswerten:
		 */
		else if (e.getSource()==btnSuchenAuto){

			ListModel<String> model = guiListe.getModel();


			//erhalten die eingegebene Titel
			//public String trim(), benuztet man diese Methode, wenn schreibt man Leerzeichen am Anfang und am Ende  keine Behinderung für Erhaltung des Titels 			     
			String suchtext = txtTitelEingeben.getText().trim();

			//erhalten Anzahl der Autotitel
			int size = model.getSize();

			//Die ganze Autotitel prüfen einzeln.
			for (int i = 0; i < size; i++) {
				Object o = model.getElementAt(i);
				//Hier kann man prüfen, ob es passende Autotitel in guiListe gibt   
				if (o.equals(suchtext)) {
					guiListe.setSelectedIndex(i);
					return;
				}
			}
			//Wenn keine passende Autotitel gesucht wird, zeigt ein Dialog " Not found".
			guiListe.setSelectedIndex(-1);   
			JOptionPane.showMessageDialog(this, "Not found. "); 
			return;   
		}
	}







	void updateGUI () {

		// Autosdatenliste in GUI-Liste übertragen:
		guiListe.setAutos(auto);
		
		// Auswertung für Anzahl Autos aktualisieren:
		
		lblAutokennzeichen.setText(""+auto.size());	
	}

	/**
	 * Hier startet unsere Anwendung.
	 * @param args Argumente der Befehlszeile, werden nicht benötigt.
	 */
	public static void main (String[] args) {

		/**
		 * Hier merken wir uns die Referenz des Anwendungsfensters
		 * in einer Klassenvariablen.
		 */
		new AutoFrame ();
	}
}
