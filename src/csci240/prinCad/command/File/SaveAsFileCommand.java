package csci240.prinCad.command.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.ui.Log;
import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/** SaveAsFileCommand class:
 * handle the action of saving the current canvas image to a new file.
 * @author dnglokpor
 *
 */
public class SaveAsFileCommand extends CommandHandler {

	// constructor
	public SaveAsFileCommand(PrinCanvas canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		try {
			Window stage = getCanvas().getScene().getWindow();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save as");
			File old = getCanvas().getOpenedModel();
			if(old != null) {// if a model file if known
				fileChooser.setInitialDirectory(old.getParentFile());
			}
			// set initial file name
			fileChooser.setInitialFileName("image.psav");
			File file = fileChooser.showSaveDialog(stage);
			if(file.exists()) { // model already exists
				File dest;
				do{ // create a proxy ending in ".bak"
					dest = new File(file.getAbsolutePath() + ".bak");
				}while(dest.exists());
				// rename existing to this
				file.renameTo(dest);
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);
			getCanvas().saveToFile(out);
			// record save file
			getCanvas().recordOpenedFile(file);
			out.flush();
			out.close();
			Log.info("saved canvas image as " + file.getName());
		}catch(Exception ex) {
			Log.error("SAVE AS FAILED", ex);
		}
	}
}

