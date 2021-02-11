package csci240.prinCad.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.paint.Color;

/** PropGuiSettings Object:
 * this object allows to record, import and save parameters of the
 * prinCad UI as a Java.util.Properties object. this uses an array
 * of values to match each required item.
 * @author dnglo
 *
 */
class PropGuiSettings implements Settings{
	// attributes
	private Properties guiSettings;
	
	/** constructor:
	 * adds the default values of the Settings interface to the Properties
	 * hash table. also initializes the file path.
	 */
	public PropGuiSettings() {
		guiSettings = new Properties(); // instantiate the Properties object
		// add data
		for(int i = 0; i < DEFAULTS.length; i++) {
			guiSettings.setProperty(LABELS[i], DEFAULTS[i]);
		}
	}
	
	// getters
	public int getSceneWidth() { return Integer.parseInt(this.guiSettings.getProperty(LABELS[0])); }
	
	public int getSceneHeight() { return Integer.parseInt(this.guiSettings.getProperty(LABELS[1])); }
	
	public int getCanvasWidth() { return Integer.parseInt(this.guiSettings.getProperty(LABELS[3])); }
	
	public int getCanvasHeight() { return Integer.parseInt(this.guiSettings.getProperty(LABELS[4])); }
	
	/**
	 * @return a color that match scene color attribute or default Color.DARKGOLDENROD
	 */
	public Color getSceneColor() {
		Color returned = assignColor(this.guiSettings.getProperty(LABELS[2]));
		if(returned != null) // check that input is valid
			return returned;
		return Color.DARKGOLDENROD;  // else return default
	}
	
	/**
	 * @return a color that match canvas color attribute or default Color.BLACK
	 */
	public Color getCanvasColor() { 
		Color returned = assignColor(this.guiSettings.getProperty(LABELS[5]));
		if(returned != null) // check that input is valid
			return returned;
		return Color.BLACK;  // else return default
	}
	
	// other methods
	/**
	 * return a Color object associated with passed string. conversion is done
	 * via the static PRESET_NAMES and PRESET_VALUES arrays.
	 * @param strColor the string name of the color
	 * @return a Color object or null if passed doesn't match any colors.
	 */
	public Color assignColor(String strColor) {
		Color selected = null;
		int i = 0;
		boolean found = false;
		while(i < PRESET_NAMES.length && !found) { // search for a matching color
			found = PRESET_NAMES[i].equals(strColor);
			if(found) {
				selected = PRESET_VALUES[i];
			}else {
				i++;
			}
		}
		return selected;
	}
	
	/**
	 * checks if the property file exists - in which case import data. else does
	 * nothing and sticks with the defaults.
	 * @throws IOException when the file wasn't found or couldn't be accessed
	 */
	public void readSettings() throws IOException {
		File settings = new File(this.FILENAME);
		
		if(settings.exists()) { // the file exists
			FileInputStream in = new FileInputStream(this.FILENAME); // set input stream
			guiSettings.load(in); // import data
			in.close(); // CLOSE THE STREAM
		}
	}
	
	/**
	 * saves the properties object to a file for future reference. uses the filename
	 * attribute of the Object.
	 * @throws IOException if the file wasn't found or couldn't be accessed.
	 */
	public void saveSettings() throws IOException {
		// check that path exists
		if(!new File(PATH_TO_SETTINGS).exists()) { 
			new File(PATH_TO_SETTINGS).mkdirs(); 
		}
		FileOutputStream out = new FileOutputStream(this.FILENAME); // set output stream
		guiSettings.store(out, "-- prinCad UI settings parameters --"); // save to file
		out.close(); // CLOSE STREAM
	}
}
