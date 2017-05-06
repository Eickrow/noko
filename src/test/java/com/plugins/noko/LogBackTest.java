package com.plugins.noko;

import ch.qos.logback.core.joran.spi.JoranException;
import com.plugins.noko.configuration.LogBackConfig;
import com.plugins.noko.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by david.yun on 2017/5/6.
 */
public class LogBackTest {
    static Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    static {
        try {
            LogBackConfig.loadConfig(FileUtils.getResource("configuration/logback.xml"), true);
        } catch (JoranException e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) throws JoranException {
        logger.trace("logback trace");
        logger.debug("logback debug");
        logger.info("logback info");
        logger.warn("logback warn");
        logger.error("logback error");
    }
}
