package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

/** CrossItem class:
 * CadItem that represent instances of drawn cross markers. records information
 * about them for later use.
 * @author dnglokpor
 */
public class XItem extends MarkerItem {
	/** constructor:
	 * instantiate a BoxItem with
	 * @param x the center x-coordinate
	 * @param y the center y-coordinate
	 */
	public XItem(double x, double y, double s) {
		super(x, y, s);
	}
	
	// other methods
	@Override
	public void draw(GraphicsContext gc) {
		double leftX = _x - _size;
		double topY = _y - _size;
		double bottomY = _y + _size;
		double rightX = _x + _size;
		gc.strokeLine(leftX, topY, rightX, bottomY);
		gc.strokeLine(rightX, topY, leftX, bottomY);
	}

	@Override
	public boolean intersects(CadLine line) {
		// respectively first diagonal and 2nd diagonal of the x
		LineItem firstDiag = new LineItem(_x - _size, _y - _size, _x + _size, _y + _size);
		LineItem secDiag = new LineItem(_x - _size, _y + _size, _x + _size, _y - _size);
		return firstDiag.intersects(line) || secDiag.intersects(line);
	}
}
