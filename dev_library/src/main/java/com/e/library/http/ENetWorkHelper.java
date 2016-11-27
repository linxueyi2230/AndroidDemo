package com.e.library.http;

import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 检测网络工具类
 * @author lxy
 * @date 2015-09-21 22:02
 * @version v1.0
 * @since v1.0
 *
 */
public class ENetWorkHelper {

    public static enum NetType {
        NET_NONE, NET_2G, NET_3G,NET_4G,NET_WIFI, NET_UNKNOWN
    }

    private static String TAG = "NetWorkHelper";

    public static Uri uri = Uri.parse("content://telephony/carriers");

    /**
     * 判断是否有网络连接
     * @param context
     * @return return true表示已联网，否则return false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            Log.w(TAG, "couldn't get connectivity manager");
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].isAvailable()) {
                        Log.d(TAG, "network is available");
                        return true;
                    }
                }
            }
        }
        Log.d(TAG, "network is not available");
        return false;
    }

    /**
     * 判断网络是否为漫游
     */
    public static boolean isNetworkRoaming(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.w(TAG, "couldn't get connectivity manager");
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null
                    && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null && tm.isNetworkRoaming()) {
                    Log.d(TAG, "network is roaming");
                    return true;
                } else {
                    Log.d(TAG, "network is not roaming");
                }
            } else {
                Log.d(TAG, "not using mobile network");
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable(Context context) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;

        isMobileDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        return isMobileDataEnable;
    }


    /**
     * 判断wifi 是否可用
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable(Context context) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }

    /**
     * 设置Mobile网络开关
     *
     * @param context
     * @param enabled
     * @throws Exception
     */
    public static void setMobileDataEnabled(Context context, boolean enabled)
            throws Exception {
        APNManager apnManager= APNManager.getInstance(context);
        List<APN> list = apnManager.getAPNList();
        if (enabled) {
            for (APN apn : list) {
                ContentValues cv = new ContentValues();
                cv.put("apn", apnManager.matchAPN(apn.apn));
                cv.put("type", apnManager.matchAPN(apn.type));
                context.getContentResolver().update(uri, cv, "_id=?",
                        new String[] { apn.apnId });
            }
        } else {
            for (APN apn : list) {
                ContentValues cv = new ContentValues();
                cv.put("apn", apnManager.matchAPN(apn.apn) + "mdev");
                cv.put("type", apnManager.matchAPN(apn.type) + "mdev");
                context.getContentResolver().update(uri, cv, "_id=?",
                        new String[] { apn.apnId });
            }
        }
    }

    public static NetType networkType(Context context) {

        NetType net = NetType.NET_NONE;
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                net = NetType.NET_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                Log.e(TAG, "Network getSubtypeName : " + _strSubTypeName);

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        net = NetType.NET_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        net = NetType.NET_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        net = NetType.NET_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            net = NetType.NET_3G;
                        }else {
                            net = NetType.NET_UNKNOWN;
                        }

                        break;
                }

                Log.e(TAG, "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }


        return net;
    }


}
