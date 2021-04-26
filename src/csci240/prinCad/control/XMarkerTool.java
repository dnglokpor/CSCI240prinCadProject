package csci240.prinCad.control;

import csci240.prinCad.model.XItem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/** CrossMarkerTool class:
 * class that define instances of cross marks that can be drawn on the
 * canvas.
 * @author dnglokpor
 *
 */
public class XMarkerTool extends MarkerTool {
	
	/** constructor:
	 * using default
	 */
	
	/**
	 * draw a "x" character at the mouse position specified.
	 * @param me the mouse event that specifies the position.
	 */
	@Override
	protected void draw(MouseEvent me, CanvasToolInterface _canvas) {
		_x = me.getX();
		_y = me.getY();
		//draw x
		double leftX = _x - _markerSize;
		double topY = _y - _markerSize;
		double bottomY = _y + _markerSize;
		double rightX = _x + _markerSize;
		_canvas.getGraphicsContext().strokeLine(leftX, topY, rightX, bottomY);
		_canvas.getGraphicsContext().strokeLine(rightX, topY, leftX, bottomY);
		// create x token
		_drawn = new XItem(_x, _y, _markerSize);
	}

}
