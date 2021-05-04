package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

/** RectItem class:
 * CadItem that represent instances of drawn rectangles. records information
 * about them for later use.
 * @author dnglokpor
 */
public class RectItem extends CadItem {
	// attributes
	public final double _xPivot;
	public final double _yPivot;
	public final double _width;
	public final double _height;
	
	public RectItem(double xP, double yP, double w, double h) {
		_xPivot = xP;
		_yPivot = yP;
		_width = w;
		_height = h;
	}
	
	// other methods
	/**
	 * load rectangle token back from string format. this expects
	 * the string to provide "xPivot yPivot width height" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates of the rectangle.
	 * @return a RectItem that can be drawn.
	 */
	public static RectItem load(String data) {
		RectItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xP = Double.parseDouble(tokens[0]);
			double yP = Double.parseDouble(tokens[1]);
			double w = Double.parseDouble(tokens[2]);
			double h = Double.parseDouble(tokens[3]);
			item = new RectItem(xP, yP, w, h);
		}catch(Exception ex) {
			Log.error("Invalid LineTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(_xPivot, _yPivot, _width, _height);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", _xPivot, _yPivot, _width, _height);
	}

	@Override
	public CadBox getRectangle() {
		return new CadBox(_xPivot, _yPivot, _xPivot + _width, _yPivot + _height);
	}

	@Override
	public boolean intersects(CadLine line) {
		// respectively top, left, right and bottom sides of the box.
		LineItem top = new LineItem(_xPivot, _yPivot, _xPivot + _width, _yPivot);
		LineItem left = new LineItem(_xPivot, _yPivot, _xPivot, _yPivot + _height);
		LineItem right = new LineItem(_xPivot + _width, _yPivot, _xPivot + _width, _yPivot + _height);
		LineItem bottom = new LineItem(_xPivot, _yPivot + _height, _xPivot + _width, _yPivot + _height);
		
		// checks if any of the side segments intersect the line
		// in which case returns true.
		return top.intersects(line) || left.intersects(line) ||
				right.intersects(line) || bottom.intersects(line);
	}

	@Override
	public boolean inRangeOf(CadPoint point) {
		// respectively top, left, right and bottom sides of the box.
				LineItem top = new LineItem(_xPivot, _yPivot, _xPivot + _width, _yPivot);
				LineItem left = new LineItem(_xPivot, _yPivot, _xPivot, _yPivot + _height);
				LineItem right = new LineItem(_xPivot + _width, _yPivot, _xPivot + _width, _yPivot + _height);
				LineItem bottom = new LineItem(_xPivot, _yPivot + _height, _xPivot + _width, _yPivot + _height);
				
				// checks if any of the side segments intersect the line
				// in which case returns true.
				return top.inRangeOf(point) || left.inRangeOf(point) ||
						right.inRangeOf(point) || bottom.inRangeOf(point);
	}

	@Override
	public RectItem clone() {
		return new RectItem(_xPivot, _yPivot, _width, _height);
	}
}
