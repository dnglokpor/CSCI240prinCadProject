package csci240.prinCad.control;

import csci240.prinCad.model.CadLine;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.ModelManager;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/** LineSelector Object:
 * inherits the Selector class and must implement a line based selection by
 * overriding the onMouseDragged() method from it's abstract parent.
 * @author dnglokpor
 */
public class LineSelectionTool extends CadTool{ 
	/** constructor:
	 * use default
	 */
	
	public void onMouseDrag(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse) {
			_canvas.draw(); // redraw image
			// update line end
			_xEnd = me.getX();
			_yEnd = me.getY();
			_canvas.getGraphicsContext().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me, CanvasToolInterface canvas) {
		if(_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			ModelManager model = canvas.getModel();
			double xWidth = Math.abs(_xEnd - _xPivot);
			double yWidth = Math.abs(_yEnd - _yPivot);
			if(xWidth < _markerSize && yWidth < _markerSize)
				model.select(new CadPoint(_xPivot, _yPivot, _markerSize));
			model.select(new CadLine(_xPivot, _yPivot, _xEnd, _yEnd));
			canvas.draw();
		}
	}
}
