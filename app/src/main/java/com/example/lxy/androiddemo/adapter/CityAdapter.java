package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.e.library.adapter.ESingleRecycleAdapter;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.entity.City;

import java.util.List;

/**
 * Created by lxy on 2016/7/6.
 */
public class CityAdapter extends ESingleRecycleAdapter<City> {

    public CityAdapter(Context context, List<City> datas) {
        super(context, datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_city;
    }

    @Override
    protected void onBindData(View convertView, City data, int position) {
        TextView tvName = getViewById(convertView,R.id.tv_name);
        tvName.setText(data.getName());
    }
}
