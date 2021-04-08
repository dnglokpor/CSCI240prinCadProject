package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.PlusMarkerTool;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** PlusMarkerCommand class:
 * handle the action of the Undo option under the Edit menu.
 * @author dnglokpor
 *
 */
public class PlusMarkerCommand extends CommandHandler {

	// constructor
	public PlusMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw +");
		// instantiate tool
		this.getCanvas().setActiveTool(new PlusMarkerTool());
	}
}