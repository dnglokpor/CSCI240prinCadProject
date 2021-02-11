package csci240.prinCad.ui;

import javafx.scene.input.MouseEvent;

class RectSelector extends Selector{

	public RectSelector(PrinCanvas pc) {
		super(pc); // build base Selector
	}
	
	public void onMouseDrag(MouseEvent me) {
		if(pad._activeMouse) {
			double x = Math.min(pad._xPivot, pad._xEnd) - 1;
			double y = Math.min(pad._yPivot, pad._yEnd) - 1;
			double w = Math.abs(pad._xEnd - pad._xPivot) + 2;
			double h = Math.abs(pad._yEnd - pad._yPivot) + 2;
			pad._gc.fillRect(x, y, w, h); // TODO figure out how to cover
			pad._xEnd = me.getX();
			pad._yEnd = me.getY();
			pad._gc.strokeRect(x, y, w, h);
		}
	}
}
