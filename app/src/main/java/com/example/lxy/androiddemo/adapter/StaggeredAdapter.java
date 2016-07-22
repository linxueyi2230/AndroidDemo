package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e.library.adapter.ESingleRecyclerAdapter;
import com.example.lxy.androiddemo.R;

import java.util.List;
import java.util.Random;

/**
 * Created by lxy on 2016/7/7.
 */
public class StaggeredAdapter extends ESingleRecyclerAdapter<String> {

    private Random random;
    public StaggeredAdapter(Context context, List<String> datas) {
        super(context, datas);
        random = new Random();
        random.setSeed(20);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_simple;
    }

    @Override
    protected void onBindData(View convertView, String data, int position) {
        TextView tvName = getViewById(convertView,R.id.tv_name);
        tvName.setText(data);
        generatorHeight(tvName);
    }

    private void generatorHeight(TextView view){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        int height = 80+random.nextInt(50);
        params.height = height;
        view.setLayoutParams(params);
    }
}
