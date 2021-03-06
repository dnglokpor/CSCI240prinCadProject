package csci240.prinCad.command.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

/** SaveFileCommand class:
 * handle the action of saving a canvas image. if the image was
 * never saved, then prompt the user for output file. if the image
 * was loaded from an existing save file, overwrite the save file.
 * in case of saving to new file, if the file name already exists,
 * changes the name to name.bak.
 * @author dnglokpor
 *
 */
public class SaveFileCommand extends CommandHandler {

	// constructor
	public SaveFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		try {
			if(getCanvas().getModel().hasModelFile()) {
				// model file exists
				File file = getCanvas().getModel().getModelFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw, true);
				
				getCanvas().getModel().save(out);
				out.flush();
				out.close();
				Log.info("saved canvas image to " + file.getName());
			}else { // calls save as
				new SaveAsFileCommand(getCanvas()).action(e);
			}
		}catch(Exception ex) {
			Log.error("SAVE FAILED", ex);
		}
	}
}

