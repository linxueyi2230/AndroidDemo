package com.e.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lxy on 2016/7/5.
 */
public abstract class EMultiRecycleAdapter<T> extends EBaseRecycleAdapter<T> {
    public EMultiRecycleAdapter(Context context, List<T> datas) {
        super(context, datas);
    }

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position);

    protected abstract int getLayoutId(int viewType);

    protected abstract void onBindData(View convertView, T data, int position, int viewType);

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        if (layoutId == invalid){
            return null;
        }
        return new RecycleHolder(LayoutInflater.from(mContext).inflate(layoutId,null));
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, int position) {
        if (holder == null){
            return;
        }

        int viewType = getItemViewType(position);
        if (viewType == invalid){
            return;
        }
        T data = mDatas.get(position);
        onBindData(holder.itemView,data,position,viewType);
        onBindItemClickListener(holder.itemView,data,position);
    }

}
