package csci240.prinCad.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** ModelManager class:
 * class that encapsulate all our CadItems. This allows to quickly manage an image of
 * the canvas of our application at all time.
 * @author dnglokpor
 */
public class ModelManager {
	// attributes
	private ArrayList<CadItem> _items;
	private File _modelFile = null; // save file
	
	/** constructor:
	 * builds the arraylist to store the items.
	 */
	public ModelManager() {
		_items = new ArrayList<CadItem>();
	}
	
	/**
	 * @return the current model file saved.
	 */
	public File getModelFile() {
		return _modelFile;
	}
	
	/**
	 * @return true if _modelFile is not null.
	 */
	public boolean hasModelFile() {
		return _modelFile != null;
	}
	
	/**
	 * set the model file attribute.
	 * @param mf
	 */
	public void setModelFile(File mf) {
		_modelFile = mf;	
	}
	
	/**
	 * adds a new item to our ArrayList.
	 * @param item the new CadItem to add.
	 */
	public void add(CadItem item) {
		_items.add(item);
	}
	
	/**
	 * erases the whole current canvas image.
	 * @param item the new CadItem to add.
	 */
	public void clear() {
		_items.clear();
		_modelFile = null; // dereference save file
	}
	
	/**
	 * toggles the value of the _isSelected property of this CadItem if inside the
	 * selection.
	 * @param boxSelect the CadBox of the current selection.
	 */
	public void select(CadBox boxSelect) {
		for(CadItem item : _items) {
			item.select(boxSelect);
		}
	}
	
	/**
	 * toggles the value of the _isSelected property of this CadItem if inside the
	 * selection.
	 * @param lineSelect the CadLine of the current selection.
	 */
	public void select(CadLine lineSelect) {
		for(CadItem item : _items) {
			item.select(lineSelect);
		}
	}
	
	/**
	 * toggles the value of the _isSelected property of this CadItem if inside the
	 * selection.
	 * @param pointSelect the CadPoint of the current selection.
	 */
	public void select(CadPoint pointSelect) {
		for(CadItem item : _items) {
			item.select(pointSelect);
		}
	}
	
	/**
	 * removes from the ArrayList of items any item that is currently selected
	 */
	public void delete() {
		_items.removeIf(item -> item._isSelected);
	}
	
	/**
	 * sets the drawing color and line width of the gc and draw the whole ArrayList
	 * of items.
	 * @param gc the GraphicsContext instance we are to draw on.
	 */
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.ORANGERED);
		gc.setLineWidth(0);
		
		for(CadItem item: _items) {
			item.draw(gc, Color.ORANGERED, Color.BLUEVIOLET);
		}
	}
	
	/**
	 * save the CadItems collection as an output file for persistence.
	 * @param out the fileOutputStream to write to. 
	 */
	public void save(PrintWriter out) {
		for(CadItem item : _items) {
			Class<? extends CadItem> cl = item.getClass(); // get the isa object
			String className = cl.getName();
			
			String data = item.save();
			out.println(String.format("<%1$s>%2$s</%1$s>", className, data)); // eg: <CircleItem><200.0 300.5 101</CircleItem>
		}
	}
	
	/**
	 * loads a canvas image from the princad save file.
	 * @param reader
	 */
	public void load(BufferedReader reader) {
		try {
			String line = reader.readLine(); // read first line
			int lineNo = 1; // for debug purposes
			while(line != null) {
				int i1 = line.indexOf("<") + 1; // find "<"
				int i2 = line.indexOf(">"); // find ">"
				String classType = line.substring(i1, i2);
				int i3 = line.indexOf("</" + classType + ">"); // find </classType>
				String data = line.substring(i2 + 1, i3); // get token data in between
				// System.out.println(classType + " " + data); // DEBUG
				CadItem cadItem;
				switch(classType) {
					case "csci240.prinCad.model.CircleItem":
						cadItem = CircleItem.load(data);
						break;
					case "csci240.prinCad.model.EllipseItem":
						cadItem = EllipseItem.load(data);
						break;
					case "csci240.prinCad.model.LineItem":
						cadItem = LineItem.load(data);
						break;
					case "csci240.prinCad.model.RectItem":
						cadItem = RectItem.load(data);
						break;
					case "csci240.prinCad.model.PolyItem":
						cadItem = PolyItem.load(data);
						break;
					case "csci240.prinCad.model.BoxItem":
						cadItem = BoxItem.load(data);
						break;
					case "csci240.prinCad.model.XItem":
						cadItem = XItem.load(data);
						break;
					case "csci240.prinCad.model.PlusItem":
						cadItem = PlusItem.load(data);
						break;
					default:
						throw new Exception("Failed to parse line " + lineNo + ": " + line);
				}
				if(cadItem != null) { // save loaded object to runtime image if it exist
					_items.add(cadItem);
				}
				
				lineNo++;
				line = reader.readLine(); // read next line
			}
		}catch(Exception ex) {
			Log.error("Failed to load file. Exception: ", ex);
		}
	}
}
