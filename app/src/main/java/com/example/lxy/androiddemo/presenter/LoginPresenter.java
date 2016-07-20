package com.example.lxy.androiddemo.presenter;

import com.example.lxy.androiddemo.model.AppModel;
import com.example.lxy.androiddemo.view.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxy on 2016/6/27.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String username, String password){
        String url = "http://e.com";
        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        model.doHttp(url, params, new AppModel.OnRequestListener() {
            @Override
            public void onFinish(String response) {
                view.hideLoading();
                if (isSuccess(response)){
                    onLoginSuccess(response);
                    view.loginSuccess(response);
                }else {
                    view.loginFail("error");
                }
            }
        });
    }

    private void onLoginSuccess(String response){
        //save
    }

}
