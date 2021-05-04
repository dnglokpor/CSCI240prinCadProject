package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

/** PlusItem class:
 * CadItem that represent instances of drawn plus markers. records information
 * about them for later use.
 * @author dnglokpor
 */
public class PlusItem extends MarkerItem {
	/** constructor:
	 * init base class attributes.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param s marker size
	 */
	public PlusItem(double x, double y, double s) {
		super(x, y, s);
	}
	
	// other methods	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x - _size, _y, _x + _size, _y);
		gc.strokeLine(_x, _y - _size, _x, _y + _size);
	}

	@Override
	public boolean intersects(CadLine line) {
		// respectively vertical and horizontal segments of the +.
		LineItem vertical = new LineItem(_x, _y - _size, _x, _y + _size);
		LineItem horizontal = new LineItem(_x - _size, _y, _x + _size, _y);
		return vertical.intersects(line) || horizontal.intersects(line);
	}

	@Override
	public PlusItem clone() {
		return new PlusItem(_x, _y, _size);
	}
}
