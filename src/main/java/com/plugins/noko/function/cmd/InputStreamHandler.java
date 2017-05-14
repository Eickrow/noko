package com.plugins.noko.function.cmd;

/**
 * Created by david.yun on 2017/5/14.
 */
public interface InputStreamHandler {
    void onSuccess(String content);

    void onError(String content);
}
