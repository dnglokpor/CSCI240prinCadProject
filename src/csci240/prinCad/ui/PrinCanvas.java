package csci240.prinCad.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import csci240.prinCad.control.BoxSelectionTool;
import csci240.prinCad.control.CadTool;
import csci240.prinCad.control.LineSelectionTool;
import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.ModelManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** PrinCanvas Object:
 * Drawing canvas for the Prin CAD tools application.
 * @author dnglokpor
 */
public class PrinCanvas extends Canvas{
	// Reference to graphics context
	private GraphicsContext _gc;
	private CadTool _selectionTool; // for selection purposes
	private CadTool _activeTool; // may not be a selection tool
	private ModelManager _model; // for managing graphic tokens

	/** constructor:
	 * initializes the drawing canvas and provides mouse input handlers.
	 * @param cadSettings object that contains canvas parameters.
	 */
	public PrinCanvas(Settings cadSettings) {
		// invoke (call) parent class constructor
		super(cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());

		// get graphics context to fill background
		this._gc = getGraphicsContext2D();
		this._gc.setFill(cadSettings.getCanvasColor());
		this._gc.fillRect(0,  0,  cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());
		
		// Subscribe to mouse events
		setOnMousePressed(e -> _activeTool.onMousePressed(e));
		setOnMouseDragged(e -> _activeTool.onMouseDrag(e));
		setOnMouseMoved(e -> _activeTool.onMouseMove(e)); // Poly
		setOnMouseReleased(e -> _activeTool.onMouseRelease(e));
		
		// set selection type by default
		this._selectionTool = new BoxSelectionTool(this);
		this._activeTool = this._selectionTool; // specify that selection is currently working
		
		// create model manager
		_model = new ModelManager();
	}
	
	// getters
	/**
	 * @return this instance graphics context.
	 */
	public GraphicsContext getGraphicsContext() { return _gc; }
	
	// setters
	
	/**
	 * change the selection object used to the other type of selection.
	 */
	public void toggleSelectionType() {
		if(this._selectionTool instanceof BoxSelectionTool) { // if already line selector
			this._selectionTool = new LineSelectionTool(this);
		}else {	// else make it line selector
			this._selectionTool = new BoxSelectionTool(this);
		}
		this._activeTool = this._selectionTool;
	}
	
	/**
	 * @param newTool set this passed tool instance as the current active. 
	 */
	public void setActiveTool(CadTool activeTool) {
		this._activeTool = activeTool;
	}
	
	/**
	 * exit a CAD Tool use to get back to selection.
	 */
	public void reset() {
		this._activeTool = this._selectionTool;
	}
	
	/**
	 * save created CAD item and set back to selection mode.
	 */
	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		this.reset(); // reset to selection
	}
	
	/**
	 * draw all graphic tokens existing 
	 */
	public void draw() {
		_gc.fillRect(0,  0,  getWidth(),  getHeight());
		_gc.setStroke(Color.ORANGERED);
		_gc.setLineWidth(0);
		_model.draw(_gc); // draw all
	}
	
	/**
	 * persist the current image of the canvas to a file
	 */
	public void saveToFile(PrintWriter out) {
		_model.save(out);
	}
	
	/**
	 * load a canvas image from a file.
	 */
	public void loadFromFile(BufferedReader reader) {
		_model.clear();
		_model.load(reader);
		draw();
	}
	
	/**
	 * clears the model to start over.
	 */
	public void freshStart() {
		_model.clear();
		draw();
	}
	
	/**
	 * record an opened file.
	 * @param of
	 */
	public void recordOpenedFile(File of) {
		_model.setModelFile(of);
	}
	
	public File getOpenedModel() {
		return _model.getModelFile();
	}
}
