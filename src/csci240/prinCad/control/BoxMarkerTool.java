package csci240.prinCad.control;

import csci240.prinCad.model.BoxItem;
import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class BoxMarkerTool extends MarkerTool {
	
	/** constructor:
	 * @param canvas PrinCanvas instance to draw on
	 */
	public BoxMarkerTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	/**
	 * draw the a small "[]" on the canvas at the mouse position.
	 * @param me the mouse event that specify the position.
	 */
	@Override
	protected void draw(MouseEvent me) {
		_x = me.getX();
		_y = me.getY();
		// draw box
		boxAt(_canvas.getGraphicsContext(), _x, _y);
		// create box token
		_drawn = new BoxItem(_x, _y);
	}
	
	/**
	 * draw a small box shaped marker at (x,y).
	 * @param x
	 * @param y
	 */
	public static void boxAt(GraphicsContext gc ,double x, double y) {
		gc.strokeRect(x, y, 3 * _markerSize, 3 * _markerSize);
	}
}
