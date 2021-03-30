package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.control.RectCadTool;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** RectMarkerCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class RectCadCommand extends CommandHandler {

	// constructor
	public RectCadCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Rect");
		this.getCanvas().setActiveTool(new RectCadTool(_canvas));
	}
}