package com.sxw.design.templatemethod;

/**
 * 模板方法
 *
 * @author shixiangweii
 * @date 2019/8/10 14:58
 */
public abstract class AbstractMemcachedManager {
    /**
     * 客户端初始化
     * 认证，连接
     */
    protected abstract void init();

    public boolean set(String key, String value) {
        return true;
    }

    public boolean set(String key, String value, int timeout) {
        return true;
    }

    public String get(String key) {
        return "";
    }

    public boolean delete(String key) {
        return true;
    }
}
