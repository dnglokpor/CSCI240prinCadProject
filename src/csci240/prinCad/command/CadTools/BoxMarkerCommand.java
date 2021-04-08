package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.control.BoxMarkerTool;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** UndoEditCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class BoxMarkerCommand extends CommandHandler {

	// constructor
	public BoxMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw []");
		// instantiate tool
		this.getCanvas().setActiveTool(new BoxMarkerTool());
	}
}