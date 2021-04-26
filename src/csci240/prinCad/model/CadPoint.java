package csci240.prinCad.model;

/** CadPoint class:
 * represent a single point of the graphics context.
 * is also used for point selection.
 * @author dnglokpor
 */
public class CadPoint {
	// attributes
	public final double xCoord, yCoord, range;
	
	/** constructor:
	 * initializes the attributes.
	 * @param x x-coordinates
	 * @param y y-coordinates
	 * @param r range to check in.
	 */
	public CadPoint(double x, double y, double r) {
		xCoord = x;
		yCoord = y;
		range = r;
	}
	
	// other methods
	/** helper:
	 * @param x specified x-coordinate
	 * @param y specified y-coordinate
	 * @return the distance between this and the specified point.
	 */
	public double distTo(double x, double y) {
		return Math.sqrt(sqr(xCoord - x) + sqr(yCoord - y));  
	}
	
	/** helper:
	 * return the square of a number.
	 * @param x
	 * @return
	 */
	public double sqr(double x) { return x * x; }
}
