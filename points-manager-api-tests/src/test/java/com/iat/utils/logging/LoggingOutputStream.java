package com.iat.utils.logging;

import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class LoggingOutputStream extends OutputStream {

    private final Logger logger;
    private final LogLevel level;
    private ByteArrayOutputStream baos;

    public enum LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR,
    }

    public LoggingOutputStream(Logger logger, LogLevel level) {
        this.logger = logger;
        this.level = level;
        this.baos = new ByteArrayOutputStream();
    }

    @Override
    public void write(int b) {
        baos.write(b);
    }

    @Override
    public void flush() {
        String value = this.baos.toString().trim();
        this.baos = new ByteArrayOutputStream();
        if (value.isEmpty()) return;

        switch (level) {
            case TRACE:
                logger.trace(value);
                break;
            case DEBUG:
                logger.debug(value);
                break;
            case ERROR:
                logger.error(value);
                break;
            case INFO:
                logger.info(value);
                break;
            case WARN:
                logger.warn(value);
                break;
        }
    }
}