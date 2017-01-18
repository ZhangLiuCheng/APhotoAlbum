package com.aiyouwai.aphotoalbum.model.entity;

import com.aiyouwai.aphotoalbum.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    private int id;

    private int icon;

    private String label;

    private boolean isPrivacy;

    public Album(int id, int icon, String label) {
        this.id = id;
        this.icon = icon;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isPrivacy() {
        return isPrivacy;
    }

    public void setPrivacy(boolean privacy) {
        isPrivacy = privacy;
    }

    public static List<Album> testData() {
        List<Album> data = new ArrayList<>();
        data.add(new Album(0, R.drawable.test1, "宝宝成长记"));
        data.add(new Album(1, R.drawable.test2, "大学同学聚会"));
        data.add(new Album(2, R.drawable.test3, "我和小米"));
        data.add(new Album(3, R.drawable.test2, "老乡会"));
        data.get(2).setPrivacy(true);
        return data;
    }
}
