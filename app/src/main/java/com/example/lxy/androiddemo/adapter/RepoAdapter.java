package com.example.lxy.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.e.library.adapter.ESingleRecyclerAdapter;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.entity.Repo;

import java.util.List;

/**
 * Created by lxy on 2016/7/10.
 */
public class RepoAdapter extends ESingleRecyclerAdapter<Repo> {

    public RepoAdapter(Context context, List<Repo> datas) {
        super(context, datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_repos;
    }

    @Override
    protected void onBindData(View convertView, Repo data, int position) {

        TextView tvName = getViewById(convertView,R.id.tv_name);
        ImageView ivPhoto = getViewById(convertView,R.id.iv_photo);
        tvName.setText(data.getName());
        Glide.with(mContext).load(data.getOwner()
                .getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(100,100)
                .error(R.mipmap.ic_launcher).into(ivPhoto);

    }
}
