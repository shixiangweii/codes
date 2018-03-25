package com.sxw.code.meizitu;

import com.sxw.code.util.http.ZhiHuHttpClientUtil;

public class Downloader {
	
	public static String download(String url, String encoding) throws Exception {
		byte[] pageBytes = ZhiHuHttpClientUtil.getWebPageBytes(url);
		String html = new String(pageBytes, encoding);
		return html;
	}
}
