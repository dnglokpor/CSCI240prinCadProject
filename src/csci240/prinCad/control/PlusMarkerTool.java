package csci240.prinCad.control;

import csci240.prinCad.model.PlusItem;
import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/** PlusMarkerTool class:
 * class that represents plus shaped objects that can be draw on the canvas.
 * 
 * @author dnglokpor
 */
public class PlusMarkerTool extends MarkerTool {
	
	/** constructor:
	 * @param canvas PrinCanvas instance to draw on
	 */
	public PlusMarkerTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	/**
	 * draw a "+" character at the mouse position specified.
	 * @param me the mouse event that specifies the position.
	 */
	@Override
	protected void draw(MouseEvent me) {
		_x = me.getX();
		_y = me.getY();
		// draw plus
		plusAt(_canvas.getGraphicsContext(), _x, _y);
		// create plus token
		_drawn = new PlusItem(_x, _y);
	}
	
	/**
	 * draw a small plus shaped marker at (x,y).
	 * @param x
	 * @param y
	 */
	public static void plusAt(GraphicsContext gc ,double x, double y) {
		double size = 2 * _markerSize;
		gc.strokeLine(x - size, y, x + size, y);
		gc.strokeLine(x, y - size, x, y + size);
	}

}
