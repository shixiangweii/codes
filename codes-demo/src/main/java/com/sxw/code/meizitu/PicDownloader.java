package com.sxw.code.meizitu;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sxw.code.util.http.ZhiHuHttpClientUtil;

import hyuki.concurrent.Concurrent;

public class PicDownloader implements Runnable {

	private static volatile boolean on = true;

	private final Logger logger = LoggerFactory.getLogger(PicDownloader.class);

	private BlockingQueue<String> queue = null;

	private static String DIR_PATH = "C:/Users/SXW/Desktop/meizitu/blockqueue2/";

	public PicDownloader(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Concurrent
	public static void stop() {
		on = false;
	}

	@Override
	public void run() {
		boolean keepWorking = true;
		while (keepWorking) {
			try {
				if (!on && queue.size() <= 0) {
					keepWorking = false;
				} else {
					String url = null;

					if (on) {
						url = queue.take();
					} else {
						synchronized (queue) {
							if (queue.size() > 0) {
								url = queue.take();
							}
						}
					}

					logger.info("take:{}", url);
					
					if (url != null) {
						int lastIndexOf = url.lastIndexOf(".");
						String suffix = url.substring(lastIndexOf);
						String saveFileName = System.currentTimeMillis() + "" + suffix;
						ZhiHuHttpClientUtil.downloadFile(url, DIR_PATH, saveFileName, true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
