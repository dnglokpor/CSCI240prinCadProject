package csci240.prinCad.command;

import javafx.event.ActionEvent;

/** CommandHandler abstract class:
 * class that any MenuItem from the UI menus will inherit to be quickly built.
 * streamline their action so that any of them can be referred to by a CommandHandler
 * reference.
 * @author dnglokpor
 *
 */
public abstract class CommandHandler {
	// attributes
	protected CanvasCommandInterface _canvas;
	
	/** constructor:
	 *	sets the _canvas attribute to the passed CanvasCommandInterface instance being drawn on.
	 * @param canvas the CanvasCommandInterface on which to manage commands.
	 */
	public CommandHandler(CanvasCommandInterface canvas) {
		_canvas = canvas;
	}
	
	// getter
	/**
	 * @return the current _canvas attribute. 
	 */
	public final CanvasCommandInterface getCanvas() { return _canvas; }
	
	// others
	/**
	 * must be overridden by each classes to properly handle the commands.
	 * @param e
	 */
	public abstract void action(ActionEvent e);
	
}
