package com.sxw.bussiness;

import com.sxw.code.classloader.chaofanwei2.server.service.BusService;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 16:10
 *
 * @author shixiangweii
 */
public class BusServiceImpl01 implements BusService {
    @Override
    public String doIt(String s) {
        return "12BusServiceImpl0134567";
    }

    @Override
    public void close() {
        System.out.println("在具体的业务逻辑中执行释放资源操作");
    }
}
