package com.example.lxy.androiddemo.http.volley;

import com.example.lxy.androiddemo.http.Response;

public interface RequestListener {

    /**
     * 在请求之前调用的方法
     */
    public  void onPreRequest();
    
    /**
     * 请求结束后调用
     */
    public void onEndRequst();

    /**
     * 请求成功调用
     * @param response
     */
    public  void onRequestSuccess(Response response);

    /**
     * 请求失败调用，致命错误
     * @param code
     * @param msg
     */
    public  void onRequestError(int code, String msg);

    /**
     * 服务器返回失败调用
     * @param code
     * @param msg
     */
    public  void onRequestFail(int code, String msg);
}
