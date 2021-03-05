package csci240.prinCad.command.Edit;

import csci240.prinCad.command.MenuManager;
import csci240.prinCad.ui.PrinCanvas;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/** EditManager class:
 * class that manages all option under the Edit menu. Uses commands defined as the individual
 * classes UndoEditCommand, RedoEditCommand, DelEditCommand, ToggleSelectEditCommand and 
 * PropertiesEditSelect.
 * @author dnglokpor
 *
 */
public class EditManager implements MenuManager{
	// edit commands
	private UndoEditCommand _undoEditCommand;
	private RedoEditCommand _redoEditCommand;
	private DelEditCommand _delEditCommand;
	private ToggleSelectEditCommand _toggleSelectEditCommand;
	private PropertiesEditCommand _propertiesEditCommand;
	
	/**
	 * takes in a canvas and associates an instance of each of the commands to it.
	 * @param canvas the PrinCanvas object being drawn on.
	 */
	public EditManager(PrinCanvas canvas) {
		_undoEditCommand = new UndoEditCommand(canvas);
		_redoEditCommand = new RedoEditCommand(canvas);
		_delEditCommand = new DelEditCommand(canvas);
		_toggleSelectEditCommand = new ToggleSelectEditCommand(canvas);
		_propertiesEditCommand = new PropertiesEditCommand(canvas);
	}
	
	/**
	 * make the Edit menu as predefined javafx Menu and its options
	 * and MenuItem objects.
	 * @return a reference to the created menu object created.
	 */
	@Override
	public Menu buildMenu() {
		// create menu items
		MenuItem miUndo = new MenuItem("Undo");
		miUndo.setOnAction(e -> _undoEditCommand.action(e));
		MenuItem miRedo = new MenuItem("Redo");
		miRedo.setOnAction(e -> _redoEditCommand.action(e));
		MenuItem miDelete = new MenuItem("Delete");
		miDelete.setOnAction(e -> _delEditCommand.action(e));
		MenuItem miToggleSelect = new MenuItem("Toggle Selection");
		miToggleSelect.setOnAction(e -> _toggleSelectEditCommand.action(e));
		MenuItem miProperties = new MenuItem("Properties");
		miProperties.setOnAction(e -> _propertiesEditCommand.action(e));
		
		// create a menu
		Menu editMenu = new Menu("Edit");
		ObservableList<MenuItem> editMenuItems = editMenu.getItems();
		
		// add menu items to menu
		editMenuItems.add(miUndo);
		editMenuItems.add(miRedo);
		editMenuItems.add(miDelete);
		editMenuItems.add(miToggleSelect);
		editMenuItems.add(miProperties);
		
		return editMenu;
	}
	
	@Override
	public void addButtonsToBar(ObservableList<Node> nodes) {
		// create buttons
		Button ueb = new Button();
		ueb.setMinWidth(80);
		ueb.setText("Undo");
		ueb.setOnAction(e -> _undoEditCommand.action(e));
		
		Button reb = new Button();
		reb.setMinWidth(80);
		reb.setText("Redo");
		reb.setOnAction(e -> _redoEditCommand.action(e));
		
		Button deb = new Button();
		deb.setMinWidth(80);
		deb.setText("Delete");
		deb.setOnAction(e -> _delEditCommand.action(e));
		
		Button tseb = new Button();
		tseb.setMinWidth(80);
		tseb.setText("^Selection");
		tseb.setOnAction(e -> _toggleSelectEditCommand.action(e));
		
		Button peb = new Button();
		peb.setMinWidth(80);
		peb.setText("Properties");
		peb.setOnAction(e -> _toggleSelectEditCommand.action(e));
		
		// attach buttons to bar
		nodes.add(ueb);
		nodes.add(reb);
		nodes.add(deb);
		nodes.add(tseb);
		nodes.add(peb);
	}

}
