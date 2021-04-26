package csci240.prinCad.model;

/** CadBox class:
 * represent the bounding box of graphics tokens in our applications.
 * this means all CadItems but also selection shapes.
 * @author ACrump
 * @author dnglokpor
 */
public class CadBox {
	// attributes
	// one time assignment of X min & max and Y min & max values
	public final double xMin, yMin, xMax, yMax;
	
	/** constructor:
	 * initializes all the attributes with the passed values.
	 * @param xMin
	 * @param yMin
	 * @param xMax
	 * @param yMax
	 */
	public CadBox(double xMin, double yMin, double xMax, double yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	/**
	 * compares passed CadBox to this to see if passed is inside
	 * this.
	 * @param rect the CadBox to compare to.
	 * @return true if rect is inside this; false else wise.
	 */
	public boolean contains(CadBox rect) {
		return (rect.xMin >= this.xMin &&
				rect.xMax <= this.xMax &&
				rect.yMin >= this.yMin &&
				rect.yMax <= this.yMax);
	}

}
