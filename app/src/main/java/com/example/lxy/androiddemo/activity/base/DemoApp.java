package com.example.lxy.androiddemo.activity.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by lxy on 2016/7/10.
 */
public class DemoApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();

    }

    public static Context getContext(){
        return mContext;
    }
}
