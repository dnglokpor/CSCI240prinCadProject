package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

/** CanvasToolInterface interface:
 * allows to separate the PrinCanvas dependency of classes that need it
 * by making this interface the dependency, and PrinCanvas inherit this. 
 * @author dnglokpor
 */
public interface CanvasToolInterface {
	// needed for tools
	public GraphicsContext getGraphicsContext();
	public void setCursor(Cursor cursor); // inherited from canvas
	public void draw();
	public void reset();
	public void reset(CadItem cadItem);
}


