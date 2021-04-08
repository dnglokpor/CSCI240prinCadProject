package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.RectCadTool;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** RectMarkerCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class RectCadCommand extends CommandHandler {

	// constructor
	public RectCadCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Rect");
		this.getCanvas().setActiveTool(new RectCadTool());
	}
}