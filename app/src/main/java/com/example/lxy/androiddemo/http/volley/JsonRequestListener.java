package com.example.lxy.androiddemo.http.volley;

import com.example.lxy.androiddemo.http.Response;

public abstract class JsonRequestListener implements RequestListener {

	public void onPreRequest() {
	}

	public void onRequestError(int code, String msg) {
		onFinish(new Response(toJson(code,msg)));
	}

	public void onRequestFail(int code, String msg) {
		onFinish(new Response(toJson(code,msg)));
	}

	@Override
	public void onRequestSuccess(Response response) {
		onFinish(response);
	}

	@Override
	public void onEndRequst() {
	}

	abstract public void onFinish(Response response);
	
	/**
	 * 
	 * @param code
	 * @param msg
	 * @return {"code":200,"message":"hello world"}
	 */
	private String toJson(int code, String msg){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"code\":").append(code).append(",\"message\":\"").append(msg).append("\"}");
		return sb.toString();
	}

}
