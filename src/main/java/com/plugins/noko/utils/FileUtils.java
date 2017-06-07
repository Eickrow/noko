package com.plugins.noko.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static void writeFile(String filePath, String data, boolean isAppened) {
        File file = new File(filePath);
        writeFile(file, data, isAppened);
    }

    public static void writeFile(File file, String data, boolean isAppened) {
        PrintWriter printWriter = null;
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileWriter = new FileWriter(file, isAppened);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.write(data);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static List<String> read(String filePath, String charset) {
        List<String> results = new ArrayList<>();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream, charset);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
