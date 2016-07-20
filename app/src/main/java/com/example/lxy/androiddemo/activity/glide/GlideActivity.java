package com.example.lxy.androiddemo.activity.glide;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.*;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by lxy on 2016/7/19.
 */
public class GlideActivity extends BaseSwipeBackActivity {

    @BindView(R.id.iv_blur)
    ImageView ivBlur;

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    @BindView(R.id.iv_gif)
    ImageView ivGif;

    @BindView(R.id.iv_gif_thumbnail)
    ImageView ivGifThumbnail;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitData() {
        String img = "http://e.hiphotos.baidu.com/image/pic/item/738b4710b912c8fcb7338df6fb039245d78821cc.jpg";
        String bg = "http://s3.sinaimg.cn/middle/4fb6d727gaa9eaebf1342&690";
        String gif= "http://img6.ph.126.net/DOdQgXn_J6dAc8SWoaYKkw==/1126462856813353436.gif";
        Glide.with(this).load(bg).bitmapTransform(new BlurTransformation(GlideActivity.this)).into(ivBlur);//高斯模糊
        Glide.with(this).load(img).bitmapTransform(new CropCircleTransformation(this)).into(ivAvatar);//圆形裁剪
        Glide.with(this).load(gif).asBitmap().placeholder(R.mipmap.ic_launcher).into(ivGifThumbnail);//加载gif第一帧（预览图）
        Glide.with(this).load(gif).asGif().placeholder(R.mipmap.ic_launcher).into(ivGif);//gif动态图
    }
}
