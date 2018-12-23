package com.sxw.code.classloader.chaofanwei2.client;

import com.sxw.code.classloader.chaofanwei2.server.Server;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 16:01
 *
 * @author shixiangweii
 */
public class Client {
    public static void main(String[] args) {

        Server server = new Server();
        server.init();

        int i = 0;
        while (true) {
            i++;
            String name = "name" + i;
            String result = server.doWork(name);
            System.out.println(result);
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
