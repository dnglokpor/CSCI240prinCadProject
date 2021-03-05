package csci240.prinCad.command.CadTools.Markers;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** PlusMarkerCommand class:
 * handle the action of the Undo option under the Edit menu.
 * @author dnglokpor
 *
 */
public class PlusMarkerCommand extends CommandHandler {

	// constructor
	public PlusMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw +");
		// TODO make the code
	}
}