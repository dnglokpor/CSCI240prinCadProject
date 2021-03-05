package csci240.prinCad.control;

import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** Selector abstract class:
 * this class is an base class that must be instantiated through subclasses that inherit it.
 * it provides two methods to handle mouse pressed and released events but requires the
 * children to provide a mouse drag handler.
 * @author dnglokpor
 */
public abstract class CadTool {
	// attributes
	protected PrinCanvas pad;
	protected double[] _old = new double[] {0,0,0,0}; // old selection coords DILLON's help
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	/** constructor:
	 * instantiate the Selector object by initializing the pad attribute to the
	 * correct canvas object.
	 * @param pc the canvas to select on
	 */
	public CadTool(PrinCanvas pc) {
		this.pad = pc;
	}
	
	// methods to implement
	/**
	 * handler that respond to the canvas sensing mouse button be pressed.
	 * @param me the mouse event to handle.
	 */
	public void onMousePressed(MouseEvent me) {
		if(me.getButton() == MouseButton.PRIMARY) {
			double x = me.getX();
			double y = me.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_activeMouse = true;
			pad.setGraphicsContext(pad.getGraphicsContext2D());
			pad.getGraphicsContext().setStroke(Color.ORANGERED);
			pad.getGraphicsContext().setLineWidth(0);
			pad.setCursor(Cursor.CROSSHAIR);
		}
	}
	
	/**
	 * handler that respond to the canvas sensing mouse being dragged across the screen.
	 * this is abstract because it can change depending on what selection must be done.
	 * @param me the mouse event to handle.
	 */
	public abstract void onMouseDrag(MouseEvent me);
	
	/**
	 * handler that respond to the canvas sensing mouse button being released.
	 * @param me the mouse event to handle.
	 */
	public void onMouseRelease(MouseEvent me) {
		if(_activeMouse) {
			_activeMouse = false;
			pad.setCursor(Cursor.DEFAULT);
			pad.getGraphicsContext().fillRect(_old[0], _old[1], _old[2], _old[3]); // delete last selection
			pad.setGraphicsContext(null);
		}
	}
	
	// helper
	/**
	 * records the parameters of the current rectangular selection so the next one
	 * can clear it before its drawn.
	 * @param x top-left x coordinate 
	 * @param y top-right y coordinates
	 * @param w rectangle width
	 * @param h rectangle height
	 */
	protected void saveOld(double x, double y, double w, double h) {
		this._old[0] = x;
		this._old[1] = y;
		this._old[2] = w;
		this._old[3] = h;
	}
}