package com.blue.doctor.bean;

/**
 * Created by Allen on 2015/7/10.
 */
public class Entity {

    private int id;
    private String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
