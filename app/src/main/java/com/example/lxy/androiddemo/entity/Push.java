package com.example.lxy.androiddemo.entity;

/**
 * Created by lxy on 2015/11/30.
 */
public class Push {

    /**
     * type : 5
     * repositoryId : 123
     */

    private int type;
    private int repositoryId;

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
