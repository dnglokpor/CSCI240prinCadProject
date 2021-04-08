package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.XMarkerTool;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** XMarkerCommand class:
 * handle the action of the Box option under the Marker menu.
 * @author dnglokpor
 *
 */
public class XMarkerCommand extends CommandHandler {

	// constructor
	public XMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw X");
		this.getCanvas().setActiveTool(new XMarkerTool());
	}
}