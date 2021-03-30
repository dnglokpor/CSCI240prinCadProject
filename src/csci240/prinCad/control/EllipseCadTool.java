package csci240.prinCad.control;

import csci240.prinCad.model.EllipseItem;
import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** EllipseCadTool class:
 * class that represents instances of ellipses that can be
 * drawn on the screen.
 * @author dnglokpor
 */
public class EllipseCadTool extends CadTool {
	// attributes
	double _radX, _radY;
	
	
	/** constructor:
	 * @param canvas set the canvas to draw on.
	 */
	public EllipseCadTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	@Override
	public void onMousePressed(MouseEvent me) {};

	@Override
	public void onMouseMove(MouseEvent me) {
		if(_activeMouse) {
			// update mouse end coordinates
			_xEnd = me.getX();
			_yEnd = me.getY();
			// calculate axis radii 
			_radX = Math.abs(_xEnd - _xPivot); 
			_radY = Math.abs(_yEnd - _yPivot);
			// calculate bounding top-left
			double xTL = _xPivot - _radX;
			double yTL = _yPivot - _radY;
			// calculate axis diameters
			double diamX = 2 * _radX;
			double diamY = 2 * _radY;
			// clear drag noise
			_canvas.draw();
			// draw ellipse
			_canvas.getGraphicsContext().strokeOval(xTL, yTL, diamX, diamY);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me) {
		if(_activeMouse) { // end of drawing
			_activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			// go back to selection
			_canvas.reset(new EllipseItem(_xPivot, _yPivot, _radX, _radY));
		}else { // drawing start
			if(me.getButton() == MouseButton.PRIMARY) {
				double x = me.getX();
				double y = me.getY();
				_xPivot = x;
				_yPivot = y;
				_xEnd = x;
				_yEnd = y;
				_activeMouse = true;
				_canvas.getGraphicsContext().setStroke(Color.ORANGERED);
				_canvas.getGraphicsContext().setLineWidth(0);
				_canvas.setCursor(Cursor.CROSSHAIR);
			}
		}
	}
}
