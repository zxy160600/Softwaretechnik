package GOOGLEMAP;

import java.awt.EventQueue;

import javax.swing.JFrame;
/*
public class GoogleMapsDemo {

	private JFrame frame;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoogleMapsDemo window = new GoogleMapsDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the application.
	 *//*
	public GoogleMapsDemo() {
		initialize();
	}

	*//**
	 * Initialize the contents of the frame.
	 *//*
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class GoogleMapsDemo {
public static void main(String[] args) throws IOException {
JFrame test = new JFrame("Google Maps");
try {
String latitude = "40.714728";
String longitude = "-9.098672";
String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
+ latitude
+ ","
+ longitude
+ "&zoom=5&size=612x612&scale=2&maptype=roadmap";
String destinationFile = "image.jpg";
// read the map image from Google
// then save it to a local file: image.jpg
//
URL url = new URL(imageUrl);
InputStream is = url.openStream();
OutputStream os = new FileOutputStream(destinationFile);
byte[] b = new byte[2048];
int length;
while ((length = is.read(b)) != -1) {
os.write(b, 0, length);
}
is.close();
os.close();
} catch (IOException e) {
e.printStackTrace();
System.exit(1);
}
// create a GUI component that loads the image: image.jpg
//
ImageIcon imageIcon = new ImageIcon((new ImageIcon("image.jpg"))
.getImage().getScaledInstance(630, 600,
java.awt.Image.SCALE_SMOOTH));
test.getContentPane().add(new JLabel(imageIcon));
// show the GUI window
test.setVisible(true);
test.pack();
}
}