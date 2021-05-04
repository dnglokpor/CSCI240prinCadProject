package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** UndoEditCommand class:
 * handle the action of the Undo option under the Edit menu.
 * @author dnglokpor
 *
 */
public class UndoEditCommand extends CommandHandler {

	// constructor
	public UndoEditCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Undid stuff");
		getCanvas().getModel().undo();
		getCanvas().draw();
	}
}
