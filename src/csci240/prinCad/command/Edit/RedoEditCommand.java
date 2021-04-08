package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class RedoEditCommand extends CommandHandler {

	// constructor
	public RedoEditCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Redo Edit Event");
	}
}
