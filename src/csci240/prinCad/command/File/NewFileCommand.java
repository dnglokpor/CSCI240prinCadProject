package csci240.prinCad.command.File;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class NewFileCommand extends CommandHandler {

	// constructor
	public NewFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("start new canvas image");
		getCanvas().getModel().clear();
		getCanvas().draw();
	}
}
