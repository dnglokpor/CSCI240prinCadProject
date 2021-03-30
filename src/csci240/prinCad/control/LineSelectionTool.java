package csci240.prinCad.control;

import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.input.MouseEvent;

/** LineSelector Object:
 * inherits the Selector class and must implement a line based selection by
 * overriding the onMouseDragged() method from it's abstract parent.
 * @author dnglokpor
 */
public class LineSelectionTool extends CadTool{ 
	/** constructor:
	 * provide the abstract parent with the required PrinCanvas context object.
	 * @param pc
	 */
	public LineSelectionTool(PrinCanvas pc) {
		super(pc); // construct base selector
	}
	
	public void onMouseDrag(MouseEvent me) {
		if(_activeMouse) {
			_canvas.draw(); // redraw image
			// update line end
			_xEnd = me.getX();
			_yEnd = me.getY();
			_canvas.getGraphicsContext().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}
}
