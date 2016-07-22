package com.example.lxy.androiddemo.entity;

/**
 * Created by lxy on 2016/7/22.
 */
public class Message {

    public static final int TYPE_TO = 0;
    public static final int TYPE_FROM = 1;

    private int type;
    private String name;
    private String photo;
    private String msg;
    private long date;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isTo(){
        return type == TYPE_TO;
    }

    public boolean isFrom(){
        return type == TYPE_FROM;
    }
}
