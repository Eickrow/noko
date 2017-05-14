package com.plugins.noko.function.cmd;

import com.plugins.noko.utils.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by david.yun on 2017/5/14.
 */
public class Cmd {
    Logger logger = LoggerFactory.getLogger(Cmd.class);

    public void exec(String command, InputStreamHandler inputStreamHandler) {
        String os = SystemUtils.getOS();
        if (os == null) {
            return;
        }
        String[] order = new String[3];
        if ("windows".equals(os)) {
            order[0] = "cmd.exe";
            order[1] = "/c";
        } else {
            order[0] = "/bin/sh";
            order[1] = "-c";
        }
        order[2] = command;
        exec(order, inputStreamHandler);
    }

    private void exec(String[] command, InputStreamHandler inputStreamHandler) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            new Thread(inputStreamHandler(process.getErrorStream(), inputStreamHandler, "error")).start();
            inputStreamHandler(process.getInputStream(), inputStreamHandler, null).run();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private Runnable inputStreamHandler(InputStream inputStream, InputStreamHandler inputStreamHandler, String type) {
        return () -> {
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                inputStreamReader = new InputStreamReader(inputStream, System.getProperty("sun.jnu.encoding"));
                bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if ("error".equals(type)) {
                        inputStreamHandler.onError(line);
                    } else {
                        inputStreamHandler.onSuccess(line);
                    }
                }
            } catch (IOException e) {
                logger.error(e.toString());
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        };
    }
}
