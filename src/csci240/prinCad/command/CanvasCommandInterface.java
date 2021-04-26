package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import csci240.prinCad.control.CadTool;
import csci240.prinCad.model.ModelManager;
import javafx.scene.Scene;

/** CanvasCommandInterface interface:
 * allows to separate the PrinCanvas dependency of classes that need it
 * by making this interface the dependency, and PrinCanvas inherit this. 
 * @author dnglokpor
 */
public interface CanvasCommandInterface {
	/**
	 * draw all graphic tokens existing 
	 */
	public void draw();
	/**
	 * change the currently used tool to the one passed.
	 * @param activeTool the tool to set as active.
	 */
	public void setActiveTool(CadTool activeTool);
	/**
	 * change the selection from the current type to the next.
	 */
	public void toggleSelectionType();
	/**
	 * @return the current primary scene of the current canvas.
	 */
	public Scene getScene();
	/**
	 * recover graphics items data from a an source. 
	 * @param reader the inputstream to recover data from.
	 */
	public void loadFromFile(BufferedReader reader);
	/**
	 * @return the current model of the graphics canvas. 
	 */
	public ModelManager getModel();
}


