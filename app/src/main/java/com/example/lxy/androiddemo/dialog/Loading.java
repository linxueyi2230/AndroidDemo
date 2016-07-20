package com.example.lxy.androiddemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.lxy.androiddemo.R;

/**
 * Created by lxy on 2016/7/15.
 */
public class Loading extends Dialog {
    public Loading(Context context) {
        super(context, com.e.library.R.style.style_dialog);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
        setContentView(view);
    }
}
