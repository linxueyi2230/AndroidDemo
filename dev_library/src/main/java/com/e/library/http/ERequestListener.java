package com.e.library.http;

/**
 * Created by lxy on 2016/5/10.
 */
public abstract class ERequestListener {

    public void onStart(){};

    public void onLoading(long count,long current){};

    public abstract <R extends EResponse> void onFinish(R response);

    public void onFailure(Throwable t,int errorNo ,String strMsg){};
}
