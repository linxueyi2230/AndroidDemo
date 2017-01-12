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

    public Test(){

        Boy boy1 = new Boy();
        Boy boy2 = new Boy();

        Girl girl = new Girl();

        if (boy1.isGay() && boy2.isGay()){
            boy1.donot(girl).love(boy2);
            boy2.donot(girl).love(boy1);
        }
    }

    public Pair<Boy,Girl> pair(){
        Boy boy = new Boy();

        if (boy.isRich() && boy.hasHouse() && boy.hasCar()){

            Girl girl = new Girl();
            boy.set("Nothing");
            girl.love(boy);

            Pair<Boy,Girl> pair = Pair.create(boy,girl);

            return pair;
        }

        return null;
    }


}
