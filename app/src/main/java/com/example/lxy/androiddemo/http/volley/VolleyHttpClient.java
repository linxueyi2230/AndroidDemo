package com.example.lxy.androiddemo.http.volley;

import java.util.Map;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.lxy.androiddemo.http.Response;

/**
 * VolleyHttpClient
 * @author lxy
 * @date 2015-09-21 22:07
 * @version v1.0
 * @since v4.0
 *
 */
public class VolleyHttpClient {
	public static final String TAG = BaseRequest.class.getSimpleName();
	private static final int TIMEOUT= 30 * 1000;  //超时时间
	
	private VolleySingleton volleySingleton;
	

	private Context mContext;

	public VolleyHttpClient(Context context) {
		mContext = context;
		volleySingleton = VolleySingleton.getInstance(context);
	}

	public void post(String url, Map<String, String> params, final RequestListener listener) {
		request(Request.Method.POST, url, params, listener);
	}
	
	public void post(String url, Map<String, String> params, final RequestListener listener, Object tag) {
		request(Request.Method.POST, url, params, listener, tag);
	}

	public void get(String url, int loadingMsg, final RequestListener listener) {
		request(Request.Method.GET, url, null, listener);
	}
	
	public void get(String url, int loadingMsg, final RequestListener listener, Object tag) {
		request(Request.Method.GET, url, null, listener, tag);
	}

	public void request(int method, String url, Map<String, String> params, final RequestListener listener) {
		if (listener != null)
			listener.onPreRequest();
		
		BaseRequest request = craeteRequest(method, url, params, listener);
		request.setTag(mContext);
		volleySingleton.addToRequestQueue(request);
	}
	
	public void request(int method, String url, Map<String, String> params, final RequestListener listener, Object tag) {
		if (listener != null)
			listener.onPreRequest();
		
		BaseRequest request = craeteRequest(method, url, params, listener);
		request.setTag(tag);
		volleySingleton.addToRequestQueue(request);
	}

	private BaseRequest craeteRequest(int method, String url, Map<String, String> params, final RequestListener listener) {
		BaseRequest request = new BaseRequest(method, url, params, new com.android.volley.Response.Listener<Response>() {
			public void onResponse(Response response) {
				try {
					if (listener != null) {
						listener.onRequestSuccess(response);
						listener.onEndRequst();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new com.android.volley.Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				try {
					String errMsg = null;
					int errCode = 500;
					if (error == null) {
						errMsg = "未知的服务错误";
					} else {
						errMsg = VolleyErrorHelper.getMessage(mContext, error);
						errCode = error.networkResponse == null ? errCode : error.networkResponse.statusCode;
					}

					if (listener != null) {
						listener.onRequestError(errCode, errMsg);
						listener.onEndRequst();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}, volleySingleton);
		
		request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, 1, 1.0f));
		return request;
	}
	
	public void cancelAllRequest(){
		volleySingleton.cancleRequest(mContext);
	}
	
	public void cancelRequest(Object tag){
		volleySingleton.cancleRequest(tag);
	}

}
