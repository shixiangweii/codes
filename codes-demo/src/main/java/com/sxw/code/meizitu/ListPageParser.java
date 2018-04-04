package com.sxw.code.meizitu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hyuki.concurrent.Concurrent;

public class ListPageParser implements Runnable {

	private static volatile boolean on = true;

	private final Logger logger = LoggerFactory.getLogger(ListPageParser.class);

	private BlockingQueue<String> queue = null;
	private AtomicInteger pageNums = null;
	private int totalPages;

	private static final String LISTURL = "http://www.meizitu.com/a/more_PAGENO.html";
	private static final String LISTURL_REPLACE_ITEM = "PAGENO";

	private static final ConcurrentMap<String, String> mark = new ConcurrentHashMap<>();

	public ListPageParser(BlockingQueue<String> queue, AtomicInteger pageNums, int total) {
		super();
		if (queue == null) {
			throw new RuntimeException();
		}
		this.queue = queue;
		this.pageNums = pageNums;
		this.totalPages = total;
	}

	@Concurrent
	public static void stop() {
		on = false;
	}

	@Override
	public void run() {
		while (on && !Thread.currentThread().isInterrupted()) {
			try {
				int pageNo = pageNums.incrementAndGet();
				if (pageNo > totalPages) {
					logger.info("目标要解析到：{}页，将要解析：{}， 超出", totalPages, pageNo);
					break;
				}
				String targetListUrl = LISTURL.replace(LISTURL_REPLACE_ITEM, pageNo + "");
				String html = Downloader.download(targetListUrl, "gbk");
				Document doc = Jsoup.parse(html);
				Elements detailLinks = doc.select("ul.wp-list a");
				// int count = 0;
				for (Element item : detailLinks) {
					String detailHref = item.attr("href");
					if (mark.containsKey(detailHref)) {
						continue;
					}
					// count++;
					String detailPageHtml = Downloader.download(detailHref, "gbk");
					Document detailDoc = Jsoup.parse(detailPageHtml);
					Elements imgs = detailDoc.select("img[src*=mm.chinasareview.com]");
					imgs.stream().forEach((ele) -> {
						String imgSrc = ele.attr("src");
						if (!mark.containsKey(imgSrc)) {
							try {
								queue.put(imgSrc);
								mark.put(imgSrc, imgSrc);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
