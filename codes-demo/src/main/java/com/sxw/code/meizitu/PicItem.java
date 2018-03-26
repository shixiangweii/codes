package com.sxw.code.meizitu;

public class PicItem {
	private String url;
	private int page; // 第几页
	private int itemNo; // 一页中第几项

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	@Override
	public String toString() {
		return "PicItem [url=" + url + ", page=" + page + ", itemNo=" + itemNo + "]";
	}

}
