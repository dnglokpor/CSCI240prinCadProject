package csci240.prinCad.ui;

import java.lang.Exception;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
// buttons
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
 * entry point of the application. pull up all resources, and launch the javafx application. 
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
		// canvas
		// Canvas canvas = new Canvas(cadSettings.getCanvasWidth(),
		//		cadSettings.getCanvasHeight());
		PrinCanvas canvas = new PrinCanvas(cadSettings);
		
		// create file manager
		_menuManager = new ArrayList<MenuManager>();
		_menuManager.add(new FileManager(canvas));
		_menuManager.add(new EditManager(canvas));
		_menuManager.add(new CadToolsManager(canvas));
		
		// border layout
		BorderPane pane = new BorderPane(canvas);
		Color sceneBackgroundColor = cadSettings.getSceneColor();
		pane.setStyle(FormatStyleColor(sceneBackgroundColor));
		
		// create menu bar
		MenuBar mb = new MenuBar();
		pane.setTop(mb);
		ObservableList<Menu> menus = mb.getMenus(); // menu bar field
		
		// create and add Vbox to hold the buttons
		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.TOP_CENTER);
		pane.setRight(vbox);
		ObservableList<Node> nodes = vbox.getChildren(); // vbox fields
		
		// add menus to menu bar and vbox
		for(Iterator<MenuManager> it = _menuManager.iterator(); it.hasNext();) {
			MenuManager currentMenu = it.next();
			menus.add(currentMenu.buildMenu());
			currentMenu.addButtonsToBar(nodes);
		}	
		
		// create a scene, attach layout pane to scene
		// scene
		Scene scene = new Scene(pane, cadSettings.getSceneWidth(), 
				cadSettings.getSceneHeight(), sceneBackgroundColor);
		
		// Apply application styles
		File file = new File("AppStyles.css");
		if(!file.exists()) {
			Log.info(file.toString() + " does not exist");
		}else {
			URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}
		
		// attach scene
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
		primaryStage.show();
	}
	
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
