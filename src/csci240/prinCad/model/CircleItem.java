package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

/** CircleItem class:
 * CadItem that represent instances of drawn circles. records information
 * about them for later use.
 * @author dnglokpor
 */
public class CircleItem extends CadItem {
	// attributes
	public final double _xCenter;
	public final double _yCenter;
	public final double _radius;
	public final double _diameter;
	
	public CircleItem(double xCenter, double yCenter, double radius) {
		_xCenter = xCenter;
		_yCenter = yCenter;
		_radius = radius;
		_diameter = 2 * radius;
	}
	
	// other methods
	/**
	 * load circle token back from string format. this expects
	 * the string to provide "xCenter yCenter radius" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the data for the circle
	 * @return a CircleItem that can be drawn
	 */
	public static CircleItem load(String data) {
		CircleItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xc = Double.parseDouble(tokens[0]);
			double yc = Double.parseDouble(tokens[1]);
			double r = Double.parseDouble(tokens[2]);
			item = new CircleItem(xc, yc, r);
		}catch(Exception ex) {
			Log.error("Invalid CircleTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(_xCenter -  _radius, _yCenter - _radius, _diameter, _diameter);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f", _xCenter, _yCenter, _radius);
	}

	@Override
	public CadBox getRectangle() {
		return new CadBox(_xCenter -  _radius, _yCenter - _radius,_xCenter +  _radius, _yCenter + _radius);
	}

	@Override
	public boolean intersects(CadLine line) {
		double xi = Math.min(line.pivotX, line.endX), 
				xe = Math.max(line.pivotX, line.endX),
				r = round(_radius),
				d = round(distFromCenter(xi, line.imageOf(xi)));
		boolean crosses = false;
		// make a partition of points on the segment.
		while(xi < xe && !crosses) {
			double yi = line.imageOf(xi);
			double di = distFromCenter(xi, yi);
			// check if the distance range ever crosses the radius length.
			crosses = (d >= r && di <= r) || (d <= r && di >= r);
			if(!crosses)
				xi += 1; // increment by a pixel
		}
		return crosses;
	}
	
	/** helper
	 * @param x the x-coordinate of the point
	 * @param y the y-coordinate of the point
	 * @return the distance from the center to the point.
	 */
	public double distFromCenter(double x, double y) {
		return Math.sqrt(Math.pow(_xCenter - x, 2) + Math.pow(_yCenter - y, 2));
	}

	@Override
	public boolean inRangeOf(CadPoint point) {
		double radius = round(_radius),
			minDist = round(distFromCenter(point.xCoord, point.yCoord) - point.range),
			maxDist = round(distFromCenter(point.xCoord, point.yCoord) + point.range);
		return  minDist <= radius && radius <= maxDist;
	}

}
