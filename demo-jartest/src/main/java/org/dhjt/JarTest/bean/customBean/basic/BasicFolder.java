package org.dhjt.JarTest.bean.customBean.basic;

public class BasicFolder extends CustomBean {
	private String catalogNo;// 目录号
	private String folderNo;// 案卷号,一般作为流水号
	//----------档案馆新增
	private String itemCount;//电子全文数

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getFolderNo() {
		return folderNo;
	}

	public void setFolderNo(String folderNo) {
		this.folderNo = folderNo;
	}

}
