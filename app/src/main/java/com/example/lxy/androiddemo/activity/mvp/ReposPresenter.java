package com.example.lxy.androiddemo.activity.mvp;

import com.example.lxy.androiddemo.entity.Repo;
import com.example.lxy.androiddemo.mvp.presenter.EMvpPresenter;
import com.example.lxy.androiddemo.mvp.view.EMvpView;

import java.util.List;

import rx.Observable;

/**
 * Created by lxy on 2016/7/25 23:38.
 */
public class ReposPresenter extends EMvpPresenter<EMvpView<List<Repo>>,List<Repo>> {

    public void getRepos(String user){
        Observable<List<Repo>> observable = service.getReposByRxJava(user);
        subscribe(observable);
    }
}
