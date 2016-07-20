package com.e.library.http;

import java.util.Map;

/**
 * Created by lxy on 2016/5/9.
 */
public interface ERequest {

    void doPost(String url, Map<String,String> params,ERequestListener listener);
    void doPost(String url, Map<String,String> params, ERequestListener listener, Object tag);

    void doGet(String url, Map<String,String> params,ERequestListener listener);
    void doGet(String url, Map<String,String> params, ERequestListener listener, Object tag);
}
