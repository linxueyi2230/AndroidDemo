package com.example.lxy.androiddemo.presenter;

import android.text.TextUtils;

import com.example.lxy.androiddemo.model.AppModel;
import com.example.lxy.androiddemo.view.BaseView;

import java.util.List;

/**
 * Created by lxy on 2016/6/26.
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V view;
    protected AppModel model;

    public BasePresenter(V view) {
        this.view = view;
        model = AppModel.getInstance();
    }

    public boolean isSuccess(String response){
        return TextUtils.equals("200",response);
    }

    public boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
