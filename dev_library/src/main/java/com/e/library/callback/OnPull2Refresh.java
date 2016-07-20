package com.e.library.callback;

import android.widget.BaseAdapter;

import java.util.List;
import java.util.Map;

/**
 * 下拉刷新所依赖接口
 * 
 * @author lxy
 * @date 2015-11-06 14:45
 * @version v1.0
 * @since v3.1
 */
public interface OnPull2Refresh<T> {

    /**
     * 在该函数设置预加载准备工作
     */
	public void onPreLoad();

    /**
     * 在该函数中设置adapter
     *
     * @param datas
     * @return
     */
	public BaseAdapter getAdapter(List<T> datas);

    /**
     * 在该函数中设置请求参数
     *
     * @param params
     */
	public void putParams(Map<String, String> params);

	/**
     * 该函数负责http请求
     *
	 * 由于ipiao后台json不规范，因此将http请求以及解析开放出，
	 * 让开发人员自己解析并判断
	 * fack
	 * @param params
     */
	public void doHttpRequest(Map<String, String> params);
}
