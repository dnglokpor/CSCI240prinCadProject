package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** DelEditCommand class:
 * handle the action of the button that deletes graphics tokens.
 * @author dnglokpor
 */
public class DelEditCommand extends CommandHandler {

	// constructor
	public DelEditCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Deleted stuff");
		getCanvas().getModel().delete();
		getCanvas().draw();
	}
}
