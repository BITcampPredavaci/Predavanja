package ba.bitcamp.lectures.logging.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test that shows how logger can be configured programmatically.
 * 
 * @author emir
 *
 */
public class JavaLoggerReconfiguration {
	private static final Logger LOG = Logger.getLogger(JavaLoggerReconfiguration.class.getName());
	
	public static void main(String[] args) {
		System.out.println("Default level");
		JavaLogger.doTestLog(LOG);
		
		LOG.setLevel(Level.SEVERE);
		System.out.println("After setting to SEVERE");
		JavaLogger.doTestLog(LOG);
		
		LOG.setLevel(Level.ALL);
		System.out.println("After setting to FINE");
		JavaLogger.doTestLog(LOG);
		
		LOG.addHandler(JavaLogger.newSimpleConsoleAppender("Simple"));
		System.out.println("After adding simple handler");
		JavaLogger.doTestLog(LOG);
	}
}
