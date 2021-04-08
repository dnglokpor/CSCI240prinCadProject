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

}
