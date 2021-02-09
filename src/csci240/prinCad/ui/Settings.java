package csci240.prinCad.ui;

import javafx.scene.paint.Color;

/** Settings interface:
 * sets the basic requirement of Objects that can be used to setup the
 * GUI of the prinCad project.
 * @author dnglo
 */
interface Settings {
	// path and file names
	String PATH_TO_SETTINGS = "./config/";
	String SETTINGS_FILE_NAME = "guiconfig";
	
	// initial values
	String[] LABELS = new String[] {
		"Scene Width: ",  	// [0]
		"Scene Height: ", 	// [1]
		"Scene Color: ",  	// [2]
		"Canvas Width: ", 	// [3]
		"Canvas Height: ",	// [4]
		"Canvas Color: "  	// [5]
	};
	String[] DEFAULTS = new String[] {
		"300", 		// [0]
		"250",		// [1]
		"yellow",	// [2]
		"275",		// [3]
		"225",		// [4]
		"black"		// [5]
	};
	
	// color presets
	String[] PRESET_NAMES = { // strings arguments
		"red", "green", "blue", "yellow", "purple", "black" 
	};
	Color[] PRESET_VALUES = { // indexed Colors
		Color.ORANGERED, Color.FORESTGREEN, Color.CADETBLUE, Color.DARKGOLDENROD,
		Color.MEDIUMPURPLE, Color.BLACK
	};
	
	// methods to override
	// getters for all the attributes needed
	int getSceneWidth();
	int getSceneHeight();
	int getCanvasWidth();	
	int getCanvasHeight();
	Color getSceneColor();
	Color getCanvasColor();
	
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
