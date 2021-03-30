package csci240.prinCad.control;

import csci240.prinCad.model.XItem;
import csci240.prinCad.ui.PrinCanvas;
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
	 * @param canvas PrinCanvas instance to draw on
	 */
	public XMarkerTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	/**
	 * draw a "x" character at the mouse position specified.
	 * @param me the mouse event that specifies the position.
	 */
	@Override
	protected void draw(MouseEvent me) {
		_x = me.getX();
		_y = me.getY();
		//draw x
		crossAt(_canvas.getGraphicsContext(), _x, _y);
		// create x token
		_drawn = new XItem(_x, _y);
	}
	
	/**
	 * draw a small x shaped marker at (x,y).
	 * @param x
	 * @param y
	 */
	public static void crossAt(GraphicsContext gc ,double x, double y) {
		double leftX = x - _markerSize;
		double topY = y - _markerSize;
		double bottomY = y + _markerSize;
		double rightX = x + _markerSize;
		gc.strokeLine(leftX, topY, rightX, bottomY);
		gc.strokeLine(rightX, topY, leftX, bottomY);
	}

}
