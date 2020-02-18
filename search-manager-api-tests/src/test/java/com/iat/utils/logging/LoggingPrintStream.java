package com.iat.utils.logging;

import io.restassured.RestAssured;

import java.io.PrintStream;

import static com.iat.utils.logging.LoggingOutputStream.LogLevel.INFO;
import static org.slf4j.LoggerFactory.getLogger;

public class LoggingPrintStream extends PrintStream {

    public LoggingPrintStream() {
        super(new LoggingOutputStream(getLogger(RestAssured.class), INFO), true);
    }
}