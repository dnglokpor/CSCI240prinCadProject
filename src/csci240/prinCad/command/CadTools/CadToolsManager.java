package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.MenuManager;
import csci240.prinCad.command.CadTools.Markers.MarkersManager;
import csci240.prinCad.ui.PrinCanvas;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/** CadToolsManager class:
 * defines the menu Cad Tools and the options under it. the options for this Menu will be other
 * Menus which will have their own MenuItems.
 * @author dnglokpor
 *
 */
public class CadToolsManager implements MenuManager{
	// underlying menu
	private MarkersManager _markersSubMenu;
	
	/** constructor:
	 * takes in a canvas and associates an instance of each of the commands to it.
	 * @param canvas the PrinCanvas object being drawn on.
	 */
	public CadToolsManager(PrinCanvas canvas) {
		// instantiate Markers sub menu
		_markersSubMenu = new MarkersManager(canvas);
	}
	
	@Override
	public Menu buildMenu() {
		// instantiate sub menu options
		Menu markersMenu = _markersSubMenu.buildMenu();
		
		// create Cad Tools menu and add sub menu
		Menu cadToolsMenu = new Menu("CAD Tools");
		ObservableList<MenuItem> cadToolsMenuItems = cadToolsMenu.getItems();
		cadToolsMenuItems.add(markersMenu);
		
		// return menu reference
		return cadToolsMenu;
	}

	@Override
	public void addButtonsToBar(ObservableList<Node> nodes) {
		// TODO Auto-generated method stub
		
	}

}
