package com.example.lxy.androiddemo.model;

import android.text.TextUtils;

import java.util.Map;

/**
 * Created by lxy on 2016/6/27.
 */
public class AppModel {

    private static AppModel instance;

    public static AppModel getInstance(){
        if (instance == null){
            synchronized (AppModel.class){
                if (instance == null){
                    instance = new AppModel();
                }
            }
        }
        return instance;
    }
    private AppModel() {
    }

    public void doHttp(String url, Map<String,String> params, OnRequestListener listener){

    }

    public interface OnRequestListener{
        void onFinish(String response);
    }
}
