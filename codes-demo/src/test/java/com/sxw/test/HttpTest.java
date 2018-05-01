package com.sxw.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.sxw.code.util.http.ZhiHuHttpClientUtil;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-03-23
 * Time: 22:14
 */
public class HttpTest {
	
	@SuppressWarnings("unused")
	@Test
	public void testHttpUtil() throws Exception {
		String webPage = ZhiHuHttpClientUtil.getWebPage("http://www.meizitu.com/", "utf-8");
		System.out.println(webPage);
		String string = new String(webPage.getBytes(), "utf-8");
		Document doc = Jsoup.parse(webPage);
		Elements meta = doc.select("meta");
		Iterator<Element> iterator = meta.iterator();
		String charset = "";
		while(iterator.hasNext()){
			Element next = iterator.next();
			String attr = next.attr("content");
			if(StringUtils.isNoneBlank(attr)&&attr.contains("charset")){
				Pattern pattern = Pattern.compile("charset=(\\w+)");
				Matcher matcher = pattern.matcher(attr);
				if(matcher.find()){
					String group = matcher.group(1);
					charset = group;
				}
			}
		}
	}
	
	@Test
	public void testHttpBytes() throws Exception {
//        System.out.println("Default Charset=" + Charset.defaultCharset());  
//        System.out.println("file.encoding=" + System.getProperty("file.encoding"));  
//        System.out.println("Default Charset=" + Charset.defaultCharset());  
//        System.out.println("Default Charset in Use=" + getDefaultCharSet());
        
		byte[] sources = ZhiHuHttpClientUtil.getWebPageBytes("http://www.meizitu.com/a//5557.html");
		
		String string = new String(sources, "gbk");
		
		// 自己对编码的理解还是不对
		String string2 = new String(string.getBytes("gbk"), "utf-8");
		
		// http://mm.chinasareview.com/wp-content/uploads/2017a/08/01/limg.jpg
		// System.out.println(string);
		
		Document doc = Jsoup.parse(string2);
		Elements imgs = doc.select("img[src*=mm.chinasareview.com]");
		
		imgs.stream().forEach((ele) -> {
			String img_src = ele.attr("src");
			System.out.println(img_src);
			int lastIndexOf = img_src.lastIndexOf(".");
			String suffix = img_src.substring(lastIndexOf);
			ZhiHuHttpClientUtil.downloadFile(img_src,"./imgs/", System.currentTimeMillis()+""+suffix, true);
		});
		
	}
	
	
	@Test
	public void testGetImgsByList() throws Exception {
		final String listUrl = "http://www.meizitu.com/a/more_PAGENO.html";
		int pages = 3;
		Map<String, String> mark = new HashMap<>();
		final Map<String, Object> map = new HashMap<>();
		for (int i = 2; i <= pages; i++) {
			String targetListUrl = listUrl.replace("PAGENO", i + "");
			
			byte[] sources = ZhiHuHttpClientUtil.getWebPageBytes(targetListUrl);
			String listPageHtml = new String(sources, "gbk");
			
			Document doc = Jsoup.parse(listPageHtml);
			Elements detailLinks = doc.select("ul.wp-list a");
			int count = 0;
			for (Element item : detailLinks) {

				String detail_href = item.attr("href");

				if (mark.containsKey(detail_href)) {
					continue;
				}

				count++;

				map.put("count", count);
				map.put("page", i);

				byte[] sources_detail_page = ZhiHuHttpClientUtil.getWebPageBytes(detail_href);
				String detailPageHtml = new String(sources_detail_page, "gbk");
				Document docDetail = Jsoup.parse(detailPageHtml);
				Elements imgs = docDetail.select("img[src*=mm.chinasareview.com]");
				imgs.stream().forEach((ele) -> {
					String img_src = ele.attr("src");
					
					if (!mark.containsKey(img_src)) {
						System.out.println(img_src);
						int lastIndexOf = img_src.lastIndexOf(".");
						String suffix = img_src.substring(lastIndexOf);
						String saveFileName = "page_" + map.get("page") + "_item_" + map.get("count")
								+ System.currentTimeMillis() + "" + suffix;
						
						String pathname = "./imgs_" + "page_" + map.get("page") + "_item_" + map.get("count") + "/";
						File folder = new File(
								pathname);
						
						if(!folder.exists()){
							folder.mkdirs();
						}
						
						ZhiHuHttpClientUtil.downloadFile(img_src, pathname, saveFileName, true);
						mark.put(img_src, img_src);
					}
					
					
				});

				mark.put(detail_href, detail_href);
				System.out.println("END PAGE:" + i + ",item:" + count);
			}
		}
	}
	
	@Test
	public void testBlockQueue() {
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
		
		for (int i = 0; i < 2; i++) {
			new Thread() { // 开启两个线程不停的往缓冲区存数据
				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep((long) (Math.random() * 1000));
							System.out.println(Thread.currentThread().getName() + "准备放数据"
									+ (queue.size() == 3 ? "..队列已满，正在等待" : "..."));
							queue.put(1);
							System.out.println(
									Thread.currentThread().getName() + "存入数据，" + "队列目前有" + queue.size() + "个数据");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}.start();
		}
		
		 new Thread() { //开启一个线程不停的从缓冲区取数据

	            @Override
	            public void run() {
	                while(true) {
	                    try {
	                        Thread.sleep(1000);
	                        System.out.println(Thread.currentThread().getName() + "准备取数据"
	                                + (queue.size() == 0?"..队列已空，正在等待":"..."));
	                        queue.take();
	                        System.out.println(Thread.currentThread().getName() + "取出数据，" 
	                                + "队列目前有" + queue.size() + "个数据");
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    } 
	                }
	            }
	        }.start();
	        
	        
	        // junit测试跑完会结束所有线程，因此在test主线程中阻塞查看以上代码执行效果
	        try {
				Thread.sleep(10*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
    @SuppressWarnings("unused")
	private static String getDefaultCharSet() {  
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());  
        String enc = writer.getEncoding();  
        return enc;  
    }  
	
}
