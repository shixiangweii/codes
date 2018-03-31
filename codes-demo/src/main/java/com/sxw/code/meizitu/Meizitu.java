package com.sxw.code.meizitu;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author SXW
 *
 */
public class Meizitu {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(500);

		int startIdx = 37;
		int total = 3;
		int tagetIdx = startIdx + total - 1;

		AtomicInteger pages = new AtomicInteger(startIdx - 1);

		List<Runnable> allRoles = new LinkedList<>();
		for (int a = 0, pNum = 3, cNum = 20; a < pNum || a < cNum; a++) {
			if (a < pNum) {
				allRoles.add(new ListPageParser(queue, pages, tagetIdx));
			}
			if (a < cNum) {
				allRoles.add(new PicDownloader(queue));
			}
		}

		// 记录显示下载进度；
		// httputil性能优化，读取超时的问题

		int i = 1;
		int j = 1;
		List<Thread> list = new LinkedList<>();
		for (Runnable role : allRoles) {
			Thread t = null;
			if (role instanceof ListPageParser) {
				t = new Thread(role, "Parser-" + (i++));
			} else {
				t = new Thread(role, "PicDownloader-" + (j++));
			}
			t.start();
		}

		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ListPageParser.stop();
		PicDownloader.stop();
		for (Thread t : list) {
			if (t.getName().contains("PicDownloader")) {
				t.interrupt();
			}
		}

	}

}
