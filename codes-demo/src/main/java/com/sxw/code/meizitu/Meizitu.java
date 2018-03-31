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
		
		int startIdx = 4;
		int total = 30;
		int tagetIdx = startIdx + total - 1;
		
		AtomicInteger pages = new AtomicInteger(startIdx-1);
		
		List<Runnable> allRoles = new LinkedList<>();
		for (int a = 0, pNum = 3, cNum = 20; a < pNum || a < cNum; a++) {
			if (a < pNum) {
				allRoles.add(new ListPageParser(queue, pages, tagetIdx));
			}
			if (a < cNum) {
				allRoles.add(new PicDownloader(queue));
			}
		}
		
		// TODO: 优雅停机；任务结束自动结束所有线程；定时记录显示下载进度；httputil性能优化

		int i = 1;
		int j = 1;
		for (Runnable role : allRoles) {
			if (role instanceof ListPageParser) {
				new Thread(role, "Parser-" + (i++)).start();
			} else {
				new Thread(role, "PicDownloader-" + (j++)).start();
			}
		}
		
		
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
