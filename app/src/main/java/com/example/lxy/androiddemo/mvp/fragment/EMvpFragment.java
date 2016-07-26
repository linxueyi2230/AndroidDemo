package com.example.lxy.androiddemo.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lxy.androiddemo.dialog.Loading;
import com.example.lxy.androiddemo.mvp.presenter.EMvpPresenter;
import com.example.lxy.androiddemo.mvp.view.EMvpView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import butterknife.ButterKnife;

/**
 * Created by lxy on 2016/7/26 23:03.
 */
public abstract class EMvpFragment<M,V extends EMvpView<M>,P extends EMvpPresenter<V,M>> extends MvpFragment<V,P> implements EMvpView<V>{

    private Loading mLoading;
    protected View mContentView;

    protected abstract int getLayoutId();
    protected abstract void onInitView(View contentView, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutId(), null);
            onInitView(mContentView,savedInstanceState);
            ButterKnife.bind(this,mContentView);
            onLoadData();
        } else {

            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeAllViewsInLayout();
            }
        }
        return mContentView;
    }

    @Override
    public void showLoading() {
        if (mLoading == null){
            mLoading = new Loading(getActivity());
        }
        if (!mLoading.isShowing()){
            mLoading.show();
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity().isFinishing()){
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
        Toast.makeText(getActivity(),toast,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int toastRes) {
        String toast = getString(toastRes);
        showToast(toast);
    }
}
