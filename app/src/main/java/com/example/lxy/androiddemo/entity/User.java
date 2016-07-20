package com.example.lxy.androiddemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lxy on 2016/7/14.
 */
@Entity()
public class User {
    @Id
    private long id;
    private String name;
    @Transient
    private String temp;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1144922831)
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
