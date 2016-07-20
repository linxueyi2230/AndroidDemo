package com.example.lxy.androiddemo.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;


//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AppUtils {
	
	private static AppUtils instance;
//	private static DisplayImageOptions mOptions;
//
//	static{
//		mOptions = new DisplayImageOptions.Builder()
//		.cacheInMemory(true)
//		.cacheOnDisk(true)
//		.showImageForEmptyUri(R.mipmap.ic_launcher)
//		.showImageOnFail(R.mipmap.ic_launcher)
//		.showImageOnLoading(R.mipmap.ic_launcher)
//		.bitmapConfig(Config.RGB_565)
//		.build();
//	}

	public static AppUtils getInstance()
	{
		if(instance == null)
		{
			instance = new AppUtils();
		}
		return instance;
	}
	

//	public DisplayImageOptions getDisplayImageOptions(){
//		return mOptions;
//	}


	/**
	 * 解决ListView 与 ScrollView冲突
	 * @param listView
	 */
	public void setListViewHeight(ListView listView) {     
        ListAdapter listAdapter = listView.getAdapter();      
        if (listAdapter == null) {     
            return;     
        }     
     
        int totalHeight = 0;     
        for (int i = 0; i < listAdapter.getCount(); i++) {     
            View listItem = listAdapter.getView(i, null, listView);     
            listItem.measure(0, 0);     
            totalHeight += listItem.getMeasuredHeight();     
        }     
     
        ViewGroup.LayoutParams params = listView.getLayoutParams();     
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));     
        listView.setLayoutParams(params);     
    }     
	
//	public void dispalyImage(String url,ImageView imageView){
//		if (TextUtils.isEmpty(url)) {
//			return;
//		}
//		imageView.setTag(url);
//		ImageLoader.getInstance().displayImage(url, imageView,mOptions);
//	}
//
//	public void dispalyImage(String url, ImageView imageView, ImageLoadingListener listener){
//		if (TextUtils.isEmpty(url)) {
//			return;
//		}
//		imageView.setTag(url);
//		ImageLoader.getInstance().displayImage(url, imageView,mOptions,listener);
//	}
	
	public <T> boolean isEmptyData(List<T> data){
		return data == null || data.isEmpty();
	}
	
	public String toUrl(String url,Map<String, String> params)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		if (params !=null && !params.isEmpty()) {
			Set<String> keys = params.keySet();
			
			sb.append("?");
			boolean hasNext = false;
			for (String key : keys)
			{
				sb.append(hasNext?"&":"");
				String value = params.get(key);
				sb.append(String.format("%s=%s", key,value));
				hasNext = true;
			}
		}
		LogUtils.i("BaseRequest", sb.toString());
		return sb.toString();
		
	}
}
