package com.example.lxy.androiddemo.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lxy on 2016/7/6.
 */
public class RecycleViewUtils {

    public static void setVerticalLayoutManager(Context context, RecyclerView rec){
        setLinearLayoutManager(context,rec,LinearLayoutManager.VERTICAL);
    }

    public static void setHorizontalLayoutManager(Context context, RecyclerView rec){
        setLinearLayoutManager(context,rec,LinearLayoutManager.HORIZONTAL);
    }

    public static void setLinearLayoutManager(Context context, RecyclerView rec,int orientation){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(orientation);
        manager.setSmoothScrollbarEnabled(true);
        rec.setLayoutManager(manager);
    }
}
