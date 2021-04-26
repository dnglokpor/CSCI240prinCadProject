package csci240.prinCad.model;

/** CadLine class:
 * represents the line returned by the LineSelectionTool.
 * Allows to detect collision with other items cadBoxes.
 * @author dnglokpor
 */
public class CadLine {
	// attributes
	public final double pivotX, pivotY, endX, endY;
	// slope and y-intercept as in y = mx + b
	public double slope, yInt;
	public final double ZERO_PRECISION = 0.000001; // supposed zero
	public final double INFINITY_VALUE = 1000000; // supposed infinity
	
	/** constructor:
	 * sets the value of the pivot and end coordinates.
	 * @param px pivot x-coordinate
	 * @param py pivot y-coordinate
	 * @param ex end x-coordinate
	 * @param ey end y-coordinate
	 */
	public CadLine(double px, double py, double ex, double ey) {
		pivotX = px;
		pivotY = py;
		endX = ex;
		endY = ey;
		slope = (endY - pivotY)/(endX - pivotX);
		if (slope == Double.POSITIVE_INFINITY || slope == Double.NEGATIVE_INFINITY)
			slope = INFINITY_VALUE;
		yInt = endY - (slope * endX);
	}
	
	/**
	 * @param pointX x-coordinate
	 * @param pointY y-coordinate
	 * @return the distance of this line to the point.
	 */
	public double distToPoint(double pointX, double pointY) {
		// turn y = mx + b into ax + by + c = 0
		double a = -slope, b = 1, c = -yInt;
		// using distance from line to point formula:
		// dist = (ax+by+c)/sqrt(a^2+b^2)
		return Math.abs(a * pointX + b * pointY + c)/Math.sqrt(a * a + 1); // b^2 is 1
	}
	
	/**
	 * checks if this point is in the line segment defined by this.
	 * note: this neglects double precision.
	 * @param pointX x-coordinate
	 * @param pointY y-coordinate
	 * @return true if the point is on this line.
	 */
	public boolean containsPoint(double pointX, double pointY) {
		//int y = (int) pointY;
		//int mxpb = (int) (slope * pointX + yInt);
		/*return (Math.min(pivotX, endX) <= pointX) &&
				(Math.max(pivotX, endX) >= pointX) &&
				(Math.min(pivotY, endY) <= pointY) &&
				(Math.max(pivotY, endY) >= pointY)
		;*/
		boolean in1 = round(Math.min(pivotX, endX)) <= round(pointX),
				in2 = round(Math.max(pivotX, endX)) >= round(pointX),
				in3 = round(Math.min(pivotY, endY)) <= round(pointY),
				in4 = round(Math.max(pivotY, endY)) >= round(pointY);
		return in1 && in2 && in3 && in4;
	}
	
	/** helper:
	 * @param val a decimal value.
	 * @return return an integer rounded value of val.
	 */
	public double round(double val) {
		double floor = Math.floor(val);
		return val - floor < 0.5D ? floor : floor + 1; 
	}
	
	/** helper:
	 * @param x an x-coordinate of a point.
	 * @return the y-coordinate of the same point on the line.
	 */
	public double imageOf(double x) {
		return slope*x + yInt;
	}
	
}
