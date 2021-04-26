package csci240.prinCad.control;

import csci240.prinCad.model.PlusItem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/** PlusMarkerTool class:
 * class that represents plus shaped objects that can be draw on the canvas.
 * 
 * @author dnglokpor
 */
public class PlusMarkerTool extends MarkerTool {
	
	/** constructor:
	 * using default
	 */
	
	/**
	 * draw a "+" character at the mouse position specified.
	 * @param me the mouse event that specifies the position.
	 */
	@Override
	protected void draw(MouseEvent me, CanvasToolInterface _canvas) {
		_x = me.getX();
		_y = me.getY();
		// draw plus
		_canvas.getGraphicsContext().strokeLine(_x - _markerSize, _y, _x + _markerSize, _y);
		_canvas.getGraphicsContext().strokeLine(_x, _y - _markerSize, _x, _y + _markerSize);
		// create plus token
		_drawn = new PlusItem(_x, _y, _markerSize);
	}
}
