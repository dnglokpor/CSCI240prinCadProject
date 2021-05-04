package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

/** LineItem class:
 * CadItem that represent instances of drawn lines. records information
 * about them for later use.
 * @author dnglokpor
 */
public class LineItem extends CadItem {
	// attributes
	public final double _xPivot;
	public final double _yPivot;
	public final double _xEnd;
	public final double _yEnd;
	
	/** constructor:
	 * makes a LineItem instance based on passed parameters.
	 * @param xP the pivot x-coordinate
	 * @param yP the pivot y-coordinate
	 * @param xE the end point x-coordinate
	 * @param yE the end point y-coordinate
	 */
	public LineItem(double xP, double yP, double xE, double yE) {
		_xPivot = xP;
		_yPivot = yP;
		_xEnd = xE;
		_yEnd = yE;
	}
	
	// other methods
	/**
	 * load line token back from string format. this expects
	 * the string to provide "xPivot yPivot xEnd yEnd" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates for line.
	 * @return a LineItem that can be drawn.
	 */
	public static LineItem load(String data) {
		LineItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xP = Double.parseDouble(tokens[0]);
			double yP = Double.parseDouble(tokens[1]);
			double xE = Double.parseDouble(tokens[2]);
			double yE = Double.parseDouble(tokens[3]);
			item = new LineItem(xP, yP, xE, yE);
		}catch(Exception ex) {
			Log.error("Invalid LineTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", _xPivot, _yPivot, _xEnd, _yEnd);
	}

	@Override
	public CadBox getRectangle() {
		return new CadBox(Math.min(_xPivot, _xEnd), Math.min(_yPivot, _yEnd), 
				Math.max(_xPivot, _xEnd), Math.max(_yPivot, _yEnd));
	}

	@Override
	public boolean intersects(CadLine line) {
		// reference
		// https://www.topcoder.com/thrive/articles/Geometry%20Concepts%20part%202:%20%20Line%20Intersection%20and%20its%20Applications
		CadLine thisLine = new CadLine(_xPivot, _yPivot, _xEnd, _yEnd);
		double a1 = -line.slope, a2 = -thisLine.slope,
				det = (a1 - a2);
		if(Math.abs(det) < line.ZERO_PRECISION) {
			return false;
		}else {
			double x = (line.yInt - thisLine.yInt) / det;
			double y = (a1 * thisLine.yInt - a2 * line.yInt) / det;
			return line.containsPoint(x, y) && thisLine.containsPoint(x, y);
		}
	}

	@Override
	public boolean inRangeOf(CadPoint point) {
		boolean inRange = false;
		
		if(inXRange(point.xCoord, point.range) && 
				inYRange(point.yCoord, point.range)) {
			// the point is in the bounding rectangle of the segment
			// so we check at what distance of the line
			double dist = new CadLine(_xPivot, _yPivot, _xEnd, _yEnd)
					.distToPoint(point.xCoord, point.yCoord);
			if(round(dist) <= point.range) // if in range
				inRange = true; // we flag it
		}
		return inRange;
	}
	
	/**
	 * @return the length of this line.
	 */
	public double length() {
		return Math.sqrt(sqr(_xEnd - _xPivot) + sqr(_yEnd - _yPivot));
	}
	
	/**
	 * checks if a value is in the interval same x-interval as 
	 * this line segment.
	 * @param val the value to test
	 * @param optional offset the of the interval
	 * @return true if xPivot <= val <= xEnd
	 */
	public boolean inXRange(double val, double offset) {
		double pivot = Math.min(_xPivot, _xEnd) - offset,
			end = Math.max(_xPivot, _xEnd) + offset;
		return pivot <= val && val <= end;
	}
	
	/**
	 * checks if a value is in the interval same y-interval as 
	 * this line segment.
	 * @param val the value to test
	 * @param optional offset the of the interval
	 * @return true if yPivot <= val <= yEnd
	 */
	public boolean inYRange(double val, double offset) {
		double pivot = Math.min(_yPivot, _yEnd) - offset,
			end = Math.max(_yPivot, _yEnd) + offset;
		return pivot <= val && val <= end;
	}

	@Override
	public LineItem clone() {
		return new LineItem(_xPivot, _yPivot, _xEnd, _yEnd);
	}
}
