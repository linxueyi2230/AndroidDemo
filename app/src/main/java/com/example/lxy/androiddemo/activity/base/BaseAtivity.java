package com.example.lxy.androiddemo.activity.base;

import android.os.Bundle;

import com.e.library.activity.EBaseActivity;
import com.example.lxy.androiddemo.dialog.Loading;

import butterknife.ButterKnife;

/**
 * Created by lxy on 2016/7/6.
 */
public abstract class BaseAtivity extends EBaseActivity {

    private Loading mLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onInitView(savedInstanceState);
        OnInitListener();
        onInitData();
    }

    @Override
    protected void OnInitListener() {
    }

    private void initLoading(){
        mLoading = new Loading(this);
    }

    public void showLoading(){
        if (mLoading !=null && !mLoading.isShowing()){
            mLoading.show();
        }
    }

    public void hideLoading(){
        if (isFinishing()){
            return;
        }
        if (mLoading !=null && mLoading.isShowing()){
            mLoading.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        mLoading = null;
        super.onDestroy();
    }
}
