package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.e.library.adapter.ESingleRecycleAdapter;
import com.example.lxy.androiddemo.R;

import java.util.List;

/**
 * Created by lxy on 2016/7/7.
 */
public class StringAdapter extends ESingleRecycleAdapter<String> {
    public StringAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_simple;
    }

    @Override
    protected void onBindData(View convertView, String data, int position) {
        TextView tvName = getViewById(convertView,R.id.tv_name);
        tvName.setText(data);

    }
}
