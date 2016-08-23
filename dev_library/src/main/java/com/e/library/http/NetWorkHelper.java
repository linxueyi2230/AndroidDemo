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
public class NetWorkHelper {

    public static enum NetType {
        WIFI, CMNET, CMWAP, NONE_NET
    }

	private static String LOG_TAG = "NetWorkHelper";

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
			Log.w(LOG_TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].isAvailable()) {
						Log.d(LOG_TAG, "network is available");
						return true;
					}
				}
			}
		}
		Log.d(LOG_TAG, "network is not available");
		return false;
	}

	/**
	 * 判断网络是否为漫游
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(LOG_TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (tm != null && tm.isNetworkRoaming()) {
					Log.d(LOG_TAG, "network is roaming");
					return true;
				} else {
					Log.d(LOG_TAG, "network is not roaming");
				}
			} else {
				Log.d(LOG_TAG, "not using mobile network");
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
		APNManager apnManager=APNManager.getInstance(context);
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


    public static NetType getNetType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE_NET;
        }
        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE_NET;

    }


}
