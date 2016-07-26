package com.example.lxy.androiddemo.activity.retrofit;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.e.library.widget.ETitleBar;
import com.e.library.widget.decorator.EDividerDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.RepoAdapter;
import com.example.lxy.androiddemo.entity.Repo;
import com.example.lxy.androiddemo.http.ApiService;
import com.example.lxy.androiddemo.http.retrofit.RetrofitHttpClient;
import com.example.lxy.androiddemo.utils.RecycleViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxy on 2016/7/10.
 */
public class JakeWhartonReposActivity extends BaseSwipeBackActivity {

    @BindView(R.id.titlebar) ETitleBar titleBar;
    @BindView(R.id.recycleview) RecyclerView recyclerView;
    private RepoAdapter adapter;
    private List<Repo> repos = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycelerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        titleBar.setTitle(R.string.activity_jake_wharton);
        recyclerView.addItemDecoration(new EDividerDecoration(this));
        RecycleViewUtils.setVerticalLayoutManager(this,recyclerView);
        adapter = new RepoAdapter(JakeWhartonReposActivity.this,repos);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onInitData() {

//        getReposByRetrofitCall();
        getReposByRxJava();
    }

    private void getReposByRetrofitCall(){
        showLoading();
        ApiService api = RetrofitHttpClient.getIns().getService();
        Call<List<Repo>> call = api.getRepos("JakeWharton");

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                List<Repo> repos = response.body();
                adapter.refreshDatas(repos);
                hideLoading();

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                shortToast("网络异常");
                hideLoading();

            }
        });
    }

    private void getReposByRxJava(){
        showLoading();
        ApiService api = RetrofitHttpClient.getIns().getService();
        Observable<List<Repo>> observable = api.getReposByRxJava("JakeWharton");
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repo>>() {
            @Override
            public void onCompleted() {
                hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                shortToast("网络异常");
                hideLoading();
            }

            @Override
            public void onNext(List<Repo> repos) {

                adapter.refreshDatas(repos);
            }
        });

    }
}
