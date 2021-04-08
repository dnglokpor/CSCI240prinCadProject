package csci240.prinCad.command.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import csci240.prinCad.command.CommandHandler;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenFileCommand extends CommandHandler {

	// constructor
	public OpenFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}
	
	// handle action event
	@Override
	public void action(ActionEvent e) {
		try {
			Window stage = getCanvas().getScene().getWindow();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Model");
			File old = getCanvas().getOpenedModel();
			if(old != null) {// if a model file if known
				fileChooser.setInitialDirectory(old.getParentFile());
			}
			File file = fileChooser.showOpenDialog(stage);
			
			if(file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				// load data
				getCanvas().loadFromFile(reader);
				// record save file
				getCanvas().recordOpenedFile(file);
				reader.close();
				fr.close();
				Log.info("loaded canvas image from: " + file.getName());
			}
		}catch(Exception ex) {
			Log.error("FAILED TO LOAD: ", ex);
		}
	}
}

