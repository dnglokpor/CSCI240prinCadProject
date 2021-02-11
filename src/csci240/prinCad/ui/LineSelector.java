package csci240.prinCad.ui;

import javafx.scene.input.MouseEvent;

/** LineSelector Object:
 * inherits the Selector class and must implement a line based selection by
 * overriding the onMouseDragged() method from it's abstract parent.
 * @author dnglokpor
 */
class LineSelector extends Selector{

	/** constructor:
	 * provide the abstract parent with the required PrinCanvas context object.
	 * @param pc
	 */
	public LineSelector(PrinCanvas pc) {
		super(pc); // construct base selector
	}
	
	// override
	public void onMouseDrag(MouseEvent me) {
		if(pad._activeMouse) {
			double x = Math.min(pad._xPivot, pad._xEnd) - 1;
			double y = Math.min(pad._yPivot, pad._yEnd) - 1;
			double w = Math.abs(pad._xEnd - pad._xPivot) + 2;
			double h = Math.abs(pad._yEnd - pad._yPivot) + 2;
			pad._gc.fillRect(x, y, w, h);
			pad._xEnd = me.getX();
			pad._yEnd = me.getY();
			pad._gc.strokeLine(pad._xPivot, pad._yPivot, pad._xEnd, pad._yEnd);
		}
	}
}
