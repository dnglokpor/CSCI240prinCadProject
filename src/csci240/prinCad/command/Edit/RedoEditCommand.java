package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/**RedoEditCommand class:
 * handle the action of the Redo option under the Edit menu.
 * @author dnglokpor
 */
public class RedoEditCommand extends CommandHandler {

	// constructor
	public RedoEditCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Undid stuff");
		getCanvas().getModel().redo();
		getCanvas().draw();
	}
}
