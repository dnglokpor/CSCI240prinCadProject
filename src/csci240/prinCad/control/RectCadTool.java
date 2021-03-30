package csci240.prinCad.control;

import csci240.prinCad.model.RectItem;
import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/** RectCadTool class:
 * class that represents instances of rectangles that can be
 * drawn on the screen.
 * @author dnglokpor
 */
public class RectCadTool extends CadTool {
	// attributes
	double x, y, w, h;
	/** constructor:
	 * @param canvas set the canvas to draw on.
	 */
	public RectCadTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	@Override
	public void onMouseDrag(MouseEvent me) {
		if(_activeMouse) {
			// update mouse end coordinates
			_xEnd = me.getX();
			_yEnd = me.getY();
			// calculate rectangle parameters
			x = Math.min(_xPivot, _xEnd) - 1;
			y = Math.min(_yPivot, _yEnd) - 1;
			w = Math.abs(_xEnd - _xPivot) + 2;
			h = Math.abs(_yEnd - _yPivot) + 2;
			// clear drag noise
			_canvas.draw();
			// draw current rectangle
			_canvas.getGraphicsContext().strokeRect(x, y, w, h);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me) {
		if(_activeMouse) {
			_activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			_canvas.reset(new RectItem(x, y, w, h)); // go back to selection
		}
	}
}
