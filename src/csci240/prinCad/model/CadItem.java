package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** abstract class CadItem:
 * super class that will define all tokens of our application canvas so we can regroup
 * them in a collection for redrawing, saving and loading purposes.
 * @author dnglokpor
 */
public abstract class CadItem {
	// attributes
	protected boolean _isSelected = false;
	
	/**
	 * draw this CadItem on using the information in the passed graphics context.
	 * @param gc
	 */
	public abstract void draw(GraphicsContext gc);
	
	/**
	 * different draw method to cather to selected objects settings. For those,
	 * it changes the color to a different color before 
	 */
	public void draw(GraphicsContext gc, Color normalColor, Color selectColor) {
		if(_isSelected) {
			gc.setStroke(selectColor);
			gc.setLineWidth(1);
			draw(gc); // item's overriden draw()
			gc.setStroke(normalColor);
			gc.setLineWidth(0);
		}else {
			draw(gc); // item's overriden draw()
		}
	}
	
	/**
	 * generates a string description of the CadItem for writing to a file. 
	 * @return
	 */
	public abstract String save();
	
	/**
	 * set this item _isSelected property to the opposite 
	 * of what it was.
	 * @param selectionBox bounding box of BoxSelectionTool.
	 */
	public void select(CadBox selectionBox) {
		if(selectionBox.contains(this.getRectangle())) {
			_isSelected = !_isSelected;
		}
	}
	
	/**
	 * set this item _isSelected property to the opposite 
	 * of what it was.
	 * @param selectionLine line by LineSelectionTool.
	 */
	public void select(CadLine selectionLine) {
		if(this.intersects(selectionLine)) {
			_isSelected = !_isSelected;
		}
	}
	
	/**
	 * set this item _isSelected property to the opposite 
	 * of what it was.
	 * @param selectionLine line by LineSelectionTool.
	 */
	public void select(CadPoint selectionPoint) {
		if(this.inRangeOf(selectionPoint)) {
			_isSelected = !_isSelected;
		}
	}
	
	/**
	 * get bounding rectangle of item
	 */
	public abstract CadBox getRectangle();
	
	/**
	 * checks if this item intersects the passed line.
	 * @param line the Cadline returned by the selection
	 * @return true if this intersects the line; false if not.
	 */
	public abstract boolean intersects(CadLine line);
	
	/**
	 * checks if this point is in range of the shape.
	 * @param point the CadPoint to compare to.
	 * @return true if the is in range. false elsewhere.
	 */
	public abstract boolean inRangeOf(CadPoint point);
	
	/** helper:
	 * @param val a decimal value.
	 * @return return an integer rounded value of val.
	 */
	public double round(double val) {
		double floor = Math.floor(val);
		return val - floor < 0.5D ? floor : floor + 1; 
	}
	
	/** helper:
	 * return the square of a number.
	 * @param x
	 * @return
	 */
	public double sqr(double x) { return x * x; }
	// IT IS NOT SAID BUT EACH CHILD CLASSES MUST IMPLEMENT A LOAD METHOD.
}
