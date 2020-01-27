package com.sxw.code.reflect.schema.entity;

public class BaseApp {
    private long recId;
    public String uuid;
    String icon;
    protected int id;
    protected String name;

    public BaseApp() {
    }

    public BaseApp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private long getRecId() {
        return recId;
    }

    protected long copyOfRecId() {
        return recId;
    }

    void resetRecId() {

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
}
