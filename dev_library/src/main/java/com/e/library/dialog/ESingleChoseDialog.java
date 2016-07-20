package com.e.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


import com.e.library.R;
import com.e.library.adapter.ESingleAdapter;
import com.e.library.callback.EListener;

import java.util.Arrays;
import java.util.List;

/**
 * 单选对话框弹窗
 * @author lxy
 *
 * @param <T>
 */
public class ESingleChoseDialog<T> extends Dialog{

	public ESingleChoseDialog(Context context, int theme) {
		super(context,theme);
	}

	/**
	 * 选择相册dialog
	 * @param context
	 * @param listener
	 * @return
	 */
	public static ESingleChoseDialog<String> choosePhotosDialog(Context context, final EListener<String> listener){
		String[] items = context.getResources().getStringArray(R.array.choose_photos);
		return singleChoseListDialog(context,Arrays.asList(items),listener);
	}
	/**
	 * 单选对话框
	 * string类型
	 * @param context
	 * @param items
	 * @param listener
	 * @return
	 */
	public static ESingleChoseDialog<String> singleChoseStringArraysDialog(Context context, final String[] items, final EListener<String> listener){
		return singleChoseListDialog(context,Arrays.asList(items),listener);
	}
	/**
	 * 单选对话框
	 * string类型
	 * @param context
	 * @param list
	 * @param listener
	 * @return
	 */
	public static ESingleChoseDialog<String> singleChoseStringListDialog(Context context, final List<String> list, final EListener<String> listener){
		return singleChoseListDialog(context,list,listener);
	}
	
	/**
	 * 单选对话框，支持任何类型java bean
	 * 
	 * @param context
	 * @param list
	 * @param listener
	 * @return
	 */
	public static <T> ESingleChoseDialog<T> singleChoseListDialog(Context context, final List<T> list, final EListener<T> listener){
		final ESingleChoseDialog<T> dialog = new ESingleChoseDialog<T>(context,R.style.style_single_chose_dialog);
		dialog.getWindow().setWindowAnimations(R.style.SingleDialogWindowAnim);
        final View contentView = View.inflate(context, R.layout.e_dialog_single_chose, null);
        
        ListView listView = (ListView)contentView.findViewById(R.id.lv_single_chose);
        ESingleAdapter<T> adapter = new ESingleAdapter<T>(context,list) {

			@Override
			protected int getLayoutId() {
				return R.layout.e_item_single_chose;
			}

			@Override
			protected void onBindData(View convertView, T data, int position) {
				
				// 对于java bean，请重写toString()函数
				TextView tv_item = getViewById(convertView, R.id.tv_item);
				tv_item.setText(data.toString());
			}
		};
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (listener !=null) {
					T item = list.get(position);
					listener.onInvoked(view, item, position);
				}
				dialog.dismiss();
			}
		});
        contentView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                return false;
            }
        });
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(contentView);
		return dialog;
	}
}
