package csci240.prinCad.model;

import csci240.prinCad.control.XMarkerTool;
import csci240.prinCad.ui.Log;
import javafx.scene.canvas.GraphicsContext;

/** CrossItem class:
 * CadItem that represent instances of drawn cross markers. records information
 * about them for later use.
 * @author dnglokpor
 */
public class XItem extends CadItem {
	// attributes
	public final double _x;
	public final double _y;
	
	public XItem(double x, double y) {
		_x = x;
		_y = y;
	}
	
	// other methods
	/**
	 * load cross token back from string format. this expects
	 * the string to provide "x y" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates.
	 * @return a XItem that can be drawn.
	 */
	public static XItem load(String data) {
		XItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			item = new XItem(x, y);
		}catch(Exception ex) {
			Log.error("Invalid LineTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		XMarkerTool.crossAt(gc, _x, _y);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f", _x, _y);
	}
}
