package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

/** abstract class CadItem:
 * super class that will define all tokens of our application canvas so we can regroup
 * them in a collection for redrawing, saving and loading purposes.
 * @author dnglokpor
 */
public abstract class CadItem {
	/**
	 * draw this CadItem on using the information in the passed graphics context.
	 * @param gc
	 */
	public abstract void draw(GraphicsContext gc);
	
	/**
	 * generates a string description of the CadItem for writing to a file. 
	 * @return
	 */
	public abstract String save();
	
	// IT IS NOT SAID BUT EACH CHILD CLASSES MUST IMPLEMENT A LOAD METHOD.
}
