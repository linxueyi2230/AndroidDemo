package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.library.adapter.ESingleRecycleAdapter;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.entity.Photo;

import java.util.List;

/**
 * Created by lxy on 2016/7/7.
 */
public class PhotoAdapter extends ESingleRecycleAdapter<Photo> {

    public PhotoAdapter(Context context, List<Photo> datas) {
        super(context, datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_photo;
    }

    @Override
    protected void onBindData(View convertView, Photo data, int position) {
        TextView tvName = getViewById(convertView,R.id.tv_name);
        ImageView ivPhoto = getViewById(convertView,R.id.iv_photo);
        tvName.setText(data.getTitie());
        Glide.with(mContext).load(data.getPhoto()).error(R.mipmap.ic_launcher).into(ivPhoto);
    }
}
