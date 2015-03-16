package ba.bitcamp.lectures.logging.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test that shows how levels are propagated to child loggers and how threshold is checked only on lowest possible level.
 * 
 * @author emir
 *
 */
public class JavaLoggerLevelPropagation {
	private static final Logger TOP = Logger.getLogger("");
	private static final Logger BA = Logger.getLogger("ba");
	private static final Logger BA_BITCAMP = Logger.getLogger("ba.bitcamp");

	public static void main(String[] args) {
		// remove default handler
		TOP.removeHandler(TOP.getHandlers()[0]);
		TOP.addHandler(JavaLogger.newSimpleConsoleAppender("TOP Handler"));
		BA.addHandler(JavaLogger.newSimpleConsoleAppender("BA Handler"));
		BA_BITCAMP.addHandler(JavaLogger.newSimpleConsoleAppender("BITCAMP Handler"));
		
		System.out.println("Dafault level conf");
		doTestLog(BA_BITCAMP);
		System.out.println("All handlers logged all messages");

		System.out.println("\n\nAfter setting SEVERE to TOP");
		TOP.setLevel(Level.SEVERE);
		doTestLog(BA_BITCAMP);
		System.out.println("Only severe messages are logged");
		
		System.out.println("\n\nAfter setting INFO to BITCAMP");
		BA_BITCAMP.setLevel(Level.INFO);
		doTestLog(BA_BITCAMP);
		System.out.println("All handler logged all messages");
	}

	private static void doTestLog(Logger log) {
		JavaLogger.printLevels(log);
		JavaLogger.doTestLog(log);
	}
}
