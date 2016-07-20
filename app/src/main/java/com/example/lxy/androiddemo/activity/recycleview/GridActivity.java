package com.example.lxy.androiddemo.activity.recycleview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.e.library.widget.decorator.EDividerGridDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.StringAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/7.
 */
public class GridActivity extends BaseSwipeBackActivity {
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    private StringAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_grid;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        GridLayoutManager manager = new GridLayoutManager(this,4);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        EDividerGridDecoration divider = new EDividerGridDecoration(this);
        divider.setDivider(R.drawable.shape_divider);
        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected void onInitData() {
        List<String> list = new ArrayList<>();
        for (int i=0;i< 100;i++){
            list.add(String.format("item #%s",i));
        }

        adapter = new StringAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
