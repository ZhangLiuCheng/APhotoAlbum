package com.aiyouwai.aphotoalbum.entity;

import com.aiyouwai.aphotoalbum.R;

import java.util.ArrayList;
import java.util.List;

public class Album {

    private int icon;

    private String label;

    public Album(int icon, String label) {
        this.icon = icon;
        this.label = label;
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

    public static List<Album> testData() {
        List<Album> data = new ArrayList<>();
        data.add(new Album(R.drawable.test1, "宝宝成长记"));
        data.add(new Album(R.drawable.test2, "大学同学聚会"));
        data.add(new Album(R.drawable.test3, "风景"));
        return data;
    }
}
