package com.e.library.utils;

import android.content.Context;
import android.util.TypedValue;

public class EDensityUtils
{
	/**  
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)  
     */    
    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;    
        return (int) (dpValue * scale + 0.5f);    
    }    
    
    /**  
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp  
     */    
    public static int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;    
        return (int) (pxValue / scale + 0.5f);    
    }    
    
    /**
	 * dp转px
	 * 
	 * @param context
	 * @return
	 */
	public static int dp2px(Context context, int dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}
}
