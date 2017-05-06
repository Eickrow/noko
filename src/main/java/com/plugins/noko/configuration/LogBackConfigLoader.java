package com.plugins.noko.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

/**
 * Created by david.yun on 2017/5/6.
 */
public class LogBackConfigLoader {
    public static void load(String configFileLocation, boolean showLoadStatus) throws JoranException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(loggerContext);
        loggerContext.reset();
        configurator.doConfigure(configFileLocation);
        if (showLoadStatus)
            StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    }
}
