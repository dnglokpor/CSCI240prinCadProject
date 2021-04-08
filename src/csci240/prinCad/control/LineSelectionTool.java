package csci240.prinCad.control;


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
}
