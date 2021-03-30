package csci240.prinCad.model;

import java.util.ArrayList;

import csci240.prinCad.ui.Log;
import javafx.scene.canvas.GraphicsContext;

/** PolyItem class:
 * CadItem that represent instances of drawn polylines. records information
 * about them for later use.
 * @author dnglokpor
 */
public class PolyItem extends CadItem {
	// attributes
	public final ArrayList<Double> _lines;
	
	/** constructor:
	 * expects an ArrayList of lineItems.
	 */
	public PolyItem(ArrayList<Double> lines) {
		_lines = lines;
	}
	
	// other methods
	/**
	 * load polyline token back from string format. this expects
	 * the string to provide a series of "x y" as the input 
	 * string with each token parsable as doubles.
	 * @param data the string containing the coordinates for the polyline.
	 * @return a PolyItem that can be drawn.
	 */
	public static PolyItem load(String data) {
		PolyItem item = null;
		ArrayList<Double> lines = new ArrayList<Double>();
		try {
			String[] tokens = data.split(" ");
			for(String token : tokens) {
				lines.add(Double.parseDouble(token));
			}
			item = new PolyItem(lines);
		}catch(Exception ex) {
			Log.error("Invalid PolyTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if(_lines.size() >= 4) { // fail safe
			for(int i = 0; i < _lines.size() - 3; i += 2) {
				gc.strokeLine(_lines.get(i), _lines.get(i+1),
						_lines.get(i+2), _lines.get(i+3));
			}		
		}else {
			Log.error("PolyItem.java 55 - Invalid data: " + 
					_lines);
		}
	}

	@Override
	public String save() {
		String record = "";
		for(double coord : _lines) { // sprint inline each value
			record += String.format("%f ", coord);
		}
		// erase end " "
		// record = record.substring(0, record.length() - 1);
		// System.out.println("record " + record); // DEBUG
		return record;
	}
}
