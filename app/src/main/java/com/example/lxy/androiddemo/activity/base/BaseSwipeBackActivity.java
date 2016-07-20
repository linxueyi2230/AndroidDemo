package com.example.lxy.androiddemo.activity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.e.library.activity.EBaseActivity;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.dialog.Loading;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * 右滑退出
 * Created by lxy on 2016/7/6.
 */
public abstract class BaseSwipeBackActivity extends EBaseActivity {

    private Loading mLoading;
    private SwipeBackActivityHelper mHelper;
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipBackLayout();
        initLoading();
        initContentView();
        ButterKnife.bind(this);
        onInitView(savedInstanceState);
        OnInitListener();
        onInitData();
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
    protected void OnInitListener() {
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

    private void initLoading() {
        mLoading = new Loading(this);
    }

    public void showLoading() {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        }
    }

    public void hideLoading() {
        if (isFinishing()) {
            return;
        }
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.hide();
        }
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        mLoading = null;
        super.onDestroy();
    }
}
