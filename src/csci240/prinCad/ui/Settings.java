package csci240.prinCad.ui;

import javafx.scene.paint.Color;
import csci240.prinCad.ui.Log.LoggingLevel;

/** Settings interface:
 * sets the basic requirement of Objects that can be used to setup the
 * GUI of the prinCad project.
 * @author dnglo
 */
interface Settings {
	// path and file names
	final static String PATH_TO_SETTINGS = "./config/";
	final static String SETTINGS_FILE_NAME = "guiconfig.txt";
	final static String FILENAME = PATH_TO_SETTINGS + SETTINGS_FILE_NAME;
	
	// initial values
	String[] LABELS = new String[] {
		"SceneWidth",  	// [0]
		"SceneHeight", 	// [1]
		"SceneColor",  	// [2]
		"CanvasWidth", 	// [3]
		"CanvasHeight",	// [4]
		"CanvasColor",  // [5]
		"Verbosity"		// [6]
	};
	String[] DEFAULTS = new String[] {
		"300", 			// [0]
		"250",			// [1]
		"yellow",		// [2]
		"275",			// [3]
		"225",			// [4]
		"black",		// [5]
		"Information"
	};
	
	// color presets
	String[] PRESET_NAMES = { // strings arguments
		"red", "green", "blue", "yellow", "purple", "grey", "black" 
	};
	Color[] PRESET_VALUES = { // indexed Colors
		Color.ORANGERED, Color.FORESTGREEN, Color.CADETBLUE, Color.DARKGOLDENROD,
		Color.MEDIUMPURPLE, Color.DARKGRAY, Color.BLACK
	};
	
	// methods to override
	// getters for all the attributes needed
	int getSceneWidth();
	int getSceneHeight();
	int getCanvasWidth();	
	int getCanvasHeight();
	Color getSceneColor();
	Color getCanvasColor();
	LoggingLevel getLoggingLevel();
	
	/**
	 * must be used to read settings from the predefined settings file
	 * indexed by PATH_TO_SETTINGS and SETTINGS_FILE_NAME in the 
	 * attributes of the interface.
	 * @throws Exception when IO operations fail for any reason
	 */
	void readSettings() throws Exception;
	/**
	 * must be used to write settings to the predefined settings file
	 * indexed by PATH_TO_SETTINGS and SETTINGS_FILE_NAME in the 
	 * attributes of the interface. Should overwrite the whole contents
	 * of the file.
	 * @throws Exception when IO operations fail for any reason
	 */
	void saveSettings() throws Exception;
}
