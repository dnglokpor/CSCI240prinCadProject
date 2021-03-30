package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.control.BoxMarkerTool;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** UndoEditCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class BoxMarkerCommand extends CommandHandler {

	// constructor
	public BoxMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw []");
		// instantiate tool
		this.getCanvas().setActiveTool(new BoxMarkerTool(_canvas));
	}
}