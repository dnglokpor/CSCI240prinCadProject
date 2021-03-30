package csci240.prinCad.control;

import csci240.prinCad.model.CircleItem;
import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/** CircleCadTool class:
 * class that represents instances of circles that can be
 * drawn on the screen.
 * @author dnglokpor
 */
public class CircleCadTool extends CadTool {
	// attributes
	double _radius;
	
	/** constructor:
	 * @param canvas set the canvas to draw on.
	 */
	public CircleCadTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	@Override
	public void onMouseDrag(MouseEvent me) {
		if(_activeMouse) {
			// update mouse end coordinates
			_xEnd = me.getX();
			_yEnd = me.getY();
			// CIRCLE calculate radius using distance formula
			_radius = Math.sqrt(Math.pow(_xEnd - _xPivot, 2) + Math.pow(_yEnd - _yPivot, 2));
			// CIRCLE calculate bounding top-left
			double xTL = _xPivot - _radius;
			double yTL = _yPivot - _radius;
			// CIRCLE compute diameter
			double diameter = 2 * _radius;
			_canvas.draw(); // redraw screen
			// draw current circle
			_canvas.getGraphicsContext().strokeOval(xTL, yTL, diameter, diameter);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me) {
		if(_activeMouse) {
			_activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			_canvas.reset(new CircleItem(_xPivot, _yPivot, _radius)); // go back to selection
		}
	}
}
