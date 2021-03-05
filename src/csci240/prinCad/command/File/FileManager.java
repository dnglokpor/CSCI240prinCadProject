package csci240.prinCad.command.File;

import csci240.prinCad.command.MenuManager;
import csci240.prinCad.ui.PrinCanvas;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/** FileManager class:
 * class that manages all options under the File menu. Uses commands defined as the individual
 * classes NewFileCommand, OpenFileCommand, SaveFileCommand, and SaveAsFileCommand to handle
 * each option action.
 * @author dnglokpor
 */
public class FileManager implements MenuManager{
	// file commands
	private NewFileCommand _newFileCommand;
	private OpenFileCommand _openFileCommand;
	private SaveFileCommand _saveFileCommand;
	private SaveAsFileCommand _saveAsFileCommand;
	
	/**
	 * takes in a canvas and associates an instance of each of the commands to it.
	 * @param canvas the PrinCanvas object being drawn on.
	 */
	public FileManager(PrinCanvas canvas) {
		_newFileCommand = new NewFileCommand(canvas);
		_openFileCommand = new OpenFileCommand(canvas);
		_saveFileCommand = new SaveFileCommand(canvas);
		_saveAsFileCommand = new SaveAsFileCommand(canvas);
	}
	
	public Menu buildMenu() {
		// create menu items
		MenuItem miNew = new MenuItem("New");
		miNew.setOnAction(e -> _newFileCommand.action(e));
		MenuItem miOpen = new MenuItem("Open");
		miOpen.setOnAction(e -> _openFileCommand.action(e));
		MenuItem miSave = new MenuItem("Save");
		miSave.setOnAction(e -> _saveFileCommand.action(e));
		MenuItem miSaveAs = new MenuItem("Save As");
		miSaveAs.setOnAction(e -> _saveAsFileCommand.action(e));
		
		// create a menu
		Menu fileMenu = new Menu("File");
		ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();
		
		// add menu items to menu
		fileMenuItems.add(miNew);
		fileMenuItems.add(miOpen);
		fileMenuItems.add(miSave);
		fileMenuItems.add(miSaveAs);
		
		return fileMenu;
	}
	
	/**
	 * adds the menu edit menu options to a specific menu bar..
	 * @param nodes points where to attach the menu items. Must be generated by the canvas.
	 */
	public void addButtonsToBar(ObservableList<Node> nodes) {
		// create buttons
		Button nfb = new Button();
		nfb.setMinWidth(80);
		nfb.setText("New File");
		nfb.setOnAction(e -> _newFileCommand.action(e));
		
		Button ofb = new Button();
		ofb.setMinWidth(80);
		ofb.setText("Open");
		ofb.setOnAction(e -> _openFileCommand.action(e));
		
		Button sfb = new Button();
		sfb.setMinWidth(80);
		sfb.setText("Save");
		sfb.setOnAction(e -> _saveFileCommand.action(e));
		
		Button safb = new Button();
		safb.setMinWidth(80);
		safb.setText("Save as");
		safb.setOnAction(e -> _saveAsFileCommand.action(e));
		
		nodes.add(nfb);
		nodes.add(ofb);
		nodes.add(sfb);
		nodes.add(safb);
	}

}
