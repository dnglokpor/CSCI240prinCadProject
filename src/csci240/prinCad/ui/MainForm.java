package csci240.prinCad.ui;

import java.lang.Exception;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForm extends Application{
	// GUI settings compacted to the interface
	public static Settings cadSettings = new TextGuiSettings(); 
	// or 
	// public static Settings cadSettings = new PropGuiSettings();
	
	// JFX application start
	@Override
	public void start(Stage primaryStage) {
		// canvas
		// Canvas canvas = new Canvas(cadSettings.getCanvasWidth(),
		//		cadSettings.getCanvasHeight());
		PrinCanvas canvas = new PrinCanvas(cadSettings);
		
		// get graphics context to fill background
		// GraphicsContext gc = canvas.getGraphicsContext2D();
		// gc.setFill(cadSettings.getCanvasColor());
		// gc.fillRect(0,  0,  cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());
		
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
		cadSettings.readSettings(); // try importing settings
		launch(args);
		cadSettings.saveSettings(); // save to file
	}
}
