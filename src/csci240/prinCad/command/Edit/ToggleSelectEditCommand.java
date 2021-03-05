package csci240.prinCad.command.Edit;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

public class ToggleSelectEditCommand extends CommandHandler {

	// constructor
	public ToggleSelectEditCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		// DEBUG Log.info("Handle Toggle Selection Edit Event");
		_canvas.changeSelection();
	}
}
