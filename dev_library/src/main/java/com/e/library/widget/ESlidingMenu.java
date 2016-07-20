package com.e.library.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

public class ESlidingMenu extends HorizontalScrollView {

	private Handler handler;

	// 在HorizontalScrollView有个LinearLayout
	private LinearLayout linearLayout;
	// 菜单，内容页
	private ViewGroup mMenu;
	private ViewGroup mContent;
	// 菜单宽度
	private int mMenuWidth;

	// 屏幕宽度
	private int mScreenWidth;
	// 菜单与屏幕右侧的距离(dp)
	private int mMenuRightPadding = 50;

	// 避免多次调用onMeasure的标志
	private boolean once = false;

	/**
	 * 自定义View需要实现带有Context、AttributeSet这2个参数的构造方法,否则自定义参数会出错
	 * 当使用了自定义属性时，会调用此构造方法
	 *
	 * @param context
	 * @param attrs
	 */
	public ESlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取屏幕宽度
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;// 屏幕宽度

        // 将dp转换px
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMenuRightPadding, context.getResources().getDisplayMetrics());

        handler = new Handler();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (!once) {// 使其只调用一次
			// this指的是HorizontalScrollView，获取各个元素
            // 第一个子元素
			linearLayout = (LinearLayout) this.getChildAt(0);
            // HorizontalScrollView下LinearLayout的第一个子元素
			mMenu = (ViewGroup) linearLayout.getChildAt(0);
            // HorizontalScrollView下LinearLayout的第二个子元素
			mContent = (ViewGroup) linearLayout.getChildAt(1);

            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mMenu.getLayoutParams().width = mMenuWidth;
            mContent.getLayoutParams().width = mScreenWidth;
		}
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
            //菜单隐藏
			super.scrollTo(mMenuWidth, 0);
            once = true;
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX = this.getScrollX();
			if (scrollX >= mMenuWidth / 2) {
				closeMenu();
			} else {
				openMenu();
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}
	
	private boolean isInterceptTouch =  true;
	
	@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(isShowMenu()){
			return super.onInterceptTouchEvent(ev);
		}
		
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if(ev.getRawX() > mScreenWidth / 10){
				isInterceptTouch = false;
			}else{
				isInterceptTouch = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			isInterceptTouch = true;
			break;
		}

        //不拦截事件， 传递给子类
		if(!isInterceptTouch){
			return false;
		}
			
		return super.onInterceptTouchEvent(ev);
    }

    public void toggle(){
        if (isShowMenu()){
            closeMenu();
        }else {
            openMenu();
        }
    }
	
	public void closeMenu(){
		handler.post(new Runnable() {
			@Override
			public void run() {	
				ESlidingMenu.this.smoothScrollTo(mMenuWidth, 0);
			}
		});
	}
	
	public void openMenu(){
		handler.post(new Runnable() {
			@Override
			public void run() {
                ESlidingMenu.this.smoothScrollTo(0, 0);
			}
		});
	}

	public boolean isShowMenu(){
		return getScrollX() == 0;
	}
	
	@Override
	public void scrollTo(int x, int y) {
		
	}
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);

        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);
	}
}