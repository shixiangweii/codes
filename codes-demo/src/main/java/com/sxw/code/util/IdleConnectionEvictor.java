package com.sxw.code.util;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-05-11
 * Time: 15:51
 *
 * @author shixiangweii
 */
public class IdleConnectionEvictor extends Thread {
    private Logger logger = LoggerFactory.getLogger(IdleConnectionEvictor.class);
    private final HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
