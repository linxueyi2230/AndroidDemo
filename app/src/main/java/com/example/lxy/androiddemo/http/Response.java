package com.example.lxy.androiddemo.http;


import com.e.library.http.EResponse;

import org.json.JSONObject;

/**
 * HTTP响应Json
 * @author lxy
 * @date 2015-09-23 15:11
 * @version v1.0
 * @since v3.0
 * 
 */
public class Response extends EResponse{

    public Response(String json) {
        super(json);
    }

    @Override
    protected String getDataKey() {
        return "data";
    }

    @Override
    protected String getCodeKey() {
        return "code";
    }

    @Override
    protected String getMessageKey() {
        return "message";
    }

    @Override
    protected void parseJson(JSONObject obj) {

    }

    @Override
    public boolean isSuccess() {
        return code == 200;
    }
	
}
