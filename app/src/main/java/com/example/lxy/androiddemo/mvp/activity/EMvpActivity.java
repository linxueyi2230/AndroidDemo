package com.example.lxy.androiddemo.mvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.dialog.Loading;
import com.example.lxy.androiddemo.mvp.presenter.EMvpPresenter;
import com.example.lxy.androiddemo.mvp.view.EMvpView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 *
 * MVP模式,支持右滑退出
 * @param <M>
 * @param <V>
 * @param <P>
 *
 * Created by lxy on 2016/7/25 22:45.
 */
public abstract class EMvpActivity<M,V extends EMvpView<M>,P extends EMvpPresenter<V,M>> extends MvpActivity<V,P> implements EMvpView<M> {

    private Loading mLoading;
    private SwipeBackActivityHelper mHelper;
    protected SwipeBackLayout mSwipeBackLayout;


    protected abstract int getLayoutId();
    protected abstract void onInitedView(Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipBackLayout();
        initContentView();
        ButterKnife.bind(this);
        onInitedView(savedInstanceState);
        onLoadData();
    }

    private void initSwipBackLayout() {
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        mSwipeBackLayout = mHelper.getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void initContentView() {
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        view.setBackgroundColor(getResources().getColor(R.color.e_white));
        setContentView(view);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackLayout;
    }

    public void setSwipeBackEnable(boolean enable) {
        mSwipeBackLayout.setEnableGesture(enable);
    }

    public void setSwipeBackEdge(int edge) {
        mSwipeBackLayout.setEdgeTrackingEnabled(edge);
    }

    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        mSwipeBackLayout.scrollToFinishActivity();
    }

    @Override
    public void showLoading() {
        if (mLoading == null){
            mLoading = new Loading(this);
        }
        if (!mLoading.isShowing()){
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
        shortToast(error);
    }

    @Override
    public void showToast(String toast) {
        shortToast(toast);
    }

    @Override
    public void showToast(int toastRes) {
        shortToast(toastRes);
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        mLoading = null;
        super.onDestroy();
    }

    public void gotoActivityForResult(Class<?> clazz, Bundle extras, int requestCode) {
        Intent mIntent = new Intent(this, clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivityForResult(mIntent, requestCode);
    }

    public void gotoActivityForResult(Class<?> clazz, int requestCode) {
        gotoActivityForResult(clazz,null,requestCode);
    }

    public void gotoActivity(Class<?> clazz, Bundle extras) {
        Intent mIntent = new Intent(this, clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivity(mIntent);
    }

    public void gotoActivity(Class<?> clazz) {
        gotoActivity(clazz, null);
    }

    public void gotoActivity(String action) {
        gotoActivity(action, null);
    }

    public void gotoActivity(Intent intent) {
        if (intent == null) {
            return;
        }
        startActivity(intent);
    }

    public void gotoActivity(String action, Bundle extras) {
        Intent mIntent = new Intent(action);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivity(mIntent);
    }


    public void defaultFinish() {
        finish();
    }

    public void longToast(int resid) {
        Toast.makeText(this, resid, Toast.LENGTH_LONG).show();
    }

    public void longToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void shortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void shortToast(int resid) {
        Toast.makeText(this, resid, Toast.LENGTH_SHORT).show();
    }

    public void hideKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public Activity getActivity(){
        return this;
    }

    public boolean isEmpty(List<?> list){
        return list ==  null || list.isEmpty();
    }
}
