package com.e.library.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.e.library.callback.EViewFinder;

import java.util.List;

/**
 * Created by lxy on 2016/5/9.
 */
public abstract class EBaseActivity extends FragmentActivity implements EViewFinder{

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(getLayoutId());
//
//        onInitView(savedInstanceState);
//
//        OnInitListener();
//
//        onInitData();
//    }

    protected abstract int getLayoutId();

    protected abstract void onInitView(Bundle savedInstanceState);

    protected abstract void OnInitListener();

    protected abstract void onInitData();

    @Override
    public <V extends View> V getViewById(int viewId) {
        return (V) findViewById(viewId);
    }

    @Override
    public <V extends View> V getViewById(View convertView, int viewId) {
        return (V) convertView.findViewById(viewId);
    }

    /**
     *
     * @param clazz
     * @param extras
     * @param requestCode
     * @author lxy
     */
    public void gotoActivityForResult(Class<?> clazz, Bundle extras, int requestCode) {
        Intent mIntent = new Intent(this, clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivityForResult(mIntent, requestCode);
    }
    /**
     *
     * @param clazz
     * @param requestCode
     * @author lxy
     */
    public void gotoActivityForResult(Class<?> clazz, int requestCode) {
        gotoActivityForResult(clazz,null,requestCode);
    }

    /**
     * @param clazz
     * @param extras
     * @author lxy
     */
    public void gotoActivity(Class<?> clazz, Bundle extras) {
        Intent mIntent = new Intent(this, clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivity(mIntent);
    }

    /**
     * @param clazz
     * @author lxy
     */
    public void gotoActivity(Class<?> clazz) {
        gotoActivity(clazz, null);
    }

    /**
     * @param action
     * @author lxy
     */
    public void gotoActivity(String action) {
        gotoActivity(action, null);
    }

    /**
     * @param intent
     * @author lxy
     */
    public void gotoActivity(Intent intent) {
        if (intent == null) {
            return;
        }
        startActivity(intent);
    }

    /**
     *
     * @param action
     * @param extras
     * @author lxy
     */
    public void gotoActivity(String action, Bundle extras) {
        Intent mIntent = new Intent(action);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivity(mIntent);
    }


    public void defaultFinish() {
        finish();
    }

    public void longToast(int resid) {
        Toast.makeText(this, resid, Toast.LENGTH_LONG).show();
    }

    public void longToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void shortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void shortToast(int resid) {
        Toast.makeText(this, resid, Toast.LENGTH_SHORT).show();
    }

    public void hideKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public Activity getActivity(){
        return this;
    }

    public boolean isEmpty(List<?> list){
        return list ==  null || list.isEmpty();
    }

}
