package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.EllipseCadTool;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** EllipseCadCommand class:
 * handle the action of the Ellipse Cad button.
 * @author dnglokpor
 */
public class EllipseCadCommand extends CommandHandler {

	// constructor
	public EllipseCadCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Ellipse");
		this.getCanvas().setActiveTool(new EllipseCadTool());
	}
}