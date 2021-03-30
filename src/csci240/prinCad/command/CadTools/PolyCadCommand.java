package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.control.PolyCadTool;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** PolyCadCommand class:
 * handle the action of the Polyline Cad button.
 * @author dnglokpor
 */
public class PolyCadCommand extends CommandHandler {

	// constructor
	public PolyCadCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Poly");
		this.getCanvas().setActiveTool(new PolyCadTool(_canvas));
	}
}