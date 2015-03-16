package ba.bitcamp.lectures.logging.jul;

import java.util.logging.Logger;

/**
 * Test that shows how handlers are inherited from parent and how it can be disabled.
 * 
 * @author emir
 *
 */
public class JavaLoggerHierarchy {
	private static final Logger TOP = Logger.getLogger("");
	private static final Logger BA = Logger.getLogger("ba");
	private static final Logger BA_BITCAMP = Logger.getLogger("ba.bitcamp");

	public static void main(String[] args) {
		doTestLog(TOP);
		doTestLog(BA);
		doTestLog(BA_BITCAMP);
		System.out.println("Only handler from root logger is invoked regardless on logger used");

		System.out.println("\n\nAfter adding new handler");
		TOP.addHandler(JavaLogger.newSimpleConsoleAppender("TOP Handler"));
		BA.addHandler(JavaLogger.newSimpleConsoleAppender("BA Handler"));
		doTestLog(BA_BITCAMP);
		System.out.println("2 from TOP (default and simple) and 1 from BA logger");

		System.out.println("\n\nAfter disabling propagetion");
		BA_BITCAMP.setUseParentHandlers(false);
		doTestLog(BA_BITCAMP);
		System.out.println("No logs since all handlers are parent handlers");
	}

	private static void doTestLog(Logger log) {
		System.out.println("Name: " + log.getName());
		System.out.println("Parent: " + log.getParent());
		System.out.println("Propagate: " + log.getUseParentHandlers());
		JavaLogger.doTestLog(log);
	}
}
