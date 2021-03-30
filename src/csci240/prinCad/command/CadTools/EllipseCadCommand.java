package csci240.prinCad.command.CadTools;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.control.EllipseCadTool;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

/** EllipseCadCommand class:
 * handle the action of the Ellipse Cad button.
 * @author dnglokpor
 */
public class EllipseCadCommand extends CommandHandler {

	// constructor
	public EllipseCadCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Draw Ellipse");
		this.getCanvas().setActiveTool(new EllipseCadTool(_canvas));
	}
}