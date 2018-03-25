package com.sxw.code.meizitu;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sxw.code.util.http.ZhiHuHttpClientUtil;

public class PicDownloader implements Runnable {

	private final Logger logger = LoggerFactory.getLogger(PicDownloader.class);

	private BlockingQueue<String> queue = null;

	private static String DIR_PATH = "C:/Users/SXW/Desktop/meizitu/blockqueue/";

	public PicDownloader(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String url = queue.take();
				logger.info("take:{}", url);
				int lastIndexOf = url.lastIndexOf(".");
				String suffix = url.substring(lastIndexOf);
				String saveFileName = System.currentTimeMillis() + "" + suffix;
				ZhiHuHttpClientUtil.downloadFile(url, DIR_PATH, saveFileName, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
