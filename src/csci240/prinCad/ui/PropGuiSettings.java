package csci240.prinCad.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.paint.Color;

import csci240.prinCad.ui.Log.LoggingLevel;

/** PropGuiSettings Object:
 * this object allows to record, import and save parameters of the
 * prinCad UI as a Java.util.Properties object. this uses an array
 * of values to match each required item.
 * @author dnglokpor
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
		this.guiSettings.setProperty(LABELS[2], "yellow");
		return Color.DARKGOLDENROD;  // else return default
	}
	
	/**
	 * @return a color that match canvas color attribute or default Color.BLACK
	 */
	public Color getCanvasColor() { 
		Color returned = assignColor(this.guiSettings.getProperty(LABELS[5]));
		if(returned != null) // check that input is valid
			return returned;
		this.guiSettings.setProperty(LABELS[5], "black");
		return Color.BLACK;  // else return default
	}
	
	/**
	 * try to send the current verbosity read from the file. if it is invalid
	 * converts value to default and send default.
	 * @return the current verbosity level or default.
	 */
	public LoggingLevel getLoggingLevel() { 
		String verb = this.guiSettings.getProperty(LABELS[6]);
		try {
			return LoggingLevel.valueOf(verb);
		}catch(Exception ex){
			System.out.println("User specified verbosity unknown.\n" + ex);
			this.guiSettings.setProperty(LABELS[6], 
					LoggingLevel.Information.toString()); // modify current value
		}
		// if we weren't done
		return LoggingLevel.valueOf(this.guiSettings.getProperty(LABELS[6]));
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
		File settings = new File(FILENAME);
		
		if(settings.exists()) { // the file exists
			FileInputStream in = new FileInputStream(FILENAME); // set input stream
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
		FileOutputStream out = new FileOutputStream(FILENAME); // set output stream
		guiSettings.store(out, "-- prinCad UI settings parameters --"); // save to file
		out.close(); // CLOSE STREAM
	}

	@Override
	/**
	 * changes the current value of the canvas width property.
	 */
	public void setCanvasWidth(double newVal) {
		guiSettings.setProperty(LABELS[3], String.valueOf((int) newVal));
	}

	/**
	 * changes the current value of the canvas height property.
	 */
	@Override
	public void setCanvasHeight(double newVal) {
		guiSettings.setProperty(LABELS[4], String.valueOf((int) newVal));
	}
	
	@Override
	/**
	 * changes the current value of the canvas width property.
	 */
	public void setSceneWidth(double newVal) {
		guiSettings.setProperty(LABELS[0], String.valueOf((int) newVal));
	}

	/**
	 * changes the current value of the scene height property.
	 */
	@Override
	public void setSceneHeight(double newVal) {
		guiSettings.setProperty(LABELS[1], String.valueOf((int) newVal));
	}
}
