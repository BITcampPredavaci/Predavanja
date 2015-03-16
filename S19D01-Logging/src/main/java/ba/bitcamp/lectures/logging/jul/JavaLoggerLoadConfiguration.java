package ba.bitcamp.lectures.logging.jul;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Test that shows how configuration can be loaded from file.
 * @author emir
 *
 */
public class JavaLoggerLoadConfiguration {
	private static final Logger TOP = Logger.getLogger("");
	private static final Logger BA = Logger.getLogger("ba");
	private static final Logger BA_BITCAMP = Logger.getLogger("ba.bitcamp");

	public static void main(String[] args) throws SecurityException, IOException {
		System.out.println("Default config");
		logTest(TOP);
		logTest(BA);
		logTest(BA_BITCAMP);
		
		/*
		 	handlers = java.util.logging.ConsoleHandler
			.level = SEVERE
			
			ba.handlers = java.util.logging.ConsoleHandler
			ba.level = WARNING
			
			ba.bitcamp.handlers = java.util.logging.ConsoleHandler
			ba.bitcamp.level = INFO
		 */
		LogManager.getLogManager().readConfiguration(JavaLoggerLoadConfiguration.class.getResourceAsStream("/java-logger.properties"));
		System.out.println("\n\nAfter loading custom configuration");
		logTest(TOP);
		logTest(BA);
		logTest(BA_BITCAMP);
	}

	private static void logTest(Logger log) {
		JavaLogger.printLevels(log);
		JavaLogger.doTestLog(log);
	}
}
