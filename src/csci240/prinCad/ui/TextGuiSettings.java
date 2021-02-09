package csci240.prinCad.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javafx.scene.paint.Color;

/** TextGuiSettings Object:
 * object that standardizes importing and writing prinCad GUI setting parameters
 * to a text file. the file file name and path are specified by the interface
 * implemented. the constructor finalizes the file as a text simple IO file.
 * @author dnglo
 */
class TextGuiSettings implements Settings{
	// initial shared attributes
	private final String FILENAME;
	private int sceneWidth;
	private int sceneHeight;
	private String sceneColor;
	private int canvasWidth;
	private int canvasHeight;
	private String canvasColor;
	
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
		// set filename
		this.FILENAME = PATH_TO_SETTINGS + SETTINGS_FILE_NAME + ".txt";
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
		// FILENAME DOESN'T HAVE TO BE CLONED
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
		File settings = new File(this.FILENAME);
		boolean valid = false; // flag
		
		// we do nothing if the file doesn't exist
		if (settings.exists()) {
			Scanner settingsFile = new Scanner(settings);
			if(settingsFile.hasNext()) { // if file is not empty
				try { // importing settings.
					readValues.sceneWidth = 
						Integer.parseInt(settingsFile.nextLine().split(" ")[2]); // scene width
					readValues.sceneHeight = 
						Integer.parseInt(settingsFile.nextLine().split(" ")[2]); // scene height
					readValues.sceneColor = 
						settingsFile.nextLine().split(" ")[2]; // scene color
					readValues.canvasWidth = 
						Integer.parseInt(settingsFile.nextLine().split(" ")[2]); // canvas width
					readValues.canvasHeight = 
						Integer.parseInt(settingsFile.nextLine().split(" ")[2]); // canvas height
					readValues.canvasColor = 
						settingsFile.nextLine().split(" ")[2]; // canvas color
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
		File settings = new File(this.FILENAME); 
		// check that path exists
		if(!new File(PATH_TO_SETTINGS).exists()) { 
			new File(PATH_TO_SETTINGS).mkdirs(); 
		}
		// create file
		settings.createNewFile();
		// open write stream to file
		BufferedWriter fw = new BufferedWriter(new FileWriter(this.FILENAME));
		// DATA WRITING
		fw.write("Scene Width: "); // label
		fw.write(Integer.toString(this.sceneWidth)); // value
		fw.newLine();
		fw.write("Scene Height: ");
		fw.write(Integer.toString(this.sceneHeight));
		fw.newLine();
		fw.write("Scene Color: ");
		fw.write(this.sceneColor);
		fw.newLine();
		fw.write("Canvas Width: ");
		fw.write(Integer.toString(this.canvasWidth));
		fw.newLine();
		fw.write("Canvas Height: ");
		fw.write(Integer.toString(this.canvasHeight));
		fw.newLine();
		fw.write("Canvas Color: ");
		fw.write(this.canvasColor);
		fw.newLine();
		// HELPER TEXT WRITING
		fw.newLine(); // extra line
		fw.write("modification directives:");
		fw.newLine(); // extra line
		fw.write("Values for widths and heights must be integers.");
		fw.newLine();
		fw.write("Values for colors must be lowercase strings. Possible values are:");
		fw.newLine();
		fw.write("red, green, blue, yellow, purple and black.");
		fw.close(); // IMPORTANT CLOSE FILEWRITERS
	}
}
