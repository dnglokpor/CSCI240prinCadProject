package csci240.prinCad.model;

import csci240.prinCad.control.BoxMarkerTool;
import csci240.prinCad.ui.Log;
import javafx.scene.canvas.GraphicsContext;

/** BoxItem class:
 * CadItem that represent instances of drawn box markers. records information
 * about them for later use.
 * @author dnglokpor
 */
public class BoxItem extends CadItem {
	// attributes
	public final double _x;
	public final double _y;
	
	public BoxItem(double x, double y) {
		_x = x;
		_y = y;
	}
	
	// other methods
	/**
	 * load box token back from string format. this expects
	 * the string to provide "x y" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates.
	 * @return a BoxItem that can be drawn.
	 */
	public static BoxItem load(String data) {
		BoxItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			item = new BoxItem(x, y);
		}catch(Exception ex) {
			Log.error("Invalid LineTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		BoxMarkerTool.boxAt(gc, _x, _y);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f", _x, _y);
	}
}
