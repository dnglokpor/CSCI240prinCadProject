package csci240.prinCad.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import csci240.prinCad.ui.Log.LoggingLevel;
import javafx.scene.paint.Color;

/** TextGuiSettings Object:
 * object that standardizes importing and writing prinCad GUI setting parameters
 * to a text file. the file file name and path are specified by the interface
 * implemented. the constructor finalizes the file as a text simple IO file.
 * @author dnglo
 */
class TextGuiSettings implements Settings{
	// initial shared attributes
	private int sceneWidth;
	private int sceneHeight;
	private String sceneColor;
	private int canvasWidth;
	private int canvasHeight;
	private String canvasColor;
	private String verbosity;
	
	/** default constructor:
	 * initialize all the attributes with the default values from the Settings
	 * interface and initializes the filename.
	 */
	public TextGuiSettings() {
		// default all attributes
		this.sceneWidth = Integer.parseInt(DEFAULTS[0]);
		this.sceneHeight = Integer.parseInt(DEFAULTS[1]);;
		this.sceneColor = DEFAULTS[2];
		this.canvasWidth = Integer.parseInt(DEFAULTS[3]);;
		this.canvasHeight = Integer.parseInt(DEFAULTS[4]);;
		this.canvasColor = DEFAULTS[5];
	}
	
	// getters
	public int getSceneWidth() { return this.sceneWidth; }
	
	public int getSceneHeight() { return this.sceneHeight; }
	
	public int getCanvasWidth() { return this.canvasWidth; }
	
	public int getCanvasHeight() { return this.canvasHeight; }
	
	/**
	 * @return a color that match sceneColor attribute or default Color.DARKGOLDENROD
	 */
	public Color getSceneColor() {
		Color returned = assignColor(this.sceneColor);
		if(returned != null) // check that input is valid
			return returned;
		return Color.DARKGOLDENROD;  // else return default
	}
	
	/**
	 * @return a color that match canvasColor attribute or default Color.BLACK
	 */
	public Color getCanvasColor() { 
		Color returned = assignColor(this.canvasColor);
		if(returned != null) // check that input is valid
			return returned;
		return Color.BLACK;  // else return default
	}
	
	/**
	 * try to send the current verbosity read from the file. if it is invalid
	 * converts value to default and send default.
	 * @return the current verbosity level or default.
	 */
	public LoggingLevel getLoggingLevel() { 
		try {
			return LoggingLevel.valueOf(this.verbosity);
		}catch(Exception ex){
			System.out.println("User specified verbosity unknown.\n" + ex);
			this.verbosity = LoggingLevel.Information.toString(); // modify current value
		}
		// if we weren't done
		return LoggingLevel.valueOf(this.verbosity);
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
	 * deep copies all the data from passed model GuiSettings instance to current
	 * instance.
	 * @param model
	 */
	public void clone(TextGuiSettings model) {
		this.sceneWidth = model.sceneWidth;
		this.sceneHeight = model.sceneHeight;
		this.sceneColor = model.sceneColor;
		this.canvasWidth = model.canvasWidth;
		this.canvasHeight = model.canvasHeight;
		this.canvasColor = model.canvasColor;
		this.verbosity = model.verbosity;
		// FILENAME DOESN'T HAVE TO BE CLONED
	}
	
	/**
	 * load the settings attributes into a String array for easy printing.
	 * @return a String array with the data listed in order specified in the attributes.
	 */
	public String[] listSettings() {
		String[] data = new String[DEFAULTS.length];
		data[0] = Integer.toString(this.sceneWidth);
		data[1] = Integer.toString(this.sceneHeight);
		data[2] = this.sceneColor;
		data[3] = Integer.toString(this.canvasWidth);
		data[4] = Integer.toString(this.canvasHeight);
		data[5] = this.canvasColor;
		data[6] = this.verbosity;
		return data;
	}
	
	/**
	 * load settings for this instance from the path and text file specified by
	 * the interface. the text file is expected to be in a certain shape. If 
	 * that shape is not maintained, the import will crash and the data imported
	 * will be simply discarded.
	 * @throws FileNotFoundException when the file couldn't be accessed.
	 */
	public void readSettings() throws FileNotFoundException{
		// create a new GuiSetting to record data from file
		TextGuiSettings readValues = new TextGuiSettings();
		// reference the file with complete path
		File settings = new File(Settings.FILENAME);
		boolean valid = false; // flag
		
		// we do nothing if the file doesn't exist
		if (settings.exists()) {
			Scanner settingsFile = new Scanner(settings);
			if(settingsFile.hasNext()) { // if file is not empty
				try { // importing settings.
					settingsFile.nextLine(); // skips over
					settingsFile.nextLine(); // the two comment lines
					// reading loop
					String read[]; // buffer
					for(int i = 0; i < DEFAULTS.length; i++) {
						read = settingsFile.nextLine().split("=");
						switch(read[0]) {
							case "SceneWidth": 
								readValues.sceneWidth = Integer.parseInt(read[1]);
								break;
							case "SceneHeight": 
								readValues.sceneHeight = Integer.parseInt(read[1]);
								break;
							case "SceneColor": 
								readValues.sceneColor = read[1];
								break;
							case "CanvasWidth": 
								readValues.canvasWidth = Integer.parseInt(read[1]);
								break;
							case "CanvasHeight": 
								readValues.canvasHeight = Integer.parseInt(read[1]);
								break;
							case "CanvasColor": 
								readValues.canvasColor = read[1];
								break;
							case "Verbosity":
								readValues.verbosity = read[1];
								break;
							default:
								break;
						}
					}
					valid = true; // mark that settings import was completed
				}catch(Exception ex) { // any exception
					System.out.println("Exception encounterd: " + ex);
					System.out.println("Generating new settings.");
				}
			}
			settingsFile.close(); // IMPORTANT CLOSE
		}
		
		if(valid) { // successful import
			this.clone(readValues); // values are imported
		}
	};
	
	/**
	 * save the current GUI settings to a file so they can be accessed next 
	 * session. this file is the one specified in the interface implemented.
	 * @throws Exception in case any IO operation failed.
	 */
	public void saveSettings() throws Exception{
		File settings = new File(Settings.FILENAME);
		// check that path exists
		if(!new File(PATH_TO_SETTINGS).exists()) { 
			new File(PATH_TO_SETTINGS).mkdirs(); 
		}
		// create file
		settings.createNewFile();
		// open write stream to file
		BufferedWriter fw = new BufferedWriter(new FileWriter(Settings.FILENAME));
		// HELPER TEXT WRITING in two first files
		fw.write("#Values for colors must be lowercase strings. Possible values are:");
		fw.newLine();
		fw.write("#red, green, blue, yellow, purple and black.");
		fw.newLine();
		// DATA WRITTING
		for(int i = 0; i < DEFAULTS.length; i++) {
			fw.write(LABELS[i] + "=" + this.listSettings()[i]);
			fw.newLine();
		}
		fw.close(); // ALWAYS CLOSE
	}

	/**
	 * set the canvas width property to the passed value.
	 * @param newVal the passed width attribute
	 */
	@Override
	public void setCanvasWidth(double newVal) {
		this.canvasWidth = (int) newVal;
	}

	@Override
	public void setCanvasHeight(double newVal) {
		this.canvasHeight = (int) newVal;
	}

	@Override
	public void setSceneWidth(double newVal) {
		this.sceneWidth = (int) newVal;
	}

	@Override
	public void setSceneHeight(double newVal) {
		this.sceneHeight = (int) newVal;
	}
}
