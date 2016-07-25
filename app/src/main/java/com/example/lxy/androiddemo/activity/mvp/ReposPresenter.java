package com.example.lxy.androiddemo.activity.mvp;

import com.example.lxy.androiddemo.entity.Repo;
import com.example.lxy.androiddemo.mvp.presenter.ELcePresenter;
import com.example.lxy.androiddemo.mvp.view.ELceView;

import java.util.List;

import rx.Observable;

/**
 * Created by lxy on 2016/7/25 23:38.
 */
public class ReposPresenter extends ELcePresenter<ELceView<List<Repo>>,List<Repo>> {

    public void getRepos(String user){
        Observable<List<Repo>> observable = service.getReposByRxJava(user);
        subscribe(observable);
    }
}
