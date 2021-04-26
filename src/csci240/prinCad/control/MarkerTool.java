package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** MarkerTool abstract class:
 * super class of all the Marker tools used in the CAD. Implements their shared
 * behavior. 
 * @author dnglokpor
 */
public abstract class MarkerTool extends CadTool {
	// shared attribute
	double _x;
	double _y;
	protected CadItem _drawn;
	
	// mouse movement properties
	//boolean _activeMouse; // actually needed?
	
	/** constructor:
	 * using default
	 */
	
	// command handler overrides
	@Override
	public void onMousePressed(MouseEvent me, CanvasToolInterface _canvas) {
		if(me.getButton() == MouseButton.PRIMARY) {
			_canvas.setCursor(Cursor.CROSSHAIR);
			this._activeMouse = true;
		}
	}
	
	@Override
	public void onMouseDrag(MouseEvent me, CanvasToolInterface _canvas) { }

	@Override
	public void onMouseRelease(MouseEvent me, CanvasToolInterface _canvas){
		if(this._activeMouse) {
			this._activeMouse = false;
			_canvas.setCursor(Cursor.DEFAULT);
			_canvas.getGraphicsContext().setStroke(Color.ORANGERED);
			_canvas.getGraphicsContext().setLineWidth(0);
			
			this.draw(me, _canvas); // draw the marker
			
			_canvas.reset(_drawn); // get the selection tool back
		}
	}
	
	// draw the shape of the marker
	protected abstract void draw(MouseEvent me, CanvasToolInterface _canvas);
}
