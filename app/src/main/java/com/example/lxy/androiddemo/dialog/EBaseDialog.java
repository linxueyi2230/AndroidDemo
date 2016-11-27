package com.example.lxy.androiddemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lxy on 2016/8/29 11:58.
 */
public abstract class EBaseDialog extends Dialog {

    private Unbinder unbinder;

    protected abstract @LayoutRes int getLayoutId();
    protected abstract void onDialogCreated(Context context);

    public EBaseDialog(Context context) {
        this(context, com.e.library.R.style.style_dialog);
    }

    public EBaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(getLayoutId(),null);
        setContentView(view);
        unbinder = ButterKnife.bind(this);
        onDialogCreated(context);
    }

    public void release(){
        dismiss();
        unbinder.unbind();
    }
}
