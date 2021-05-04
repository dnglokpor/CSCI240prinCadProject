package csci240.prinCad.control;

import csci240.prinCad.util.Log;
import javafx.scene.input.KeyEvent;

/** KeyBoardControlTool class:
 * class that responds to keyboard input. 
 * @author dnglokpor
 */
public class KeyBoardControlTool extends CadTool {
	// default constructor is enough for now.
	
	/**
	 * respond to the shortcut received.
	 * @param ke the KeyEvent triggered.
	 * @param canvas the primary canvas to effect changes on.
	 */
	public void react(KeyEvent ke, CanvasToolInterface canvas) {
		switch(ke.getCode()) {
			case Z:
				canvas.getModel().undo();
				break;
			case Y:
				canvas.getModel().redo();
				break;
			default:
				Log.error("Undefined keyboard shortcut Ctrl+" + ke.getCode());
		}
		canvas.draw();
			
	}	
}
