package csci240.prinCad.control;

import csci240.prinCad.model.LineItem;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/** LineCadTool class:
 * class that represents instances of single lines that can be
 * drawn on the screen.
 * @author dnglokpor
 */
public class LineCadTool extends CadTool {
	/** constructor:
	 * use default
	 */
	
	@Override
	public void onMouseDrag(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse) {
			// update line end
			_xEnd = me.getX();
			_yEnd = me.getY();
			_canvas.draw(); // clear screen
			// draw the line
			// System.out.println("draw time: " + _xPivot + " " + _yPivot + " " + _xEnd + " " + _yEnd); // DEBUG
			_canvas.getGraphicsContext().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse) {
			_activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			// System.out.println("record time: " + _xPivot + " " + _yPivot + " " + _xEnd + " " + _yEnd); // DEBUG
			_canvas.reset(new LineItem(_xPivot, _yPivot, _xEnd, _yEnd)); // go back to selection
		}
	}
}
