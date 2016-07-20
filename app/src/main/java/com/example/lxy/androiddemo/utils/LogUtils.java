package com.example.lxy.androiddemo.utils;


import android.util.Log;

import com.example.lxy.androiddemo.BuildConfig;

/**
 * debug日志
 * 
 * @author lxy
 * @date 2015-07-14 11:36
 *
 */
public class LogUtils {
	// DEBUG MODE OR NOT
	public static boolean DEBUG = BuildConfig.DEBUG;


	public static void i(String tag, String msg) {
		if (DEBUG)
			Log.i(tag, msg);
	}

	public static void i(Object obj, String msg) {
		if (obj == null)
			return;
		i(obj.getClass().getSimpleName(), msg);
	}

	public static void d(String tag, String msg) {
		if (DEBUG)
			Log.d(tag, msg);
	}

	public static void d(Object obj, String msg) {
		if (obj == null)
			return;
		d(obj.getClass().getSimpleName(), msg);
	}

	public static void e(String tag, String msg) {
		if (DEBUG)
			Log.e(tag, msg);
	}

	public static void e(Object obj, String msg) {
		if (obj == null)
			return;
		e(obj.getClass().getSimpleName(), msg);
	}

	public static void w(String tag, String msg) {
		if (DEBUG)
			Log.w(tag, msg);
	}

	public static void w(Object obj, String msg) {
		if (obj == null)
			return;
		w(obj.getClass().getSimpleName(), msg);
	}
}
