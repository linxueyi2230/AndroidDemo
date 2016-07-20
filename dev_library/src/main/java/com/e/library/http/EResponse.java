package com.e.library.http;

import android.text.TextUtils;

import com.e.library.utils.EGsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by lxy on 2016/5/9.
 */
public abstract class EResponse {
    protected String json;

    protected int code;
    protected String data;
    protected String message;

    public EResponse(String json){
        this.json = json;
        if (TextUtils.isEmpty(json)) {
            code = -1;
            return;
        }
        try {

            JSONObject obj = new JSONObject(json);

            data =obj.optString(getDataKey());
            message = obj.optString(getMessageKey());
            code = obj.optInt(getCodeKey(),-1);
            parseJson(obj);

        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
    }

    protected abstract String getDataKey();
    protected abstract String getCodeKey();
    protected abstract String getMessageKey();
    protected abstract void parseJson(JSONObject obj);
    public abstract boolean isSuccess();

    public <T> T toObject(Class<T> clazz){
        return EGsonUtils.toObject(data,clazz);
    }

    public <T>List<T> toList(Class<T> clazz){
        return EGsonUtils.toList(data,clazz);
    }

    public int getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getJson() {
        return json;
    }
}
