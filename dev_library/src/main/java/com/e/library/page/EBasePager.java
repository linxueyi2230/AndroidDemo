package com.e.library.page;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.e.library.callback.EViewFinder;

/**
 * Tab页面
 * @author lxy
 * @date 2015-09-11
 * @version v1.0
 * @since v1.0
 *
 * @param <A> BaseActivity
 */
public abstract class EBasePager<A extends Activity> implements EViewFinder{

	protected final String TAG = EBasePager.class.getSimpleName();
	
	protected A mActivity;
	protected View mContentView;
	protected ViewPager mPager;

	public EBasePager(A a) {
		this.mActivity = a;
		onCreateView(a);
	}
	public EBasePager(A a, ViewPager pager) {
		this(a);
		this.mPager = pager;
	}

	protected abstract int getLayoutId();
    protected abstract void onInitView();
    protected abstract void onInitData();
	
	public abstract void release();
	
	private void onCreateView(A a){
		mContentView = LayoutInflater.from(a).inflate(getLayoutId(), null);
        onInitView();
        onInitData();
	}
	
	public void setViewPager(ViewPager pager){
		this.mPager = pager;
	}
	
	public ViewPager getViewPager(){
		return this.mPager;
	}
	
	public void setCurrentPager(int item){
		mPager.setCurrentItem(item);
	}
	
	public View getContentView(){
		return mContentView;
	}
	
	public A getActivity() {
		return mActivity;
	}

    @Override
    public <V extends View> V getViewById(int viewId){
        return getViewById(mContentView,viewId);
    }

    @Override
    public <V extends View> V getViewById(View contentView,int viewId){
        return (V)contentView.findViewById(viewId);
    }
}
