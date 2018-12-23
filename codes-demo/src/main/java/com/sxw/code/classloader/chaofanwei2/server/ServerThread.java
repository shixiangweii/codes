package com.sxw.code.classloader.chaofanwei2.server;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 15:39
 *
 * @author shixiangweii
 */
public class ServerThread extends Thread {
    private ServerRunnable serverRunnable;

    public ServerThread(ServerRunnable serverRunnable) {
        this.serverRunnable = serverRunnable;
    }

    @Override
    public void run() {
        super.run();
    }
}
