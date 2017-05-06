package com.plugins.noko.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by david.yun on 2017/5/6.
 */
public class FileUtils {
    static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static InputStream getResource(String filePath) {
        return FileUtils.class.getClassLoader().getResourceAsStream(filePath);
    }

    public static String readResource(String filePath) {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String result = null;
        try {
            inputStream = getResource(filePath);
            if (inputStream == null) {
                logger.error("file not found");
            } else {
                byte[] bytes = new byte[1024];
                int readSize = 0;
                byteArrayOutputStream = new ByteArrayOutputStream();
                while ((readSize = inputStream.read(bytes)) > 0) {
                    byteArrayOutputStream.write(bytes, 0, readSize);
                }
                result = byteArrayOutputStream.toString("UTF-8");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        return result;
    }
}