package com.e.library.utils;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 *
 * @author lxy
 * @date 2015/07/08 14:39 
 * @version V1.0
 *
 */
public class EViewUtils {

    /**
     * 通用的ViewHolder
     * @param convertView
     * @param id
     * @param <V>
     * @return
     */
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


    /**
     * @param contentView
     * @param viewId
     * @author lxy
     */
    public static  <V extends View> V getViewById(View contentView,int viewId){
        return (V)contentView.findViewById(viewId);
    }

    /**
     * 解决ListView 与 ScrollView冲突
     * @param listView
     */
    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
