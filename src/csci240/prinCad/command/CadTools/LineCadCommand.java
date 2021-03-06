package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.LineCadTool;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** LineMarkerCommand class:
 * handle the action of the Line option under the Marker menu.
 * @author dnglokpor
 *
 */
public class LineCadCommand extends CommandHandler {

	// constructor
	public LineCadCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Line");
		this.getCanvas().setActiveTool(new LineCadTool());
	}
}