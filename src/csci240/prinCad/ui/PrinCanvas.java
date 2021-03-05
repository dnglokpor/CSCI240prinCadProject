package csci240.prinCad.ui;

import csci240.prinCad.control.BoxSelectionTool;
import csci240.prinCad.control.CadTool;
import csci240.prinCad.control.LineSelectionTool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/** PrinCanvas Object:
 * Drawing canvas for the Prin CAD tools application.
 * @author dnglokpor
 */
public class PrinCanvas extends Canvas{
	// Reference to graphics context
	private GraphicsContext _gc;
	private CadTool slType; // for selection purposes

	/** constructor:
	 * initializes the drawing canvas and provides mouse input handlers.
	 * @param cadSettings object that contains canvas parameters.
	 */
	public PrinCanvas(Settings cadSettings) {
		// invoke (call) parent class constructor
		super(cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());

		// get graphics context to fill background
		setGraphicsContext(getGraphicsContext2D());
		getGraphicsContext().setFill(cadSettings.getCanvasColor());
		getGraphicsContext().fillRect(0,  0,  cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());

		// set selection type by default
		this.slType = new LineSelectionTool(this);
		// or
		// this.slType = new RectSelector(this);
		
		// Subscribe to mouse events
		setOnMousePressed(e -> slType.onMousePressed(e));
		setOnMouseDragged(e -> slType.onMouseDrag(e));
		setOnMouseReleased(e -> slType.onMouseRelease(e));
	}
	
	// getters
	/**
	 * @return this instance graphics context.
	 */
	public GraphicsContext getGraphicsContext() { return _gc; }
	
	// setters
	/**
	 * set the graphics context of the PrinCanvas.
	 * @param _gc
	 */
	public void setGraphicsContext(GraphicsContext _gc) {
		this._gc = _gc;
	}
	
	/**
	 * change the selection object used to the other type of selection.
	 */
	public void changeSelection() {
		if(slType instanceof LineSelectionTool) { // if already line selector
			slType = new BoxSelectionTool(this);
		}else {	// else make it line selector
			slType = new LineSelectionTool(this);
		}
	}
}
