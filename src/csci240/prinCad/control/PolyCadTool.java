package csci240.prinCad.control;

import java.util.ArrayList;

import csci240.prinCad.model.PolyItem;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/** PolyCadTool class:
 * class that represents instances of lines that can be
 * drawn on the screen one after the other. The end point of
 * each is the starting point of the next one. A left click
 * will stop the drawing.
 * @author dnglokpor
 */
public class PolyCadTool extends CadTool {
	// attributes
	ArrayList<Double> _lines;
	
	
	/** constructor:
	 * initialize array of points.
	 */
	public PolyCadTool() {
		super();
		_lines = new ArrayList<Double>();
	}
	
	@Override
	public void onMousePressed(MouseEvent me, CanvasToolInterface _canvas) {
		if(me.getButton() == MouseButton.SECONDARY) {
			// done drawing
			_lines.add(_xEnd);
			_lines.add(_yEnd);
			// System.out.println("right released"); // DEBUG
			_activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			// save shape and go back to selection
			_canvas.reset(new PolyItem(_lines));
		}
		// else do what is usually done
		super.onMousePressed(me, _canvas);
	}
		
	@Override
	public void onMouseMove(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse) {
			// update line end
			_xEnd = me.getX();
			_yEnd = me.getY();
			// redraw screen
			_canvas.draw();
			// redraw current polyline state
			new PolyItem(_lines).draw(_canvas.getGraphicsContext());
			// draw new line
			_canvas.getGraphicsContext().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}
	
	@Override
	public void onMouseRelease(MouseEvent me, CanvasToolInterface _canvas) {
		if(_activeMouse && me.getButton() == MouseButton.PRIMARY) {
			// record pivots coordinates
			_lines.add(_xPivot);
			_lines.add(_yPivot);
		}
	}
}
