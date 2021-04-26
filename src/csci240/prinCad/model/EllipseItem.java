package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

/** EllipseItem class:
 * CadItem that represent instances of drawn ellipses. records information
 * about them for later use.
 * @author dnglokpor
 */
public class EllipseItem extends CadItem {
	// attributes
	public final double _xCenter;
	public final double _yCenter;
	public final double _hRadius;
	public final double _vRadius;
	public final double _vDiameter;
	public final double _hDiameter;
	
	public EllipseItem(double xCenter, double yCenter, double hRad, double vRad) {
		_xCenter = xCenter;
		_yCenter = yCenter;
		_vRadius = vRad;
		_hRadius = hRad;
		_vDiameter = 2 * _vRadius;
		_hDiameter = 2 * _hRadius;
	}
	
	// other methods
	/**
	 * load ellipse token back from string format. this expects
	 * the string to provide "xCenter yCenter hRadius vRadius" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates of the ellipse.
	 * @return a EllipseItem that can be drawn
	 */
	public static EllipseItem load(String data) {
		EllipseItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xc = Double.parseDouble(tokens[0]);
			double yc = Double.parseDouble(tokens[1]);
			double hr = Double.parseDouble(tokens[2]);
			double vr = Double.parseDouble(tokens[3]);
			item = new EllipseItem(xc, yc, hr, vr);
		}catch(Exception ex) {
			Log.error("Invalid EllipseTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(_xCenter -  _hRadius, _yCenter - _vRadius, _hDiameter, _vDiameter);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", _xCenter, _yCenter, _hRadius, _vRadius);
	}

	@Override
	public CadBox getRectangle() {
		return new CadBox(_xCenter -  _hRadius, _yCenter - _vRadius, 
				_xCenter +  _hRadius, _yCenter + _vRadius);
	}

	@Override
	public boolean intersects(CadLine line) {
		// respectively top, left, right and bottom sides of the box.
		LineItem top = new LineItem(_xCenter - _hRadius, _yCenter - _vRadius, _xCenter + _hRadius, _yCenter - _vRadius);
		LineItem left = new LineItem(_xCenter - _hRadius, _yCenter - _vRadius, _xCenter -  _hRadius, _yCenter + _vRadius);
		LineItem right = new LineItem(_xCenter + _hRadius, _yCenter - _vRadius, _xCenter +  _hRadius, _yCenter + _vRadius);
		LineItem bottom = new LineItem(_xCenter - _hRadius, _yCenter + _vRadius, _xCenter + _hRadius, _yCenter + _vRadius);
		
		// checks if any of the side segments intersect the line
		// in which case returns true.
		return top.intersects(line) || left.intersects(line) ||
			right.intersects(line) || bottom.intersects(line);
	}

	@Override
	public boolean inRangeOf(CadPoint point) {
		boolean inRange = false;
		// using ellipse equation
		// (x-x0)^2/rH^2 + (y-y)^2/rV^2 = 1
		double op1 = sqr(point.xCoord - _xCenter)/sqr(_hRadius),
				op2 = sqr(point.yCoord - _yCenter)/sqr(_vRadius);
		if(round(op1 + op2) == 1)
			inRange = true;
		return inRange;
	}
}
