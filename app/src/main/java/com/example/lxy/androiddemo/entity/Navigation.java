package com.example.lxy.androiddemo.entity;

import android.content.Intent;

public class Navigation {
	
	private int icon;
	private String name;
    private String lable;
    private Class<?> clazz;
	private Intent target;

    public Navigation() {
    }

    public Navigation(String name, Intent target) {
		super();
		this.name = name;
		this.target = target;
	}
	
	public Navigation(String name, Class<?> clazz) {
		super();
		this.name = name;
		this.clazz = clazz;
	}

	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Intent getTarget() {
		return target;
	}
	public void setTarget(Intent target) {
		this.target = target;
	}
}
