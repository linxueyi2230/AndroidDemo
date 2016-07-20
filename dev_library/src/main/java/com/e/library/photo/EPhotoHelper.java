package com.e.library.photo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by lxy on 2016/5/10.
 */
public abstract class EPhotoHelper {

    public static final int REQ_CODE_ALBUM = 2101;
    public static final int REQ_CODE_CAMERA = 2102;
    public static final int REQ_CODE_ALBUM_BUCKET = 2103;

    public static final int MAX_PHOTOS = 9;
    public static final String EXTRA_MAX_COUNT = "max_count";
    public static final String EXTRA_PHOTO_LIST = "photo_list";
    public static final String EXTRA_BUCKET_NAME = "bucket_name";
    public static final String EXTRA_CURRENT_POSITION = "position";
    public static final String EXTRA_SELECTED_PHOTOS = "selected_photos";

    protected static final String PHOTO_INVALID = "无效图片";
    protected static final String PHOTO_DISMISS = "图片不存在";
    protected static final String SDCARD_NOT_EXISTS = "SD卡不存在，无法拍照";

    protected Activity mActivity;
    protected boolean isCrop = false;
    protected String mActivityTitle;
    protected String mTempPhotoPath;

    //裁剪比例默认1：1
    protected int mAspectRatioX = 1;
    protected int mAspectRatioY = 1;

    protected int mOutputW = 400;
    protected int mOutputH = 400;

    protected onPhotoListener mListener;

    public EPhotoHelper(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 裁剪图片相关
     * @return
     */
    public abstract int getCropRequestCode();
    public abstract void startCropPhoto(Uri uri);
    public abstract void onCropedPhoto(Intent data);

    /**
     * 图片复选
     * @param clazz
     * @param photos
     */
    public void startMultiPhotos(Class<?> clazz, ArrayList<String> photos) {
        startMultiPhotos(clazz,photos,MAX_PHOTOS);
    }

    /**
     * 图片复选
     * @param clazz
     * @param photos
     * @param maxCount
     */
    public void startMultiPhotos(Class<?> clazz, ArrayList<String> photos, int maxCount) {
        if (photos == null){
            photos = new ArrayList<>();
        }
        Intent intent = new Intent(mActivity,clazz);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_MAX_COUNT, maxCount);
        bundle.putStringArrayList(EXTRA_SELECTED_PHOTOS,photos);
        intent.putExtras(bundle);
        mActivity.startActivityForResult(intent,REQ_CODE_ALBUM_BUCKET);
    }

    /**
     * 打开相机
     */
    public void startCamera() {
        if (!isExistSDCard()){
            showToast(SDCARD_NOT_EXISTS);
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, makePhotoUri());
        mActivity.startActivityForResult(intent, REQ_CODE_CAMERA);
    }

    /**
     * 打开相册
     */
    public void startAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        mActivity.startActivityForResult(intent, REQ_CODE_ALBUM);
    }

    public void onActivityForPhotos(int requestCode, int resultCode, Intent data){
        if (resultCode !=Activity.RESULT_OK || data == null){
            return;
        }
        if (requestCode == REQ_CODE_ALBUM_BUCKET){
            onSelectedMultiPhotos(data);
            return;
        }

        if (requestCode == REQ_CODE_ALBUM){
            if (isCrop){
                onCropPhotoFromAlbum(data);
            }else {
                getPhotoFromAlbum(data);
            }
            return;
        }

        if (requestCode == REQ_CODE_CAMERA){
            if (isCrop){
                onCropPhotoFromCamera();
            }else {
                getPhotoFromCamera();
            }
            return;
        }

        if (requestCode == getCropRequestCode()){
            onCropedPhoto(data);
            return;
        }
    }

    protected void onCropPhotoFromCamera() {
        if (!isPhotoExists()){
            return;
        }
        startCropPhoto(Uri.parse("file://" + mTempPhotoPath));
    }

    protected void onCropPhotoFromAlbum(Intent data) {
        Uri uri = data.getData();
        if (uri == null){
            showToast(PHOTO_INVALID);
            return;
        }
        startCropPhoto(uri);
    }

    protected boolean isPhotoExists() {
        try {
            if (TextUtils.isEmpty(mTempPhotoPath)) {
                showToast(PHOTO_DISMISS);
                return false;
            }
            File temp = new File(mTempPhotoPath);
            if (!temp.exists()) {
                showToast(PHOTO_DISMISS);
                return false;
            }
            return true;

        } catch (Exception e) {
            showToast(PHOTO_DISMISS);
            return false;
        }
    }

    protected void getPhotoFromCamera() {
        if (!isPhotoExists()){
            return;
        }
        onSelectedSinglePhoto(mTempPhotoPath);
    }

    private void getPhotoFromAlbum(Intent data){
        Uri uri = data.getData();
        if (uri ==null){
            showToast(PHOTO_DISMISS);
            return;
        }

        try {
            String[] dataColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = mActivity.getContentResolver().query(uri, dataColumn, null, null, null);
            if (cursor == null){
                showToast(PHOTO_DISMISS);
                return;
            }
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(dataColumn[0]);
            String photo = cursor.getString(columnIndex);
            cursor.close();
            onSelectedSinglePhoto(photo);

        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    protected void onSelectedSinglePhoto(String photo){
        if (mListener == null){
            return;
        }
        mListener.onSinglePhoto(photo);
    }

    protected void onSelectedMultiPhotos(Intent data){
        if (mListener == null){
            return;
        }
        ArrayList<String> photos = data.getStringArrayListExtra(EXTRA_SELECTED_PHOTOS);
        mListener.onMutltiPhoto(photos);
    }

    private Uri makePhotoUri() {
        File dirs = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        File photoPath = new File(dirs, String.format("%s.png",UUID.randomUUID()));
        mTempPhotoPath = photoPath.getAbsolutePath();
        return Uri.fromFile(photoPath);
    }

    protected boolean isExistSDCard(){
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    public void setActivityTitle(String mActivityTitle) {
        this.mActivityTitle = mActivityTitle;
    }

    public void setAspectRatioX(int aspectRatioX) {
        this.mAspectRatioX = aspectRatioX;
    }

    public void setAspectRatioY(int aspectRatioY) {
        this.mAspectRatioY = aspectRatioY;
    }

    public void setOutputW(int outputW) {
        this.mOutputW = outputW;
    }

    public void setOutputH(int outputH) {
        this.mOutputH = outputH;
    }

    public void setCropable(boolean isCrop){
        this.isCrop = isCrop;
    }

    public void setListener(onPhotoListener listener) {
        this.mListener = listener;
    }

    public interface onPhotoListener{
        void onSinglePhoto(String photos);
        void onMutltiPhoto(ArrayList<String> photos);
    }
}
