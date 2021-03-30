package csci240.prinCad.command.File;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

public class NewFileCommand extends CommandHandler {

	// constructor
	public NewFileCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("start new canvas image");
		getCanvas().freshStart();
	}
}
