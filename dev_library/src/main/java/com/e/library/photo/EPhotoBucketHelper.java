package com.e.library.photo;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


/**
 * 图片工具类
 */
public class EPhotoBucketHelper {

    private Context mContext;
    private static EPhotoBucketHelper instance;
    private ArrayMap<String, String> mThumbnails = new ArrayMap<String, String>();
    private ArrayMap<String, EPhotoBucket> mBuckets = new ArrayMap<String, EPhotoBucket>();

    private EPhotoBucketHelper() {
    }

    private EPhotoBucketHelper(Context context) {
        this.mContext = context;
    }

    public static EPhotoBucketHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (EPhotoBucketHelper.class) {
                instance = new EPhotoBucketHelper(context);
            }
        }
        return instance;
    }

    /**
     * 是否已加载过了相册集合
     */
    boolean hasBuildPhotosBuckets = false;

    /**
     * 得到图片集
     *
     * @param refresh
     * @return
     */
    public List<EPhotoBucket> getImagesBucketList(boolean refresh) {
        if (refresh || (!refresh && !hasBuildPhotosBuckets)) {
            buildImagesBucketList();
        }
        List<EPhotoBucket> tmpList = new ArrayList<EPhotoBucket>();
        Iterator<Entry<String, EPhotoBucket>> itr = mBuckets.entrySet()
                .iterator();
        while (itr.hasNext()) {
            Entry<String, EPhotoBucket> entry = itr
                    .next();
            tmpList.add(entry.getValue());
        }
        return tmpList;
    }

    /**
     * 得到图片集
     */
    private void buildImagesBucketList() {
        Cursor cur = null;
        try {
            long startTime = System.currentTimeMillis();

            // 构造缩略图索引
            getThumbnail();

            // 构造相册索引
            String columns[] = new String[]{Media._ID, Media.BUCKET_ID,
                    Media.DATA, Media.BUCKET_DISPLAY_NAME};
            // 得到一个游标
            cur = mContext.getContentResolver().query(
                    Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
            if (cur.moveToFirst()) {
                // 获取指定列的索引
                int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
                int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
                int bucketDisplayNameIndex = cur
                        .getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
                int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);

                do {
                    String _id = cur.getString(photoIDIndex);
                    String path = cur.getString(photoPathIndex);
                    String bucketName = cur.getString(bucketDisplayNameIndex);
                    String bucketId = cur.getString(bucketIdIndex);

                    EPhotoBucket bucket = mBuckets.get(bucketId);
                    if (bucket == null) {
                        bucket = new EPhotoBucket();
                        mBuckets.put(bucketId, bucket);
                        bucket.photos = new ArrayList<>();
                        bucket.bucketName = bucketName;
                    }
                    bucket.count++;
                    bucket.photos.add(path);

                }
                while (cur.moveToNext());
            }

            hasBuildPhotosBuckets = true;
            long endTime = System.currentTimeMillis();
            Log.d(EPhotoBucketHelper.class.getName(), "use time: "
                    + (endTime - startTime) + " ms");
        } finally {
            cur.close();
        }
    }

    /**
     * 得到缩略图
     */
    private void getThumbnail() {
        Cursor cursor = null;
        try {
            String[] projection = {Thumbnails.IMAGE_ID, Thumbnails.DATA};
            cursor = mContext.getContentResolver().query(
                    Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null,
                    null);
            getThumbnailColumnData(cursor);
        } finally {
            cursor.close();
        }
    }

    /**
     * 从数据库中得到缩略图
     *
     * @param cur
     */
    private void getThumbnailColumnData(Cursor cur) {
        if (cur.moveToFirst()) {
            int image_id;
            String image_path;
            int image_idColumn = cur.getColumnIndex(Thumbnails.IMAGE_ID);
            int dataColumn = cur.getColumnIndex(Thumbnails.DATA);

            do {
                image_id = cur.getInt(image_idColumn);
                image_path = cur.getString(dataColumn);

                mThumbnails.put("" + image_id, image_path);
            }
            while (cur.moveToNext());
        }
    }

}
