package com.e.library.callback;

import android.view.View;

/**
 * 使用泛型解绑控件强转
 * Created by lxy on 2016/5/15.
 * @see com.e.library.fragment.EBaseFragment
 * @see com.e.library.activity.EBaseActivity
 * @see com.e.library.page.EBasePager
 */
public interface EViewFinder {

    <V extends View> V getViewById(int viewId);

    <V extends View> V getViewById(View convertView,int viewId);
}
