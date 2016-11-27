package com.example.lxy.androiddemo.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lxy on 2016/8/24 22:17.
 */
public abstract class EBasePopup extends PopupWindow {

    protected View view;
    private Unbinder unbinder;

    public EBasePopup(Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(getLayoutId(),null);
        this.setContentView(view);
        unbinder = ButterKnife.bind(this,view);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0));

        onPopupCreated(context);
    }

    abstract protected int getLayoutId();
    abstract protected void onPopupCreated(Context context);
    abstract public void showPopup(View parent);

    public void release(){
        unbinder.unbind();
    }
}
