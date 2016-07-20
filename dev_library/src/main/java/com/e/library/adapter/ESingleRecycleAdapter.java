package com.e.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lxy on 2016/7/5.
 */
public abstract class ESingleRecycleAdapter<T> extends EBaseRecycleAdapter<T> {

    protected abstract int getLayoutId();
    protected abstract void onBindData(View convertView,T data,int position);

    public ESingleRecycleAdapter(Context context, List<T> datas) {
        super(context, datas);
    }

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleHolder(LayoutInflater.from(mContext).inflate(getLayoutId(),null));
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, int position) {
        T data = mDatas.get(position);
        onBindData(holder.itemView,data,position);
        onBindItemClickListener(holder.itemView,data,position);
    }
}
