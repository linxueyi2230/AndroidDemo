package com.e.library.callback;

import android.view.View;

/**
 * Created by lxy on 2016/5/9.
 */
public interface EListener<T>{
    void onInvoked(View view, T data, int position);
}
