package com.plugins.noko;

import com.plugins.noko.function.cmd.Cmd;
import com.plugins.noko.function.cmd.InputStreamHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by david.yun on 2017/5/14.
 */
public class CmdTest {
    static Logger logger = LoggerFactory.getLogger(Cmd.class);

    public static final String GetCPUID = "cpuid |grep 'processor serial number:'|sort -u |awk -F': ' '{print $2}'";

    @Test
    public void test() {
        Cmd cmd = new Cmd();
        InputStreamHandler inputStreamHandler = new InputStreamHandler() {
            @Override
            public void onSuccess(String content) {
                logger.info(content);
            }

            @Override
            public void onError(String content) {
                logger.error(content);
            }
        };
        cmd.exec(GetCPUID, inputStreamHandler);
    }
}
