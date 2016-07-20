package com.e.library.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

/**
 * Created by lxy on 2016/5/9.
 */
public class ELocalReceiverUtils {

    /**
     * 注册广播
     */
    public static void registerLocalReceiver(Context context,BroadcastReceiver receiver, String[] filters) {
        IntentFilter intentFilter = new IntentFilter();
        for (String filter : filters) {
            intentFilter.addAction(filter);
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, intentFilter);
    }

    /**
     * 反注册广播
     *
     * @param receiver receiver
     */
    public static void unregisterLocalReceiver(Context context,BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }


    /**
     * 发送广播（不有内容）
     * @param action
     */
    public static void sendLocalBoradCast(Context context,String action){
        sendLocalBoradcast(context,action,null);
    }

    /**
     * 发送广播（含有内容）
     * @param action
     * @param bundle 内容
     */
    public static void sendLocalBoradcast(Context context,String action, Bundle bundle){
        if (TextUtils.isEmpty(action)){
            return;
        }
        Intent intent = new Intent(action);
        if (bundle !=null){
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
