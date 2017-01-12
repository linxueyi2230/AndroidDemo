package com.example.lxy.androiddemo;

/**
 * Created by lxy on 2017/1/2.
 */

public class Boy {

    public boolean isRich(){
        return true;
    }

    public boolean hasHouse(){
        return true;
    }

    public boolean hasCar(){
        return true;
    }

    public void set(String s){

    }

    public boolean isGay(){
        return false;
    }

    public void love(Girl girl){
    }

    public void love(Boy boy){
    }

    public Boy donot(Girl girl){
        return this;
    }

    public Boy donot(Boy boy){
        return this;
    }
}
