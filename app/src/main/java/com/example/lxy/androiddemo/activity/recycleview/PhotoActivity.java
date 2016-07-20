package com.example.lxy.androiddemo.activity.recycleview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.e.library.widget.ETitleBar;
import com.e.library.widget.decorator.ESpacesItemDecoration;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;
import com.example.lxy.androiddemo.adapter.PhotoAdapter;
import com.example.lxy.androiddemo.entity.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by lxy on 2016/7/7.
 */
public class PhotoActivity extends BaseSwipeBackActivity {


    @BindView(R.id.titlebar) ETitleBar titleBar;
    @BindView(R.id.recycleview) RecyclerView recyclerView;
    private PhotoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycelerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

        titleBar.setTitle(R.string.activity_photo);
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //解决滑动过程中item闪烁问题
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new ESpacesItemDecoration(16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                manager.invalidateSpanAssignments();
            }
        });
    }

    @Override
    protected void onInitData() {
        List<Photo> list = new ArrayList<>();
        List<String> photos = new ArrayList<>();
        photos.add("http://n.sinaimg.cn/sports/20160707/gvZY-fxtwihp9720981.jpg");
        photos.add("http://n.sinaimg.cn/sports/20160707/Y2RU-fxtwihv0029949.jpg");
        photos.add("http://n.sinaimg.cn/sports/20160706/BoMn-fxtsats1629398.jpg");
        photos.add("http://www.sinaimg.cn/dy/slidenews/2_img/2016_27/786_1848402_749389.jpg");
        photos.add("http://www.sinaimg.cn/dy/slidenews/2_img/2016_27/786_1848403_725524.jpg");
        photos.add("http://www.sinaimg.cn/dy/slidenews/2_img/2016_27/786_1848404_709971.jpg");
        photos.add("http://www.sinaimg.cn/dy/slidenews/2_img/2016_27/786_1848409_398662.jpg");

        Random random = new Random();
        for (int i=0;i<20;i++){
            int index = random.nextInt(6);
            if (index <0 || index >6){
                index = 0;
            }
            Photo photo = new Photo();
            photo.setTitie("NBA #"+i);
            photo.setPhoto(photos.get(index));
            list.add(photo);
        }

        adapter = new PhotoAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
