package com.dhjt.JarTest.bean.customBean.basic;

public class BasicFolderItem extends CustomBean {
	private int itemNo; // 卷内序号
	private String pageNo;// 页次（页号）
	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
