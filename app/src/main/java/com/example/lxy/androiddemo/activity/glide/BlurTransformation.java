package com.example.lxy.androiddemo.activity.glide;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.RenderScript;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.e.library.utils.EBitmapUtils;

/**
 * Created by lxy on 2016/7/19.
 */
public class BlurTransformation extends BitmapTransformation {

    private String imageId;
    private Context context;
    RenderScript rs;
    public BlurTransformation(Context context,String imageId) {
        super(context);
        this.context = context;
        this.imageId = imageId;
        rs = RenderScript.create(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap blurredBitmap = EBitmapUtils.blurBitmap(context,toTransform,25);

        return blurredBitmap;
    }

    @Override
    public String getId() {
        return imageId;
    }
}
