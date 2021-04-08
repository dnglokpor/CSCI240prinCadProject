package csci240.prinCad.ui;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.command.CadTools.BoxMarkerCommand;
import csci240.prinCad.command.CadTools.CircleCadCommand;
import csci240.prinCad.command.CadTools.EllipseCadCommand;
import csci240.prinCad.command.CadTools.LineCadCommand;
import csci240.prinCad.command.CadTools.PlusMarkerCommand;
import csci240.prinCad.command.CadTools.PolyCadCommand;
import csci240.prinCad.command.CadTools.RectCadCommand;
import csci240.prinCad.command.CadTools.XMarkerCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/** CADToolsManager class:
 * class that manages all option under the Markers menu. Uses commands defined as the individual
 * classes PlusMarkerCommand, BoxMarkerCommand, XMarkerCommand, LineMarkerCommand,
 * CircleMarkerCommand, RectMarkerCommand and PolyMarkerCommand (more to come). 
 * @author dnglokpor
 *
 */
public class CadToolsManager implements MenuManager{
	// commands
	private PlusMarkerCommand _plusMarkerCommand;
	private BoxMarkerCommand _boxMarkerCommand;
	private XMarkerCommand _xMarkerCommand;
	private LineCadCommand _lineCadCommand;
	private CircleCadCommand _circleCadCommand;
	private EllipseCadCommand _ellipseCadCommand;
	private RectCadCommand _rectCadCommand;
	private PolyCadCommand _polyCadCommand;
	
	/** constructor:
	 * takes in a canvas and associates an instance of each of the commands to it.
	 * @param canvas the CanvasCommandInterface object being drawn on.
	 */
	public CadToolsManager(CanvasCommandInterface canvas) {
		_plusMarkerCommand = new PlusMarkerCommand(canvas);
		_boxMarkerCommand = new BoxMarkerCommand(canvas);
		_xMarkerCommand = new XMarkerCommand(canvas);
		_lineCadCommand = new LineCadCommand(canvas);
		_circleCadCommand = new CircleCadCommand(canvas);
		_ellipseCadCommand = new EllipseCadCommand(canvas);
		_rectCadCommand = new RectCadCommand(canvas);
		_polyCadCommand = new PolyCadCommand(canvas);
	}
	
	@Override
	public Menu buildMenu() {
		// create menu items
		MenuItem miPlus = new MenuItem("Plus");
		miPlus.setOnAction(e -> _plusMarkerCommand.action(e));
		MenuItem miBox = new MenuItem("Box");
		miBox.setOnAction(e -> _boxMarkerCommand.action(e));
		MenuItem miX = new MenuItem("CrissCross");
		miX.setOnAction(e -> _xMarkerCommand.action(e));
		MenuItem miLine = new MenuItem("Line");
		miLine.setOnAction(e -> _lineCadCommand.action(e));
		MenuItem miCircle = new MenuItem("Circle");
		miCircle.setOnAction(e -> _circleCadCommand.action(e));
		MenuItem miEllipse = new MenuItem("Ellipse");
		miEllipse.setOnAction(e -> _ellipseCadCommand.action(e));
		MenuItem miRect = new MenuItem("Rectangle");
		miRect.setOnAction(e -> _rectCadCommand.action(e));
		MenuItem miPoly = new MenuItem("Polyline");
		miPoly.setOnAction(e -> _polyCadCommand.action(e));
		
		// instantiate sub menu options
		Menu markersMenu = new Menu("Markers");
		ObservableList<MenuItem> markersMenuItems = markersMenu.getItems();
		
		// add markers to sub menu
		markersMenuItems.add(miPlus);
		markersMenuItems.add(miBox);
		markersMenuItems.add(miX);
		
		// instantiate Cad Tools menu and add options 
		Menu cadToolsMenu = new Menu("CAD Tools");
		ObservableList<MenuItem> cadToolsMenuItems = cadToolsMenu.getItems();
		cadToolsMenuItems.add(markersMenu);
		cadToolsMenuItems.add(miLine);
		cadToolsMenuItems.add(miCircle);
		cadToolsMenuItems.add(miEllipse);
		cadToolsMenuItems.add(miRect);
		cadToolsMenuItems.add(miPoly);
		
		// return menu reference
		return cadToolsMenu;
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
			bmb.setText("Box");
			bmb.setOnAction(e -> _boxMarkerCommand.action(e));
			
			Button xmb = new Button();
			xmb.setMinWidth(80);
			xmb.setText("CrissCross");
			xmb.setOnAction(e -> _xMarkerCommand.action(e));
			
			Button lmb = new Button();
			lmb.setMinWidth(80);
			lmb.setText("Line");
			lmb.setOnAction(e -> _lineCadCommand.action(e));
			
			Button cmb = new Button();
			cmb.setMinWidth(80);
			cmb.setText("Circle");
			cmb.setOnAction(e -> _circleCadCommand.action(e));
			
			Button emb = new Button();
			emb.setMinWidth(80);
			emb.setText("Ellipse");
			emb.setOnAction(e -> _ellipseCadCommand.action(e));
			
			Button rmb = new Button();
			rmb.setMinWidth(80);
			rmb.setText("Rectangle");
			rmb.setOnAction(e -> _rectCadCommand.action(e));
			
			Button pomb = new Button();
			pomb.setMinWidth(80);
			pomb.setText("Polyline");
			pomb.setOnAction(e -> _polyCadCommand.action(e));
			
			// attach buttons to bar
			nodes.add(pmb);
			nodes.add(bmb);
			nodes.add(xmb);
			nodes.add(lmb);
			nodes.add(cmb);
			nodes.add(emb);
			nodes.add(rmb);
			nodes.add(pomb);
	}

}
