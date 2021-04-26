package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

/** MarkerItem abstract class:
 * CadItem that serves as base for all the markers. provides their
 * base attributes and shared methods. must be implemented by all
 * marker items.
 * @author dnglokpor
 */
public abstract class MarkerItem extends CadItem {
	// attributes
	public final double _x;
	public final double _y;
	public final double _size;
	
	/** constructor:
	 * instantiate a BoxItem with
	 * @param x the center x-coordinate
	 * @param y the center y-coordinate
	 */
	public MarkerItem(double x, double y, double s) {
		_x = x;
		_y = y;
		_size = s;
	}
	
	// other methods
	/**
	 * load box token back from string format. this expects
	 * the string to provide "x y" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates.
	 * @return a BoxItem that can be drawn.
	 */
	public static BoxItem load(String data) {
		BoxItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			double s = Double.parseDouble(tokens[2]);
			item = new BoxItem(x, y, s);
		}catch(Exception ex) {
			Log.error("Invalid LineTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public abstract void draw(GraphicsContext gc);

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f", _x, _y, _size);
	}

	@Override
	public CadBox getRectangle() {
		return new CadBox(_x - _size, _y - _size, _x + _size, _y + _size);
	}

	@Override
	public abstract boolean intersects(CadLine line);

	@Override
	public boolean inRangeOf(CadPoint point) {
		double dist = Math.sqrt((point.xCoord - _x)*(point.xCoord - _x)
				+ (point.yCoord - _y)*(point.yCoord - _y));
		return dist <= 2 * _size;
	}
}
