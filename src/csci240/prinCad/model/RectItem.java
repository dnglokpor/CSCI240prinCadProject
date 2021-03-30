package csci240.prinCad.model;

import csci240.prinCad.ui.Log;
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
}
