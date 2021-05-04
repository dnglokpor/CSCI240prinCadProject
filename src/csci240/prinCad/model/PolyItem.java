package csci240.prinCad.model;

import java.util.ArrayList;

import csci240.prinCad.util.Log;
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

	@Override
	public CadBox getRectangle() {
		double minX , minY, maxX, maxY;
		minX = maxX = _lines.get(0); // init both min and max x coord as first x in array
		minY = maxY = _lines.get(1); // init both min and max y coord as first y in array
		for(int i = 2; i < _lines.size(); i+=2) {
			minX = Math.min(minX, _lines.get(i));
			minY = Math.min(minY, _lines.get(i + 1));
			maxX = Math.max(maxX, _lines.get(i));
			maxY = Math.max(maxY, _lines.get(i + 1));
		}
		
		return new CadBox(minX, minY, maxX, maxY);
	}

	@Override
	public boolean intersects(CadLine line) {
		int i = 0;
		boolean intersects = false;
		// goes through an check intersection for each
		// line segment
		while(i < _lines.size() - 2 && !intersects) {
			LineItem segment = new LineItem(_lines.get(i), _lines.get(i+1),
					_lines.get(i+2), _lines.get(i+3));
			intersects = segment.intersects(line); // stops at first intersection
			if(!intersects) // else checks next
				i += 2;
		}
		return intersects;
	}

	@Override
	public boolean inRangeOf(CadPoint point) {
		int i = 0;
		boolean inRange = false;
		// goes through an check for each line segment for one
		// in range
		while(i < _lines.size() - 2 && !inRange) {
			LineItem segment = new LineItem(_lines.get(i), _lines.get(i+1),
				_lines.get(i+2), _lines.get(i+3));
			inRange = segment.inRangeOf(point); // stops at first intersection
			if(!inRange) // else checks next
				i += 2;
		}
		
		return inRange;
	}

	@Override
	public PolyItem clone() {
		ArrayList<Double> newLines = new ArrayList<Double>();
		_lines.forEach((i) -> newLines.add(i));
		return new PolyItem(newLines); 
	}
}
