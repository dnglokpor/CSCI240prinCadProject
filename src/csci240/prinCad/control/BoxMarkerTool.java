package csci240.prinCad.control;

import csci240.prinCad.model.BoxItem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class BoxMarkerTool extends MarkerTool {
	
	/** constructor:
	 * using default
	 */
	
	/**
	 * draw the a small "[]" on the canvas at the mouse position.
	 * @param me the mouse event that specify the position.
	 */
	@Override
	protected void draw(MouseEvent me, CanvasToolInterface _canvas) {
		_x = me.getX();
		_y = me.getY();
		// draw box
		double sideLength = 2 * _markerSize; // double the size for boxes
		_canvas.getGraphicsContext().strokeRect(_x - _markerSize, _y - _markerSize, sideLength, sideLength);
		// create box token
		_drawn = new BoxItem(_x, _y, _markerSize);
	}
}
