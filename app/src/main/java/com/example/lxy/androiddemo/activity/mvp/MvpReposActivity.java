package com.example.lxy.androiddemo.activity.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.e.library.widget.ETitleBar;
import com.e.library.widget.decorator.EDividerDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.adapter.RepoAdapter;
import com.example.lxy.androiddemo.entity.Repo;
import com.example.lxy.androiddemo.mvp.ELceActivity;
import com.example.lxy.androiddemo.mvp.view.ELceView;
import com.example.lxy.androiddemo.utils.RecycleViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @see ELceView
 * @see ReposPresenter
 * @see ELceActivity
 *
 * Created by lxy on 2016/7/25 23:37.
 */
public class MvpReposActivity extends ELceActivity<List<Repo>,ELceView<List<Repo>>,ReposPresenter> {

    @BindView(R.id.titlebar)
    ETitleBar titleBar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    private RepoAdapter adapter;
    private List<Repo> repos = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycelerview;
    }

    @Override
    protected void onInitedView(Bundle savedInstanceState) {
        titleBar.setTitle(R.string.activity_mvp);
        recyclerView.addItemDecoration(new EDividerDecoration(this));
        RecycleViewUtils.setVerticalLayoutManager(this,recyclerView);
        adapter = new RepoAdapter(this,repos);
        recyclerView.setAdapter(adapter);

        loadData();
    }

    @Override
    public void loadData() {
        presenter.getRepos("JakeWharton");
    }


    @Override
    public void setData(List<Repo> data) {
        adapter.refreshDatas(data);
    }

    @NonNull
    @Override
    public ReposPresenter createPresenter() {
        return new ReposPresenter();
    }
}
