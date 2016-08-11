package com.example.lxy.androiddemo.utils;

import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.R.mipmap;

public class AppUtils {
	
	private static AppUtils instance;

	public static AppUtils getInstance()
	{
		if(instance == null)
		{
			instance = new AppUtils();
		}
		return instance;
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

    /**
     * 根据图片名称获取R.java中对应的id
     * linxueyi2230@gmail.com
     * @param name
     * @return
     */
    public static int getImageIdByName(String name) {
        int value = 0;
        if (!TextUtils.isEmpty(name)) {
            if (name.indexOf(".") != -1) {
                name = name.substring(0, name.indexOf("."));
            }
            Class<mipmap> cls = R.mipmap.class;
            try {
                value = cls.getDeclaredField(name).getInt(null);
            } catch (Exception e) {
                e.printStackTrace();
                return value;
            }
        }
        return value;
    }
}
