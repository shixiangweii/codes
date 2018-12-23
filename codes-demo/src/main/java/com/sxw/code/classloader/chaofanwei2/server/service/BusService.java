package com.sxw.code.classloader.chaofanwei2.server.service;

/**
 * Description: 业务接口类
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 15:23
 *
 * @author shixiangweii
 */
public interface BusService {
    /**
     * 具体的业务逻辑
     *
     * @param name
     * @return
     */
    String doIt(String name);

    /**
     * 释放资源
     */
    void close();
}
