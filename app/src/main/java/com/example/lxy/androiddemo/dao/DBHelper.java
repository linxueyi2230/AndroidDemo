package com.example.lxy.androiddemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lxy on 2016/6/30.
 */
public class DBHelper {

    private static final String DB_NAME = "app.db";
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private static volatile DBHelper instance;
    public static DBHelper getIns(Context context){
        if (instance == null){
            synchronized (DBHelper.class){
                if (instance ==null){
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    private DBHelper(Context context) {
        Context cxt = context.getApplicationContext();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(cxt,DB_NAME,null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDatabase(){
        return db;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public CityDao getCityDao(){
        return daoSession.getCityDao();
    }
}
