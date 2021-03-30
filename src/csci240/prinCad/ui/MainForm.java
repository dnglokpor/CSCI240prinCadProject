package csci240.prinCad.ui;

import java.lang.Exception;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen; // screen control
import javafx.scene.paint.Color;
// buttons
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import csci240.prinCad.command.MenuManager;
import csci240.prinCad.command.CadTools.CadToolsManager;
import csci240.prinCad.command.Edit.EditManager;
import csci240.prinCad.command.File.FileManager;

/** MainForm class:
 * entry point of the application. pull up all resources, and launch the javaFX application. 
 * @author dnglokpor
 *
 */
public class MainForm extends Application{
	// GUI settings compacted to the interface
	// public static Settings cadSettings = new TextGuiSettings(); 
	// or 
	public static Settings cadSettings = new PropGuiSettings();
	
	// managers of commands
	private ArrayList<MenuManager> _menuManager;
	
	// application scene and canvas
	private PrinCanvas _primaryCanvas;
	private Scene _primaryScene;
	private BorderPane _primaryPane;
	
	// helper: format color for use with setStyle
	private String FormatStyleColor(Color color) {
		String rx = String.format("%02X", Math.round(color.getRed() * 255.0));
		String gx = String.format("%02X", Math.round(color.getGreen() * 255.0));
		String bx = String.format("%02X", Math.round(color.getBlue() * 255.0));
		String fx = "-fx-background-color: #" + rx + gx + bx + ";";
		return fx;
	}
	
	// JFX application start
	@Override
	public void start(Stage primaryStage) throws Exception{
		// window setup
		primaryStage.setMinHeight(600); // min height 600
		primaryStage.setMinWidth(900); // min width 900
		primaryStage.setMaxHeight(
				Screen.getScreens().get(0).getBounds().getHeight()); // max height
		primaryStage.setMaxHeight(
				Screen.getScreens().get(0).getBounds().getWidth()); // max width
		
		/** ATTEMPT AT SCREEN RESIZE
		// Subscribe window events
		// help from https://stackoverflow.com/questions/38216268/how-to-listen-resize-event-stage-in-javafx
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> 
			onWidthResize((ObservableValue) obs, (Double)newVal)); // listener on width
		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> 
			onHeightResize((ObservableValue) obs, (Double)newVal)); // listener on height
		*/
		
		// create canvas
		_primaryCanvas = new PrinCanvas(cadSettings);
		
		// create file manager
		_menuManager = new ArrayList<MenuManager>();
		_menuManager.add(new FileManager(_primaryCanvas)); // in right bar
		_menuManager.add(new EditManager(_primaryCanvas)); // in right bar
		_menuManager.add(new CadToolsManager(_primaryCanvas)); // in left bar
		
		// border layout
		_primaryPane = new BorderPane(_primaryCanvas);
		_primaryPane.setStyle(FormatStyleColor(cadSettings.getSceneColor()));
		
		// create menu bar
		MenuBar mb = new MenuBar();
		_primaryPane.setTop(mb);
		ObservableList<Menu> menus = mb.getMenus(); // menu bar field
		
		// create VBoxes to hold the buttons
		VBox[] vBoxesLR = new VBox[2];
		for(int i = 0; i < vBoxesLR.length; i++) {
			vBoxesLR[i] = new VBox(5);
			vBoxesLR[i].setPadding(new Insets(10));
			vBoxesLR[i].setAlignment(Pos.TOP_CENTER);
		}
		
		// place vBoxes left and right 
		_primaryPane.setLeft(vBoxesLR[0]);
		ObservableList<Node> lNodes = vBoxesLR[0].getChildren(); // left vbox fields
		_primaryPane.setRight(vBoxesLR[1]);
		ObservableList<Node> rNodes = vBoxesLR[1].getChildren(); // left vbox fields
		
		// add menus to menu bar and vboxes
		for(Iterator<MenuManager> it = _menuManager.iterator(); it.hasNext();) {
			MenuManager currentMenu = it.next();
			menus.add(currentMenu.buildMenu());
			if(!(currentMenu instanceof CadToolsManager))
				currentMenu.addButtonsToBar(rNodes); // add right
			else
				currentMenu.addButtonsToBar(lNodes); // add right
		}	
		
		// create a scene, attach layout pane to scene
		// scene
		_primaryScene = new Scene(_primaryPane, cadSettings.getSceneWidth(), 
				cadSettings.getSceneHeight(), cadSettings.getSceneColor());
		
		// Apply application styles
		File file = new File("AppStyles.css");
		if(!file.exists()) {
			Log.info(file.toString() + " does not exist");
		}else {
			URL url = file.toURI().toURL();
			_primaryScene.getStylesheets().add(url.toExternalForm());
		}
		
		// attach scene
		primaryStage.setScene(_primaryScene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
		primaryStage.show();
	}
	
	/**
	 * adapts the Canvas and the Scene width to the screen width.
	 * @param primaryStage the main stage (window) of the application.
	 * @param newSize the new size of the main stage.
	 */
	public void onWidthResize(ObservableValue<Stage> primaryStage, double newSize) {
		System.out.println("stage: " + primaryStage); // DEBUG
		System.out.println("newVal: " + newSize); // DEBUG
		// get Scene to Canvas width difference
		int deltaW = Integer.parseInt(Settings.DEFAULTS[0]) - 
				Integer.parseInt(Settings.DEFAULTS[3]);
		System.out.println("deltaW: " + deltaW); // DEBUG
		int canvasW = (int) Math.abs(newSize - deltaW);
		System.out.println("canvasW: " + canvasW); // DEBUG
		System.out.println("sceneW: " + _primaryScene.getWidth()); // DEBUG
		// change canvas size
		_primaryCanvas.setWidth(canvasW);
		// save changes for next launch
		cadSettings.setSceneWidth(newSize - 5);
		cadSettings.setCanvasWidth(canvasW);
	};
	
	/**
	 * adapts the Canvas and the Scene height to the screen height.
	 * @param primaryStage the main stage (window) of the application.
	 * @param newSize the new size of the main stage.
	 */
	public void onHeightResize(ObservableValue<Stage> primaryStage, double newSize) {
		System.out.println("stage: " + primaryStage); // DEBUG
		System.out.println("newVal: " + newSize); // DEBUG
		// get Scene to Canvas width difference
		int deltaH = Integer.parseInt(Settings.DEFAULTS[1]) - 
			Integer.parseInt(Settings.DEFAULTS[4]);
		System.out.println("deltaH: " + deltaH); // DEBUG
		int canvasH = (int) Math.abs(newSize - deltaH);
		System.out.println("canvasH: " + canvasH); // DEBUG
		System.out.println("sceneH: " + _primaryScene.getHeight()); // DEBUG
		// change canvas size
		_primaryCanvas.setHeight(canvasH);
		// save changes for next launch
		cadSettings.setSceneHeight(newSize - 5);
		cadSettings.setCanvasHeight(canvasH);
	};
	
	// java main entry point
	public static void main(String[] args) throws Exception{
		cadSettings.readSettings(); // restore/init settings
		
		Log.info("PrinCad begin execution"); // log successful start
		
		try {
			// launch the fx app
			launch(args);
			cadSettings.saveSettings(); // save to file
			
			/** TEST FORCED ERROR
			* String[] test = new String[2];
			* test[3] = "blowUP";
			*/
		}catch(Exception ex) {
			Log.error("PrinCad crash with exception ", ex);
			throw ex;
		}
		
		Log.info("PrinCad end execution"); // log successful end
	}
}
