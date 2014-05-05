import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

appender("FILE", FileAppender) {
	file = "star-catalog.log"
	append = true
	encoder(PatternLayoutEncoder) {
		pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n"
	}
}

appender("CONSOLE", ConsoleAppender) {
	append = true
	encoder(PatternLayoutEncoder) {
		pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
	}
}

root(INFO, ["CONSOLE", "FILE"])
