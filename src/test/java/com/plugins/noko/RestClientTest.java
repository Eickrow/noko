package com.plugins.noko;

import ch.qos.logback.core.joran.spi.JoranException;
import com.plugins.noko.configuration.LogBackConfig;
import com.plugins.noko.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import static com.plugins.noko.configuration.RestTemplateConfig.RestClient;

/**
 * Created by david.yun on 2017/5/6.
 */
public class RestClientTest {
    static Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    static {
        try {
            LogBackConfig.loadConfig(FileUtils.getResource("configuration/logback.xml"), false);
        } catch (JoranException e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> formEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = RestClient.exchange("https://www.baidu.com", HttpMethod.GET, formEntity, String.class);
        logger.info(responseEntity.getStatusCode().getReasonPhrase() + responseEntity.getStatusCodeValue());
        logger.info(responseEntity.getBody());
    }
}
