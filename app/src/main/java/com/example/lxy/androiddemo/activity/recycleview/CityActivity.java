package com.example.lxy.androiddemo.activity.recycleview;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e.library.callback.EListener;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.CityAdapter;
import com.e.library.widget.decorator.EDividerDecoration;
import com.example.lxy.androiddemo.entity.City;
import com.example.lxy.androiddemo.utils.RecycleViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * RecycleView测试
 * Created by lxy on 2016/7/6.
 */
public class CityActivity extends BaseSwipeBackActivity {

    @BindView(R.id.recycleview) RecyclerView recyclerView;
    private List<City> cities = new ArrayList<>();
    private CityAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
    }

    @Override
    protected void onInitData() {

        EDividerDecoration divider = new EDividerDecoration(this);
        divider.setLeftPadding(15).setDivider(R.drawable.shape_divider);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(divider);
        RecycleViewUtils.setVerticalLayoutManager(this,recyclerView);

        adapter = new CityAdapter(this,cities);
        recyclerView.setAdapter(adapter);

        cities.add(new City("广州"));
        cities.add(new City("湛江"));
        cities.add(new City("茂名"));
        cities.add(new City("惠州"));
        cities.add(new City("江门"));
        cities.add(new City("梅州"));
        cities.add(new City("东莞"));
        cities.add(new City("佛山"));
        cities.add(new City("深圳"));
        cities.add(new City("清远"));
        cities.add(new City("云浮"));
        cities.add(new City("肇庆"));
        cities.add(new City("潮州"));
        cities.add(new City("汕头"));
        cities.add(new City("揭阳"));
        cities.add(new City("阳江"));
        cities.add(new City("中山"));
        cities.add(new City("珠海"));

        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new EListener<City>() {
            @Override
            public void onInvoked(View view, City data, int position) {
                shortToast(data.getName());
            }
        });
    }
}
