package ba.bitcamp.lectures.logging.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Test that shows how log4j is used and configured.
 * 
 * Note that it is not common to configure it programmatically.
 * 
 * @author emir
 *
 */
public class Log4jTest {
	private static final Logger LOG = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		/*	by default log4j.properties is loaded from classpath
		 
			log4j.rootLogger=DEBUG, STDOUT
			log4j.appender.STDOUT=org.apache.log4j.ConsoleAppender
			log4j.appender.STDOUT.layout=org.apache.log4j.PatternLayout
			log4j.appender.STDOUT.layout.ConversionPattern=%d [%t] %-5p %c - %m%n
		 */
		doTestLog();
		System.out.println();
		System.out.println("All messages logged");
		
		System.out.println("\n\nAfter additional configuration");
		BasicConfigurator.configure();
		doTestLog();
		System.out.println("Both default and basic appenders logged all messages");
		
		
		/*
			log4j.appender.BITCAMP=org.apache.log4j.ConsoleAppender
			log4j.appender.BITCAMP.layout=org.apache.log4j.PatternLayout
			log4j.appender.BITCAMP.layout.ConversionPattern=%-5p %m%n
			
			
			log4j.logger.ba.bitcamp=WARN, BITCAMP
		 */
		PropertyConfigurator.configure(Log4jTest.class.getResourceAsStream("/log4j-bitcamp.properties"));
		System.out.println("\n\nAfter loading custom properties");
		doTestLog();
		System.out.println("Info message not logged, other logged by all appenders");
		
	}
	
	private static void doTestLog() {
		if (LOG.isInfoEnabled()) {
			LOG.info("Info message!!!");
		}
		
		LOG.warn("Warn message!!!");
		LOG.error("Error message!!!");
	}
}
