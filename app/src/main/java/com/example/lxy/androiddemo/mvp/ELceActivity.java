package com.example.lxy.androiddemo.mvp;

import android.os.Bundle;
import android.widget.Toast;

import com.example.lxy.androiddemo.dialog.Loading;
import com.example.lxy.androiddemo.mvp.presenter.ELcePresenter;
import com.example.lxy.androiddemo.mvp.view.ELceView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;

/**
 *
 * MVP模式
 * @param <M>
 * @param <V>
 * @param <P>
 *
 * Created by lxy on 2016/7/25 22:45.
 */
public abstract class ELceActivity<M,V extends ELceView<M>,P extends ELcePresenter<V,M>> extends MvpActivity<V,P> implements ELceView<M>{

    private Loading mLoading;

    protected abstract int getLayoutId();
    protected abstract void onInitedView(Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onInitedView(savedInstanceState);
    }

    private void initLoading(){
        mLoading = new Loading(this);
    }

    @Override
    public void showLoading() {
        if (mLoading !=null && !mLoading.isShowing()){
            mLoading.show();
        }
    }

    @Override
    public void hideLoading() {
        if (isFinishing()){
            return;
        }
        if (mLoading !=null && mLoading.isShowing()){
            mLoading.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        showToast(error);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int toastRes) {
        String toast = getString(toastRes);
        showToast(toast);
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        mLoading = null;
        super.onDestroy();
    }
}
