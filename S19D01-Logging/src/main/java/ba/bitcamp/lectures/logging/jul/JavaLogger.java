package ba.bitcamp.lectures.logging.jul;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Utility class containing log test method (invoke info, warning and debug on provided logger), plus some helper methods
 * like factory method for simple console logger, and printing level hierarchy.
 * 
 * @author emir
 *
 */
public class JavaLogger {
	
	public static void doTestLog(Logger log) {
		if (log.isLoggable(Level.INFO)) {
			log.info(log.getName() + "::: Info message!!!");
			log.info(log.getName() + "::: Tested first because likely it is not enabled " + "and there are some concatination");
		}
		log.warning(log.getName() + "::: Warning message!!!!");
		log.severe(log.getName() + "::: Severe message!!!!");
		log.log(Level.SEVERE, "{0} Parameterized severe!!!!", log.getName());
	}
	
	public static ConsoleHandler newSimpleConsoleAppender(final String handlerName) {
		ConsoleHandler simple = new ConsoleHandler();
		simple.setFormatter(new Formatter() {
			
			@Override
			public String format(LogRecord record) {
				return handlerName + ": " + record.getLevel() + ":" + formatMessage(record) + "\n";
			}
		});
		return simple;
	}

	public static void printLevels(Logger log) {
		Logger parent = log;
		while (parent != null) {
			System.out.println(parent.getName() + " level: " + parent.getLevel());
			parent = parent.getParent();
		}
	}
}
