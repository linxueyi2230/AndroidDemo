package com.e.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 多个item adapter
 * @author lxy
 *
 * @param <T>
 */
public abstract class EMultiAdapter<T> extends EBaseAdapter<T> {

	public EMultiAdapter(Context context, List<T> datas) {
		super(context, datas);
	}

    @Override
    public abstract int getViewTypeCount();

    @Override
    public abstract int getItemViewType(int position);

	protected abstract int getLayoutId(int viewType);
	
	protected abstract void onBindData(View convertView,T data,int position,int type);

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
		int layoutId = getLayoutId(type);
        if (layoutId == invalid){
            return null;
        }

        T data = mDatas.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(layoutId, null);
		}
		
		onBindData(convertView, data, position,type);
		
		return convertView;
	}
	

}
