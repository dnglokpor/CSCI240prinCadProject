package csci240.prinCad.ui;

import java.lang.Exception;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForm extends Application{
	// GUI settings compacted
	// public static TextGuiSettings cadSettings;
	// or 
	public static PropGuiSettings cadSettings;
	
	// JFX application start
	@Override
	public void start(Stage primaryStage) {
		// canvas
		Canvas canvas = new Canvas(cadSettings.getCanvasWidth(),
				cadSettings.getCanvasHeight());
		
		// graphics context
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(cadSettings.getCanvasColor());
		gc.fillRect(0,  0,  cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());
		
		// border layout
		BorderPane pane = new BorderPane(canvas);
		
		// scene
		Scene scene = new Scene(pane, cadSettings.getSceneWidth(), 
				cadSettings.getSceneHeight(), cadSettings.getSceneColor());
		
		// attach scene
		primaryStage.setScene(scene);
		
		// other settings
		primaryStage.setTitle("CSCI 240 PrinCad Project");
		primaryStage.show();
	}
	
	// java main entry point
	public static void main(String[] args) throws Exception{
		// make object
		// cadSettings = new TextGuiSettings();
		// or
		cadSettings = new PropGuiSettings();
		cadSettings.readSettings(); // try importing settings
		launch(args);
		cadSettings.saveSettings(); // save to file
	}
}
