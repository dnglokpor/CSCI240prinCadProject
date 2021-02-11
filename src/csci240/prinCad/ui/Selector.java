package csci240.prinCad.ui;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** Selector abstract class:
 * this class is an base class that must be instantiated through subclasses that inherit it.
 * it provides two methods to handle mouse pressed and released events but requires the
 * children to provide a mouse drag handler.
 * @author dnglo
 */
abstract class Selector {
	// attributes
	protected PrinCanvas pad;
	
	/** constructor:
	 * instantiate the Selector object by initializing the pad attribute to the
	 * correct canvas object.
	 * @param pc the canvas to select on
	 */
	public Selector(PrinCanvas pc) {
		this.pad = pc;
	}
	
	// methods to implement
	/**
	 * handler that respond to the canvas sensing mouse button be pressed.
	 * @param me the mouse event to handle.
	 */
	void onMousePressed(MouseEvent me) {
		if(me.getButton() == MouseButton.PRIMARY) {
			double x = me.getX();
			double y = me.getY();
			pad._xPivot = x;
			pad._yPivot = y;
			pad._xEnd = x;
			pad._yEnd = y;
			pad._activeMouse = true;
			pad._gc = pad.getGraphicsContext2D();
			pad._gc.setStroke(Color.ORANGERED);
			pad._gc.setLineWidth(0);
			pad.setCursor(Cursor.CROSSHAIR);
		}
	}
	
	/**
	 * handler that respond to the canvas sensing mouse being dragged across the screen.
	 * this is abstract because it can change depending on what selection must be done.
	 * @param me the mouse event to handle.
	 */
	abstract void onMouseDrag(MouseEvent me);
	
	/**
	 * handler that respond to the canvas sensing mouse button being released.
	 * @param me the mouse event to handle.
	 */
	void onMouseRelease(MouseEvent me) {
		if(pad._activeMouse) {
			pad._activeMouse = false;
			pad.setCursor(Cursor.DEFAULT);
			pad._gc = null;
		}
	}
}
