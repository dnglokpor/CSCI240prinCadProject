package csci240.prinCad.command.CadTools.Markers;

import csci240.prinCad.command.MenuManager;
import csci240.prinCad.ui.PrinCanvas;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/** MarkersManager class:
 * class that manages all option under the Markers menu. Uses commands defined as the individual
 * classes PlusMarkerCommand, BoxMarkerCommand, XMarkerCommand, LineMarkerCommand,
 * CircleMarkerCommand, RectMarkerCommand and PolyMarkerCommand (more to come). 
 * @author dnglokpor
 *
 */
public class MarkersManager implements MenuManager{
	// commands
	private PlusMarkerCommand _plusMarkerCommand;
	private BoxMarkerCommand _boxMarkerCommand;
	private XMarkerCommand _xMarkerCommand;
	private LineMarkerCommand _lineMarkerCommand;
	private CircleMarkerCommand _circleMarkerCommand;
	private RectMarkerCommand _rectMarkerCommand;
	private PolyMarkerCommand _polyMarkerCommand;
	
	/**
	 * takes in a canvas and associates an instance of each of the commands to it.
	 * @param canvas the PrinCanvas object being drawn on.
	 */
	public MarkersManager(PrinCanvas canvas) {
		_plusMarkerCommand = new PlusMarkerCommand(canvas);
		_boxMarkerCommand = new BoxMarkerCommand(canvas);
		_xMarkerCommand = new XMarkerCommand(canvas);
		_lineMarkerCommand = new LineMarkerCommand(canvas);
		_circleMarkerCommand = new CircleMarkerCommand(canvas);
		_rectMarkerCommand = new RectMarkerCommand(canvas);
		_polyMarkerCommand = new PolyMarkerCommand(canvas);
	}
	
	@Override
	public Menu buildMenu() {
		// create Markers menu items
		MenuItem miPlus = new MenuItem("Plus");
		miPlus.setOnAction(e -> _plusMarkerCommand.action(e));
		MenuItem miBox = new MenuItem("Box");
		miBox.setOnAction(e -> _boxMarkerCommand.action(e));
		MenuItem miX = new MenuItem("CrissCross");
		miX.setOnAction(e -> _xMarkerCommand.action(e));
		MenuItem miLine = new MenuItem("Line");
		miLine.setOnAction(e -> _lineMarkerCommand.action(e));
		MenuItem miCircle = new MenuItem("Circle");
		miCircle.setOnAction(e -> _circleMarkerCommand.action(e));
		MenuItem miRect = new MenuItem("Rect");
		miRect.setOnAction(e -> _rectMarkerCommand.action(e));
		MenuItem miPoly = new MenuItem("Poly");
		miPoly.setOnAction(e -> _polyMarkerCommand.action(e));
		
		// create Marker Menu
		Menu markersMenu = new Menu("Markers");
		ObservableList<MenuItem> markersMenuItems = markersMenu.getItems();
		
		// add menu items to the Markers menu
		markersMenuItems.add(miPlus);
		markersMenuItems.add(miBox);
		markersMenuItems.add(miX);
		markersMenuItems.add(miLine);
		markersMenuItems.add(miCircle);
		markersMenuItems.add(miRect);
		markersMenuItems.add(miPoly);
		
		// return menu ref
		return markersMenu;
	}
	
	
	@Override
	public void addButtonsToBar(ObservableList<Node> nodes) {
		// create buttons
		Button pmb = new Button();
		pmb.setMinWidth(80);
		pmb.setText("Plus");
		pmb.setOnAction(e -> _plusMarkerCommand.action(e));
		
		Button bmb = new Button();
		bmb.setMinWidth(80);
		bmb.setText("Plus");
		bmb.setOnAction(e -> _boxMarkerCommand.action(e));
		
		Button xmb = new Button();
		xmb.setMinWidth(80);
		xmb.setText("Plus");
		xmb.setOnAction(e -> _xMarkerCommand.action(e));
		
		Button lmb = new Button();
		lmb.setMinWidth(80);
		lmb.setText("Plus");
		lmb.setOnAction(e -> _lineMarkerCommand.action(e));
		
		Button cmb = new Button();
		cmb.setMinWidth(80);
		cmb.setText("Plus");
		cmb.setOnAction(e -> _circleMarkerCommand.action(e));
		
		Button rmb = new Button();
		rmb.setMinWidth(80);
		rmb.setText("Plus");
		rmb.setOnAction(e -> _rectMarkerCommand.action(e));
		
		Button pomb = new Button();
		pomb.setMinWidth(80);
		pomb.setText("Plus");
		pomb.setOnAction(e -> _polyMarkerCommand.action(e));
		
		// attach buttons to bar
		nodes.add(pmb);
		nodes.add(bmb);
		nodes.add(xmb);
		nodes.add(lmb);
		nodes.add(cmb);
		nodes.add(rmb);
		nodes.add(pomb);
		
	}

}