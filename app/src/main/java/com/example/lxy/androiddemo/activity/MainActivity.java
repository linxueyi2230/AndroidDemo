package com.example.lxy.androiddemo.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e.library.callback.EListener;
import com.e.library.widget.decorator.EDividerDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseAtivity;
import com.example.lxy.androiddemo.adapter.NavAdapter;
import com.example.lxy.androiddemo.entity.Navigation;
import com.example.lxy.androiddemo.utils.RecycleViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/6.
 */
public class MainActivity extends BaseAtivity {

    @BindView(R.id.rv_nav) RecyclerView rvNav;
    private NavAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        rvNav.setHasFixedSize(false);
        EDividerDecoration divider = new EDividerDecoration(this);
        divider.setLeftPadding(15);
        rvNav.addItemDecoration(divider);
        RecycleViewUtils.setVerticalLayoutManager(this,rvNav);

    }

    @Override
    protected void onInitData() {
        adapter = new NavAdapter(this,queryActivities());
        rvNav.setAdapter(adapter);
        adapter.setOnItemClickListener(new EListener<Navigation>() {
            @Override
            public void onInvoked(View view, Navigation data, int position) {
                gotoActivity(data.getTarget());
            }
        });
    }

    protected List<Navigation> queryActivities() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setPackage(getPackageName());
        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);

        List<Navigation> items = new ArrayList<>();
        final int count = infos.size();
        for (int i = 0; i < count; i++) {
            final ResolveInfo info = infos.get(i);


            String name = filter2SimpleName(info.activityInfo.name);
            Intent target = new Intent();
            target.setClassName(info.activityInfo.applicationInfo.packageName,info.activityInfo.name);
            Navigation item = new Navigation(name, target);

            int labelRes = info.activityInfo.labelRes;
            if (labelRes >0){
                String label = getString(info.activityInfo.labelRes);
                item.setLable(label);
            }
            items.add(item);
        }
        return items;
    }

    private String filter2SimpleName(String name){
        int index = name.lastIndexOf(".");
        if (index !=-1) {
            name = name.substring(index+1, name.length());
        }
        return name;
    }
}
