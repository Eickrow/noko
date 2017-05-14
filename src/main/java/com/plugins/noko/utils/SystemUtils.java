package com.plugins.noko.utils;

/**
 * Created by david.yun on 2017/5/14.
 */
public class SystemUtils {
    public static String getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("windows")) {
            os = "windows";
        } else if (os.startsWith("linux")) {
            os = "linux";
        } else {
            os = null;
        }
        return os;
    }
}
