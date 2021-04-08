package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class DelEditCommand extends CommandHandler {

	// constructor
	public DelEditCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Delete Edit Event");
	}
}
