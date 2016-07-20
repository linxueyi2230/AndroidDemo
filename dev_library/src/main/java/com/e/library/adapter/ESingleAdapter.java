package com.e.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lxy on 2016/4/16.
 */
public abstract class ESingleAdapter<T> extends EBaseAdapter<T> {
    public ESingleAdapter(Context context, List<T> datas) {
        super(context, datas);
    }

    protected abstract int getLayoutId();
    protected abstract void onBindData(View convertView,T data,int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (getLayoutId() == invalid){
            return null;
        }
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(getLayoutId(),null);
        }
        T data = mDatas.get(position);
        onBindData(convertView,data,position);

        return convertView;
    }
}
