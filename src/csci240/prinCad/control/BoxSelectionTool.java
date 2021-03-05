package csci240.prinCad.control;

import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class BoxSelectionTool extends CadTool{

	/** constructor:
	 * builds a rectangle selection object by passing the PrinCanvas argument to base
	 * selector class.
	 * @param pc the PrinCanvas being drawn on.
	 */
	public BoxSelectionTool(PrinCanvas pc) {
		super(pc); // build base Selector
	}
	
	public void onMouseDrag(MouseEvent me) {
		if(_activeMouse) {
			// update mouse end coordinates
			_xEnd = me.getX();
			_yEnd = me.getY();
			// calculate rectangle parameters
			double x = Math.min(_xPivot, _xEnd) - 1;
			double y = Math.min(_yPivot, _yEnd) - 1;
			double w = Math.abs(_xEnd - _xPivot) + 2;
			double h = Math.abs(_yEnd - _yPivot) + 2;
			// clear old selection
			pad.getGraphicsContext().fillRect(_old[0], _old[1], _old[2], _old[3]);
			// draw current rectangle
			pad.getGraphicsContext().strokeRect(x, y, w, h);
			// save this parameters for later deletion with offsets to cover the
			// whole thing
			saveOld(x - 1, y - 1, w + 2, h + 2);
		}
	}
}