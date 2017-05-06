package com.plugins.noko.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by david.yun on 2017/5/6.
 */
public class LogBackConfig {
    public static void loadConfig(InputStream configFileInputStream, boolean showLoadStatus) throws JoranException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(loggerContext);
        loggerContext.reset();
        configurator.doConfigure(configFileInputStream);
        if (showLoadStatus)
            StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    }
}
