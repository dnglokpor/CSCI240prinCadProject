package csci240.prinCad.control;


import csci240.prinCad.model.ModelManager;
import csci240.prinCad.model.CadBox;
import csci240.prinCad.model.CadPoint;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class BoxSelectionTool extends CadTool{
	// attributes
	double _x, _y, _w, _h;
	
	/** constructor:
	 * use default
	 */
	@Override
	public void onMouseDrag(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse) {
			// clear old selection
			_canvas.draw();
			// update mouse end coordinates
			_xEnd = me.getX();
			_yEnd = me.getY();
			// calculate rectangle parameters
			_x = Math.min(_xPivot, _xEnd) - 1;
			_y = Math.min(_yPivot, _yEnd) - 1;
			_w = Math.abs(_xEnd - _xPivot) + 2;
			_h = Math.abs(_yEnd - _yPivot) + 2;
			// draw current rectangle
			_canvas.getGraphicsContext().strokeRect(_x, _y, _w, _h);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me, CanvasToolInterface canvas) {
		if(_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			ModelManager model = canvas.getModel();
			if(_w < _markerSize && _h < _markerSize)
				model.select(new CadPoint(_xPivot, _yPivot, _markerSize));
			else
				model.select(new CadBox(_x, _y, _x + _w, _y + _h));
			_w = 0; // reset these
			_h = 0; // reset these
			canvas.draw();
		}
	}
	
}
