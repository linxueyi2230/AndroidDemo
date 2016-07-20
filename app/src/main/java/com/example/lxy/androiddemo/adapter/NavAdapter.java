package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.e.library.adapter.ESingleRecycleAdapter;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.entity.Navigation;

import java.util.List;

/**
 * Created by lxy on 2016/7/6.
 */
public class NavAdapter extends ESingleRecycleAdapter<Navigation> {

    public NavAdapter(Context context, List<Navigation> datas) {
        super(context, datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_simple;
    }

    @Override
    protected void onBindData(View convertView, Navigation data, int position) {
        TextView tvName = getViewById(convertView,R.id.tv_name);
        StringBuilder name = new StringBuilder(data.getName());
        name.append(" ").append(data.getLable());
        tvName.setText(name.toString());
    }
}
