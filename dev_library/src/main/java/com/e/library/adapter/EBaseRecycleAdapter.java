package com.e.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e.library.callback.EListener;
import com.e.library.utils.EViewUtils;

import java.util.List;

/**
 * Created by lxy on 2016/7/5.
 */
public abstract class EBaseRecycleAdapter<T> extends RecyclerView.Adapter<EBaseRecycleAdapter.RecycleHolder> {

    protected int invalid = -1;
    protected List<T> mDatas;
    protected Context mContext;
    protected EListener<T> onItemClickListener;

    public EBaseRecycleAdapter(Context context,List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getItemCount() {
        return mDatas ==null?0: mDatas.size();
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
            notifyItemInserted(mDatas.size()-1);
        }
    }

    public void remove(T data){
        if (mDatas !=null && data !=null) {
            int index = mDatas.indexOf(data);
            mDatas.remove(data);
            notifyItemRemoved(index);
        }
    }

    public void remove(int position){
        if (mDatas !=null) {
            mDatas.remove(position);
            notifyItemRemoved(position);
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
            int start = mDatas.size()-1;
            int itemCount = list.size();
            mDatas.addAll(list);
            notifyItemRangeInserted(start,itemCount);
        }
    }

    public void setOnItemClickListener(EListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected <V extends View> V getViewById(View convertView, int id){
        return EViewUtils.findView(convertView, id);
    }

    protected void onBindItemClickListener(final View view, final T data, final int position){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener !=null){
                    onItemClickListener.onInvoked(view,data,position);
                }
            }
        });
    }

    public static class RecycleHolder extends RecyclerView.ViewHolder{
        public RecycleHolder(View itemView) {
            super(itemView);
        }
    }
}
