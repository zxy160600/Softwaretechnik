package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import Model.Auto;
import Model.Autos;

/**
 * GUIAutoListe erben von JList.
 */


public class GUIAutosliste extends JList<String> {

	private AutoFrame frame;

	private DefaultListModel<String> myListModel = new DefaultListModel<String> ();

	/* Hier definieren wir einen Eventhandler, um auf Mausereignisse zu reagieren.
	 */
	private class ListClickHandler extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			// Doppelklick mit linker Taste auf Liste?
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				// Ein vorhandener Student wird bearbeitet:
				int index = getSelectedIndex();
				if (index >= 0)	{

					Auto m = frame.auto.get(index);
					AutoDialog dlg = new AutoDialog (frame, m);
					if (dlg.closedOK) {
						frame.updateGUI();
					}
					//String name = myListModel.get (index);
					//JOptionPane.showMessageDialog(GUIAutosliste.this, "Doppelklick auf " + name);
				}
			}
		}
	}


	public GUIAutosliste (AutoFrame frame) {

		this.frame = frame;
		// Model und Liste verbinden:
		setModel (myListModel);
		// Maushandler verbinden, um auf Doppelklick zu reagieren:
		addMouseListener (new ListClickHandler());
	
		//BorderLayout wird benutzt und ein Bild hinzufügen in guiListe 
				setLayout(new BorderLayout());
				add(new JLabel(new ImageIcon("image\\Autokennzeichen.jpg")),BorderLayout.EAST);
				
	}


	public void setAutos (Autos auto) {

		// Initialisierung der Autosliste
		myListModel.removeAllElements();
		for (Auto m: auto) {

			myListModel.addElement(m.getAutokennzeichen());
		}

	}
}


