package com.example.lxy.androiddemo.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by lxy on 2016/7/25 22:46.
 */
public interface ELceView<M> extends MvpView{
    void showLoading();
    void hideLoading();

    void loadData();
    void setData(M data);
    void showError(String error);

    void showToast(String toast);
    void showToast(int toastRes);
}
