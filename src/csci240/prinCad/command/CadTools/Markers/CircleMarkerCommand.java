package csci240.prinCad.command.CadTools.Markers;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** CircleMarkerCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class CircleMarkerCommand extends CommandHandler {

	// constructor
	public CircleMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Circle");
		// TODO make the code
	}
}