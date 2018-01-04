package com.epitech.foodielife.beans;

/**
 * Created by Tsy-jon on 04/05/2017.
 */

public class Info {
    private String title;
    private String info;

    public Info(String title, String info) {
        this.setTitle(title);
        this.setInfo(info);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
