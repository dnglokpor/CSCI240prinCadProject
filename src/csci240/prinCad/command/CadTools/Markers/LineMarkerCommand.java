package csci240.prinCad.command.CadTools.Markers;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** LineMarkerCommand class:
 * handle the action of the Line option under the Marker menu.
 * @author dnglokpor
 *
 */
public class LineMarkerCommand extends CommandHandler {

	// constructor
	public LineMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Line");
		// TODO make the code
	}
}