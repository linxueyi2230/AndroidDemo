package com.example.lxy.androiddemo.http.volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.lxy.androiddemo.http.Response;
import com.example.lxy.androiddemo.utils.AppUtils;
import com.example.lxy.androiddemo.utils.LogUtils;

/**
 * 
 * @author lxy
 *
 */
public class BaseRequest extends Request<Response> {

    public static final String TAG = BaseRequest.class.getSimpleName();

    private com.android.volley.Response.Listener<Response> mListener;
    private Map<String,String> mParams;
    private VolleySingleton mVolleySingleton;

    public BaseRequest(int method, String url, Map<String,String> params, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener Errorlistener) {
        super(method, url, Errorlistener);
        mListener = listener;
        this.mParams =params;
        
        toUrl(url,params);
    }

    public BaseRequest(int method, String url, Map<String,String> params, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener Errorlistener, VolleySingleton volleySingleton) {
    	super(method, url, Errorlistener);
    	mListener = listener;
    	this.mParams =params;
    	this.mVolleySingleton = volleySingleton;
    	
    	toUrl(url,params);
    }

    @Override
    protected com.android.volley.Response parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString  = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            LogUtils.e(TAG,"JSON:"+jsonString);

            Response jsonResponse = new Response(jsonString);
            
			if (mVolleySingleton != null) {
				String mCookie; // 取出sessionId
				for (String s : response.headers.keySet()) {
					if (s.contains("Set-Cookie")) {
						mCookie = response.headers.get(s);
						mVolleySingleton.setSessionId(mCookie);
						break;
					}
				}
			}
            
            return com.android.volley.Response.success(jsonResponse,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return  null;
    }
    
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
    	if (mVolleySingleton != null) {
    		Map<String, String> map = new HashMap<String, String>();  
    		map.put("Cookie", mVolleySingleton.getSessionId());   //从新放入SessionID
    		return map;
    	}else{
    		return super.getHeaders();
    	}
    }

    @Override
    protected void deliverResponse(Response response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
    
    private String toUrl(String url,Map<String, String> params){
		return AppUtils.getInstance().toUrl(url, params);
		
	}

}
