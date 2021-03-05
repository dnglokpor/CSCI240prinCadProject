package csci240.prinCad.command.File;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

public class OpenFileCommand extends CommandHandler {

	// constructor
	public OpenFileCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Open File Event");
	}
}

