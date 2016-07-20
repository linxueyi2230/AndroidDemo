package com.example.lxy.androiddemo.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * 通用的ViewHolder
 * @author lxy
 * @date 2015/07/08 14:39 
 * @version V1.0
 *
 */
public class ViewHolder {
	public static <V extends View> V findView(View convertView,int id) {
		SparseArray<View> holder = (SparseArray<View>) convertView.getTag();
		if (holder == null) {
			holder = new SparseArray<View>();
			convertView.setTag(holder);
		}
		
		View childView = holder.get(id);
		if (childView == null) {
			childView = convertView.findViewById(id);
			holder.put(id, childView);
		}
		return (V)childView;
	}
}
