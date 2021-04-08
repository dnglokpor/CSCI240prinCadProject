package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import csci240.prinCad.control.CadTool;
import javafx.scene.Scene;

/** CanvasCommandInterface interface:
 * allows to separate the PrinCanvas dependency of classes that need it
 * by making this interface the dependency, and PrinCanvas inherit this. 
 * @author dnglokpor
 */
public interface CanvasCommandInterface {
	public void setActiveTool(CadTool activeTool);
	public void toggleSelectionType();
	public void freshStart();
	public Scene getScene();
	public File getOpenedModel();
	public void loadFromFile(BufferedReader reader);
	public void recordOpenedFile(File of);
	public void saveToFile(PrintWriter out);
}


