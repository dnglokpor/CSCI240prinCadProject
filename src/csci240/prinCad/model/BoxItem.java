package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

/** BoxItem class:
 * CadItem that represent instances of drawn box markers. records information
 * about them for later use.
 * @author dnglokpor
 */
public class BoxItem extends MarkerItem {
	/** constructor:
	 * instantiate a BoxItem with
	 * @param x the center x-coordinate
	 * @param y the center y-coordinate
	 */
	public BoxItem(double x, double y, double s) {
		super(x, y, s);
	}
	
	// other methods
	@Override
	public void draw(GraphicsContext gc) {
		double sideLength = 2 * _size; // double the size for boxes
		gc.strokeRect(_x - _size, _y - _size, sideLength, sideLength);
	}

	@Override
	public boolean intersects(CadLine line) {
		// respectively top, left, right and bottom sides of the box.
		LineItem top = new LineItem(_x - _size, _y - _size, _x + _size, _y - _size);
		LineItem left = new LineItem(_x - _size, _y - _size, _x - _size, _y + _size);
		LineItem right = new LineItem(_x + _size, _y - _size, _x + _size, _y + _size);
		LineItem bottom = new LineItem(_x - _size, _y + _size, _x + _size, _y + _size);
		
		// checks if any of the side segments intersect the line
		// in which case returns true.
		return top.intersects(line) || left.intersects(line) ||
				right.intersects(line) || bottom.intersects(line);
	}

	@Override
	public BoxItem clone() {
		return new BoxItem(_x, _y, _size);
	}
}
