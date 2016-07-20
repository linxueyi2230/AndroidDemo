package com.e.library.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.e.library.page.EBasePager;

import java.util.List;

/**
 * @author lxy
 * @since 3.0
 * @version v2.0
 *
 */
public class EPagerAdapter<T extends Activity> extends PagerAdapter {
	
	private List<EBasePager<T>> pagers;
	
	public EPagerAdapter(List<EBasePager<T>> pagers) {
		// TODO Auto-generated constructor stub
		this.pagers = pagers;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pagers.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(pagers.get(position).getContentView());
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(pagers.get(position).getContentView());
		return pagers.get(position).getContentView();
	}
	
	public List<EBasePager<T>> getPagers(){
		return this.pagers;
	}

}
