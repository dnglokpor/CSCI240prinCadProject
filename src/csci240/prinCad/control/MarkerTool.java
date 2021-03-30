package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;
import csci240.prinCad.ui.PrinCanvas;
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
	protected static final double _markerSize = 10;
	double _x;
	double _y;
	protected CadItem _drawn;
	
	// mouse movement properties
	//boolean _activeMouse; // actually needed?
	
	/** constructor:
	 * instantiate parent CadTool class.
	 * @param canvas
	 */
	public MarkerTool(PrinCanvas canvas) {
		super(canvas);
	}
	
	// command handler overrides
	@Override
	public void onMousePressed(MouseEvent me) {
		if(me.getButton() == MouseButton.PRIMARY) {
			this._canvas.setCursor(Cursor.CROSSHAIR);
			this._activeMouse = true;
		}
	}
	
	@Override
	public void onMouseDrag(MouseEvent me) { }

	@Override
	public void onMouseRelease(MouseEvent me){
		if(this._activeMouse) {
			this._activeMouse = false;
			this._canvas.setCursor(Cursor.DEFAULT);
			this._canvas.getGraphicsContext().setStroke(Color.ORANGERED);
			this._canvas.getGraphicsContext().setLineWidth(0);
			
			this.draw(me); // draw the marker
			
			this._canvas.reset(_drawn); // get the selection tool back
		}
	}
	
	// draw the shape of the marker
	protected abstract void draw(MouseEvent me);
}
