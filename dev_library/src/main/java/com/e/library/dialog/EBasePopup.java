package com.e.library.dialog;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import com.e.library.callback.EViewFinder;

/**
 * Created by lxy on 2016/5/15.
 */
public abstract class EBasePopup extends PopupWindow implements EViewFinder {

    private View view;

    protected abstract void initView(Context context);

    public abstract void showPopup(View parent);
    @Override
    public <V extends View> V getViewById(int viewId) {
        return getViewById(view,viewId);
    }

    @Override
    public <V extends View> V getViewById(View convertView, int viewId) {
        return (V) convertView.findViewById(viewId);
    }
}
