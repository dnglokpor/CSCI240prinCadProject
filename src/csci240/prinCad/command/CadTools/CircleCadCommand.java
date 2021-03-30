package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.control.CircleCadTool;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** CircleMarkerCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class CircleCadCommand extends CommandHandler {

	// constructor
	public CircleCadCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Circle");
		this.getCanvas().setActiveTool(new CircleCadTool(_canvas));
	}
}