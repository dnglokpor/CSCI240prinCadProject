package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PropertiesEditCommand extends CommandHandler {

	// constructor
	public PropertiesEditCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Properties Edit Event");
	}
}
