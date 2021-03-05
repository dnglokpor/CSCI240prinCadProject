package csci240.prinCad.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Log class:
 * class that handle all logging operations of the application. uses singleton design
 * pattern to ensure that only one instance at most exists at all time. implement
 * writing to the log file by respecting user specified verbose level.
 * @author dnglokpor
 *
 */
public class Log {
	/** Logging interface:
	 * base interface for logging operations. all classes that control logging
	 * operations under the verbose level must implement this so they are all
	 * streamlined.
	 * @author dnglokpor
	 *
	 */
	private interface Logging{
		
		/**
		 * logs errors in the log file. entries are time stamped.
		 */
		public void error(String errorText);
		
		/**
		 * logs exceptions in the log file. entries are time stamped.
		 */
		public void error(String text, Exception ex);
		
		/**
		 * logs informations in the log file. entries are time stamped.
		 */
		public void info(String infoText);
	}
	
	/** LogNone class:
	 *	this class handle logging operations under the None verbose level.
	 * @author dnglokpor
	 *
	 */
	private class LogNone implements Logging{
		 // writer
		public void error(String errorText) {
			
		}
		
		// error logger
		public void error(String text, Exception ex) {
			
		}
		
		// info logger
		public void info(String infoText) {
			
		}
		
	}
	
	private class LogError implements Logging{
		// writer
		public void error(String errorText) {
			write(errorText);
		}
		
		// error logger
		public void error(String text, Exception ex) {
			String errorText = String.format("%s - %s", text, ex.toString());
			error(errorText); // add error level and save it
		}
		
		// info logger
		public void info(String infoText) {
			
		}
		
	}
	
	private class LogInfo implements Logging{
		// writer
		public void error(String errorText) {
			write(errorText);
		}
		
		// error logger
		public void error(String text, Exception ex) {
			String errorText = String.format("%s - %s", text, ex.toString());
			error(errorText); // add error level and save it
		}
		
		// info logger
		public void info(String infoText) {
			write(infoText);
		}
		
	}
	
	
	/** LoggingLevel enum:
	 * gives three values of desired verbose level for logging operations:
	 * - None: no logging at all
	 * - Error: Errors and Exceptions only
	 * - Info: logs everything.
	 * @author dnglokpor
	 *
	 */
	public enum LoggingLevel{
		None, Error, Information
	}
	
	// Log object attributes
	private static Log _instance = new Log(); // singleton instance of log object
	Logging _logging; // factory design requirement
	final static String LogFilePath = "PrinCad.log"; // path to file
	final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // create formatter
	
	/** constructor:
	 * instantiate the right logger depending on the global user defined verbose
	 * level. (factory design).
	 */
	private Log() {
		LoggingLevel loggingLevel = MainForm.cadSettings.getLoggingLevel();
		
		// loggers factory
		switch(loggingLevel) {
			case None:
				_logging = new LogNone();
				break;
			case Error:
				_logging = new LogError();
				break;
			case Information:
				_logging = new LogInfo();
				break;
		}
	}
	
	/**
	 * calls in the right error logging method for the level of verbose.
	 * @param errorText error message returned by the VM.
	 */
	public static void error(String errorText) {
		_instance._logging.error(errorText);
	}
	
	/**
	 * converts the passed error exception and messages to a String and call
	 * the logger method on it.
	 * @param text error message returned by the VM.
	 * @param ex the exception triggered.
	 */
	public static void error(String text, Exception ex) {
		_instance._logging.error(text, ex);
	}
	
	/**
	 * calls in the right information logging method for the level of verbose.
	 * @param infoText information message returned by the program.
	 */
	public static void info(String infoText) {
		_instance._logging.info(infoText);
	}
	
	/**
	 * open a file stream to the log file and write the desired log messages to it.
	 * @param text the contents of the error/info message.
	 */
	private static void write(String text) {
		try {
			File file = new File(LogFilePath);
			FileWriter fw = new FileWriter(file, true); // append file
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);
			
			// local date time instance
			LocalDateTime localDateTime = LocalDateTime.now();
			
			// get formatted string
			String ldtString = dateTimeFormatter.format(localDateTime);
			
			out.println(String.format("%s - %s", ldtString, text));
			
			out.flush();
			out.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
