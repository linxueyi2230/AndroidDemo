package com.example.lxy.androiddemo.activity.recycleview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.e.library.widget.decorator.EDividerGridDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/7.
 */
public class StaggeredActivity extends BaseSwipeBackActivity {
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    private StaggeredAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_staggered;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new EDividerGridDecoration(this).setDivider(R.drawable.shape_divider));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onInitData() {
        List<String> list = new ArrayList<>();
        for (int i=0;i< 100;i++){
            list.add(String.format("item #%s",i));
        }

        adapter = new StaggeredAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
