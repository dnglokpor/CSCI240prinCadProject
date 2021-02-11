package csci240.prinCad.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Cursor;

// Drawing canvas for the Prin CAD tools application
class PrinCanvas extends Canvas{
	// Reference to graphics context
	GraphicsContext _gc;
	
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	// Data constructor
	public PrinCanvas(Settings cadSettings) {
		// invoke (call) parent class constructor
		super(cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());
		
		// get graphics context to fill background
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(cadSettings.getCanvasColor());
		gc.fillRect(0,  0,  cadSettings.getCanvasWidth(), cadSettings.getCanvasHeight());
		
		// Subscribe to mouse events
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDrag(e));
		setOnMouseReleased(e -> onMouseRelease(e));
	}
	
	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e){
		if(e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_activeMouse = true;
			_gc = getGraphicsContext2D();
			_gc.setStroke(Color.ORANGERED);
			_gc.setLineWidth(0);
			setCursor(Cursor.CROSSHAIR);
		}
	}
	
	// Handle mouse pressed (button down)
	private void onMouseDrag(MouseEvent e){
		if(_activeMouse) {
			double x = Math.min(_xPivot, _xEnd) - 1;
			double y = Math.min(_yPivot, _yEnd) - 1;
			double w = Math.abs(_xEnd - _xPivot) + 2;
			double h = Math.abs(_yEnd - _xPivot) + 2;
			_gc.fillRect(x,  y, w, h);
			_xEnd = e.getX();
			_yEnd = e.getY();
			_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}
	
	// Handle mouse pressed (button down)
	private void onMouseRelease(MouseEvent e){
		if(_activeMouse) {
			_activeMouse = false;
			setCursor(Cursor.DEFAULT);
			_gc = null;
		}
	}
}
