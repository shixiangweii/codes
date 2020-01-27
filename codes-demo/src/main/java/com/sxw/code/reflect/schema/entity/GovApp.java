package com.sxw.code.reflect.schema.entity;

public class GovApp extends BaseApp {
    private String url;
    private int unify;

    public GovApp() {
    }

    private GovApp(int id, String name) {
        super(id, name);
    }

    public GovApp(int id, String name, String url, Integer unify) {
        super(id, name);
        this.url = url;
        this.unify = unify;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUnify() {
        return unify;
    }

    public void setUnify(Integer unify) {
        this.unify = unify;
    }

    private void show() {

    }

    @Override
    public String toString() {
        return "GovApp{" +
                "url='" + url + '\'' +
                ", unify=" + unify +
                '}';
    }
}
