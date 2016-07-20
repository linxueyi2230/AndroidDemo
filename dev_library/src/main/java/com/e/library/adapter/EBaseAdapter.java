package com.e.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.e.library.callback.EListener;
import com.e.library.utils.EViewUtils;

import java.util.List;

/**
 * 通用适配器
 * @author lxy
 * @param <T>
 */
public abstract class EBaseAdapter<T> extends BaseAdapter {

    protected int invalid = -1;
	protected Context mContext;
	protected List<T> mDatas;
    protected EListener<T> listener;

	public EBaseAdapter(Context context, List<T> datas) {
		super();
		this.mContext = context;
		this.mDatas = datas;
	}
	
	@Override
	abstract public View getView(int position, View convertView, ViewGroup parent);

    public void setListener(EListener<T> listener) {
        this.listener = listener;
    }

    public Context getContext(){
		return mContext;
	}
	
	public List<T> getDatas(){
		return mDatas;
	}
	
	public T getItemData(int position){
		return mDatas.get(position);
	}
	
	public void setDatas(List<T> datas){
		this.mDatas = datas;
	}
	
	public void add(T data){
		if (mDatas !=null && data !=null) {
			mDatas.add(data);
			notifyDataSetChanged();
		}
	}
	
	public void remove(T data){
		if (mDatas !=null && data !=null) {
			mDatas.remove(data);
			notifyDataSetChanged();
		}
	}
	
	public void remove(int position){
		if (mDatas !=null) {
			mDatas.remove(position);
			notifyDataSetChanged();
		}
	}

    public void refreshDatas(List<T> list){
        mDatas.clear();
        if (list !=null && !list.isEmpty()){
            mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addMoreDatas(List<T> list){
        if (list !=null && !list.isEmpty()){
            mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

	@Override
	public int getCount() {
		return mDatas == null?0: mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	protected <V extends View> V getViewById(View convertView, int id){
		return EViewUtils.findView(convertView, id);
	}

}


