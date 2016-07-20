package com.e.library.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.library.callback.EViewFinder;

/**
 * 
 * @author lxy
 *
 * @param <A>
 */
public abstract class EBaseFragment<A extends FragmentActivity> extends Fragment implements EViewFinder{

	protected static final String TAG = "AppFragment";
	protected A mActivity;
	protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutId(), null);
            onInitView(mContentView,savedInstanceState);
            
            onInitData();

        } else {

            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeAllViewsInLayout();
            }
        }
        return mContentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (A)activity;
    }

    /**
     * 设置布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化界面
     * @param contentView
     * @param savedInstanceState
     */
    protected abstract void onInitView(View contentView,Bundle savedInstanceState);
    
    /**
     * 设置数据
     */
    protected abstract void onInitData();

    @Override
    public <V extends View> V getViewById(int viewId) {
        return getViewById(mContentView,viewId);
    }

    @Override
    public <V extends View> V getViewById(View convertView, int viewId) {
        return (V) convertView.findViewById(viewId);
    }

    public A getAppActivity(){
    	return mActivity;
    }
}
