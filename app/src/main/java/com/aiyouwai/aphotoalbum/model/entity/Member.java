package com.aiyouwai.aphotoalbum.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Member implements Cloneable {

    public String name;
    public String brief;
    public String image;

    public static List<Member> getTestData() {
        List<Member> data = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            data.add(new Member());
        }
        return data;
    }
}
