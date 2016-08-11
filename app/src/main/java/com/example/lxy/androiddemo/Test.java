package com.example.lxy.androiddemo;

import android.util.Pair;

import com.example.lxy.androiddemo.entity.City;
import com.example.lxy.androiddemo.entity.Note;

/**
 * Created by lxy on 2016/8/1 14:38.
 */
public class Test {

    public Pair<City,Note> test(){
        City city = new City();
        Note note = new Note();
        Pair<City,Note> pair = Pair.create(city,note);

        return pair;

    }
}
