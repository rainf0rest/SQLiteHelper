package com.example.rain.sqlitetest2;

/**
 * Created by rain on 2016/10/8.
 */
public class Role {
    private String name;
    private int level;
    private  int id;

    public  Role(){
    }

    public Role(String na, int lv, int ix) {
        name = na;
        level = lv;
        id = ix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
