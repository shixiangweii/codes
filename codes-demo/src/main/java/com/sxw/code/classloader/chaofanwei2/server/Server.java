package com.sxw.code.classloader.chaofanwei2.server;

import com.sxw.code.classloader.chaofanwei2.server.service.BusService;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 15:33
 *
 * @author shixiangweii
 */
public class Server {
    /**
     * class 文件位置
     */
    String codePath = "D:\\java\\workspace\\busservice\\bin\\";
    /**
     * 具体的业务实现类
     */
    String busServiceClass = "cn.myroute.server.impl.BusServiceImpl";

    BusService busService;

    public String doWork(String name) {
        if (null != busService) {
            return busService.doIt(name);
        }
        return "default";
    }

    public void init() {
        new Thread() {
            long lastTime = 0;

            @Override
            public void run() {
                File f = new File(codePath + "version.txt");
                while (true) {
                    if (lastTime != f.lastModified()) {
                        lastTime = f.lastModified();
                        ClassLoader cl = this.getClass().getClassLoader();
                        System.out.println("Server.class的类类型：" + cl);
                        MyClassLoader myLoader = new MyClassLoader(new URL[0]);
                        try {
                            // 其实这些都是闭包
                            myLoader.addDir(codePath);
                            Class<BusService> clazz = (Class<BusService>) myLoader.loadClass(busServiceClass);
                            BusService busService2 = clazz.newInstance();
                            BusService temp = busService;
                            busService = busService2;
                            //释放资源，尤其是线程，若线程不关闭的话，则类不会卸载，且会一直运行
                            // 释放旧类时，若旧类里面启动新的线程的话，一定要关闭，否则既不会释放旧类，而且线程会一直运行
                            // 虽然加载了最新的类，但旧类并没有释放，会导致内存占用和一些别的不可预估的问题
                            temp.close();
                            ClassLoader c = temp.getClass().getClassLoader();
                            if (c instanceof URLClassLoader) {
                                //释放资源
                                ((URLClassLoader) c).close();
                            }
                            System.out.println("busService:" + busService + "  ,classloader:" + busService.getClass().getClassLoader());
                            System.out.println("end test " + new Date());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
