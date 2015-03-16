package ba.bitcamp.lectures.logging.slf4j.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Test that shows how logback is used and configured through slf4j API. 
 * 
 * Note that it is not common to configure logging from code and that it is not common to assume logger implementation
 * in case slf4j is used. 
 * 
 * @author emir
 *
 */
public class Logback {
	private static final Logger LOG = LoggerFactory.getLogger(Logback.class);
	
	public static void main(String[] args) throws JoranException {
		/*
			<configuration>
				<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
					<encoder>
						<pattern>%-5level %msg%n</pattern>
					</encoder>
				</appender>
			
				<root level="debug">
					<appender-ref ref="STDOUT" />
				</root>
			</configuration>
		 */
		doTestLog();
		System.out.println();
		System.out.println("All messages logged");
		
		BasicConfigurator.configureDefaultContext();
		System.out.println("\n\nAfter additional configuration");
		doTestLog();
		System.out.println("Both default and basic appenders logged all messages");
		
		JoranConfigurator conf = new JoranConfigurator();
		conf.setContext((Context) LoggerFactory.getILoggerFactory());
		conf.doConfigure(Logback.class.getResourceAsStream("/logback-bitcamp.xml"));
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
