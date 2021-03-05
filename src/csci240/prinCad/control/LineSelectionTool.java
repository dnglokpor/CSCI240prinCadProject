package csci240.prinCad.control;

import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/** LineSelector Object:
 * inherits the Selector class and must implement a line based selection by
 * overriding the onMouseDragged() method from it's abstract parent.
 * @author dnglokpor
 */
public class LineSelectionTool extends CadTool{
	// attributes
	private double[] _old = new double[] {0,0,0,0}; 

	/** constructor:
	 * provide the abstract parent with the required PrinCanvas context object.
	 * @param pc
	 */
	public LineSelectionTool(PrinCanvas pc) {
		super(pc); // construct base selector
	}
	
	public void onMouseDrag(MouseEvent me) {
		if(_activeMouse) {
			// update line end
			// calculate insider clear
			double x = Math.min(_xPivot, _xEnd) - 1;
			double y = Math.min(_yPivot, _yEnd) - 1;
			double w = Math.abs(_xEnd - _xPivot) + 2;
			double h = Math.abs(_yEnd - _yPivot) + 2;
			_xEnd = me.getX();
			_yEnd = me.getY();
			pad.getGraphicsContext().fillRect(x, y, w, h);
			pad.getGraphicsContext().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
			saveOld(Math.min(_xPivot, _xEnd) - 1,
					Math.min(_yPivot, _yEnd) - 1,
					Math.abs(_xEnd - _xPivot) + 2,
					Math.abs(_yEnd - _yPivot) + 2
			);
		}
	}
}
