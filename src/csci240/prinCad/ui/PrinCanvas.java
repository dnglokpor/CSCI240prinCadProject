package csci240.prinCad.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/** PrinCanvas Object:
 * Drawing canvas for the Prin CAD tools application.
 * @author dnglokpor
 */
class PrinCanvas extends Canvas{
	// Reference to graphics context
	GraphicsContext _gc;
	private Selector slType; // for selection purposes

	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;

	/** constructor:
	 * initializes the drawing canvas and provides mouse input handlers.
	 * @param cadSettings object that contains canvas parameters.
	 */
	public PrinCanvas(Settings cadSettings) {
		// invoke (call) parent class constructor
		super(cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());

		// get graphics context to fill background
		_gc = getGraphicsContext2D();
		_gc.setFill(cadSettings.getCanvasColor());
		_gc.fillRect(0,  0,  cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());

		// set selection type
		//this.slType = new LineSelector(this);
		// or
		this.slType = new RectSelector(this);
		
		// Subscribe to mouse events
		setOnMousePressed(e -> slType.onMousePressed(e));
		setOnMouseDragged(e -> slType.onMouseDrag(e));
		setOnMouseReleased(e -> slType.onMouseRelease(e));
	}
}
