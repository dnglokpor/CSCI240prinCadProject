package csci240.prinCad.control;

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
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	/** constructor:
	 * using default
	 */
	
	// methods to implement
	/**
	 * handler that respond to the canvas sensing mouse button be pressed.
	 * @param me the mouse event to handle.
	 */
	public void onMousePressed(MouseEvent me, CanvasToolInterface _canvas) {
		if(me.getButton() == MouseButton.PRIMARY) {
			double x = me.getX();
			double y = me.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_activeMouse = true;
			_canvas.getGraphicsContext().setStroke(Color.ORANGERED);
			_canvas.getGraphicsContext().setLineWidth(0);
			_canvas.setCursor(Cursor.CROSSHAIR);
		}
	}
	
	/**
	 * handler that respond to the canvas sensing mouse being dragged across the screen with a 
	 * button pressed. this is abstract because it can change depending on what selection must
	 * be done.
	 * @param me the mouse event to handle.
	 */
	public void onMouseDrag(MouseEvent me, CanvasToolInterface _canvas) {}
	
	/**
	 * handler that respond to the canvas sensing mouse being dragged across the screen without
	 * a button pressed. this is abstract because it can change depending on what selection must
	 * be done.
	 * @param me the mouse event to handle.
	 */
	public void onMouseMove(MouseEvent me, CanvasToolInterface _canvas) {}
	
	/**
	 * handler that respond to the canvas sensing mouse button being released.
	 * @param me the mouse event to handle.
	 */
	public void onMouseRelease(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse) {
			_activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			_canvas.draw(); // redraw the canvas image
		}
	}
	
}
