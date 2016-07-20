package com.example.lxy.androiddemo.view;

/**
 * Created by lxy on 2016/6/27.
 */
public interface LoginView extends BaseView {

    void loginSuccess(String response);
    void loginFail(String error);
}
